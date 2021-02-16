<template>
  <div>
    <center id="out">
      <v-card class="mx-auto" max-width="380" id="in">
        <img
          src="https://commitmentbucket.s3.ap-northeast-2.amazonaws.com/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85.png"
          alt="logo"
        />
        <v-card-text>
          <p>환영합니다!</p>
          <p>이메일 인증이 완료되었습니다.</p>
          <p>커밋먼트와 함께 여행을 떠나보세요</p>
        </v-card-text>
        <center>
          <v-btn color="pink" dark dense flat class="ma-2" @click="maingo()">
            로그인 페이지로 이동
          </v-btn>
        </center>
      </v-card>
    </center>
  </div>
</template>

<script>
export default {
  name: "EmailCheck",
  data() {
    return {};
  },
  created() {},
  methods: {
    mainlink() {
      this.$router.push({ name: "Login" });
    },
    maingo() {
      this.$router.push({ name: "Main" });
    },
  },
};
</script>

<style scoped>
#but {
  padding: "100px";
}
img {
  width: 60%;
}
#out {
  width: 100%;
  padding: 8%;
  text-align: center;
}
#in {
  display: inline-block;
  width: 90%;
  height: 50%;
}
</style>
