import CryptoJS from 'crypto-js';

// .env ファイルから秘密鍵を読み込む
const SECRET_KEY = import.meta.env.VITE_CRYPTO_SECRET_KEY;

if (!SECRET_KEY) {
  // 秘密鍵が設定されていない場合はエラーを投げるか、開発モードでのみデフォルト値を使う
  throw new Error("暗号化のための秘密鍵が設定されていません。.envファイルを確認してください。");
}

/**
 * 文字列を暗号化します。
 * @param text 暗号化する文字列
 * @returns 暗号化された文字列
 */
export function encrypt(text: string): string {
  return CryptoJS.AES.encrypt(text, SECRET_KEY).toString();
}

/**
 * 暗号化された文字列を復号します。
 * @param ciphertext 暗号化された文字列
 * @returns 復号された文字列
 */
export function decrypt(ciphertext: string): string {
  const bytes = CryptoJS.AES.decrypt(ciphertext, SECRET_KEY);
  return bytes.toString(CryptoJS.enc.Utf8);
}
