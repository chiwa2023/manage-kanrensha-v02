import { createRouter, createWebHistory } from "vue-router";
import RoutePathConstants from "./routePathConstants";

const routes = [
    // トップ
    { path: RoutePathConstants.PAGE_LOGIN, name: "TopPage", component: () => import("./components/main/pages/TopPage.vue") },
    // 開発用テンプレート
    { path: RoutePathConstants.PAGE_DEVELOP_TEMPLATE, name: "Template", component: () => import("./components/main/Template.vue") },
    


];

const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
});

export default router;