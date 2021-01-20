import Vue from 'vue'
import App from './App.vue'
import * as VueGoogleMaps from "vue2-google-maps";

Vue.use(VueGoogleMaps, {
  load: {
    key: process.env.VUE_APP_GMAP_KEY,
    libraries: "places",
    // map_ids: process.env.VUE_APP_GAMP_ID,
  },
});

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')