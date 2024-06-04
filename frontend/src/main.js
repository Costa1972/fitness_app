import { createApp } from "vue";
import App from "./App.vue";
import index from "./router/router";
import store from "./store";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from './plugins/font-awesome'

createApp(App)
    .use(index)
    .use(store)
    .component("font-awesome-icon", FontAwesomeIcon)
    .mount("#app");