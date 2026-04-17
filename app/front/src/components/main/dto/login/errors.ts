export class AccessTokenNotFoundError extends Error {
  constructor(message = 'アクセストークンが見つかりません。') {
    super(message);
    this.name = 'AccessTokenNotFoundError';
  }
}

export class TokenRefreshError extends Error {
  constructor(message = 'トークンのリフレッシュに失敗しました。') {
    super(message);
    this.name = 'TokenRefreshError';
  }
}
