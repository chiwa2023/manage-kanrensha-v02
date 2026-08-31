import { defineStore } from 'pinia';
import { ref, type Ref } from 'vue';
import { decrypt, encrypt } from '../utils/crypto';

export const rememberMeStore = defineStore('rememberMeStore', () => {
    const BLANK: string = "";
    // state（リアクティブな状態）
    const mail: Ref<string> = ref(BLANK);
    const password: Ref<string> = ref(BLANK);

    /**
     * 復号されたメールアドレスを取得する
     * @returns メールアドレス
     */
    const getMail = (): string => {
        return decrypt(mail.value);
    }

    /**
     * 復号されたパスワードを取得する
     * @returns パスワード 
     */
    const getPassowrd = (): string => {
        return decrypt(password.value);
    }

    /**
     * メールアドレスを暗号化して保存する
     */
    function setMail(data: string) {
        mail.value = encrypt(data);
    }

    /**
     * パスワードを暗号化して保存する
     */
    function setPassword(data: string) {
        password.value = encrypt(data);
    }

    /**
     * 保存内容を初期化する
     */
    const initialize = () => {
        mail.value = BLANK;
        password.value = BLANK;
    }

    function hasData(): boolean {
        return BLANK !== mail.value && BLANK !== password.value;
    }

    // 外部に公開する値や関数を返す
    return {
        // 変数そのものをRetuenしないと永続化されない
        // https://kojinjigyou.org/88192/
        mail,
        password,
        getMail,
        getPassowrd,
        setMail,
        setPassword,
        initialize,
        hasData,
    };

},
    {
        persist: true
    }
);