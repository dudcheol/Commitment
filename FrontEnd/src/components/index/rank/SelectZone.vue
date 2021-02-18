<template>
  <div>
    <vs-card>
      <template #title>
        <div>
          <div class="d-flex justify-space-between align-center">
            <h1 class="text">지역</h1>
            <v-tooltip right color="blue-grey" transition="slide-x-reverse-transition">
              <template v-slot:activator="{ on, attrs }">
                <v-icon v-bind="attrs" v-on="on" color="blue-grey lighten-3"
                  >mdi-information</v-icon
                >
              </template>
              <span
                >지역별 커밋 랭킹에 도전해보세요<br />순위마다 <strong>프로필</strong>과
                <strong>커밋 수</strong>, <strong>닉네임</strong>을 알 수 있어요</span
              >
            </v-tooltip>
          </div>
          <p class="text" color="green">선택하세요</p>
        </div>
      </template>

      <template #text>
        <div class="filters">
          <vs-button class="big_button" gradient @click="SelectArea('total')">
            전국
          </vs-button>
          <div>
            <div class="small_buttons">
              <vs-button gradient @click="SelectArea('seoul')">
                서울
              </vs-button>
              <vs-button color="rgb(59,222,200)" gradient @click="SelectArea('gyeonggi')">
                경기
              </vs-button>
              <vs-button danger gradient @click="SelectArea('ulsan')">
                울산
              </vs-button>
            </div>
            <div class="small_buttons">
              <vs-button warn gradient @click="SelectArea('gangwon')">
                강원
              </vs-button>
              <vs-button color="#7d33ff" gradient @click="SelectArea('gwangju')">
                광주
              </vs-button>
              <vs-button color="rgb(59,222,200)" gradient @click="SelectArea('busan')">
                부산
              </vs-button>
            </div>
          </div>
        </div>
      </template>
    </vs-card>
  </div>
</template>

<script>
export default {
  data: () => ({
    area: '',
  }),
  methods: {
    SelectArea(e) {
      this.area = e;
      this.$emit('area', this.area);
    },
  },
};
</script>

<style scoped>
.text {
  text-align: center;
}
.big_button {
  width: 95%;
  display: flex;
  justify-content: center;
}

.small_buttons {
  display: flex;
  justify-content: center;
}
</style>
