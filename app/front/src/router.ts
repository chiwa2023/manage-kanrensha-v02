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


    // 利用者組織登録編集
    { path: RoutePathConstants.PAGE_REGIST_RIYOUSHA_ORG, name: "RegistRiyoushaOrg", component: () => import("./components/main/pages/regist_riyousha_org/RegistRiyoushaOrg.vue") },

    // 利用者運営者編集
    { path: RoutePathConstants.PAGE_EDIT_RIYOUSHA_MANAGER, name: "EditRiyoushaManager", component: () => import("./components/main/pages/edit_riyousha/EditRiyoushaManager.vue") },

    // 利用者APIユーザ編集
    { path: RoutePathConstants.PAGE_EDIT_RIYOUSHA_PARTNER, name: "EditRiyoushaPartnerApi", component: () => import("./components/main/pages/edit_riyousha/EditRiyoushaPartnerApi.vue") },


    // 利用者検索
    { path: RoutePathConstants.PAGE_RIYOUSHA_SEARCH, name: "SearchEditRiyousha", component: () => import("./components/main/pages/search_edit_riyousha/SearchEditRiyousha.vue") },

];

const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
});

export default router;