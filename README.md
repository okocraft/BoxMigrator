# BoxMigrator

旧バージョンの Box のデータを新バージョンのストレージへ移行するプラグイン。

現在は `v3.x.x` -> `v4.x.x` をサポートしています。

## 使い方

**⚠ 移行前に必ず Box ディレクトリ (`./plugins/Box`) をバックアップしてください ⚠**

1. [リリース](https://github.com/okocraft/BoxMigrator/releases) より Jar をダウンロードする
2. `plugins` ディレクトリに Jar を移動し、サーバーを再起動する
3. `/boxmigrate <sqlite/mysql>` をコンソールから実行し、表示される手順を踏んで移行する

### `/boxmigrate` のメッセージの日本語訳

#### SQLite

SQLite (`database.db`) からの移行を開始します。

在庫データは既存の在庫数に加算されます。自動回収設定は個別のアイテム設定を除き移行されません。

移行を開始するには、`/boxmigrate confirm` を実行してください。

#### MySQL

MySQL からの移行を開始します。

在庫データは既存の在庫数に加算されます。自動回収設定は個別のアイテム設定を除き移行されません。

MySQL から移行するために、次の作業を行ってください。

1. `./plugins/Box/mysql.yml` を作成する
2. 次の Yaml を書き込む

```yml
database:
  mysql-settings:
    host: "<localhost or database address>"
    port: "<database port, default 3306>"
    user: "<username>"
    password: "<password>"
    db-name: "<database-name>"
```

この構造は以前の `config.yml` と同じです。

3. `/boxmigrate confirm` を実行する
