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
    // 管理者メニュー
    { path: RoutePathConstants.PAGE_MENU_MANAGER, name: "MenuRiyoushaManagerBase", component: () => import("./components/main/pages/menu/MenuRiyoushaManagerBase.vue") },
    // APIユーザメニュー
    { path: RoutePathConstants.PAGE_MENU_PARTNER_API, name: "MenuRiyoushaPartnerApiBase", component: () => import("./components/main/pages/menu/MenuRiyoushaPartnerApiBase.vue") },

    // 関連者メニュー
    { path: RoutePathConstants.PAGE_MENU_KANRENSHA, name: "MenuKanrenshaBase", component: () => import("./components/main/pages/menu/MenuKanrenshaBase.vue") },


    // 利用者検索
    { path: RoutePathConstants.PAGE_RIYOUSHA_SEARCH, name: "SearchEditRiyousha", component: () => import("./components/main/pages/search_edit_riyousha/SearchEditRiyousha.vue") },

];

const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
});

export default router;