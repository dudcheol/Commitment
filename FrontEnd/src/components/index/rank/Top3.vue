<template>
  <div class="d-flex justify-center">
    <!-- Top3프로필 -->
    <div class="three">
      <div class="profiles justify-center">
        <div class="second">
          <v-avatar size="80">
            <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
          </v-avatar>
          <h3 class="nickname">nickname</h3>
        </div>
        <div class="first">
          <v-avatar size="100">
            <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
          </v-avatar>
          <h3 class="nickname">nickname</h3>
        </div>
        <div class="third">
          <v-avatar size="80">
            <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
          </v-avatar>
          <h3 class="nickname">nickname</h3>
        </div>
      </div>
      <div class="images">
        <img src="../../../assets/img/rank/rank.png" width="70%" alt="John" />
      </div>
    </div>

    <!-- Top3단상 -->
  </div>
</template>
<script>
export default {
  data: () => ({ valueOne: '', valueTwo: '' }),
};
</script>

<style scoped>
.three {
  display: grid;
}
.profiles {
  display: flex;
  text-align: center;
  height: 50%;
  background-color: white;
}
.second {
  margin-top: 8%;
  z-index: 1;
}
.first {
  display: grid;
  margin-left: 5%;
  margin-right: 5%;
  z-index: 1;
}
.third {
  margin-top: 11%;
  z-index: 1;
}
.images {
  text-align: center;
  margin-top: -260px;
  z-index: 0;
}
.nickname {
  color: rgb(255, 255, 255);
}
</style>
