<script setup lang="ts">
import { onBeforeMount, ref, type Ref } from 'vue';
import router from '../../../../router';
import AllUserInfo from '../../common/user_info/AllUserInfo.vue';
import { deletePasskey, fetchPasskeyList, getDefaultDeviceName, getLoginUser, isWebAuthnSupported, MessageConstants, MessageView, registerPasskey, type LeastUserDtoInterface, type PasskeySummaryDto } from 'seijishikin-jp-normalize_common-tool';

// 定数
const MESS_PAGE_NAME = 'パスキー管理';
const INIT_CALLER = 'PasskeyManage';
const PROMPT_SKIP_KEY_PREFIX = 'passkey_prompt_skip_';

// ユーザ情報
const userDto: Ref<LeastUserDtoInterface> = ref(getLoginUser());

// メッセージ表示定数
const infoLevel: Ref<number> = ref(MessageConstants.LEVEL_NONE);
const messageType: Ref<number> = ref(MessageConstants.VIEW_NONE);
const caller: Ref<string> = ref(INIT_CALLER);
const message: Ref<string> = ref('');

// パスキー状態
const isSupported: Ref<boolean> = ref(true);
const defaultDeviceName: Ref<string> = ref('パスキー端末');
const newDeviceName: Ref<string> = ref('');
const passkeyList: Ref<PasskeySummaryDto[]> = ref([]);
const isLoading: Ref<boolean> = ref(false);
const isRegistering: Ref<boolean> = ref(false);

// パスキー案内表示フラグ (TopPageのdontShowPasskeyPromptAgainに対応)
const dontShowPasskeyPromptAgain: Ref<boolean> = ref(false);

// 削除確認モーダル
const isConfirmDeleteOpen: Ref<boolean> = ref(false);
const deleteTargetId: Ref<number | null> = ref(null);
const deleteTargetName: Ref<string> = ref('');

onBeforeMount(async () => {
    isSupported.value = isWebAuthnSupported();
    defaultDeviceName.value = getDefaultDeviceName();
    newDeviceName.value = defaultDeviceName.value;

    loadPromptSetting();

    if (isSupported.value) {
        await loadPasskeys();
    }
});

/**
 * ログイン時のパスキー案内表示設定を取得する
 */
function loadPromptSetting() {
    if (userDto.value && userDto.value.userPersonId) {
        const skipKey = PROMPT_SKIP_KEY_PREFIX + userDto.value.userPersonId;
        dontShowPasskeyPromptAgain.value = localStorage.getItem(skipKey) === 'true';
    }
}

/**
 * ログイン時のパスキー案内表示設定を保存する
 */
function savePromptSetting() {
    if (!userDto.value || !userDto.value.userPersonId) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = 'ユーザ情報が取得できないため、設定を保存できませんでした。';
        return;
    }
    const skipKey = PROMPT_SKIP_KEY_PREFIX + userDto.value.userPersonId;
    if (dontShowPasskeyPromptAgain.value) {
        localStorage.setItem(skipKey, 'true');
        infoLevel.value = MessageConstants.LEVEL_INFO;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = 'ログイン時のパスキー登録案内を「非表示」に設定しました。次回ログイン時に案内は表示されません。';
    } else {
        localStorage.removeItem(skipKey);
        infoLevel.value = MessageConstants.LEVEL_INFO;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = 'ログイン時のパスキー登録案内を「表示」に設定しました。パスキー未登録の場合、次回ログイン時に案内が表示されます。';
    }
}

/**
 * 案内の表示設定に戻す（リセット）
 */
function resetPromptSetting() {
    dontShowPasskeyPromptAgain.value = false;
    savePromptSetting();
}

/**
 * 登録済みパスキー一覧を取得する
 */
async function loadPasskeys() {
    isLoading.value = true;
    try {
        passkeyList.value = await fetchPasskeyList();
    } catch (e: any) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = e?.message || 'パスキー一覧の取得に失敗しました';
    } finally {
        isLoading.value = false;
    }
}

/**
 * 新しいパスキーを登録する
 */
