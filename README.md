# Bento
弁当注文アプリ

## 開発環境
* MacOS 10.14.5 Mojave

* IDEA
  * IntelliJ(2019.1.1)
* フレームワーク
  * SpringBoot(2.1.6)
* DB
  * PostgreSQL(11.3)

## 導入方法
1. gitでmasterブランチをclone
2. `import propject` でcloneしたフォルダ(Bento)を選択
3. Gradleを選択してインポート
4. `use Gradle Wrapper Task`、`java1.8.0_201`を選択
5. 右端のGradle ⇨ Gradleプロジェクトをリフレッシュ

### PostgreSQLの導入方法
1. `brew install postgresql`でインストール
2. `\connect - <USER_NAME>`でユーザーを切り替える
3. `createdb bento;`でデータベースbentoを作成
4. `src/main/resources`の中にあるapplication.propertiesに設定を記入<br>
   `spring.datasource.username`にPostgreSQLのユーザ名<br>
   `spring.datasource.password`にPostgreSQLのパスワード

### Lombok起動方法
* IntelliJ IDEA ⇨ Preferences ⇨ Annotation Processors <br>
　⇨ Enable annotation processingにチェックを入れる

## 画面説明
* 注文フォーム画面
  * GET: `/bento/user/order`
  * 必要事項入力後、`登録`ボタンを押す

* 明細画面
  * 入力した内容が確認できる
  
* 注文履歴画面
  * GET: `/bento/user/orderList`
  * 過去に注文した内容のすべての履歴を見ることができる
  
  
