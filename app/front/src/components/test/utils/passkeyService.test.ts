import { describe, it, expect, vi, beforeEach } from 'vitest';
import {
    isWebAuthnSupported,
    getDefaultDeviceName,
    registerPasskey,
    authenticatePasskey,
    fetchPasskeyList,
    deletePasskey
} from '../../main/utils/passkeyService';
import * as simpleWebAuthn from '@simplewebauthn/browser';
import * as authArea from '../../main/dto/login/getAuthorizedPromiseArea';

vi.mock('@simplewebauthn/browser', () => ({
    browserSupportsWebAuthn: vi.fn(),
    startRegistration: vi.fn(),
    startAuthentication: vi.fn()
}));

vi.mock('../../main/dto/login/getAuthorizedPromiseArea', () => ({
    default: vi.fn()
}));

describe('passkeyService', () => {
    beforeEach(() => {
        vi.clearAllMocks();
    });

    describe('isWebAuthnSupported', () => {
        it('ブラウザのWebAuthnサポート状態を委譲して返却する', () => {
            vi.mocked(simpleWebAuthn.browserSupportsWebAuthn).mockReturnValue(true);
            expect(isWebAuthnSupported()).toBe(true);

            vi.mocked(simpleWebAuthn.browserSupportsWebAuthn).mockReturnValue(false);
            expect(isWebAuthnSupported()).toBe(false);
        });
    });

    describe('getDefaultDeviceName', () => {
        it('デフォルト端末名が文字列として返却される', () => {
            const name = getDefaultDeviceName();
            expect(typeof name).toBe('string');
            expect(name.length).toBeGreaterThan(0);
        });
    });

    describe('registerPasskey', () => {
        it('正常フローで登録チャレンジ取得、ブラウザ登録、検証送信が行われる', async () => {
            vi.mocked(authArea.default).mockResolvedValue('mock-jwt-token');

            const mockChallenge = {
                challenge: 'test-challenge',
                rpId: 'localhost',
                rpName: '政治資金関連者管理システム',
                userPersonCode: 12345,
                userName: 'user@example.com',
                userDisplayName: 'テストユーザ',
                excludeCredentialIds: ['cred-old']
            };

            const mockRegResponse = {
                id: 'new-cred-id',
                rawId: 'new-cred-rawId',
                response: {
                    clientDataJSON: 'clientDataJSON-base64',
                    attestationObject: 'attestationObject-base64'
                },
                authenticatorAttachment: 'platform' as const,
                clientExtensionResults: {},
                type: 'public-key' as const
            };

            const mockVerifyResult = {
                success: true,
                passkeyId: 999,
                credentialId: 'new-cred-id',
                deviceName: 'テスト端末',
                message: '登録成功'
            };

            vi.mocked(simpleWebAuthn.startRegistration).mockResolvedValue(mockRegResponse);

            const fetchMock = vi.fn()
                .mockResolvedValueOnce({
                    ok: true,
                    status: 200,
                    json: async () => mockChallenge
                })
                .mockResolvedValueOnce({
                    ok: true,
                    status: 200,
                    json: async () => mockVerifyResult
                });

            vi.stubGlobal('fetch', fetchMock);

            const result = await registerPasskey('テスト端末');

            expect(result.success).toBe(true);
            expect(result.passkeyId).toBe(999);
            expect(simpleWebAuthn.startRegistration).toHaveBeenCalledOnce();
            expect(fetchMock).toHaveBeenCalledTimes(2);
        });
    });

    describe('authenticatePasskey', () => {
        it('正常フローでブラウザ認証、検証リクエスト送信が行われる', async () => {
            const mockAuthResponse = {
                id: 'test-cred-id',
                rawId: 'test-raw-id',
                response: {
                    clientDataJSON: 'clientDataJSON-str',
                    authenticatorData: 'authData-str',
                    signature: 'sig-str',
                    userHandle: 'userHandle-str'
                },
                authenticatorAttachment: 'platform' as const,
                clientExtensionResults: {},
                type: 'public-key' as const
            };

            const mockLoginResult = {
                jwtTokenDto: {
                    accessToken: 'final-access-token',
                    refreshToken: 'final-refresh-token',
                    expiresAt: '2026-12-31T23:59:59'
                },
                userDto: {
                    userPersonId: 1,
                    userPersonCode: 123,
                    userPersonName: 'テストユーザ',
                    listRoles: ['ROLE_ADMIN']
                },
                requiresPasskey: false
            };

            vi.mocked(simpleWebAuthn.startAuthentication).mockResolvedValue(mockAuthResponse);

            const fetchMock = vi.fn().mockResolvedValueOnce({
                ok: true,
                status: 200,
                json: async () => mockLoginResult
            });

            vi.stubGlobal('fetch', fetchMock);

            const result = await authenticatePasskey('pre-auth-token-123', 'challenge-xyz', ['test-cred-id']);

            expect(result.requiresPasskey).toBe(false);
            expect(result.jwtTokenDto?.accessToken).toBe('final-access-token');
            expect(simpleWebAuthn.startAuthentication).toHaveBeenCalledOnce();
            expect(fetchMock).toHaveBeenCalledOnce();
        });
    });

    describe('fetchPasskeyList & deletePasskey', () => {
        it('一覧取得および削除が正常に動作する', async () => {
            vi.mocked(authArea.default).mockResolvedValue('mock-jwt-token');

            const mockList = [
                {
                    passkeyId: 1,
                    credentialId: 'cred-1',
                    deviceName: 'PC',
                    createdAt: '2026-10-24T12:00:00',
                    lastUsedAt: null,
                    aaguid: 'test-aaguid'
                }
            ];

            const fetchMock = vi.fn()
                .mockResolvedValueOnce({
                    ok: true,
                    status: 200,
                    json: async () => mockList
                })
                .mockResolvedValueOnce({
                    ok: true,
                    status: 204
                });

            vi.stubGlobal('fetch', fetchMock);

            const list = await fetchPasskeyList();
            expect(list.length).toBe(1);
            expect(list[0]?.passkeyId).toBe(1);

            const deleted = await deletePasskey(1);
            expect(deleted).toBe(true);
        });
    });
});
