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
import ScrollAnimation from './directive/scrollanimation.js';

Vue.directive('scrollanimation', ScrollAnimation);
import * as VueGoogleMaps from 'vue2-google-maps';
import firebase from 'firebase/app';
import { FIREBASE_CONFIG } from './config';
import { LoaderPlugin } from 'vue-google-login';

Vue.config.productionTip = false;

Vue.use(Vuesax, {});
Vue.use(VueRouter);
Vue.use(VueHorizontalTimeline);
Vue.use(ScrollBar);
Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyDq5jEL1HSwZ9dDUxhUJ_G5U_f8KvMF6ys',
    libraries: 'places',
  },
});
Vue.use(LoaderPlugin, {
  client_id: '265137181932-gh7omk39se04nearqok9pdinleer99ur.apps.googleusercontent.com',
});

firebase.initializeApp(FIREBASE_CONFIG);

new Vue({
  vuetify,
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
