/**
 * パスキー認証検証リクエストDto
 */
export interface PasskeyAuthenticationVerifyRequestDto {
    preAuthToken: string;
    credentialId: string;
    rawId: string;
    clientDataJSON: string;
    authenticatorData: string;
    signature: string;
    userHandle: string;
}
