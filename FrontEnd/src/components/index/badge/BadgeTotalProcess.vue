<template>
  <div>
    <div class="d-flex justify-center align-center pb-2">
        <h3><i class="bx bxs-hot pr-1"></i>뱃지 수집률</h3>
    </div>

      <div class="totalCollect mx-auto">
        <v-progress-circular
          :rotate="180"
          :size="100"
          :width="15"
          :value=user.badgeCnt/9*100
          color="pink"
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
.message {
  display: flex;
  width: min-content;
}

.vs-avatar-content {
  width: 30px;
  height: 30px;
}
/* .v-list-item__title {
    font-size: 30px;
} */
.totalCollect {
  text-align: center;
}
</style>
