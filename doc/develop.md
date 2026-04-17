# 開発環境構築

リポジトリを新しくする時の手順一覧

## front側

1. vite-vue導入 `npm create vite@latest --save-dev  . -- --template vue-ts`

2. pinia導入 `npm install pinia`

3. pinia永続化プラグイン導入 `npm i pinia-plugin-persistedstate`

4. 暗号化crypto-js導入 `npm i --save-dev @types/crypto-js`

5. vitest.config.jsの追加

6. eslint導入 `npm install eslint --save-dev`

7. vitest導入 `npm i -D vitest`

8. @vue/test-utils導入 `npm install --save-dev @vue/test-utils`

9. jsdom導入 `npm install --save-dev jsdom`

10. vitest.config.jsの追加

11. このgithubアカウント共通機能を導入する

- npm install seijishikin-jp-normalize_common-tool-x.y.z.tgz
- npm install seijishikin-jp-normalize-x.y.z.tgz

※ 9,10はコンポーネントテスト用。コンポーネントをテストしない場合は不要。

### 2. back側

1. spring boot導入
新規プロジェクト-spring starter project
    - boot本体
    - spring batch
    - spring oauth2
    - spring security
    - spring mail
    - spring JDBC
    - spring MySQLドライバ

2.このgithubアカウント全体共通機能jarを導入する

3.spring security用キーペアの作成
opensslインストールディレクトリ/binからcmdを起動。秘密鍵(パスフレーズなし)と公開鍵の作成は下記の通り

```
openssl genrsa -out app.key 2048
openssl rsa -in app.key -pubout -out app.pub
```

githubに鍵本体をpushするとセキュリティ警告が来るので、.gitignoreでpush除外とする必要がある。

```
src/main/resources/app.pub
src/main/resources/app.key
```

### 3. Database

### 4. Upgrade

a.back側

pom.xml内のspring-boot-starter-parentのVersion番号を、Eclipseに通知があり次第修正

b.front側

`npm audit fix` またはvite全体更新の時は `npm i vite`

## 4.起動

### 1. front

フロントエンドアプリケーションを初めて起動する前に、暗号化キーを設定する必要があります。

1.  `app/front` ディレクトリにある `.env.example` ファイルをコピーして、同じディレクトリに `.env` という名前のファイルを作成します。
2.  作成した `.env` ファイルを開き、`VITE_CRYPTO_SECRET_KEY` の値をあなただけのユニークでランダムな文字列に変更してください。

viteを起動 `npm run dev`

### 2. back

mvnからspring起動 `mvnw spring-boot:run`

## 5.テスト

### a. front

vitestを起動`npm run test`
VsCodeを使用している場合はJUnit感覚でテスト駆動できる[Vitestプラグイン](https://marketplace.visualstudio.com/items?itemName=vitest.explorer)の利用をお勧めする

### b. back

EclipseからJUnit起動
