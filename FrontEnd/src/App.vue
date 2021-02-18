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

.body,
div,
span {
  font-family: 'Noto Sans KR', sans-serif;
}

.app-name {
  font-family: 'S-CoreDream-8Heavy';
}

.text-custom-bold {
  font-family: 'Cafe24Ohsquare';
}

@font-face {
  font-family: 'Cafe24Ohsquare';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Cafe24Ohsquare.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}

@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);

@font-face {
  font-family: 'S-CoreDream-8Heavy';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-8Heavy.woff')
    format('woff');
  font-weight: normal;
  font-style: normal;
}
</style>
