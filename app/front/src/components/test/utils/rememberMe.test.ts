import { describe, it, expect } from 'vitest';
import { rememberMeStore } from '../../main/stores/remeberMe';
import App from '../../../App.vue'
import { createPinia } from 'pinia';
import { createApp } from 'vue';
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

// pinia設定
const app = createApp(App);
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate)
app.use(pinia);

describe('saveMail', () => {
    it('メールが保存される', () => {
        // セッション初期化処理
        localStorage.clear();

        const rememberMe = rememberMeStore();
        const mail = "12345@example.com"
        rememberMe.setMail(mail);

        // 暗号化されていること
        console.log(rememberMe.mail)

        expect(rememberMe.getMail()).toBe(mail);
    });
});

describe('savePassowrd', () => {
    it('パスワードが保存される', () => {
        // セッション初期化処理
        localStorage.clear();

        const rememberMe = rememberMeStore();
        const password = "qwerty12345"
        rememberMe.setPassword(password);

        // 暗号化されていること
        console.log(rememberMe.password)

        expect(rememberMe.getPassowrd()).toBe(password);
    });
});


describe('initialize', () => {
    it('初期化処理', () => {
        // セッション初期化処理
        localStorage.clear();

        const rememberMe = rememberMeStore();
        const password = "qwerty12345"
        const mail = "12345@example.com"
        rememberMe.setMail(mail);
        rememberMe.setPassword(password);
        // 初期化
        rememberMe.initialize();

        expect(rememberMe.getMail()).toBe("");
        expect(rememberMe.getPassowrd()).toBe("");
    });
});
