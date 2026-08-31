# AWS インフレ構築・ネットワーク設定手順書

本ドキュメントは、採用構成である**「サブドメイン分離 ＋ ALB（Application Load Balancer）構成」**に基づき、AWSインフラ（Route 53、ACM、ALB、セキュリティグループ）を新規構築および強化するための具体的な作業手順書です。

既存の `doc/deploy.md` における、インターネットからEC2への直接のポート開放（ポート80やアプリポート）を廃止し、ALBを前段に置いてSSL（HTTPS）通信を終端させ、EC2側はALBからのトラフィックのみを許可する、堅牢かつスケーラブルな構成に移行します。

> ⚠️ **重要（未実装システムについて）：**
> 「書面作成（`create-doc`）」および「調査（`investigate`）」システムは現在、ソースコードが存在しないため、**インフラ構築においても無効化（コメントアウト）**しています。
> 本手順書では、将来的な拡張を見越した手順をコメントアウト（または参考値）として残しており、今回は**関連者システム（`kanrensha`）のみを稼働させる構成**で構築します。

---

## 📐 1. 設計概要（ネットワークおよびルーティング構造）

各システム共通で、インターネットからは HTTPS (443) のみを受け付け、ALBが各ターゲットグループへポートを変換してEC2上の Docker コンテナに転送します。

### ルーティング一覧

| 接続元ホスト名 (Host Header) | リクエストパス (Path) | ターゲットグループ名 | 転送先ホストポート | 状況 | 転送先コンテナ内ポート |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **`kanrensha.example.com`** | `/api/*` | `tg-kanrensha-back` | **`6180`** | **稼働** | Spring Boot (`6180`) |
| **`kanrensha.example.com`** | `/*` (デフォルト) | `tg-kanrensha-front` | **`8010`** | **稼働** | Nginx/Vite (`80`) |
| **`create-doc.example.com`** | `/api/*` | `tg-shomen-back` | *`6280`* | *未実装* | Spring Boot (`6280`) |
| **`create-doc.example.com`** | `/*` (デフォルト) | `tg-shomen-front` | *`8020`* | *未実装* | Nginx/Vite (`80`) |
| **`investigate.example.com`** | `/api/*` | `tg-chousa-back` | *`6380`* | *未実装* | Spring Boot (`6380`) |
| **`investigate.example.com`** | `/*` (デフォルト) | `tg-chousa-front` | *`8030`* | *未実装* | Nginx/Vite (`80`) |

---

## 🚀 2. 構築手順

「パターンA：CloudFormationによる一括全自動構築」

---

### パターン A: CloudFormationによる全自動構築 (推奨)

プロジェクト内の `config/aws/alb-route53-acm.yml` に定義されたテンプレートを使用し、一撃で全てのインフラを構築します。
※テンプレート上でも、書面作成および調査システム関連の設定はコメントアウトされており、今回余計なリソースやルールは作成されません。
実際にはコンソール > CloudFornation > スタック > スタックの作成 > テンプレートファイルのアップロード > テンプレートファイル内の定数指定 の順で作業を行った

#### 事前準備

1. AWS CLIのセットアップを行い、管理者権限（Route53, ACM, ALB, EC2の編集権限）を持つクレデンシャルを設定。
2. AWS Route 53に登録済みのホストゾーン（例: `example.com`）の **ホストゾーンID** を手元に用意します。

#### コマンド実行 (CloudFormationのデプロイ)

```bash
# 環境変数を設定 (ご自身の環境に合わせて変更してください)
SET STACK_NAME=prod-multisys-infra
SET DOMAIN_NAME=example.com
SET HOSTED_ZONE_ID=Z0123456789ABCDEF
SET VPC_ID=vpc-0123456789abcdef0
SET PUBLIC_SUBNET_1=subnet-0123456789abcdef1
SET PUBLIC_SUBNET_2=subnet-0123456789abcdef2
SET TARGET_EC2_ID=i-0123456789abcdef0

# 構築コマンドの実行
aws cloudformation create-stack ^
  --stack-name %STACK_NAME% ^
  --template-body file://config/aws/alb-route53-acm.yml ^
  --parameters ^
    ParameterKey=EnvironmentName,ParameterValue=prod ^
    ParameterKey=DomainName,ParameterValue=%DOMAIN_NAME% ^
    ParameterKey=HostedZoneId,ParameterValue=%HOSTED_ZONE_ID% ^
    ParameterKey=VpcId,ParameterValue=%VPC_ID% ^
    ParameterKey=PublicSubnet1,ParameterValue=%PUBLIC_SUBNET_1% ^
    ParameterKey=PublicSubnet2,ParameterValue=%PUBLIC_SUBNET_2% ^
    ParameterKey=TargetEc2InstanceId,ParameterValue=%TARGET_EC2_ID% ^
  --capabilities CAPABILITY_IAM
```

