import { describe, it, expect, beforeEach } from 'vitest';
import { setActivePinia, createPinia } from 'pinia';
import getAuthorizedPromiseArea from '../../../main/dto/login/getAuthorizedPromiseArea';
import { useUserInfoStore } from '../../../main/stores/storeUserInfo';

/**
 * @vitest-environment jsdom
 */

// 注意: このテストスイートは実際のバックエンドAPIへの接続を試みます。
// 実行する前に、バックエンドサーバーが起動しており、
// app/front/src/routePathConstants.ts の設定が正しいことを確認してください。
// また、有効なリフレッシュトークンを持つユーザー情報が必要です。
// 環境が整備されていない場合は、このテストは失敗します。
describe.skip('getAuthorizedPromiseArea with real backend access', () => {
  beforeEach(() => {
    setActivePinia(createPinia());
  });

  it('期限切れトークンと有効なリフレッシュトークンで新しいアクセストークンを取得できる', async () => {
    const userInfo = useUserInfoStore();
    const pastDate = new Date();
    pastDate.setMinutes(pastDate.getMinutes() - 10); // 10分前に期限切れ

    // ▼▼▼ 事前にバックエンドで有効なリフレッシュトークンを取得して設定してください ▼▼▼
    userInfo.jwtDto = {
      accessToken: 'expired-access-token',
      refreshToken: 'ここにバックエンドで有効なリフレッシュトークンを設定してください',
      expiresAt: pastDate,
    };
    // ▲▲▲ ▲▲▲

    // このテストはネットワーク通信を伴うため、タイムアウト時間を長めに設定します
    const newAccessToken = await getAuthorizedPromiseArea();

    expect(newAccessToken).toBeDefined();
    expect(typeof newAccessToken).toBe('string');
    expect(newAccessToken).not.toBe('expired-access-token');

    // ストアの情報も更新されていることを確認
    expect(userInfo.jwtDto.accessToken).toBe(newAccessToken);
  }, 15000); // タイムアウトを15秒に設定
});
