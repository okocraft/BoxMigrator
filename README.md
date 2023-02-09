# BoxMigrator

Box `v3.x.x` のデータを `v4.x.x` に移行するプラグインです。

現行のメジャーバージョンである `v5.x.x` や将来のバージョンには対応していません。

`v4.x.x` から `v5.x.x` への移行は、初回起動時に自動的に行われます。

このプラグインを使用するには **Java 17 以上** が必要です。

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
