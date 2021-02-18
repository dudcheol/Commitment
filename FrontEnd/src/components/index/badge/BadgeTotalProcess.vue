<template>
  <div>
    <div class="d-flex justify-space-between pa-4">
        <h3>내 뱃지 수</h3>
        <v-tooltip right color="blue-grey" transition="slide-x-reverse-transition">
              <template v-slot:activator="{ on, attrs }">
                <v-icon v-bind="attrs" v-on="on" color="blue-grey lighten-3"
                  >mdi-information</v-icon
                >
              </template>
              <span
                >획득한 뱃지를 볼 수 있어요<br>아래 뱃지를 클릭하면 대표 뱃지로 사용할 수 있어요</span
              >
            </v-tooltip>
    </div>

      <div class="d-flex justify-center align-center pt-3">
        <v-progress-circular
          :rotate="180"
          :size="100"
          :width="15"
          :value=user.badgeCnt/9*100
          color="pink lighten-2"
          background-color="blue-grey lighten-5"
        >
          <span class="text-h3 font-weight-black">{{ user.badgeCnt }}</span>
        </v-progress-circular>
      </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: 'CommitNow',
  data() {
    return {
      interval: {},
      value: 0,
      badgeper: 0,
    };
  },
   computed: {
    ...mapGetters({ user: ["getUserInfo"] }),
  },
  
};
</script>

<style scoped>
/* .message {
  display: flex;
  width: min-content;
}

.vs-avatar-content {
  width: 30px;
  height: 30px;
} */
/* .v-list-item__title {
    font-size: 30px;
} */
/* .totalCollect {
  text-align: center;
} */
</style>
