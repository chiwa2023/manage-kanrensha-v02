# 配備手順

成果物をAWSに配備・更新する手順
実際のこのプロジェクトでの配備は行っていないので、実際に行ったときに再修正

> 💡 **重要（サブドメイン分離 ＋ ALB構成への移行）：**
> 現在は、複数のシステム（関連者・書面作成・調査）をサブドメインで分離し、前段の ALB（Application Load Balancer）で SSL 通信を終端して各コンテナへルーティングする構成を採用しています。
> ネットワーク、Route 53、ACM、ALB、およびセキュリティグループの設定手順については、新たに作成された **[AWS インフラ構築・ネットワーク設定手順書](aws_infrastructure_setup.md)** を参照してください。本手順書と併せてインフラの構築を進める必要があります。

## 開発ソースからのコンパイル準備

### front側

ソースコピーをする

### back側

build-package処理

```
mvnw clean package -Dmaven.test.skip=true
```

## dokcer imageを起動して自PCで動作確認

```
docker compose down
docker-compose up --build
```

## データベースデータを複写

```
docker cp <sqlファイル> <コンテナイメージId>:<ディレクトリ>

mysql -u <ユーザ> -p --database manage_kanrensha_v02 < <イメージ内sqlファイル>

```

## ローカルの住居SQLをaws上DBに複写

```
scp -i <接続キー> -r <複写元> ec2-user@<EC2のIP>:<複写先>

// 住所県ファイル一括
cd <複写先>
for f in *.sql; do mysql -u <ユーザー名> -p<パスワード> <データベース名> < "$f"; done

```

## デプロイ処理・実装の更新のみ

### 1. 共通ツールの接続先を本番環境に向ける

```
# docker image作成用にbuild-packするときは有効にする
VITE_API_DOMAIN=https://kanrensha.normalize-jp-seijishikin.net/api

# 開発用
# VITE_API_DOMAIN=http://localhost:6180/api
```

### 2.共通ツール コンパイルしてpackする

```
npm run build
npm pack
```

### 3.共通ツールを関連者に適用する

- libディレクトリにseijishikin-jp-normalize_common-tool-x.x.x.tgz を複写する

```
npm install seijishikin-jp-normalize_common-tool-x.x.x.tgz
npm install
```

### 4. 関連者の接続先を本番環境に向ける

```
# docker image作成用にbuild-packするときは有効にする
VITE_API_DOMAIN=https://kanrensha.normalize-jp-seijishikin.net/api

# 開発用
# VITE_API_DOMAIN=http://localhost:6180/api
```

### 5. ECRへのログイン認証

```
aws ecr get-login-password --region <リージョン> | docker login --username AWS --password-stdin <ログイン文字列>
```

### 6. イメージにECR用のタグを付与

```
docker tag kanrensha-db:latest <ログイン文字列>/kanrensha-db:latest
docker tag kanrensha-back:latest <ログイン文字列>/kanrensha-back:latest
docker tag kanrensha-front:latest <ログイン文字列>/kanrensha-front:latest
```

### 7. ECRへpush

```
docker push 343771139194.dkr.ecr.ap-northeast-3.amazonaws.com/kanrensha-db:latest
docker push 343771139194.dkr.ecr.ap-northeast-3.amazonaws.com/kanrensha-back:latest
docker push 343771139194.dkr.ecr.ap-northeast-3.amazonaws.com/kanrensha-front:latest
```

### 8.SSH接続(ElasticIP)

```
ssh -i "C:\key\kanrensha-v02.pem" ec2-user@<IPアドレス>
```

### 9. EC2でプルして実行

```

aws ecr get-login-password --region <リージョン> | docker login --username AWS --password-stdin <ログイン文字列>

docker pull <ログイン文字列>/kanrensha-db:latest
docker pull <ログイン文字列>/kanrensha-back:latest
docker pull <ログイン文字列>/kanrensha-front:latest

```

### 10. EC2で実行

現状本番環境ではdbを先に立ち上げてから後からfront/backを立ち上げないとタイミングが合わない

```
docker compose down
docker compose up -d db
docker compose up -d back front

# ログ表示
docker compose logs -f
```

## (参考)デプロイEC2作成も含む処理

### 1. ECRリポジトリの作成(powershell・1回限り)

```
aws ecr create-repository --repository-name kanrensha-db --region ap-northeast-3
aws ecr create-repository --repository-name kanrensha-back  --region ap-northeast-3
aws ecr create-repository --repository-name kanrensha-front  --region ap-northeast-3
```

### 2. ECRへのログイン認証

```
aws ecr get-login-password --region <リージョン> | docker login --username AWS --password-stdin <ログイン文字列>
```

### 3. イメージにECR用のタグを付与

```
docker tag kanrensha-db:latest <ログイン文字列>/kanrensha-db:latest
docker tag kanrensha-back:latest <ログイン文字列>/kanrensha-back:latest
docker tag kanrensha-front:latest <ログイン文字列>/kanrensha-front:latest
```

### 4. ECRへプッシュ

