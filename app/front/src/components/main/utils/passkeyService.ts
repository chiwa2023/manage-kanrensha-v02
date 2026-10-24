import {
    browserSupportsWebAuthn,
    startRegistration,
    startAuthentication
} from '@simplewebauthn/browser';
import RoutePathConstants from '../../../routePathConstants';
import getAuthorizedPromiseArea from '../dto/login/getAuthorizedPromiseArea';
import type { LoginUserResultDtoInterface } from '../dto/login/loginUserResultDto';
import type { PasskeyRegistrationChallengeDto } from '../dto/passkey/passkeyRegistrationChallengeDto';
import type { PasskeyRegistrationVerifyRequestDto } from '../dto/passkey/passkeyRegistrationVerifyRequestDto';
import type { PasskeyRegistrationResultDto } from '../dto/passkey/passkeyRegistrationResultDto';
import type { PasskeyAuthenticationVerifyRequestDto } from '../dto/passkey/passkeyAuthenticationVerifyRequestDto';
import type { PasskeySummaryDto } from '../dto/passkey/passkeySummaryDto';

/**
 * WebAuthn (パスキー) をブラウザがサポートしているか確認する
 *
 * @returns サポート有無
 */
export function isWebAuthnSupported(): boolean {
    return browserSupportsWebAuthn();
}

/**
 * 実行環境のOS / 端末に応じたデフォルト端末名を取得する
 *
 * @returns デフォルト端末名
 */
export function getDefaultDeviceName(): string {
    const userAgent = typeof navigator !== 'undefined' ? navigator.userAgent : '';
    if (/iPhone|iPad|iPod/i.test(userAgent)) {
        return 'Apple端末 (Touch ID / Face ID)';
    }
    if (/Macintosh/i.test(userAgent)) {
        return 'Mac (Touch ID)';
    }
    if (/Windows/i.test(userAgent)) {
        return 'Windows PC (Windows Hello)';
    }
    if (/Android/i.test(userAgent)) {
        return 'Android端末';
    }
    return 'パスキー端末';
}

/**
 * パスキー登録を実行する (ログイン済みユーザ用)
 *
 * @param deviceName 登録する端末名称 (省略時は自動判別)
 * @returns 登録結果Dto
 */
export async function registerPasskey(deviceName?: string): Promise<PasskeyRegistrationResultDto> {
    const token = await getAuthorizedPromiseArea();
    const urlBack = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

    // 1. 登録Challenge取得
    const challengeUrl = urlBack + '/passkey/register/challenge';
    const challengeRes = await fetch(challengeUrl, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        }
    });

    if (!challengeRes.ok) {
        throw new Error('パスキー登録用チャレンジの取得に失敗しました (ステータス: ' + challengeRes.status + ')');
    }

    const challengeDto: PasskeyRegistrationChallengeDto = await challengeRes.json();

    // 2. ブラウザWebAuthn API呼び出し
    const creationOptions = {
        challenge: challengeDto.challenge,
        rp: {
            id: challengeDto.rpId || (typeof window !== 'undefined' ? window.location.hostname : 'localhost'),
            name: challengeDto.rpName || '政治資金関連者管理システム'
        },
        user: {
            id: btoa(String(challengeDto.userPersonCode)),
            name: challengeDto.userName,
            displayName: challengeDto.userDisplayName || challengeDto.userName
        },
        pubKeyCredParams: [
            { alg: -7, type: 'public-key' as const },
            { alg: -257, type: 'public-key' as const }
        ],
        timeout: 60000,
        attestation: 'none' as const,
        excludeCredentials: (challengeDto.excludeCredentialIds || []).map((id) => ({
            id: id,
            type: 'public-key' as const,
            transports: ['internal' as const, 'hybrid' as const, 'usb' as const, 'ble' as const, 'nfc' as const]
        })),
        authenticatorSelection: {
            residentKey: 'preferred' as const,
            requireResidentKey: false,
            userVerification: 'preferred' as const
        }
    };

    const regResp = await startRegistration({ optionsJSON: creationOptions });

    // 3. Attestation検証リクエスト
    const chosenName = deviceName && deviceName.trim() ? deviceName.trim() : getDefaultDeviceName();
    const verifyPayload: PasskeyRegistrationVerifyRequestDto = {
        credentialId: regResp.id,
        rawId: regResp.rawId,
        clientDataJSON: regResp.response.clientDataJSON,
        attestationObject: regResp.response.attestationObject,
        deviceName: chosenName
    };

    const verifyUrl = urlBack + '/passkey/register/verify';
    const verifyRes = await fetch(verifyUrl, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        },
        body: JSON.stringify(verifyPayload)
    });

    if (!verifyRes.ok) {
        const errorDto = await verifyRes.json().catch(() => ({}));
        throw new Error(errorDto.message || 'パスキー登録検証に失敗しました');
    }

    return await verifyRes.json();
}

