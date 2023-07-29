import Vue from "vue"
import App from "./App.vue"
import router from "./router"
import store from "./store"
import ElementUI from "element-ui"
import 'element-ui/lib/theme-chalk/index.css'
import "./router/router-config"  // 路由守卫
import axios from 'axios'


Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.prototype.$axios = axios
axios.defaults.baseURL = '/api';
axios.defaults.headers.post['Content-Type'] = 'application/json';

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app")