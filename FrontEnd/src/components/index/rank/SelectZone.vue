<template>
  <div>
    <vs-card>
      <template #title>
        <h3 class="text">지역</h3>
        <p class="text" color="green">선택하세요</p>
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
