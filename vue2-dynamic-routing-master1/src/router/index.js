import Vue from "vue"
import VueRouter from "vue-router"
import Layout from "../layout"
import Register from "../views/register/Register.vue"

Vue.use(VueRouter)

const routes = [
  { name: "login", path: "/login", meta: { title: "login" }, component: () => import("../views/login/index"), hidden: true },
  // {
  //   path: '/register',
  //   name: 'Register',
  //   component: Register
  // },
//   { name: "/table", path: "/table", component: Layout, redirect: "/table/index", hidden: false, children: [
// 	  { name: "/table/index", path: "/table/index", meta: { title: "table" }, component: () => import("../views/table/index") }
//   ] },
]

const router = new VueRouter({ routes })

export default router
// http://openapi.baidu.com/oauth/2.0/authorize?response_type=code&client_id=dAXQGopA0OkpQjyIeH34DZDp&redirect_uri=www.baidu.com&scope=basic&display=popup
// http://openapi.baidu.com/oauth/2.0/token?grant_type=authorization_code&code={CODE}&client_id=dAXQGopA0OkpQjyIeH34DZDp&client_secret={CLIENT_SECRET}&redirect_uri=oob