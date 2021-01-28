<template>

<div class="d-flex justify-center">
  <!-- Top3프로필 -->
  <div class="three">
    <div class="profiles justify-center">
      <div class="second">
        <v-avatar
        size="80"  
        >
          <img
            src="https://cdn.vuetifyjs.com/images/john.jpg"
            alt="John"
          >
        </v-avatar>
        <h3 class="nickname">nickname</h3>
      </div>
      <div class="first">
        <v-avatar
        size="100"  
        >
          <img
            src="https://cdn.vuetifyjs.com/images/john.jpg"
            alt="John"
          >
        </v-avatar>
        <h3 class="nickname">nickname</h3>
      </div>
      <div class="third">
        <v-avatar
        size="80"  
        >
          <img
            src="https://cdn.vuetifyjs.com/images/john.jpg"
            alt="John"
          >
        </v-avatar>
        <h3 class="nickname">nickname</h3>
      </div>
    </div>
    <div class="images">
      <img
            src="../../../img/rank.png"
            width="70%"
            alt="John"
          >
    </div>
  </div>

  <!-- Top3단상 -->


</div>

</template>

<script>
export default {
    data:()=>({
      valueOne: '',
      valueTwo: '',
    })
}
</script>

<style>
.three{
  display:grid;
}
.profiles{
  display: flex;
  text-align: center;
  height: 50%;
  background-color: white;
}
.second{
  margin-top: 18%;
  background-color: white;
}
.first{
  display:grid;
  margin-left:5%;
  margin-right:5%;
  background-color: white;
}
.third{
  margin-top: 18%;
  background-color: white;
}
.images{
  text-align: center;
  margin-top:-70px;
}
.nickname{
  color:rgb(67, 117, 255);
}
</style>