#### PUBLIC_SUBNET_2について

  🛠️ PUBLIC_SUBNET_2 の見つけ方手順

  以下のステップで、AWSコンソールから簡単に見つけることができます。

  ステップ1：EC2画面で「VPC ID」と「アベイラビリティーゾーン」を確認する

   1. EC2コンソールで kanrensha インスタンスの詳細画面を開きます。
   2. 以下の2つをメモします。
      * VPC ID（例：vpc-0123456789abcdef0）
      * アベイラビリティーゾーン (AZ)（例：ap-northeast-1a）
      * ※ すでに見つけている PUBLIC_SUBNET_1 のサブネットIDもメモしておきます。

  ステップ2：VPCコンソールでサブネット一覧を開く

   1. AWSコンソール上部の検索窓に「VPC」と入力し、VPCサービスを開きます。
   2. 左側のメニューから 「サブネット (Subnets)」 をクリックします。
   3. 検索窓に、ステップ1でメモした 「VPC ID」 を入力してEnterを押し、該当VPC内のサブネットだけに絞り込みます。

  ステップ3：PUBLIC_SUBNET_2 を選定する
  絞り込まれたサブネット一覧から、以下の条件を満たすものを1つ探します。

* 条件①：アベイラビリティーゾーン（AZ）が PUBLIC_SUBNET_1 と異なること
  * 例：PUBLIC_SUBNET_1 が ap-northeast-1a なら、もう1つは ap-northeast-1c（または 1d）に属するものを選びます。
* 条件②：パブリックサブネット（インターネットからアクセス可能）であること
  * 名前に「Public」や「public-subnet」などのタグ名がついていることが多いです。
  * （確実に見分けるには、サブネットを選択して下部の「ルートテーブル」タブを確認し、送信先 0.0.0.0/0 がターゲット
       igw-xxxx（インターネットゲートウェイ）になっているものを選びます。）

  ここで見つかったサブネットのID（例：subnet-9876543210fedcba）を PUBLIC_SUBNET_2
  としてCloudFormationのコンソールで指定してください。

#### 検証と完了

* CloudFormationが `CREATE_COMPLETE` になれば構築完了です。ACMのDNS検証も自動的にRoute 53にレコードが登録され、自動で検証（認証）されます。

---
<!-- 
### パターン B: AWS CLIによる手動構築（ステップバイステップ）

手動で詳細を確認しながら1つずつ設定・構築を進める場合の手順です。現時点で未実装のシステムに関する手順はコメントアウトまたは無効化されています。

#### 1. 変数の設定

```bash
SET DOMAIN_NAME=example.com
SET HOSTED_ZONE_ID=Z0123456789ABCDEF
SET VPC_ID=vpc-0123456789abcdef0
SET PUBLIC_SUBNET_1=subnet-0123456789abcdef1
SET PUBLIC_SUBNET_2=subnet-0123456789abcdef2
SET TARGET_EC2_ID=i-0123456789abcdef0
SET REGION_NAME=ap-northeast-1
```

#### 2. ACM SSL/TLSワイルドカード証明書の取得とDNS検証

ワイルドカード（`*.example.com`）およびルート（`example.com`）をカバーするSSL/TLS証明書を申請します。

```bash
# 証明書申請
aws acm request-certificate ^
  --domain-name "*.%DOMAIN_NAME%" ^
  --subject-alternative-names "%DOMAIN_NAME%" ^
  --validation-method DNS ^
  --query "CertificateArn" ^
  --output text
```

※出力された `CertificateArn` を変数にセットします。 (例: `SET CERTIFICATE_ARN=arn:aws:acm:ap-northeast-1:123456789012:certificate/abc-123...`)

DNS検証用のレコード名と値を取得し、Route 53に登録します。

