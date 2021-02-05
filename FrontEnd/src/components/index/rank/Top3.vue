<template>
  <div class="frame">
    <!-- Top3프로필 -->
    <div class="box">
      <div class="three">
        <div class="profiles justify-center">
          <div class="second">
            <v-avatar size="80">
              <img src="http://news.samsungdisplay.com/wp-content/uploads/2018/08/8.jpg" alt="John" />
            </v-avatar>
            <h3 class="percentage">74.3%</h3>
            <p class="nickname">nickname</p>
            <vs-button
              border
              :active="active == 2"
              @click="active = 2"
              class="like"
            >
              <i class="bx bxs-heart"></i> Like
            </vs-button>
          </div>
          <div class="first">
            <v-avatar size="100">
              <img src="https://www.gohawaii.com/sites/default/files/styles/image_gallery_bg_xl/public/hero-unit-images/12709.jpg?itok=RByMHmpc" alt="John" />
            </v-avatar>
            <h3 class="percentage">82.7%</h3>
            <p class="nickname">nickname</p>
            <vs-button
              border
              :active="active == 2"
              @click="active = 2"
              class="likeMiddle"
            >
              <i class="bx bxs-heart"></i> Like
            </vs-button>
          </div>
          <div class="third">
            <v-avatar size="80">
              <img src="https://images.chosun.com/resizer/nt--G8y3-98W0alOwx6Ws2UqDYI=/464x0/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/V7QZJM2PAG6DDUWOIBWMW3ZHAI.jpg" alt="John" />
            </v-avatar>
            <h3 class="percentage">71.01%</h3>
            <p class="nickname">nickname</p>
            <vs-button
              border
              :active="active == 2"
              @click="active = 2"
              class="like"
            >
              <i class="bx bxs-heart"></i> Like
            </vs-button>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>
<script>
export default {
  data: () => ({ valueOne: '', valueTwo: '' }),
};
</script>

<style scoped>
.frame{
  display: flex;
  justify-content: center;
}
.like{
  margin-top:-10%;
  margin-left:15%;
}
.likeMiddle{
    margin-top:-10%;
    margin-left:20%;
}
.box{
  width: 100%;
  height: 100%;
}
.three {
  display: grid;
}
.profiles {
  display: flex;
  text-align: center;
  height: 50%;
}
.second {
  margin-top: 8%;
  z-index: 1;
}
.first {
  margin-left: 5%;
  margin-right: 5%;
  /* z-index: 1; */
}
.third {
  margin-top: 8%;
  z-index: 1;
}
.nickname {
  color: rgba(0, 0, 0, 0.514);
  margin-top: 2%;
}
.percentage{
  color: rgba(0, 0, 0, 0.589);
}
</style>
