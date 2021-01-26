<template>
  <v-app>
    <router-view></router-view>
  </v-app>
</template>

<script src="https://unpkg.com/vue/dist/vue.js"></script>
<script src="https://unpkg.com/vuesax"></script>
<script>
import HelloWorld from './components/HelloWorld';
import Header from './components/layout/Footer';
import Footer from './components/layout/Header';

export default {
  name: 'App',

  components: {
    HelloWorld,
  },

  data: () => ({
    //
  }),
};
</script>
