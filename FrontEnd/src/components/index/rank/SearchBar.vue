<template id="app-template">
  <div class="app">
    <br>
    <input type="text" class="search" v-model="searchVal" placeholder=''>
  </div>
</template>

<script>
  export default {
    data:()=>({
      searchVal: "",
      types: [],
    }),
    computed: {
    },
};

</script>

<style scoped>
* {
  font-size:10.5%;
}

.app {
  width: 100%;
  margin: 50px auto;
}

.search {
  height: 3rem;
  margin: 0 auto 50px;
  width: 90%;
  display: block;
  border-radius: 10rem;
  border: 2px solid #4e9fd4;
  font-size: 1.8rem;
  padding: 0 2rem;
  outline: none;
  transition: all .2s;
}

.search::-webkit-input-placeholder {
  color: lightgray;
}

.search:focus {
  background: #a9d8f6;
  color: white;
}

.search:focus::-webkit-input-placeholder {
  color: rgb(161, 175, 187);
}


.list {
  padding: 0;
}

.image {
  width: auto;
  height: 10rem;
  vertical-align: bottom;
}

.content {
  margin-left: 2rem;
  width: 80%;
  display: inline-block;
}

.title,
.date {
  font-family: 'Questrial';
  margin: 0;
}

.title {
  color: #2574A9;
  font-size: 2.1rem;
  margin-bottom: 1rem;
  letter-spacing: .05rem;
}

.date {
  color: #aaa;
  font-size: 1.5rem;
  display: inline;
}

.button {
  background: transparent;
  border: 2px solid #2574A9;
  padding: .8rem 0;
  width: 10rem;
  display: inline-block;
  text-align: center;
  color: #2574A9;
  text-decoration: none;
  font-size: 1.3rem;
  font-family: 'Questrial';
  border-radius: 5rem;
  margin-left: 1.5rem;
  letter-spacing: 0.1rem;
  transition: all .2s;
  float: right;
}

.button:hover {
  background: #2574A9;
  color: white;
}

.button.-not-available {
  border: none;
}

.no-results {
  color: #aaa;
  font-family: 'Questrial';
  font-size: 2rem;
  letter-spacing: 0.08rem;
  text-align: center;
}

</style>