async function onRegister() {
    if (!isSupported.value) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = 'お使いのブラウザはWebAuthn（パスキー）に対応していません。';
        return;
    }

    isRegistering.value = true;
    try {
        const result = await registerPasskey(newDeviceName.value);
        if (result.success) {
            infoLevel.value = MessageConstants.LEVEL_INFO;
            messageType.value = MessageConstants.VIEW_OK;
            message.value = `パスキー「${result.deviceName}」を登録しました。次回ログイン時から生体認証等での2要素認証が利用できます。`;
            newDeviceName.value = defaultDeviceName.value;
            await loadPasskeys();
        } else {
            infoLevel.value = MessageConstants.LEVEL_ERROR;
            messageType.value = MessageConstants.VIEW_OK;
            message.value = result.message || 'パスキーの登録に失敗しました';
        }
    } catch (e: any) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = e?.message || 'パスキー登録中にエラーが発生しました（端末の認証がキャンセルされたかタイムアウトしました）。';
    } finally {
        isRegistering.value = false;
    }
}

/**
 * 削除確認モーダルを開く
 */
function promptDelete(passkey: PasskeySummaryDto) {
    deleteTargetId.value = passkey.passkeyId;
    deleteTargetName.value = passkey.deviceName;
    isConfirmDeleteOpen.value = true;
}

/**
 * パスキー削除を実行する
 */
async function executeDelete() {
    if (deleteTargetId.value === null) return;
    const targetId = deleteTargetId.value;
    const targetName = deleteTargetName.value;
    isConfirmDeleteOpen.value = false;

    try {
        const ok = await deletePasskey(targetId);
        if (ok) {
            infoLevel.value = MessageConstants.LEVEL_INFO;
            messageType.value = MessageConstants.VIEW_OK;
            message.value = `パスキー「${targetName}」を削除しました。`;
            await loadPasskeys();
        } else {
            infoLevel.value = MessageConstants.LEVEL_ERROR;
            messageType.value = MessageConstants.VIEW_OK;
            message.value = 'パスキーの削除に失敗しました。';
        }
    } catch (e: any) {
        infoLevel.value = MessageConstants.LEVEL_ERROR;
        messageType.value = MessageConstants.VIEW_OK;
        message.value = e?.message || 'パスキー削除中にエラーが発生しました。';
    } finally {
        deleteTargetId.value = null;
        deleteTargetName.value = '';
    }
}

function cancelDelete() {
    isConfirmDeleteOpen.value = false;
    deleteTargetId.value = null;
    deleteTargetName.value = '';
}

/**
 * 日時フォーマット表示
 */
function formatDate(dateStr: string | null | undefined): string {
    if (!dateStr) return '未利用';
    try {
        const d = new Date(dateStr);
        if (isNaN(d.getTime())) return dateStr;
        return d.toLocaleString('ja-JP', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit',
            hour: '2-digit',
            minute: '2-digit'
        });
    } catch {
        return dateStr;
    }
}

function onBack() {
    router.back();
}

function recieveSubmit() {
    infoLevel.value = 0;
    messageType.value = 0;
}
</script>

