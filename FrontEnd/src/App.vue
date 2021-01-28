<template>
  <v-app>
    <div id="develop-navigation">
      <router-link to="/">main</router-link> / <router-link to="/signup">signup</router-link> /
      <router-link to="/login">login</router-link> / <router-link to="/mypage">mypage</router-link> /
      <router-link to="/rank">ranking</router-link> / <router-link to="/badge">Badge</router-link>
    </div>
    <router-view></router-view>
  </v-app>
</template>

<script src="https://unpkg.com/vue/dist/vue.js"></script>
<script src="https://unpkg.com/vuesax"></script>
<script>
export default {
  name: 'App',

  components: {},

  data: () => ({
    //
  }),
};
</script>

<style scoped>
#develop-navigation {
  position: fixed;
  z-index: 9999;
}
</style>
