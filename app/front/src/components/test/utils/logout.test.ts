import { describe, it, expect } from 'vitest';
import { logout } from '../../main/utils/logout';
import LoginConstants from '../../main/dto/user/loginConstants';

describe('logout', () => {

    it('元のセッションが空でも落ちない', () => {
        // セッション初期化処理
        sessionStorage.clear();
        logout();// ログアウト処理
        // ユーザを取得してもnull
        expect(sessionStorage.getItem(LoginConstants.SESSION_KEY_USER)).toBeNull();
    });

    it('値が設定してある場合はクリアされる', () => {
        // セッションログアウト処理前状態
        sessionStorage.clear();
        sessionStorage.setItem(LoginConstants.SESSION_KEY_USER, "本当はJSONテキスト");
        sessionStorage.setItem(LoginConstants.SESSION_KEY_TOKEN, "本当はトークン");
        logout();// ログアウト処理
        expect(sessionStorage.getItem(LoginConstants.SESSION_KEY_USER)).toBeNull();
        expect(sessionStorage.getItem(LoginConstants.SESSION_KEY_TOKEN)).toBeNull();
    });
});
