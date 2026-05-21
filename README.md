# ec-site-java

Spring Boot を使用して構築された EC サイト向け Web アプリケーションです。

## 技術スタック

| コンポーネント         | バージョン            |
| --------------- | ---------------- |
| Java            | 21               |
| Spring Boot     | 3.5.12           |
| Maven           | 3.9+（Wrapper 同梱） |
| MyBatis         | 3.0.5            |
| H2 Database     | 組み込み型（ファイルベース）   |
| Thymeleaf       | 3.x              |
| Spring Security | 6.x              |

## 前提条件

以下のツールがインストールされていることを確認してください。

* **JDK 21** – [Adoptium](https://adoptium.net/) または互換性のあるディストリビューション
* **Git** – [git-scm.com](https://git-scm.com/)
* **Maven** – 任意（本プロジェクトには Maven Wrapper（`mvnw`）が含まれています）

インストール確認：

```bash
java --version
# 想定出力: openjdk 21.x.x ...

git --version
# 想定出力: git version 2.x.x ...

# Maven Wrapper を利用する場合、別途 Maven のインストールは不要です。
# Maven をグローバルにインストールしている場合:
mvn --version
# 想定出力: Apache Maven 3.9.x ...
```

## セットアップ手順

### 1. リポジトリをクローン

```bash
git clone https://github.com/diveintocode-corp/ec-site-java.git
cd ec-site-java
```

### 2. プロジェクトをビルド

Maven Wrapper を使用する場合（推奨）：

```bash
./mvnw clean install
```

または、Maven をグローバルにインストールしている場合：

```bash
mvn clean install
```

ビルドが成功すると `BUILD SUCCESS` が表示され、`target/ec-site-0.0.1-SNAPSHOT.jar` が生成されます。

### 3. アプリケーションを起動

```bash
./mvnw spring-boot:run
```

または、生成済み JAR を直接実行します：

```bash
java -jar target/ec-site-0.0.1-SNAPSHOT.jar
```

デフォルトでは **8080** ポートで起動します。

### 4. アプリケーション動作確認

Web ブラウザで以下の URL にアクセスしてください。

| URL                                                                            | 説明             |
| ------------------------------------------------------------------------------ | -------------- |
| [http://localhost:8080/](http://localhost:8080/)                               | トップページ         |
| [http://localhost:8080/products](http://localhost:8080/products)               | 商品一覧           |
| [http://localhost:8080/accounts/signup](http://localhost:8080/accounts/signup) | 会員登録           |
| [http://localhost:8080/accounts/login](http://localhost:8080/accounts/login)   | ログイン           |
| [http://localhost:8080/h2-console](http://localhost:8080/h2-console)           | H2 データベースコンソール |

アプリケーションが起動していることを確認するには、以下を実行してください。

```bash
curl -s -o /dev/null -w "%{http_code}" http://localhost:8080/
# 想定出力: 200
```

## データベース

本アプリケーションでは、ローカルファイル（`./data/ecsitedb`）として保存される組み込み型 H2 データベースを利用しています。外部データベースのセットアップは不要です。

### H2 コンソールへのアクセス

[http://localhost:8080/h2-console](http://localhost:8080/h2-console) にアクセスし、以下の情報を入力してください。

| 設定項目      | 値                              |
| --------- | ------------------------------ |
| JDBC URL  | `jdbc:h2:file:./data/ecsitedb` |
| User Name | `sa`                           |
| Password  | *(空欄)*                         |

## アプリケーション構成

```text
ec-site-java/
├── src/main/java/com/example/ecsite/
│   ├── config/           # Security、MVC、および Model Advice の設定
│   ├── controller/       # Web コントローラー（公開画面 + 管理画面）
│   ├── entity/           # ドメインモデルクラス
│   ├── exception/        # カスタム例外およびグローバル例外ハンドラー
│   ├── form/             # バリデーション付きフォームオブジェクト
│   ├── mapper/           # MyBatis Mapper インターフェース
│   ├── repository/       # Repository レイヤー
│   ├── security/         # 認証・認可
│   ├── service/          # ビジネスロジック層
│   └── viewmodel/        # Thymeleaf テンプレート用 ViewModel
├── src/main/resources/
│   ├── application.properties   # アプリケーション設定
│   ├── schema.sql               # データベーススキーマ（起動時に自動適用）
│   └── templates/               # Thymeleaf HTML テンプレート
└── src/test/                    # テストコード
```

## 主な機能

* 会員登録・ログイン機能（Spring Security）
* 商品一覧・商品画像表示
* ショッピングカート管理
* 注文処理および注文履歴管理
* ユーザープロフィール管理
* 商品・注文・顧客を管理する管理者ダッシュボード
* 外部依存のないファイルベース H2 データベース

## ポート設定

デフォルトポートを変更する場合は、`src/main/resources/application.properties` に以下を追加してください。

```properties
server.port=9090
```

設定変更後、アプリケーションを再ビルド・再起動してください。