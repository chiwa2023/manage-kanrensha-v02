import { describe, it, expect, vi, beforeEach } from 'vitest';
import { mount } from '@vue/test-utils';
import PasskeyManage from '../../../main/pages/user_edit/PasskeyManage.vue';
import { createPinia, setActivePinia } from 'pinia';
import { LeastUserDto } from 'seijishikin-jp-normalize_common-tool';

// モック
const mockUserDto = new LeastUserDto();
mockUserDto.userPersonId = 999;
mockUserDto.userPersonName = 'テスト太郎';
mockUserDto.listRoles = ['ADMIN'];

vi.mock('../../../main/common/user_info/AllUserInfo.vue', () => ({
    default: {
        name: 'AllUserInfo',
        template: '<div class="mock-all-user-info"></div>'
    }
}));

vi.mock('seijishikin-jp-normalize_common-tool', async () => {
    const actual = await vi.importActual<any>('seijishikin-jp-normalize_common-tool');
    return {
        ...actual,
        getLoginUser: vi.fn(() => mockUserDto),
        isWebAuthnSupported: vi.fn(() => true),
        getDefaultDeviceName: vi.fn(() => 'テスト端末'),
        fetchPasskeyList: vi.fn(async () => []),
        registerPasskey: vi.fn(),
        deletePasskey: vi.fn()
    };
});

vi.mock('../../../../router', () => ({
    default: {
        back: vi.fn(),
        push: vi.fn()
    }
}));

describe('PasskeyManage - dontShowPasskeyPromptAgain 編集機能', () => {
    const skipKey = 'passkey_prompt_skip_999';

    beforeEach(() => {
        const pinia = createPinia();
        setActivePinia(pinia);
        localStorage.clear();
        vi.clearAllMocks();
    });

    it('localStorageに非表示フラグがない場合、案内は「表示」状態として初期化される', async () => {
        const wrapper = mount(PasskeyManage, {
            global: {
                stubs: {
                    AllUserInfo: true,
                    MessageView: true
                }
            }
        });

        // 状態バッジが表示状態であること
        expect(wrapper.find('.status-badge').text()).toContain('表示（ログイン時に案内する）');
        const checkbox = wrapper.find<HTMLInputElement>('#dontShowPasskeyPromptAgain');
        expect(checkbox.element.checked).toBe(false);
    });

    it('localStorageに非表示フラグ("true")がある場合、案内は「非表示」状態として初期化され、リセットボタンが表示される', async () => {
        localStorage.setItem(skipKey, 'true');

        const wrapper = mount(PasskeyManage, {
            global: {
                stubs: {
                    AllUserInfo: true,
                    MessageView: true
                }
            }
        });

        expect(wrapper.find('.status-badge').text()).toContain('非表示（ログイン時に案内しない）');
        const checkbox = wrapper.find<HTMLInputElement>('#dontShowPasskeyPromptAgain');
        expect(checkbox.element.checked).toBe(true);

        // 「案内の表示に戻す」ボタンが存在すること
        const resetBtn = wrapper.find('.reset-setting-btn');
        expect(resetBtn.exists()).toBe(true);
        expect(resetBtn.text()).toContain('案内の表示に戻す');
    });

    it('チェックボックスをONにして保存ボタンを押すと、localStorageに"true"が保存され非表示状態になる', async () => {
        const wrapper = mount(PasskeyManage, {
            global: {
                stubs: {
                    AllUserInfo: true,
                    MessageView: true
                }
            }
        });

        const checkbox = wrapper.find<HTMLInputElement>('#dontShowPasskeyPromptAgain');
        await checkbox.setValue(true);

        const saveBtn = wrapper.find('.save-setting-btn');
        await saveBtn.trigger('click');

        expect(localStorage.getItem(skipKey)).toBe('true');
        expect(wrapper.find('.status-badge').text()).toContain('非表示（ログイン時に案内しない）');
    });

    it('非表示状態から「案内の表示に戻す」ボタンを押すと、チェックが外れlocalStorageから削除されて表示状態に戻る', async () => {
        localStorage.setItem(skipKey, 'true');

        const wrapper = mount(PasskeyManage, {
            global: {
                stubs: {
                    AllUserInfo: true,
                    MessageView: true
                }
            }
        });

        const resetBtn = wrapper.find('.reset-setting-btn');
        expect(resetBtn.exists()).toBe(true);

        await resetBtn.trigger('click');

        // localStorageから削除されること
        expect(localStorage.getItem(skipKey)).toBeNull();
        // チェックボックスが外れること
        const checkbox = wrapper.find<HTMLInputElement>('#dontShowPasskeyPromptAgain');
        expect(checkbox.element.checked).toBe(false);
        // バッジが表示に戻ること
        expect(wrapper.find('.status-badge').text()).toContain('表示（ログイン時に案内する）');
        // リセットボタンが非表示になること
        expect(wrapper.find('.reset-setting-btn').exists()).toBe(false);
    });

    it('非表示状態からチェックボックスを外して保存ボタンを押すと、表示状態に戻る', async () => {
        localStorage.setItem(skipKey, 'true');

        const wrapper = mount(PasskeyManage, {
            global: {
                stubs: {
                    AllUserInfo: true,
                    MessageView: true
                }
            }
        });

        const checkbox = wrapper.find<HTMLInputElement>('#dontShowPasskeyPromptAgain');
        await checkbox.setValue(false);

        const saveBtn = wrapper.find('.save-setting-btn');
        await saveBtn.trigger('click');

        expect(localStorage.getItem(skipKey)).toBeNull();
        expect(wrapper.find('.status-badge').text()).toContain('表示（ログイン時に案内する）');
    });

    it('userPersonIdが存在しない場合はエラーメッセージを設定して保存を中断する', async () => {
        mockUserDto.userPersonId = 0;

        const wrapper = mount(PasskeyManage, {
            global: {
                stubs: {
                    AllUserInfo: true,
                    MessageView: true
                }
            }
        });

        const saveBtn = wrapper.find('.save-setting-btn');
        await saveBtn.trigger('click');

        expect(localStorage.getItem(skipKey)).toBeNull();
        mockUserDto.userPersonId = 999;
    });
});