/**
 * パスキーによる第2要素認証を実行する (ログイン2段階目)
 *
 * @param preAuthToken 1段階目認証で取得したPre-Auth Token
 * @param challenge 認証用Challenge
 * @param allowCredentials 許可クレデンシャルID一覧
 * @returns ログイン結果Dto (正式なJWTトークンを含む)
 */
export async function authenticatePasskey(
    preAuthToken: string,
    challenge: string,
    allowCredentials: string[]
): Promise<LoginUserResultDtoInterface> {
    const urlBack = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;

    // 1. ブラウザWebAuthn API呼び出し
    const requestOptions = {
        challenge: challenge,
        rpId: typeof window !== 'undefined' ? window.location.hostname : 'localhost',
        allowCredentials: (allowCredentials || []).map((id) => ({
            id: id,
            type: 'public-key' as const,
            transports: ['internal' as const, 'hybrid' as const, 'usb' as const, 'ble' as const, 'nfc' as const]
        })),
        timeout: 60000,
        userVerification: 'preferred' as const
    };

    const authResp = await startAuthentication({ optionsJSON: requestOptions });

    // 2. Assertion検証リクエスト
    const verifyPayload: PasskeyAuthenticationVerifyRequestDto = {
        preAuthToken: preAuthToken,
        credentialId: authResp.id,
        rawId: authResp.rawId,
        clientDataJSON: authResp.response.clientDataJSON,
        authenticatorData: authResp.response.authenticatorData,
        signature: authResp.response.signature,
        userHandle: authResp.response.userHandle || ''
    };

    const verifyUrl = urlBack + '/passkey/authenticate/verify';
    const verifyRes = await fetch(verifyUrl, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(verifyPayload)
    });

    if (!verifyRes.ok) {
        throw new Error('パスキー認証に失敗しました。認証情報が不正であるか、有効期限が切れています。');
    }

    return await verifyRes.json();
}

/**
 * 登録済みパスキー一覧を取得する
 *
 * @returns パスキーサマリーリスト
 */
export async function fetchPasskeyList(): Promise<PasskeySummaryDto[]> {
    const token = await getAuthorizedPromiseArea();
    const urlBack = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;
    const url = urlBack + '/passkey/list';

    const res = await fetch(url, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        }
    });

    if (!res.ok) {
        throw new Error('パスキー一覧の取得に失敗しました (ステータス: ' + res.status + ')');
    }

    return await res.json();
}

/**
 * 指定したパスキーを削除する
 *
 * @param passkeyId 内部パスキーID
 * @returns 成功フラグ
 */
export async function deletePasskey(passkeyId: number): Promise<boolean> {
    const token = await getAuthorizedPromiseArea();
    const urlBack = RoutePathConstants.DOMAIN + RoutePathConstants.BASE_PATH;
    const url = urlBack + '/passkey/' + passkeyId;

    const res = await fetch(url, {
        method: 'DELETE',
        headers: {
            'X-AUTH-TOKEN': 'Bearer ' + token
        }
    });

    return res.status === 200 || res.status === 204;
}