```
docker push 343771139194.dkr.ecr.ap-northeast-3.amazonaws.com/kanrensha-db:latest
docker push 343771139194.dkr.ecr.ap-northeast-3.amazonaws.com/kanrensha-back:latest
docker push 343771139194.dkr.ecr.ap-northeast-3.amazonaws.com/kanrensha-front:latest
```

### 5. コンソール上からインスタンスを作成

### 6. 鍵(.pem)をダウンロードする

- 作成したpemは利用中のユーザ(xxxxxx)だけが利用できるように権限を変更する必要がある(administratorなども削除!)

### 7. docker と docker compose のインストール

```
sudo yum update -y

sudo yum install docker -y
sudo systemctl start docker
sudo systemctl enable docker

sudo usermod -a -G docker ec2-user

sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o  /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
sudo ln -s /usr/local/bin/docker-compose /usr/libexec/docker/cli-plugins/docker-compose
```

### 5.SSH接続(ElasticIP)

```
ssh -i "C:\key\kanrensha-v02.pem" ec2-user@<IPアドレス>
```

### 6. EC2でプルして実行

  EC2にSSHログイン後、ECRにログインしてイメージを取得します。(※更新push作業)

```

aws ecr get-login-password --region <リージョン> | docker login --username AWS --password-stdin <ログイン文字列>

docker pull <ログイン文字列>/kanrensha-db:latest
docker pull <ログイン文字列>/kanrensha-back:latest
docker pull <ログイン文字列>/kanrensha-front:latest

```

### 6. EC2で実行

現状本番環境ではdbを先に立ち上げてから後からfront/backを立ち上げないとタイミングが合わない

```
docker compose down
docker compose up -d db
docker compose up -d back front
# ログ表示
docker compose logs -f
```

<!--- 

## AWS CLIの起動

- AWS CLIに認証情報を設定する

```
SET aws_id=abcdefg(実際は12桁の数字)
SET region_name=setsuzokusaki(実際はAWSのリージョン名)
```

- 一時環境変数を設定する
  - frontのdocker image名 vite など
  - backのdocker image名 spring など
  - dbのdocker image名 db など
  - dbとbackデータの永続化volumeのdocker image名 vol など

```
SET front_image = vite
SET back_image =  spring
SET db_image = db
SET volume_image = vlm
```

## ECRにレポジトリを作成する

```
aws ecr create-repository --repository-name %front_image% --image-scanning-configuration scanOnPush=true
aws ecr create-repository --repository-name %back_image% --image-scanning-configuration scanOnPush=true
aws ecr create-repository --repository-name %db_image% --image-scanning-configuration scanOnPush=true
aws ecr create-repository --repository-name %volume_image% --image-scanning-configuration scanOnPush=true
```

## dockerにベースとなるイメージを用意する

```
# 実際にpullするイメージは各Dockerfileを参照のこと
docker pull node
docker pull eclipse-temurin 
docker pull mysql  
```

## ECRにdocker imageをpush

```
aws ecr get-login-password --region %region_name% | docker login --username AWS --password-stdin %aws_id%.dkr.ecr.%region_name%.amazonaws.com

aws ecr describe-repositories --repository-names %front_image% --query  'repositories[0].repositoryUri' --output text

docker tag %front_image%:latest %aws_id%.dkr.ecr.%region_name%.amazonaws.com/%front_image%:latest
docker push %aws_id%.dkr.ecr.%region_name%.amazonaws.com/%front_image%:latest

docker tag %back_image%:latest %aws_id%.dkr.ecr.%region_name%.amazonaws.com/%back_image%:latest
docker push %aws_id%.dkr.ecr.%region_name%.amazonaws.com/%back_image%:latest

docker tag %db_image%latest %aws_id%.dkr.ecr.%region_name%.amazonaws.com/%db_image%
docker push %aws_id%.dkr.ecr.%region_name%.amazonaws.com/%db_image%  

docker tag volume_image:latest %aws_id%.dkr.ecr.%region_name%.amazonaws.com/%volume_image%:latest
docker push %aws_id%.dkr.ecr.%region_name%.amazonaws.com/%volume_image%:latest

```

## ECRとEC2操作用iam設定を作成

### ECRからpullするロール作成

```
aws iam create-role --role-name EC2ECRReadAccess --assume-role-policy-document 
  "{\"Version\":\"%yyyy-mm-dd%\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"Service\":\"ec2.
  amazonaws.com\"},\"Action\":\"sts:AssumeRole\"}]}"
```

%yyyy-mm-dd%は実施日付と入れ替える

### IAMロール EC2ECRReadAccess を作成

```
aws iam create-role --role-name EC2ECRReadAccess --assume-role-policy-document 
  file://C:/%role-josn%
```

%role-josn%はローカルPCの書き出したいパスに置き換える

### このロールに AmazonEC2ContainerRegistryReadOnly ポリシーをアタッチ

```
aws iam attach-role-policy --role-name EC2ECRReadAccess --policy-arn 
  arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryReadOnly  
```

### インスタンス起動時に、このIAMロールをアタッチするために、インスタンスプロファイルを作成する

```
aws iam create-instance-profile --instance-profile-name EC2ECRReadAccessProfile
```

### 作成したIAMロール EC2ECRReadAccess をインスタンスプロファイル EC2ECRReadAccessProfile に追加

