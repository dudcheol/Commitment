<template>
    <div class="grid-container">
        
    <div class="map">
        <div class="back_button">
        <vs-button icon relief>
        <i class='bx bx-arrow-back' ></i>
        </vs-button>
        </div>
        <div class="my_location">
            서울특별시 강남구 역삼동 테헤란로 212
            <!-- <v-alert
            border="left"
            colored-border
            color="deep-purple accent-4"
            elevation="2"
            >
            서울특별시 강남구 역삼동 테헤란로 212
            </v-alert> -->
        </div>
        <v-img
         max-height="300"
         src="https://docs.microsoft.com/ko-kr/azure/azure-maps/media/migrate-google-maps-web-app/google-maps-marker.png">
           
        </v-img>
    </div>

    <div class="article">
        <ArticleDetail />
    </div>

    </div>
</template>

<script>

import ArticleDetail from './../components/detailpage/ArticleDetail'
export default {
    name: "Detail",
    components: {
        ArticleDetail
    }
}
</script>

<style>

.grid-container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 300px 1fr;
  gap: 5px 0px;
  grid-template-areas:
    "map map map map"
    ". article article .";
}

.map { grid-area: map;
    position:relative;
 }

.article { grid-area: article; }

.map .back_button {
    display: block;
    position: absolute;
    left: 25px;
    top: 20px;
}

.my_location {
    font-size: 25px;
    background: rgba(220, 220, 220, 0.5);
    position: absolute;
    z-index: 1;
    top: 50%;
    left: 50%;
    transform: translate( -50%, -50% );
}

</style>