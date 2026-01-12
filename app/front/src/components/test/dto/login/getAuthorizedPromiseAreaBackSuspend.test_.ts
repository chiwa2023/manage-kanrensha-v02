// import { describe, it, expect, beforeEach, afterEach } from 'vitest';
// import { setActivePinia, createPinia } from 'pinia';
// import getAuthorizedPromiseArea from '../../../main/dto/login/getAuthorizedPromiseArea';
// import { useUserInfoStore } from '../../../main/stores/storeUserInfo';
// import RoutePathConstants from '../../../../routePathConstants';
// import { TokenRefreshError } from '../../../main/dto/login/errors';

/**
 * @vitest-environment jsdom
 */

// describe('getAuthorizedPromiseArea with suspended connection', () => {
  // let originalDomain: string;

  // beforeEach(() => {
  //   setActivePinia(createPinia());
  //   // テスト前に元のドメイン名を保存
  //   originalDomain = RoutePathConstants.DOMAIN;
  // });

  // afterEach(() => {
  //   // テスト後にドメイン名を元に戻す
  //   RoutePathConstants.DOMAIN = originalDomain;
  // });

  // it('fetch通信が遮断されている（存在しないホストにアクセスする）場合、エラーをスローする', async () => {
  //   // 通信失敗をシミュレートするために、一時的に存在しないドメインに書き換える
  //   RoutePathConstants.DOMAIN = 'http://localhost:9999';

  //   const userInfo = useUserInfoStore();
  //   const pastDate = new Date();
  //   pastDate.setMinutes(pastDate.getMinutes() - 5);

  //   userInfo.jwtDto = {
  //     accessToken: 'expired-token',
  //     refreshToken: 'any-refresh-token',
  //     expiresAt: pastDate.toISOString(),
  //   };

  //   // fetch自体が失敗すると、await fetchで例外が発生し、呼び出し元に伝播する。
  //   // getAuthorizedPromiseAreaにはtry...catchがないため、fetchがスローしたエラーがそのままスローされる。
  //   // そのため、特定のカスタムエラーではなく、何らかのエラーがスローされることを確認する。
  //   await expect(getAuthorizedPromiseArea()).rejects.toThrow();
  // });
// });
