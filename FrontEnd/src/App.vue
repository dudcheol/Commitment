<template>
  <v-app>
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

<style>
.mid-size {
  max-width: 680px;
  width: 100%;
}
</style>