<template>
    <!-- ユーザメニュー兼チェック -->
    <AllUserInfo :user-dto="userDto"></AllUserInfo>

    <div class="container">
        <h1>パスキー（2要素認証）管理</h1>

        <div class="intro-box">
            <p>
                <strong>パスキー（FIDO2 / WebAuthn）</strong>を設定すると、Touch ID、Face ID、Windows Hello
                やセキュリティキーを利用して、フィッシングやパスワード漏洩のリスクなく安全にログイン（2段階認証）できます。
            </p>
            <p class="sub-note">
                ※ 予備端末（スマートフォンとPCなど）を複数登録しておくと、万一の端末紛失時でも安心です。
            </p>
        </div>

        <!-- ブラウザ非対応アラート -->
        <div v-if="!isSupported" class="warning-box">
            お使いのブラウザ・環境はパスキー（WebAuthn）に対応していません。最新の Google Chrome、Microsoft Edge、Safari 等をご利用ください。
        </div>

        <!-- パスキー新規登録セクション -->
        <div class="card-section" v-if="isSupported">
            <h3 class="accent-h3">新しいパスキーを登録</h3>
            <div class="register-form">
                <div class="form-group">
                    <label for="passkeyDeviceName">端末の名称:</label>
                    <input
                        id="passkeyDeviceName"
                        type="text"
                        v-model="newDeviceName"
                        :placeholder="defaultDeviceName"
                        :disabled="isRegistering"
                        class="text-input"
                    />
                </div>
                <button
                    @click="onRegister"
                    :disabled="isRegistering"
                    class="footer-button register-btn"
                >
                    <span v-if="!isRegistering">この端末のパスキーを登録</span>
                    <span v-else>端末の認証を待機中...</span>
                </button>
            </div>
            <p v-if="isRegistering" class="registering-help">
                ※ ブラウザまたは端末に認証ポップアップ（生体認証やPIN入力）が表示されます。画面の指示に従って認証を行ってください。
            </p>
        </div>

        <!-- 登録済みパスキー一覧セクション -->
        <div class="card-section">
            <h3 class="accent-h3">登録済みパスキー一覧</h3>
            <div v-if="isLoading" class="loading-text">
                読み込み中...
            </div>
            <div v-else-if="passkeyList.length === 0" class="empty-text">
                現在登録されているパスキーはありません。上記のボタンから最初のパスキーを登録してください。
            </div>
            <table v-else class="passkey-table">
                <thead>
                    <tr>
                        <th>端末名称</th>
                        <th>登録日時</th>
                        <th>最終利用日時</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in passkeyList" :key="item.passkeyId">
                        <td class="device-cell">
                            <span class="key-icon">🔑</span>
                            <span class="device-name">{{ item.deviceName }}</span>
                        </td>
                        <td>{{ formatDate(item.createdAt) }}</td>
                        <td>{{ formatDate(item.lastUsedAt) }}</td>
                        <td>
                            <button
                                @click="promptDelete(item)"
                                class="delete-btn"
                                title="このパスキーを削除"
                            >
                                削除
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- ログイン時のパスキー登録案内設定セクション -->
        <div class="card-section">
            <h3 class="accent-h3">ログイン時のパスキー登録案内設定</h3>
            <p class="section-desc">
                パスキー未登録でログインした際に表示される「パスキー（2要素認証）登録のお願い」案内の表示・非表示を設定できます。
            </p>
            <div class="prompt-setting-area">
                <div class="status-indicator">
                    <span class="status-label">現在の設定:</span>
                    <span v-if="dontShowPasskeyPromptAgain" class="status-badge badge-hidden">
                        非表示（ログイン時に案内しない）
                    </span>
                    <span v-else class="status-badge badge-visible">
                        表示（ログイン時に案内する）
                    </span>
                </div>
                <div class="form-checkbox">
                    <input
                        type="checkbox"
                        id="dontShowPasskeyPromptAgain"
                        v-model="dontShowPasskeyPromptAgain"
                    />
                    <label for="dontShowPasskeyPromptAgain">
                        次回からこの案内を表示しない（案内を非表示にする）
                    </label>
                </div>
                <div class="setting-actions">
                    <button @click="savePromptSetting" class="footer-button save-setting-btn">
                        設定を保存
                    </button>
                    <button
                        v-if="dontShowPasskeyPromptAgain"
                        @click="resetPromptSetting"
                        class="footer-button reset-setting-btn"
                        type="button"
                    >
                        案内の表示に戻す
                    </button>
                </div>
            </div>
            <p class="setting-note">
                ※ ログイン画面で「次回からこの案内を表示しない」にチェックして閉じた場合でも、ここからいつでも表示に戻すことができます。
            </p>
        </div>

        <div class="footer">
            <button @click="onBack" class="footer-button">戻る</button>
        </div>
    </div>

    <!-- 削除確認モーダル -->
    <div v-if="isConfirmDeleteOpen" class="modal-overlay">
        <div class="modal-dialog">
            <h3>パスキーの削除確認</h3>
            <p>
                パスキー「<strong>{{ deleteTargetName }}</strong>」を削除してもよろしいですか？
            </p>
            <p class="modal-warning">
                ※ 削除すると、この端末でのパスキー2段階認証が利用できなくなります。
            </p>
            <div class="modal-actions">
                <button @click="cancelDelete" class="footer-button">キャンセル</button>
                <button @click="executeDelete" class="footer-button delete-confirm-btn">削除する</button>
            </div>
        </div>
    </div>

    <!-- メッセージ表示 -->
    <div class="overMessage" v-if="messageType !== MessageConstants.VIEW_NONE">
        <MessageView
            :info-level="infoLevel"
            :message-type="messageType"
            :title="MESS_PAGE_NAME"
            :message="message"
            :caller="caller"
            @send-submit="recieveSubmit"
        />
    </div>
</template>

