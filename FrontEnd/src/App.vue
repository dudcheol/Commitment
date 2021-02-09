<template>
  <v-app>
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
.v-slider__track-container {
  height: 12px !important;
}
.v-slider__track-background {
  border-radius: 6px;
}
</style>
