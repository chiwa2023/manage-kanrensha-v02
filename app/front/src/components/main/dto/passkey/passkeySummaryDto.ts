/**
 * 登録済みパスキー一覧表示用サマリーDto
 */
export interface PasskeySummaryDto {
    passkeyId: number;
    credentialId: string;
    deviceName: string;
    createdAt: string;
    lastUsedAt: string | null;
    aaguid: string;
}