```bash
# DNS検証用のレコード情報を取得
aws acm describe-certificate ^
  --certificate-arn %CERTIFICATE_ARN% ^
  --query "Certificate.DomainValidationOptions[0].ResourceRecord"
```

Route 53ホストゾーンに、上記で取得した `Name` (CNAME) と `Value` を使ってレコードを登録します。

#### 3. セキュリティグループの作成

ALB用セキュリティグループを作成し、インターネットから443(HTTPS)と80(HTTP)を許可します。

```bash
# ALB用セキュリティグループ
aws ec2 create-security-group ^
  --group-name prod-alb-sg ^
  --description "Security group for Multi-Sys ALB" ^
  --vpc-id %VPC_ID% ^
  --query "GroupId" ^
  --output text
```

※出力されたSG of ALBのIDを変数にセットします。 (例: `SET ALB_SG_ID=sg-012345abcdef67890`)

```bash
# インバウンドルール追加 (HTTPS: 443)
aws ec2 authorize-security-group-ingress ^
  --group-id %ALB_SG_ID% ^
  --protocol tcp ^
  --port 443 ^
  --cidr 0.0.0.0/0

# インバウンドルール追加 (HTTP: 80 - Redirect用)
aws ec2 authorize-security-group-ingress ^
  --group-id %ALB_SG_ID% ^
  --protocol tcp ^
  --port 80 ^
  --cidr 0.0.0.0/0
```

#### 4. ALB（Application Load Balancer）の新規作成

```bash
aws elbv2 create-load-balancer ^
  --name prod-multisys-alb ^
  --subnets %PUBLIC_SUBNET_1% %PUBLIC_SUBNET_2% ^
  --security-groups %ALB_SG_ID% ^
  --query "LoadBalancers[0].LoadBalancerArn" ^
  --output text
```

※出力されたALBのArnを変数にセットします。 (例: `SET ALB_ARN=arn:aws:elasticloadbalancing:...`)

#### 5. ターゲットグループの作成とEC2インスタンスの登録

現時点で稼働する**関連者システム**のフロントおよびバックエンド用ターゲットグループを作成し、EC2を登録します。

```bash
# -- 関連者システム ターゲットグループ作成 --
aws elbv2 create-target-group --name tg-kanrensha-front --protocol HTTP --port 8010 --vpc-id %VPC_ID% --health-check-path /
aws elbv2 create-target-group --name tg-kanrensha-back --protocol HTTP --port 6180 --vpc-id %VPC_ID% --health-check-path /api/actuator/health --matcher HttpCode="200,401"

# [未実装のためスキップ] 書面作成システム(create-doc)
# aws elbv2 create-target-group --name tg-shomen-front --protocol HTTP --port 8020 --vpc-id %VPC_ID% --health-check-path /
# aws elbv2 create-target-group --name tg-shomen-back --protocol HTTP --port 6280 --vpc-id %VPC_ID% --health-check-path /api/actuator/health --matcher HttpCode="200,401"

# [未実装のためスキップ] 調査システム(investigate)
# aws elbv2 create-target-group --name tg-chousa-front --protocol HTTP --port 8030 --vpc-id %VPC_ID% --health-check-path /
# aws elbv2 create-target-group --name tg-chousa-back --protocol HTTP --port 6380 --vpc-id %VPC_ID% --health-check-path /api/actuator/health --matcher HttpCode="200,401"
```

```bash
# ターゲットグループにEC2を登録 (関連者フロント: 8010)
SET TG_KANRENSHA_FRONT_ARN=(取得したtg-kanrensha-frontのARN)
aws elbv2 register-targets --target-group-arn %TG_KANRENSHA_FRONT_ARN% --targets Id=%TARGET_EC2_ID%,Port=8010

# ターゲットグループにEC2を登録 (関連者バック: 6180)
SET TG_KANRENSHA_BACK_ARN=(取得したtg-kanrensha-backsのARN)
aws elbv2 register-targets --target-group-arn %TG_KANRENSHA_BACK_ARN% --targets Id=%TARGET_EC2_ID%,Port=6180
```

#### 6. HTTPSリスナーおよびリスナールールの作成

HTTPS通信をSSL証明書を適用した状態で開始します。

