/**
 * パスキー登録用ChallengeレスポンスDto
 */
export interface PasskeyRegistrationChallengeDto {
    challenge: string;
    rpId: string;
    rpName: string;
    userPersonCode: number;
    userName: string;
    userDisplayName: string;
    excludeCredentialIds: string[];
}