```
aws iam add-role-to-instance-profile --instance-profile-name EC2ECRReadAccessProfile --role-name EC2ECRReadAccess
```

## セキュリティグループの設定

### セキュリティグループを作成

```

SET security_group = project-sg

aws ec2 create-security-group --group-name %security_group% --description "Security group for      
  EC2 instance" --vpc-id (vpcのID) 
```

### セキュリティグループにSSH (22番ポート) とHTTP (80番ポート) のインバウンドルールを追加

```
aws ec2 authorize-security-group-ingress --group-name %security_group% --protocol tcp --port 22 --cidr 0.0.0.0/0  
```

### HTTPのインバウンドルールを追加

```
aws ec2 authorize-security-group-ingress --group-name %security_group% --protocol tcp --port 80 --cidr  0.0.0.0/0
```

## EC2インスタンスを起動

```

SET instance_value = project-ec2

aws ec2 run-instances --image-id (イメージid) --instance-type (インスタンスタイプ) --key-name 
  (インスタンスの名称) --security-group-ids (セキュリティグループID) --iam-instance-profile
  Name=EC2ECRReadAccessProfile --subnet-id (サブネットId) --associate-public-ip-address 
  --tag-specifications 'ResourceType=instance,Tags=[{Key=Name,Value=%instance_value%}]'
```

## インスタンスのパブリックIPアドレスを取得して、SSH接続の準備

```
aws ec2 describe-instances --filters "Name=tag:Name,Values=%instance_value%"  
  "Name=instance-state-name,Values=running" --query "Reservations[0].Instances[0].PublicIpAddress"   
  --output text
```

※作成したpemは利用中のユーザ(ユーザ:ユーザ省略名)だけが利用できるように権限を変更する必要がある(administratorなども削除!)

## SSH接続の準備

```
cmd
ssh  -v -i "C:(ローカルPCのpemの絶対パス)" ec2-user@(リージョンのIPアドレス)
```

## インスタンスに docker / docker-compose がない場合はインストール

```
sudo yum update -y

sudo yum install docker -y
sudo systemctl start docker
sudo systemctl enable docker

sudo usermod -a -G docker ec2-user

sudo curl -L <https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname> -s)-$(uname -m) -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
docker-compose version
```

## SSHからECRにログイン認証

```
aws ecr get-login-password --region %region_name% | docker login --username AWS 
  --password-stdin %aws_id%.dkr.ecr.%region_name%.amazonaws.com
```

## docker-compose.yml作成

```
cat <<EOF > /home/ec2-user/docker-compose.yml
version: '3.8'
services:
  db:
    image: %aws_id%.dkr.ecr.%region_name%.amazonaws.com/%db_image%
    volumes:
      - db_data:/var/lib/mysql
    environment:
      MYSQL_ROOT_PASSWORD: (パスワード)
      MYSQL_DATABASE: (任意の名称)
  front:
    image: %aws_id%.dkr.ecr.%region_name%.amazonaws.com/%front_image%:latest
    ports:
      - "80:80"
    volumes:
      - front_data:/var/www/html
  back:
    image: %aws_id%.dkr.ecr.%region_name%.amazonaws.com/%back_image%:latest
    ports:
      - "8080:8080"
    volumes:
      - back_data:/var/www/data
volumes:
  db_data:
  front_data:
  back_data:
EOF
```

## 起動

```
docker-compose up -d
```

## 後片付け

### EC2削除

```
aws ec2 terminate-instances --instance-ids $(aws ec2 describe-instances --filters                        │
  "Name=tag:Name,Values=%instance_value%" "Name=instance-state-name,Values=running" --query                   │
  "Reservations[0].Instances[0].InstanceId" --output text)
```

### ECR削除

```
aws ecr batch-delete-image --repository-name %front_image% --image-ids imageTag=latest
aws ecr delete-repository --repository-name %front_image% --force
aws ecr batch-delete-image --repository-name %back_image% --image-ids imageTag=latest
aws ecr delete-repository --repository-name %back_image% --force
aws ecr batch-delete-image --repository-name %db_image%--image-ids imageTag=latest
aws ecr delete-repository --repository-name %db_image% --force
aws ecr batch-delete-image --repository-name %volume_image% --image-ids imageTag=latest
aws ecr delete-repository --repository-name %volume_image% --force

```

### セキュリティグループ削除

```
aws ec2 delete-security-group --group-id (セキュリティグループId)
```

### EC2アタッチロール

```
aws iam detach-role-policy --role-name EC2ECRReadAccess --policy-arn  
  arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryReadOnly
```

### インスタンスプロファイルからロールを削除

```
aws iam remove-role-from-instance-profile --instance-profile-name EC2ECRReadAccessProfile  
 --role-name EC2ECRReadAccess  
```

### インスタンスプロファイルを削除

```
aws iam delete-instance-profile --instance-profile-name EC2ECRReadAccessProfile
```

### ロールを削除

```
aws iam delete-role --role-name EC2ECRReadAccess
```

### キーペアを削除

```
aws ec2 delete-key-pair --key-name (キーペア名)  
```
-->