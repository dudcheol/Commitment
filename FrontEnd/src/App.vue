<template>
  <v-app>
<<<<<<< HEAD
    <div id="develop-navigation">
      <router-link to="/">main</router-link> /
      <router-link to="/signup">signup</router-link> /
      <router-link to="/login">login</router-link> /
      <router-link to="/mypage">mypage</router-link> /
      <router-link to="/rank">ranking</router-link> /
      <router-link to="/badge">Badge</router-link>
    </div>
=======
>>>>>>> 1f9d1dbf1e0dd4cc019a3343bcac7a372d92951f
    <router-view></router-view>
  </v-app>
</template>

<script>
export default {
  name: 'App',

  components: {},

  data: () => ({}),
};
</script>

<style>
.mid-size {
  max-width: 680px;
  width: 100%;
}
::-webkit-scrollbar {
  width: 8px;
}
::-webkit-scrollbar-thumb {
  border-radius: 5px;
  background-color: #cfd8dc;
}
::-webkit-scrollbar-track {
  background-color: #eceff1;
}
</style>
