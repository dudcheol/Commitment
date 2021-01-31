<template>
  <div class="d-flex justify-center" @click="goToMyPage">
    <vs-card>
      <template #title>
        <h3 class="text-center">헬로우</h3>
      </template>
      <template #img>
        <img
          src="https://post-phinf.pstatic.net/MjAxOTA2MDdfMjc1/MDAxNTU5ODg3NDE5ODg1.KvvxxigkXBP7Pj0bC954MZYWUsFio34hEK6junONCm0g.05M1GyB0htqPHRrPLqLusp4fCdh0ACQwGTN5IuMfgpMg.JPEG/g2.jpg?type=w1200"
          alt=""
        />
      </template>
      <template #text>
        <div class="d-flex justify-center">
          잋츠미
        </div>
        <div class="profile_buttons">
          <vs-button circle relief>
            <i class="bx bx-user"></i>
          </vs-button>
          <vs-button relief>
            <i class="bx bx-stats"></i>
          </vs-button>
          <vs-button relief>
            <i class="bx bx-badge"></i>
          </vs-button>
        </div>
      </template>
      <template #interactions> </template>
    </vs-card>
  </div>
</template>

<script>
export default {
  data: () => ({}),
  methods: {
    goToMyPage() {
      console.log('%cIndex.vue line:5', 'color: #007acc;');
      this.$router.push({ name: 'MyPage' });
    },
  },
};
</script>

<style scoped>
.vs-card__title {
  display: flex;
  justify-content: center;
}
.profile {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.profile_buttons {
  display: flex;
  justify-content: center;
}
</style>
