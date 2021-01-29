<template>
  <div class="grid-container">
    <div class="map">
      <div class="back_button">
        <vs-button icon relief>
          <i class="bx bx-arrow-back"></i>
        </vs-button>
      </div>
      <div class="my_location"></div>
      <v-img max-height="300" src="https://www.archives.go.kr/next/images/site3/map_seoul.png">
      </v-img>
    </div>

    <div class="article">
      <ArticleDetail />
    </div>
  </div>
</template>

<script>
import ArticleDetail from './../../components/detail/ArticleDetail';
export default {
  name: 'Detail',
  components: {
    ArticleDetail,
  },
};
</script>

<style scoped>
.grid-container {
  display: grid;
  grid-template-columns: 2fr 1fr 1fr 2fr;
  grid-template-rows: 300px 100%;
  gap: 5px 0px;
  grid-template-areas:
    'map map map map'
    '. article article .';
}

.map {
  grid-area: map;
  position: relative;
}

.article {
  grid-area: article;
}

.map .back_button {
  display: block;
  position: absolute;
  left: 25px;
  top: 20px;
}

.my_location {
  font-size: 15px;
  background: rgba(220, 220, 220, 0.5);
  position: absolute;
  z-index: 1;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
