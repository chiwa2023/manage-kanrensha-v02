import { describe, it, expect, beforeEach, vi } from 'vitest';
import { setActivePinia, createPinia } from 'pinia';
import getAuthorizedPromiseArea from '../../../main/dto/login/getAuthorizedPromiseArea';
import { useUserInfoStore } from '../../../main/stores/storeUserInfo';
import { AccessTokenNotFoundError, TokenRefreshError } from '../../../main/dto/login/errors';
import type { JwtTokenDtoInterface } from '../../../main/dto/login/jwtTokenDto';

// global.fetchのモック
global.fetch = vi.fn();

describe('getAuthorizedPromiseArea', () => {
  beforeEach(() => {
    // Piniaのセットアップ
    setActivePinia(createPinia());
    // fetchモックのリセット
    vi.mocked(global.fetch).mockClear();
  });

  it('有効なアクセストークンが存在する場合、そのトークンを返す', async () => {
    const userInfo = useUserInfoStore();
    const futureDate = new Date();
    futureDate.setHours(futureDate.getHours() + 1); // 1時間後

    userInfo.jwtDto = {
      accessToken: 'valid-token',
      refreshToken: 'valid-refresh',
      expiresAt: futureDate,
    };

    await expect(getAuthorizedPromiseArea()).resolves.toBe('valid-token');
    expect(global.fetch).not.toHaveBeenCalled();
  });

  it('アクセストークンが期限切れの場合、リフレッシュして新しいトークンを返す', async () => {
    const userInfo = useUserInfoStore();
    const pastDate = new Date();
    pastDate.setMinutes(pastDate.getMinutes() - 5); // 5分前

    userInfo.jwtDto = {
      accessToken: 'expired-token',
      refreshToken: 'valid-refresh',
      expiresAt: pastDate,
    };

    const newJwt: JwtTokenDtoInterface = {
      accessToken: 'new-access-token',
      refreshToken: 'new-refresh-token',
      expiresAt: new Date(),
    };

    // fetchのレスポンスをモック
    vi.mocked(global.fetch).mockResolvedValue({
      ok: true,
      status: 200,
      json: async () => newJwt,
    } as Response);

    const result = await getAuthorizedPromiseArea();

    expect(result).toBe('new-access-token');
    expect(global.fetch).toHaveBeenCalledTimes(1);
    expect(userInfo.jwtDto.accessToken).toBe('new-access-token');
  });

  it('トークンのリフレッシュに失敗した場合、TokenRefreshErrorをスローする', async () => {
    const userInfo = useUserInfoStore();
    const pastDate = new Date();
    pastDate.setMinutes(pastDate.getMinutes() - 5);

    userInfo.jwtDto = {
      accessToken: 'expired-token',
      refreshToken: 'valid-refresh',
      expiresAt: pastDate,
    };

    // fetchのレスポンスをモック（エラーケース）
    vi.mocked(global.fetch).mockResolvedValue({
      ok: false,
      status: 401, // Unauthorized
    } as Response);

    await expect(getAuthorizedPromiseArea()).rejects.toThrow(TokenRefreshError);
    expect(global.fetch).toHaveBeenCalledTimes(1);
  });

  it('アクセストークンが存在しない場合、AccessTokenNotFoundErrorをスローする', async () => {
    const userInfo = useUserInfoStore();
    userInfo.jwtDto = {
      accessToken: '',
      refreshToken: '',
      expiresAt: new Date(1947, 7, 29, 0, 0, 0),
    };

    await expect(getAuthorizedPromiseArea()).rejects.toThrow(AccessTokenNotFoundError);
    expect(global.fetch).not.toHaveBeenCalled();
  });
});
