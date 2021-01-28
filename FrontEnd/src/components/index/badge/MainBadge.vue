<template>
<div>
<!--   <div class="percentageNum">
    <p>도전과제 75% 완료!</p>
  </div>
  <div class="percentageBar">
    <v-progress-linear
      color="red lighten-2"
      buffer-value="75"
      height="10"
      stream
    ></v-progress-linear>
    <br>
  </div> -->
  <div class="d-flex justify-center">
    
    <div>
      <vs-card class="maincard">
        <template #img>
        <v-avatar
            size="130"  
            >
              <img
                src="../../../img/badge1.png"
                alt="John"
              >
        </v-avatar>
        </template>
        <template #title>
          <h3>대표 뱃지</h3>
        </template>
        <template #text>
          <p class="badgeDescription">
            커밋홀릭
          </p>
        </template>
    <!--     <template #interactions>
          <vs-button danger icon>
            <i class='bx bx-heart'></i>
          </vs-button>
        </template> -->
      </vs-card>
    </div>
  </div>

</div>


</template>
<script>
export default {

}
</script>


<style scoped>
.percentageBar{
  text-align: center;
  width:100%;
  
}
.percentageNum{
  color:rgb(224, 136, 150);
  font-size: 15px;
  left:70%;
}
.maincard{
  width:200px;
}
.badgeDescription{
  text-align: center;
}
</style>