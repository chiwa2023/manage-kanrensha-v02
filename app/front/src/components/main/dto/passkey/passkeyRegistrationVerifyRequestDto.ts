/**
 * パスキー登録検証リクエストDto
 */
export interface PasskeyRegistrationVerifyRequestDto {
    credentialId: string;
    rawId: string;
    clientDataJSON: string;
    attestationObject: string;
    deviceName: string;
}