<style scoped>
.intro-box {
    background-color: #f0f7ff;
    border-left: 4px solid var(--primary-color, #4169e1);
    padding: 1rem 1.2rem;
    margin-bottom: 1.5rem;
    border-radius: 4px;
    font-size: 0.95rem;
}
.sub-note {
    margin-top: 0.5rem;
    color: #666;
    font-size: 0.85rem;
}
.warning-box {
    background-color: #fff3cd;
    border-left: 4px solid #ffc107;
    color: #856404;
    padding: 1rem;
    margin-bottom: 1.5rem;
    border-radius: 4px;
}
.card-section {
    background: #ffffff;
    border: 1px solid #e0e0e0;
    border-radius: 6px;
    padding: 1.2rem 1.5rem;
    margin-bottom: 1.5rem;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
}
.register-form {
    display: flex;
    align-items: center;
    gap: 1rem;
    flex-wrap: wrap;
    margin-top: 0.8rem;
}
.form-group {
    display: flex;
    align-items: center;
    gap: 0.5rem;
}
.text-input {
    min-width: 260px;
    padding: 0.45rem 0.6rem;
    border: 1px solid #ccc;
    border-radius: 4px;
}
.register-btn {
    background-color: #2e7d32;
    color: #fff;
    cursor: pointer;
    font-weight: 500;
}
.register-btn:disabled {
    background-color: #a5d6a7;
    cursor: not-allowed;
}
.registering-help {
    margin-top: 0.6rem;
    font-size: 0.85rem;
    color: #d32f2f;
}
.passkey-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 0.8rem;
    font-size: 0.95rem;
}
.passkey-table th,
.passkey-table td {
    padding: 0.75rem 0.8rem;
    border-bottom: 1px solid #eee;
    text-align: left;
}
.passkey-table th {
    background-color: #f8f9fa;
    color: #555;
    font-weight: 600;
}
.device-cell {
    display: flex;
    align-items: center;
    gap: 0.5rem;
}
.key-icon {
    font-size: 1.1rem;
}
.device-name {
    font-weight: 500;
}
.delete-btn {
    background-color: #e53935;
    color: #fff;
    border: none;
    padding: 0.35rem 0.75rem;
    border-radius: 4px;
    cursor: pointer;
    font-size: 0.85rem;
}
.delete-btn:hover {
    background-color: #c62828;
}
.empty-text {
    padding: 1.2rem 0;
    color: #777;
    font-style: italic;
}
.loading-text {
    padding: 1rem 0;
    color: #666;
}
.modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background: rgba(0, 0, 0, 0.45);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
}
.modal-dialog {
    background: #fff;
    padding: 1.8rem 2rem;
    border-radius: 8px;
    max-width: 480px;
    width: 90%;
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
}
.modal-warning {
    color: #c62828;
    font-size: 0.88rem;
    margin-top: 0.5rem;
}
.modal-actions {
    display: flex;
    justify-content: flex-end;
    gap: 1rem;
    margin-top: 1.5rem;
}
.delete-confirm-btn {
    background-color: #e53935;
    color: #fff;
}
.section-desc {
    font-size: 0.95rem;
    color: #555;
    margin-top: 0.4rem;
    margin-bottom: 1rem;
    line-height: 1.5;
}
.prompt-setting-area {
    display: flex;
    flex-direction: column;
    gap: 0.8rem;
    margin-bottom: 0.8rem;
}
.status-indicator {
    display: flex;
    align-items: center;
    gap: 0.6rem;
    font-size: 0.95rem;
}
.status-label {
    font-weight: 600;
    color: #444;
}
.status-badge {
    padding: 0.25rem 0.6rem;
    border-radius: 4px;
    font-size: 0.85rem;
    font-weight: bold;
}
.badge-visible {
    background-color: #e8f5e9;
    color: #2e7d32;
    border: 1px solid #c8e6c9;
}
.badge-hidden {
    background-color: #fff3e0;
    color: #e65100;
    border: 1px solid #ffe0b2;
}
.form-checkbox {
    display: flex;
    align-items: center;
    gap: 0.5rem;
    font-size: 0.95rem;
}
.form-checkbox input[type="checkbox"] {
    width: 1.1rem;
    height: 1.1rem;
    cursor: pointer;
}
.form-checkbox label {
    cursor: pointer;
}
.setting-actions {
    display: flex;
    gap: 0.8rem;
    margin-top: 0.4rem;
    flex-wrap: wrap;
}
.save-setting-btn {
    background-color: #007bff;
    color: #fff;
    cursor: pointer;
}
.save-setting-btn:hover {
    background-color: #0056b3;
}
.reset-setting-btn {
    background-color: #28a745;
    color: #fff;
    cursor: pointer;
}
.reset-setting-btn:hover {
    background-color: #218838;
}
.setting-note {
    font-size: 0.85rem;
    color: #666;
    margin-top: 0.5rem;
}
</style>
