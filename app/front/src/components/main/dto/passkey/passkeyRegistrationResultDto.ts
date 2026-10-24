/**
 * パスキー登録結果Dto
 */
export interface PasskeyRegistrationResultDto {
    success: boolean;
    passkeyId: number;
    credentialId: string;
    deviceName: string;
    message: string;
}
