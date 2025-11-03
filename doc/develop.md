# 開発環境構築

リポジトリを新しくする時の手順一覧

## front側

1. vite-vue導入 `npm create vite@latest --save-dev  . -- --template vue-ts`

2. vue-router導入 `npm install vue-router --save-dev`

3. eslint導入 `npm install eslint --save-dev`

4. vitest導入 `npm i -D vitest`


5. @vue/test-utils導入 `npm install --save-dev @vue/test-utils`


6. jsdom導入 `npm install --save-dev jsdom`

7. vitest.config.jsの追加

※ 5,6はコンポーネントテスト用。コンポーネントをテストしない場合は不要。

### 2. back側






### 3. Database






### 4. Upgrade

a.back側

pom.xml内のspring-boot-starter-parentのVersion番号を、Eclipseに通知があり次第修正

b.front側

`npm audit fix` またはvite全体更新の時は `npm i vite`

## 4.起動

### 1. front

viteを起動 `npm run dev`

### 2. back

mvnからspring起動 `mvnw spring-boot:run`

## 5.テスト

### a. front

vitestを起動`npm run test`
VsCodeを使用している場合はJUnit感覚でテスト駆動できる[Vitestプラグイン](https://marketplace.visualstudio.com/items?itemName=vitest.explorer)の利用をお勧めする

### b. back

EclipseからJUnit起動
