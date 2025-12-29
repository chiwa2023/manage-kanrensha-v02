import { createRouter, createWebHistory } from "vue-router";
import  RoutePathConstants  from "./routePathConstants";

const routes = [
    // トップ
    { path: RoutePathConstants.PAGE_LOGIN, name: "TopPage", component: () => import("./components/main/pages/TopPage.vue") },
    // ログアウト
    { path: RoutePathConstants.PAGE_LOGOUT, name: "Logout", component: () => import("./components/main/pages/Logout.vue") },

    // 開発用テンプレート
    { path: RoutePathConstants.PAGE_DEVELOP_TEMPLATE, name: "Template", component: () => import("./components/main/Template.vue") },

    // 利用者SEメニュー
    { path: RoutePathConstants.PAGE_MENU_ADMIN, name: "MenuRiyoushaAdminBase", component: () => import("./components/main/pages/menu/MenuRiyoushaAdminBase.vue") },
    // 利用者運営者メニュー
    { path: RoutePathConstants.PAGE_MENU_MANAGER, name: "MenuRiyoushaManagerBase", component: () => import("./components/main/pages/menu/MenuRiyoushaManagerBase.vue") },
    // 利用者APIユーザメニュー
    { path: RoutePathConstants.PAGE_MENU_PARTNER_API, name: "MenuRiyoushaPartnerApiBase", component: () => import("./components/main/pages/menu/MenuRiyoushaPartnerApiBase.vue") },

    // 関連者メニュー
    { path: RoutePathConstants.PAGE_MENU_KANRENSHA, name: "MenuKanrenshaBase", component: () => import("./components/main/pages/menu/MenuKanrenshaBase.vue") },


    // 利用者組織登録編集
    { path: RoutePathConstants.PAGE_REGIST_RIYOUSHA_ORG, name: "RegistRiyoushaOrg", component: () => import("./components/main/pages/regist_riyousha_org/RegistRiyoushaOrg.vue") },
    // 利用者運営者編集
    { path: RoutePathConstants.PAGE_EDIT_RIYOUSHA_MANAGER, name: "EditRiyoushaManager", component: () => import("./components/main/pages/edit_riyousha/EditRiyoushaManager.vue") },
    // 利用者APIユーザ編集
    { path: RoutePathConstants.PAGE_EDIT_RIYOUSHA_PARTNER, name: "EditRiyoushaPartnerApi", component: () => import("./components/main/pages/edit_riyousha/EditRiyoushaPartnerApi.vue") },

    // 利用者検索
    { path: RoutePathConstants.PAGE_RIYOUSHA_SEARCH, name: "SearchEditRiyousha", component: () => import("./components/main/pages/search_edit_riyousha/SearchEditRiyousha.vue") },

    // マスタ一括登録
    { path: RoutePathConstants.PAGE_REGI_BULK_MASTER_KIGYOU, name: "RegistBulkMasterKigyou", component: () => import("./components/main/pages/regist_bulk_master/RegistBulkMasterKigyouDt.vue") },
    { path: RoutePathConstants.PAGE_REGI_BULK_MASTER_PERSON, name: "RegistBulkMasterPerson", component: () => import("./components/main/pages/regist_bulk_master/RegistBulkMasterPerson.vue") },
    { path: RoutePathConstants.PAGE_REGI_BULK_MASTER_POLI_ORG, name: "RegistBulkMasterSeijidantai", component: () => import("./components/main/pages/regist_bulk_master/RegistBulkMasterSeijidantai.vue") },

    // 履歴一括登録
    { path: RoutePathConstants.PAGE_REGI_BULK_HISTORY_KIGYOU, name: "RegistBulkHistoryKigyou", component: () => import("./components/main/pages/regist_bulk_history/RegistBulkHistoryKigyouDt.vue") },
    { path: RoutePathConstants.PAGE_REGI_BULK_HISTORY_PERSON, name: "RegistBulkHistoryPerson", component: () => import("./components/main/pages/regist_bulk_history/RegistBulkHistoryPerson.vue") },
    { path: RoutePathConstants.PAGE_REGI_BULK_HISTORY_POLI_ORG, name: "RegistBulkHistorySeijidantai", component: () => import("./components/main/pages/regist_bulk_history/RegistBulkHistorySeijidantai.vue") },

    // 強制処理CSVダンプ
    { path: RoutePathConstants.PAGE_DUMP_MASTER, name: "ForceDumpMaster", component: () => import("./components/main/pages/z_force_dump/ForceDumpMaster.vue") },
    { path: RoutePathConstants.PAGE_DUMP_HISTORY, name: "ForceDumpHistory", component: () => import("./components/main/pages/z_force_dump/ForceDumpHistory.vue") },
    { path: RoutePathConstants.PAGE_DUMP_MASTER_STD, name: "ForceDumpMasterStd", component: () => import("./components/main/pages/z_force_dump/ForceDumpMasterStd.vue") },

    // 強制処理差分CSVダンプ(差分)
    { path: RoutePathConstants.PAGE_DUMP_SABUN_MASTER, name: "ForceSabunDumpMaster", component: () => import("./components/main/pages/z_force_dump_sabun/ForceSabunDumpMaster.vue") },
    { path: RoutePathConstants.PAGE_DUMP_SABUN_HISTORY, name: "ForceSabunDumpHistory", component: () => import("./components/main/pages/z_force_dump_sabun/ForceSabunDumpHistory.vue") },
    { path: RoutePathConstants.PAGE_DUMP_SABUN_MASTER_STD, name: "ForceSabunDumpMasterStd", component: () => import("./components/main/pages/z_force_dump_sabun/ForceSabunDumpMasterStd.vue") },

    // XMLから編集
    { path: RoutePathConstants.PAGE_ADD_XML, name: "AddByXml", component: () => import("./components/main/pages/add_xml/AddByXml.vue") },

    // データダウンロード
    { path: RoutePathConstants.PAGE_DOWNLOAD_MASTER_STD, name: "DownloadMasterStd", component: () => import("./components/main/pages/download_data/DownloadMasterStd.vue") },
    { path: RoutePathConstants.PAGE_DOWNLOAD_MASTER_MIN, name: "DownloadMasterMin", component: () => import("./components/main/pages/download_data/DownloadMasterMin.vue") },
    { path: RoutePathConstants.PAGE_DOWNLOAD_HISTORY, name: "DownloadHistory", component: () => import("./components/main/pages/download_data/DownloadHistory.vue") },

    { path: RoutePathConstants.PAGE_DOWNLOAD_SABUN_MASTER_STD, name: "DownloadSabunMasterStd", component: () => import("./components/main/pages/download_sabun/DownloadSabunMasterStd.vue") },
    { path: RoutePathConstants.PAGE_DOWNLOAD_SABUN_MASTER_MIN, name: "DownloadSabunMasterMin", component: () => import("./components/main/pages/download_sabun/DownloadSabunMasterMin.vue") },
    { path: RoutePathConstants.PAGE_DOWNLOAD_SABUN_HISTORY, name: "DownloadSabunHistory", component: () => import("./components/main/pages/download_sabun/DownloadSabunHistory.vue") },

];

const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
});

export default router;