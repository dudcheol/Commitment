<template id="app-template">
  <div class="app">
    <br />
    <input type="text" class="search" placeholder="닉네임으로 검색" @input="onInput" />
  </div>
</template>

<script>
export default {
  data: () => ({
    message: '',
  }),
  methods: {
    onInput(e) {
      this.message = e.target.value;
      this.$emit('keyword', this.message); //e.target.valut는 부모의 inputValue
    },
  },
};
</script>

<style scoped>
.app {
  width: 100%;
}

.search {
  height: 3rem;
  margin: 0 auto;
  width: 90%;
  display: block;
  border-radius: 10rem;
  border: 2px solid rgb(161, 175, 187);
  font-size: 15px;
  padding: 0 2rem;
  outline: none;
  transition: all 0.2s;
}

.search::-webkit-input-placeholder {
  color: lightgray;
}

.search:focus {
  background: rgb(161, 175, 187);
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
  color: #2574;
  font-size: 2.1rem;
  margin-bottom: 1rem;
  letter-spacing: 0.05rem;
}

.date {
  color: #aaa;
  font-size: 1.5rem;
  display: inline;
}

.button {
  background: transparent;
  border: 2px solid #2574a9;
  padding: 0.8rem 0;
  width: 10rem;
  display: inline-block;
  text-align: center;
  color: #2574a9;
  text-decoration: none;
  font-size: 1.3rem;
  font-family: 'Questrial';
  border-radius: 5rem;
  margin-left: 1.5rem;
  letter-spacing: 0.1rem;
  transition: all 0.2s;
}

.button:hover {
  background: #2574a9;
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
