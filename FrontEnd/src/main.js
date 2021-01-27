import Vue from 'vue'
import App from './App.vue'
import router from "./router/router";
import Vuesax from 'vuesax'
import vuetify from './plugins/vuetify';
import 'vuesax/dist/vuesax.css' //Vuesax styles
import 'boxicons/css/boxicons.min.css'
import 'material-icons/iconfont/material-icons.css';

import VueRouter from 'vue-router'


Vue.config.productionTip = false

Vue.use(Vuesax, {
})
Vue.use(VueRouter)

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')

