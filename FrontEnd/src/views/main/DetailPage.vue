<template>
    <div class="grid-container">
        
    <div class="map">
        <div class="back_button">
        <vs-button icon relief>
        <i class='bx bx-arrow-back' ></i>
        </vs-button>
        </div>
        <div class="my_location">
            이것은 나의 위치
        </div>
        <v-img
         max-height="300"
         src="https://docs.microsoft.com/ko-kr/azure/azure-maps/media/migrate-google-maps-web-app/google-maps-marker.png">
           
        </v-img>
    </div>

    <div class="article">
        
    </div>

    </div>
</template>

<script>
export default {
    name: "Detail",
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
    background: green;
    position: absolute;
    z-index: 1;
    top: 50%;
    left: 50%;
    transform: translate( -50%, -50% );
}

</style>