```bash
# HTTPSリスナー作成 (デフォルトアクションは404固定応答)
aws elbv2 create-listener ^
  --load-balancer-arn %ALB_ARN% ^
  --protocol HTTPS ^
  --port 443 ^
  --certificates CertificateArn=%CERTIFICATE_ARN% ^
  --default-actions Type=fixed-response,FixedResponseConfig="{MessageBody='Host Not Found',StatusCode=404,ContentType='text/plain'}" ^
  --query "Listeners[0].ListenerArn" ^
  --output text
```

※出力されたリスナーのArnを変数にセットします。 (例: `SET LISTENER_ARN=arn:aws:elasticloadbalancing:...`)

```bash
# HTTPからHTTPSへのリダイレクト用リスナー (Port 80)
aws elbv2 create-listener ^
  --load-balancer-arn %ALB_ARN% ^
  --protocol HTTP ^
  --port 80 ^
  --default-actions Type=redirect,RedirectConfig="{Protocol=HTTPS,Port=443,StatusCode=HTTP_301}"
```

#### 7. リスナールール（ルーティング）の設定

優先度（Priority）に従って稼働中システムのルールのみを登録します。

```bash
# 関連者システムバックエンド (Priority: 10)
aws elbv2 create-rule --listener-arn %LISTENER_ARN% --priority 10 ^
  --conditions Field=host-header,HostHeaderConfig="{Values=['kanrensha.%DOMAIN_NAME%']}" ^
               Field=path-pattern,PathPatternConfig="{Values=['/api/*']}" ^
  --actions Type=forward,TargetGroupArn=%TG_KANRENSHA_BACK_ARN%

# 関連者システムフロントエンド (Priority: 11)
aws elbv2 create-rule --listener-arn %LISTENER_ARN% --priority 11 ^
  --conditions Field=host-header,HostHeaderConfig="{Values=['kanrensha.%DOMAIN_NAME%']}" ^
  --actions Type=forward,TargetGroupArn=%TG_KANRENSHA_FRONT_ARN%

# [未実装のため今回はスキップ] 書面作成(create-doc) および 調査(investigate) ルール作成
```

#### 8. Route 53 レコードセットの登録 (ALBへのAエイリアスレコード)

ALBのDNS名を取得し、Route 53にエイリアスレコードとして登録します。現在稼働するサブドメイン（`kanrensha`）のみを登録します。

```bash
# ALBのDNS名とHostedZoneIdの取得
aws elbv2 describe-load-balancers --load-balancer-arns %ALB_ARN% --query "LoadBalancers[0].[DNSName,CanonicalHostedZoneId]" --output text
```

※出力例：`prod-multisys-alb-12345.ap-northeast-1.elb.amazonaws.com`　`Z2YN85XYZ`

ローカルに `route53_change.json` を作成して適用します。

```json
{
  "Comment": "Add A Alias record for Kanrensha subdomain to ALB",
  "Changes": [
    {
      "Action": "UPSERT",
      "ResourceRecordSet": {
        "Name": "kanrensha.example.com.",
        "Type": "A",
        "AliasTarget": {
          "HostedZoneId": "Z2YN85XYZ",
          "DNSName": "prod-multisys-alb-12345.ap-northeast-1.elb.amazonaws.com.",
          "EvaluateTargetHealth": true
        }
      }
    }
    /* 未実装システムは、実装が完了した際に以下のレコードをUPSERT追加します。
    ,{
      "Action": "UPSERT",
      "ResourceRecordSet": {
        "Name": "create-doc.example.com.",
        "Type": "A",
        "AliasTarget": {
          "HostedZoneId": "Z2YN85XYZ",
          "DNSName": "prod-multisys-alb-12345.ap-northeast-1.elb.amazonaws.com.",
          "EvaluateTargetHealth": true
        }
      }
    },
    {
      "Action": "UPSERT",
      "ResourceRecordSet": {
        "Name": "investigate.example.com.",
        "Type": "A",
        "AliasTarget": {
          "HostedZoneId": "Z2YN85XYZ",
          "DNSName": "prod-multisys-alb-12345.ap-northeast-1.elb.amazonaws.com.",
          "EvaluateTargetHealth": true
        }
      }
    }
    */
  ]
}
```

```bash
# Route 53にレコード適用
aws route53 change-resource-record-sets --hosted-zone-id %HOSTED_ZONE_ID% --change-batch file://route53_change.json
```

---
-->

## 🔒 3. EC2 セキュリティグループの強化（超重要）

