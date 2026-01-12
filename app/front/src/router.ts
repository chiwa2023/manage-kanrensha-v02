import { createRouter, createWebHistory } from "vue-router";
import RoutePathConstants from "./routePathConstants";

const routes = [
    // トップ
    { path: RoutePathConstants.PAGE_LOGIN, name: "TopPage", component: () => import("./components/main/pages/TopPage.vue") },
    // ログアウト
    { path: RoutePathConstants.PAGE_LOGOUT, name: "Logout", component: () => import("./components/main/pages/Logout.vue") },

    // 開発用テンプレート
    { path: RoutePathConstants.PAGE_DEVELOP_TEMPLATE, name: "Template", component: () => import("./components/main/Template.vue") },

    // SEメニュー
    { path: RoutePathConstants.PAGE_MENU_ADMIN, name: "MenuRiyoushaAdminBase", component: () => import("./components/main/pages/menu/MenuRiyoushaAdminBase.vue") },
    // 運営者メニュー
    { path: RoutePathConstants.PAGE_MENU_MANAGER, name: "MenuRiyoushaManagerBase", component: () => import("./components/main/pages/menu/MenuRiyoushaManagerBase.vue") },
    // APIユーザメニュー
    { path: RoutePathConstants.PAGE_MENU_PARTNER_API, name: "MenuRiyoushaPartnerApiBase", component: () => import("./components/main/pages/menu/MenuRiyoushaPartnerApiBase.vue") },

    // 関連者メニュー
    { path: RoutePathConstants.PAGE_MENU_KANRENSHA, name: "MenuKanrenshaBase", component: () => import("./components/main/pages/menu/MenuKanrenshaBase.vue") },


    // 新規アカウント作成
    { path: RoutePathConstants.PAGE_ADD_ACCOUNT, name: "AddAccountMenu", component: () => import("./components/main/pages/add_account/AddAccountMenu.vue") },
    { path: RoutePathConstants.PAGE_SEND_ACCESS_CODE, name: "SendAccessCode", component: () => import("./components/main/pages/add_account/SendAccessCode.vue") },
    { path: RoutePathConstants.PAGE_SWITCH_USER_KBN, name: "SwitchNewUserKbn", component: () => import("./components/main/pages/add_account/SwitchNewUserKbn.vue") },

    // 関連者・利用者追加
    { path: RoutePathConstants.PAGE_INSERT_KANRENSHA, name: "InsertKanrensha", component: () => import("./components/main/pages/add_account/InsertKanrensha.vue") },
    { path: RoutePathConstants.PAGE_INSERT_PARTNER_API, name: "InsertRiyoushaPartner", component: () => import("./components/main/pages/add_account/InsertRiyoushaPartner.vue") },
    { path: RoutePathConstants.PAGE_INSERT_MANAGER, name: "InsertRiyoushaManager", component: () => import("./components/main/pages/add_account/InsertRiyoushaManager.vue") },


    // 利用者検索
    { path: RoutePathConstants.PAGE_RIYOUSHA_SEARCH, name: "SearchEditRiyousha", component: () => import("./components/main/pages/search_edit_riyousha/SearchEditRiyousha.vue") },

    // ユーザ編集
    { path: RoutePathConstants.PAGE_REFRESH_PASSWORD, name: "RefreshPassword", component: () => import("./components/main/pages/user_edit/RefreshPassword.vue") },
    { path: RoutePathConstants.PAGE_RESET_PASSWORD, name: "ResetPassword", component: () => import("./components/main/pages/user_edit/ResetPassword.vue") },
    { path: RoutePathConstants.PAGE_USER_EDIT, name: "EditUser", component: () => import("./components/main/pages/user_edit/EditUser.vue") },
    { path: RoutePathConstants.PAGE_USER_WITHDRAW, name: "WithdrawUser", component: () => import("./components/main/pages/user_edit/WithdrawUser.vue") },
    { path: RoutePathConstants.PAGE_USER_CHANGE, name: "ChangeUser", component: () => import("./components/main/pages/user_edit/ChangeUser.vue") },

];

const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
});

export default router;