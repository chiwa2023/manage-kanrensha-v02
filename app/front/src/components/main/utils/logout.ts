import LoginConstants from "../dto/user/loginConstants";

function logout() {
    // ユーザ情報削除
    const userText: string | null = sessionStorage.getItem(LoginConstants.SESSION_KEY_USER);
    if (userText !== null) {
        sessionStorage.removeItem(LoginConstants.SESSION_KEY_USER);
    }

    // トークン削除
    const tokenText: string | null = sessionStorage.getItem(LoginConstants.SESSION_KEY_TOKEN);
    if (tokenText !== null) {
        sessionStorage.removeItem(LoginConstants.SESSION_KEY_TOKEN);
    }
}

export { logout }