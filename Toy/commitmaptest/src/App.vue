<template>
  <div id="app">
    <commit-map-test></commit-map-test>
    <CommitMap v-if="false"></CommitMap>
    <commit-map-2 v-if="false"></commit-map-2>
  </div>
</template>

<script>
import CommitMap from './views/CommitMap.vue';
import CommitMap2 from './views/CommitMap2.vue';
import CommitMapTest from './views/CommitMapTest.vue';

export default {
  name: 'App',
  components: {
    CommitMap,
    CommitMap2,
    CommitMapTest,
  },
};
</script>

<style>
#app {
  /* font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px; */
}
</style>
