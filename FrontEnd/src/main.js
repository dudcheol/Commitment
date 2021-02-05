import Vue from 'vue'
import App from './App.vue'
import router from "./router/router";
import Vuesax from 'vuesax'
import vuetify from './plugins/vuetify';
import 'vuesax/dist/vuesax.css'
import 'boxicons/css/boxicons.min.css'
import VueHorizontalTimeline from "vue-horizontal-timeline";
import VueRouter from 'vue-router'
import ScrollBar from '@morioh/v-perfect-scrollbar'

Vue.config.productionTip = false

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')

Vue.use(Vuesax, {
})
Vue.use(VueRouter)
Vue.use(VueHorizontalTimeline);
Vue.use(ScrollBar);