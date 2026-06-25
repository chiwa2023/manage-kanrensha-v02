export default class RoutePathConstants {

    /** ドメイン */
    static readonly DOMAIN: string = "http://localhost:6180";

    /** ベース */
    static readonly BASE_PATH: string = "/manage-kanrensha";

    /** ログインページ */
    static readonly PAGE_LOGIN: string = this.BASE_PATH + "/";

    /** ログアウトページ */
    static readonly PAGE_LOGOUT: string = this.BASE_PATH + "/logout";

    // /** 開発台紙 */
    // static readonly PAGE_COMPONENT: string = this.BASE_PATH + "/component";

    /** SE権限メニュー */
    static readonly PAGE_MENU_ADMIN: string = this.BASE_PATH + "/menu-admin";
    /** 運営者メニュー */
    static readonly PAGE_MENU_MANAGER: string = this.BASE_PATH + "/menu-manager";
    /** APIパートナーメニュー */
    static readonly PAGE_MENU_PARTNER_API: string = this.BASE_PATH + "/menu-partner-api";
    /** 関連者メニュー */
    static readonly PAGE_MENU_KANRENSHA: string = this.BASE_PATH + "/menu-kanrensha";

    /** 新規追加メニュー */
    static readonly PAGE_ADD_ACCOUNT: string = this.BASE_PATH + "/add-account";
    /** アクセスコード送信 */
    static readonly PAGE_SEND_ACCESS_CODE: string = this.BASE_PATH + "/send-access-code";
    /** ユーザの種類変更 */
    static readonly PAGE_SWITCH_USER_KBN: string = this.BASE_PATH + "/switch-user-kbn";

    /** 関連者追加 */
    static readonly PAGE_INSERT_KANRENSHA: string = this.BASE_PATH + "/insert-kanrensha";
    /** APIパートナー追加 */
    static readonly PAGE_INSERT_PARTNER_API: string = this.BASE_PATH + "/insert-partner";
    /** 運営者追加 */
    static readonly PAGE_INSERT_MANAGER: string = this.BASE_PATH + "/insert-manager";

    /** 利用者運営者編集メニュー */
    static readonly PAGE_EDIT_RIYOUSHA_MANAGER: string = this.BASE_PATH + "/edit-riyousha-manager";
    /** 利用者APIユーザ編集メニュー */
    static readonly PAGE_EDIT_RIYOUSHA_PARTNER: string = this.BASE_PATH + "/edit-riyousha-partner";

    /** 利用者組織更新メニュー */
    static readonly PAGE_REGIST_RIYOUSHA_ORG: string = this.BASE_PATH + "/regist-riyousha-org";

    /** ユーザパスワード更新 */
    static readonly PAGE_REFRESH_PASSWORD: string = this.BASE_PATH + "/user/refresh-password";
    /** ユーザパスワードリセット */
    static readonly PAGE_RESET_PASSWORD: string = this.BASE_PATH + "/user/reset-password";
    /** ユーザ自身を編集 */
    static readonly PAGE_USER_EDIT: string = this.BASE_PATH + "/user/edit";
    /** ユーザ退会 */
    static readonly PAGE_USER_WITHDRAW: string = this.BASE_PATH + "/user/withdraw";
    /** 他者ユーザを編集 */
    static readonly PAGE_USER_CHANGE: string = this.BASE_PATH + "/user/change";
    /** SE権限追加推薦 */
    static readonly PAGE_ADMIN_PROMOTE: string = this.BASE_PATH + "/user/admin-promote";
    /** SE権限追加認否 */
    static readonly PAGE_ADMIN_ACCEPT: string = this.BASE_PATH + "/user/admin-accept";


    /** APIパートナーTokenおきかえ */
    static readonly PAGE_PARTNER_TOKEN_REPLACE: string = this.BASE_PATH + "/partner-api/token-replace";

    /** 利用者検索 */
    static readonly PAGE_RIYOUSHA_SEARCH: string = this.BASE_PATH + "/riyousha-search";


    /** 郵便番号建物入力 */
    static readonly PAGE_POSTAL_BUILDING: string = this.BASE_PATH + "/postal-code-building";
    /** 郵便番号編集 */
    static readonly PAGE_POSTAL_EDIT: string = this.BASE_PATH + "/postal-code-edit";
    /** 郵便番号移動 */
    static readonly PAGE_POSTAL_MOVE: string = this.BASE_PATH + "/postal-code-move";

    /** 郵便番号差分 */
    static readonly PAGE_POSTAL_SABUN: string = this.BASE_PATH + "/postal-code-sabun";
    /** 郵便番号差分ワークテーブル編集 */
    static readonly PAGE_POSTAL_SABUN_UPDATE: string = this.BASE_PATH + "/postal-wktbl-update";

    /** アドレス・ベース・レジストリ編集 */
    static readonly PAGE_ADDRESS_REGI_EDIT: string = this.BASE_PATH + "/address-registry-edit";
   /** アドレス・ベース・レジストリ差分編集 */
    static readonly PAGE_ADDRESS_SABUN_EDIT: string = this.BASE_PATH + "/address-wktbl-edit";
   /** アドレス・ベース・レジストリ差分一括更新 */
    static readonly PAGE_ADDRESS_SABUN_UPDATE: string = this.BASE_PATH + "/address-wktbl-update";
 
    /** アドレス・ベース・レジストリ差分 */
    static readonly PAGE_ADDRESS_REGI_SABUN: string = this.BASE_PATH + "/address-registry-sabun";

    /** 地方自治体コード差分 */
    static readonly PAGE_LGCODE_SABUN: string = this.BASE_PATH + "/lgcode-sabun";
    static readonly PAGE_LGCODE_SABUN_EDIT: string = this.BASE_PATH + "/lgcode-sabun-edit";


    /** 関連者企業・団体履歴一括登録 */
    static readonly PAGE_REGI_BULK_HISTORY_KIGYOU: string = this.BASE_PATH + "/bulk-history-kigyou";
    /** 関連者個人履歴一括登録 */
    static readonly PAGE_REGI_BULK_HISTORY_PERSON: string = this.BASE_PATH + "/bulk-history-person";
    /** 関連者政治団体履歴一括登録 */
    static readonly PAGE_REGI_BULK_HISTORY_POLI_ORG: string = this.BASE_PATH + "/bulk-history-seijidantai";

    /** 関連者企業・団体マスタ一括登録 */
    static readonly PAGE_REGI_BULK_MASTER_KIGYOU: string = this.BASE_PATH + "/bulk-master-kigyou";
    /** 関連者個人マスタ一括登録 */
    static readonly PAGE_REGI_BULK_MASTER_PERSON: string = this.BASE_PATH + "/bulk-master-person";
    /** 関連者政治団体マスタ一括登録 */
    static readonly PAGE_REGI_BULK_MASTER_POLI_ORG: string = this.BASE_PATH + "/bulk-master-seijidantai";

    /** 関連者個人－企業／団体紐づけ登録 */
    static readonly PAGE_REGI_BULK_COMBINE_KIGYOU: string = this.BASE_PATH + "/bulk-combine-kigyou";
    /** 関連者個人－企業／団体紐づけ登録 */
    static readonly PAGE_REGI_BULK_COMBINE_SEIJIDANTAI: string = this.BASE_PATH + "/bulk-combine-seijidantai";

    /** 関連者マスタ一ダンプ(指定期間まで) */
    static readonly PAGE_DUMP_MASTER: string = this.BASE_PATH + "/dump-master";
    /** 関連者履歴一ダンプ(指定期間まで) */
    static readonly PAGE_DUMP_HISTORY: string = this.BASE_PATH + "/dump-history";
    /** 関連者マスタ一標準ダンプ(指定期間まで) */
    static readonly PAGE_DUMP_MASTER_STD: string = this.BASE_PATH + "/dump-master-std";

    /** 関連者マスタ一差分ダンプ */
    static readonly PAGE_DUMP_SABUN_MASTER: string = this.BASE_PATH + "/dump-sabun-master";
    /** 関連者履歴一差分ダンプ */
    static readonly PAGE_DUMP_SABUN_HISTORY: string = this.BASE_PATH + "/dump-sabun-history";
    /** 関連者マスタ一(標準)差分ダンプ */
    static readonly PAGE_DUMP_SABUN_MASTER_STD: string = this.BASE_PATH + "/dump-sabun-master-std";

    /** 関連者XML編集登録 */
    static readonly PAGE_ADD_XML: string = this.BASE_PATH + "/add-by-xml";

    /** 関連者マスタ標準データダウンロード */
    static readonly PAGE_DOWNLOAD_MASTER_STD: string = this.BASE_PATH + "/download-master-std";
    /** 関連者最小標準データダウンロード */
    static readonly PAGE_DOWNLOAD_MASTER_MIN: string = this.BASE_PATH + "/download-master-min";
    /** 関連者履歴データダウンロード */
    static readonly PAGE_DOWNLOAD_HISTORY: string = this.BASE_PATH + "/download-history";

    /** 関連者マスタ標準データ差分ダウンロード */
    static readonly PAGE_DOWNLOAD_SABUN_MASTER_STD: string = this.BASE_PATH + "/download-sabun-master-std";
    /** 関連者最小標準データ差分ダウンロード */
    static readonly PAGE_DOWNLOAD_SABUN_MASTER_MIN: string = this.BASE_PATH + "/download-sabun-master-min";
    /** 関連者履歴データ差分ダウンロード */
    static readonly PAGE_DOWNLOAD_SABUN_HISTORY: string = this.BASE_PATH + "/download-sabun-history";

    /** 作業内容承認 */
    static readonly PAGE_WORKS_APPROVAL: string = this.BASE_PATH + "/works-approval";

    /** 利用者組織登録(SE検索) */
    static readonly PAGE_SEARCH_RIYOUSHA_ORG: string = this.BASE_PATH + "/search-riyousha-org-regist";

    /** 利用者組織登録編集(所属組織) */
    static readonly PAGE_RIYOUSHA_ORG_EDIT: string = this.BASE_PATH + "/riyousha-org-edit";

    /** 利用者組織編集(他者) */
    // static readonly PAGE_RIYOUSHA_ORG_EDIT: string = this.BASE_PATH + "/riyousha-org-regist";

    /** 利用者組織個人招待 */

    /** 利用者組織個人承認 */

    /** 予約実行 */
    static readonly PAGE_TIMER_YOTEI: string = this.BASE_PATH + "/timer-yotei";

    /** 運営者による関連者管理 */
    static readonly PAGE_KANRENSHA_MANAGE: string = this.BASE_PATH + "/kanrensha-manage";

    /** 本人による関連者編集 */
    static readonly PAGE_KANRENSHA_MYSELF: string = this.BASE_PATH + "/kanrensha-myself";

    /** 利用者組織に個人を招待 */
    static readonly PAGE_INSERT_COMBINE_ORG: string = this.BASE_PATH + "/insert-combine-org";
    /** 利用者組織に個人を招待 */
    static readonly PAGE_INVITE_ORG_PERSON: string = this.BASE_PATH + "/invite-combine-manager";
    /** 利用者組織に招待を個人が承認 */
    static readonly PAGE_ACCEPT_ORG_PERSON: string = this.BASE_PATH + "/accept-combine-riyousha";



    // /** 運営者による利用者検索編集 */
    // static readonly PAGE_SEARCH_RIYOUSHA: string = this.BASE_PATH + "/search-riyousha";

    // /** 運営者組織個人紐づけ */
    // static readonly PAGE_COMBINE_MANAGER: string = this.BASE_PATH + "/combine-manager";
    // /** APIパートナー組織個人紐づけ */
    // static readonly PAGE_COMBINE_COMRADE: string = this.BASE_PATH + "/combine-comrade";


    // /** APIパートナー編集 */
    // static readonly PAGE_EDIT_COMRADE: string = this.BASE_PATH + "/edit-comrade";
    // /** 運営者編集 */
    // static readonly PAGE_EDIT_MANAGER: string = this.BASE_PATH + "/edit-manager";

    // /** APIパートナー組織に個人を招待 */
    // static readonly PAGE_INVITE_COMRADE_PERSON: string = this.BASE_PATH + "/invite-comrade";
    // /** APIパートナー組織に招待を個人が承認 */
    // static readonly PAGE_ACCEPT_COMRADE_PERSON: string = this.BASE_PATH + "/accept-comrade";



    /** タスク計画検索 */
    static readonly PAGE_SEARCH_TASK_PLAN: string = this.BASE_PATH + "/search-task-plan";

    /** タスク情報検索 */
    static readonly PAGE_SEARCH_TASK_INFO: string = this.BASE_PATH + "/search-task-info";

    /** 開発テンプレート(共通ツール) */
    static readonly PAGE_DEVELOP_TEMPLATE: string = this.BASE_PATH + "/develop-template";

}
