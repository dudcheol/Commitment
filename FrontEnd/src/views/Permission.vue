<template>
  <v-container fluid fill-height class="blue-grey lighten-5">
    <v-row justify="center" align="center" fill-height>
      <v-col class="d-flex justify-center">
        <v-sheet flat rounded="xl" width="500" max-width="500" class="pa-5">
          <div class="text-center">
            <div class="text-left">
              <h1><v-icon x-large color="error">mdi-alert-circle</v-icon>위치정보가 필요합니다</h1>
            </div>
            <br />
            <h3>사용중이신 기기가 위치정보를 사용할 수 없거나</h3>
            <h3>위치 권한을 차단하셨는지 확인해주세요</h3>
            <h5 class="text-caption">[설정 - 위치] 에서 확인할 수 있습니다</h5>
          </div>
          <br />
          <div class="d-flex justify-center">
            <vs-button @click="goMain" flat size="large" class="font-weight-black"
              >다시 시도</vs-button
            >
          </div>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  created() {},
  methods: {
    goMain() {
      this.$router.replace('/');
    },
  },
};
</script>

<style socped></style>
