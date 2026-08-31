# ==========================================
# 1. ビルド用ステージ (Build Stage)
# ==========================================
FROM node:22.19 AS build-stage

WORKDIR /app

# ローカル依存ライブラリ (.tgz) のコピー
COPY lib/ ./lib/

# package.json と package-lock.json をコピーしてインストール（キャッシュの有効活用）
COPY package*.json ./
RUN npm install

# アプリケーション全体のファイルをコピー
COPY . .

# Vue (TypeScript/Vite) アプリケーションを本番用にビルド
# (ビルド結果は /app/dist に出力されます)
RUN npm run build


# ==========================================
# 2. 本番配信ステージ (Production Stage)
# ==========================================
FROM nginx:1.27-alpine AS production-stage

# Nginxのデフォルト設定ファイルを削除し、カスタムの nginx.conf を配置
COPY nginx.conf /etc/nginx/conf.d/default.conf

# ビルドした静的ファイルを Nginx のベースパス配下（/）に配置
COPY --from=build-stage /app/dist /usr/share/nginx/html

# 80ポートを公開
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
