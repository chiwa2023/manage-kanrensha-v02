# ベースイメージの指定
FROM eclipse-temurin:25-jre

# ワーキングディレクトリの設定
WORKDIR /app

# コンパイルする
# mvnw install -DskipTests=true

# アプリケーションのJARファイルをコンテナにコピー
COPY manage-kanrensha-v02-0.0.1-SNAPSHOT.jar app.jar

# ポートの公開
EXPOSE 6180

# アプリケーションの実行コマンド
ENTRYPOINT ["java", "-jar", "app.jar"]

# ビルド mvnw package -DskipTests