本インフラ設計で最も重要な「セキュリティの厳格化」です。
インターネットからEC2インスタンスへの直接のHTTPアクセスを遮断し、**ALBのセキュリティグループから転送された通信のみを許可**するようにEC2のセキュリティグループルールを設定します。

### インバウンドルールの変更内容

1. `doc/deploy.md` に記載されていた `0.0.0.0/0`（全インターネット）からEC2への `ポート 80` への直接接続を**削除**します。
2. 新たに以下のインバウンドルールをEC2のセキュリティグループに追加（または新規作成してアタッチ）します。（現在未実装のシステム用ポートは設定不要、またはコメントアウトします）

#### 設定するルール一覧

| プロトコル | ポート範囲 | ソース (許可する接続元) | 説明 | 状況 |
| :--- | :--- | :--- | :--- | :--- |
| **TCP** | `22` | 管理者の固定IP等 (Cidr) | SSH接続用（必要時のみ開放） | **許可** |
| **TCP** | `8010` | **`prod-alb-sg` (ALBのセキュリティグループID)** | 関連者フロント用 | **許可** |
| **TCP** | `6180` | **`prod-alb-sg` (ALBのセキュリティグループID)** | 関連者バックエンド用 | **許可** |
| *TCP* | *`8020`* | *`prod-alb-sg`* | *書面作成フロント用* | *未実装につき除外* |
| *TCP* | *`6280`* | *`prod-alb-sg`* | *書面作成バックエンド用* | *未実装につき除外* |
| *TCP* | *`8030`* | *`prod-alb-sg`* | *調査フロント用* | *未実装につき除外* |
| *TCP* | *`6380`* | *`prod-alb-sg`* | *調査バックエンド用* | *未実装につき除外* |

### AWS CLIによるEC2インバウンドルール設定手順

既存のEC2用セキュリティグループIDを `EC2_SG_ID` 、ALB用セキュリティグループIDを `ALB_SG_ID` とします。

```bash
SET EC2_SG_ID=sg-0abcdef1234567890
SET ALB_SG_ID=sg-012345abcdef67890

# 1. 既存の 0.0.0.0/0 からのポート80許可ルールを削除
aws ec2 revoke-security-group-ingress ^
  --group-id %EC2_SG_ID% ^
  --protocol tcp ^
  --port 80 ^
  --cidr 0.0.0.0/0

# 2. ALBのセキュリティグループからの稼働中アプリケーションポートへの通信のみを許可する
aws ec2 authorize-security-group-ingress --group-id %EC2_SG_ID% --protocol tcp --port 8010 --source-group %ALB_SG_ID%
aws ec2 authorize-security-group-ingress --group-id %EC2_SG_ID% --protocol tcp --port 6180 --source-group %ALB_SG_ID%

# ※ 8020, 6280, 8030, 6380 ポートについては未実装のため、今回の設定からは除外します。
```

これによって、仮に誰かがインターネット経由で `http://(EC2のパブリックIP):8010` や `http://(EC2のパブリックIP):6180` に直接アクセスしようとしても、ファイアウォール（セキュリティグループ）で完全にパケットが破棄されます。
必ず `https://kanrensha.example.com` のようにALBを経由した安全な暗号化通信だけがEC2のコンテナに到達するようになります。

---

## 🔍 4. 疎通・結合検証テスト

インフラ構築完了後、関連者コンテナ側が立ち上がったら、以下のテストを行い疎通が正しく行われているか確認してください。

### ① DNS名前解決の確認

ローカルPCのコマンドプロンプトまたはターミナルから、ドメインにpingを送り、ALBのパブリックIP/DNS名に向けて名前解決されているか確認します。

```bash
nslookup kanrensha.example.com
```

※ALBのパブリックIPが表示されればRoute 53の設定は正常です。

### ② HTTPS・SSL証明書の検証

ブラウザから `https://kanrensha.example.com` にアクセスし、鍵マーク（保護された通信）が表示されていること、証明書の発行元が `Amazon` であり、ドメインが `*.example.com` に一致していることを確認します。

### ③ ルーティングとCORS検証

`https://kanrensha.example.com/api/...` へのアクセス（例: `https://kanrensha.example.com/api/actuator/health`）がバックエンドコンテナに転送され、200 OK や適切なJSONレスポンスが返ることを確認します。
CORSエラーが完全に発生しないことをフロントエンド（ブラウザの開発者ツール）から確認します。
