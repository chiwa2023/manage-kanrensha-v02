import { createRouter, createWebHistory } from "vue-router";

const routes = [
    // トップ
    { path: "/", name: "Template",  component: () => import("./components/main/Template.vue")  },
];

const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
});

export default router;