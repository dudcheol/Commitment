import Vue from 'vue';
import App from './App.vue';
import router from './router';
import Vuesax from 'vuesax';
import vuetify from './plugins/vuetify';
import 'vuesax/dist/vuesax.css'; //Vuesax styles
import store from './vuex/store';
// import 'material-icons/iconfont/material-icons.css';
import 'boxicons/css/boxicons.min.css';
import VueHorizontalTimeline from 'vue-horizontal-timeline'; //타임라인
import VueRouter from 'vue-router';
import ScrollBar from '@morioh/v-perfect-scrollbar';
import ScrollAnimation from './directive/scrollanimation.js'

Vue.directive('scrollanimation', ScrollAnimation)

Vue.config.productionTip = false;


Vue.use(Vuesax, {});
Vue.use(VueRouter);
Vue.use(VueHorizontalTimeline);
Vue.use(ScrollBar);

Vue.use(Vuesax, {
})
Vue.use(VueRouter)
Vue.use(VueHorizontalTimeline);
Vue.use(ScrollBar);

new Vue({
  vuetify,
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');