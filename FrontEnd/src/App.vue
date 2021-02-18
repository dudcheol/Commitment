<template>
  <v-app>
    <keep-alive>
      <router-view></router-view>
    </keep-alive>
  </v-app>
</template>

<script>
import { mapActions } from 'vuex';
export default {
  name: 'App',
  components: {},
  data: () => ({}),
  methods: {
    ...mapActions(['GET_REALTIME_COMMIT_LIST']),
  },
  created() {
    this.GET_REALTIME_COMMIT_LIST();
  },
};
</script>

<style>
body {
  overflow: hidden;
  height: 100%;
  width: 100%;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}
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
.v-slider__track-fill {
  border-radius: 6px 0 0 6px;
}
/* Commit Map Style */
.line {
  white-space: nowrap;
}
</style>
