<template>
  
  <v-app>
    <router-view></router-view>
  </v-app>

</template>

<script src="https://unpkg.com/vue/dist/vue.js"></script>
<script src="https://unpkg.com/vuesax"></script>
<script>
import Header from './components/common/Footer';
import Footer from './components/common/Header';

export default {
  name: 'App',

  components: {
    Header,
    Footer,

  },

  data: () => ({
    //
  }),
  
};
</script>
