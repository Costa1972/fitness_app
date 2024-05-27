import HomeView from "@/views/HomeView.vue";
import { createRouter, createWebHistory } from "vue-router";
import type {RouteRecordRaw} from "vue-router";
import ShowDetails from "@/views/ShowDetailsView.vue";
import AddUserView from "@/views/AddUserView.vue";
import AboutView from "@/views/AboutView.vue";
import LoginView from "@/views/LoginView.vue";
import EditUserView from "@/views/EditUserView.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
    path: "/about",
    name: "about",
    component: AboutView,
    meta: {
      requiresAuth: true,
    },
  },
  {
    path: "/edit/user/:id",
    name: "edit",
    component: EditUserView,
    props: true,
  },
  {
    path: "/add/user",
    name: "add",
    component: AddUserView,
    props: true,
  },
  {
    path: "/details/user/:id",
    name: "details",
    component: ShowDetails,
  },
];

const router = createRouter({
  routes,
  history: createWebHistory('/'),
});

export default router;