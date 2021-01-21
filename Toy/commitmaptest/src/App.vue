<template>
  <div id="app">
    <CommitMap></CommitMap>
    <commit-map-2></commit-map-2>
  </div>
</template>

<script>
// import CommitMap from './views/CommitMap.vue';
import CommitMap2 from './views/CommitMap2.vue';

export default {
  name: 'App',
  components: {
    // CommitMap,
    CommitMap2,
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
