<template>
  <div class="d-flex justify-center">
    <v-card rounded="xl" class="px-3" max-width="680" width="680" elevation="0">
      <v-container class="my-2">
        <v-row>
          <v-col>
            <h4>
              <v-icon class="mr-1" color="primary">mdi-crosshairs-gps</v-icon
              >{{ address ? address : '위치를 찾는 중...' }}
            </h4>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <div class="d-flex flex-row">
              <div class="flex-grow-0" @click="goToProfile" style="cursor:pointer">
                <v-avatar v-if="user.profile" circle size="40">
                  <img :src="user.profile.filePath" />
                </v-avatar>
                <v-avatar
                  v-else
                  circle
                  size="40"
                  color="blue-grey"
                  class="font-weight-medium display-2"
                >
                  <v-icon color="white">mdi-emoticon-happy</v-icon>
                </v-avatar>
              </div>
              <div class="flex-grow-1 ml-2">
                <v-chip
                  large
                  style="height:40px; width:100%; cursor:pointer"
                  @click="click"
                  color="blue-grey lighten-5"
                  text-color="blue-grey darken-2"
                  :ripple="false"
                  depressed
                >
                  {{ user.nickname }}님, 여기는 어떤 곳인가요?
                </v-chip>
              </div>
            </div>
          </v-col>
        </v-row>
      </v-container>
      <v-overlay absolute :value="totalTime != 0 || !latlng" color="blue-grey lighten-3">
        <v-sheet color="white" class="blue-grey--text pa-3" rounded="lg" elevation="3">
          <span v-if="latlng">
            <v-icon color="blue-grey">mdi-lock</v-icon>
            <strong>{{ min }}:{{ sec }}</strong> 후에 커밋할 수 있어요</span
          >
          <span v-else
            ><v-progress-circular indeterminate color="blue-grey"></v-progress-circular></span
        ></v-sheet>
      </v-overlay>
    </v-card>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  computed: {
    ...mapGetters({
      latlng: ['getCurrentLatlng'],
      address: ['getCurrentAddress'],
      user: ['getUserInfo'],
      totalTime: ['getTotalTime'],
      minutes: ['getMinutes'],
      seconds: ['getSeconds'],
    }),
    min() {
      return (this.minutes < 10 ? '0' : '') + this.minutes;
    },
    sec() {
      return (this.seconds < 10 ? '0' : '') + this.seconds;
    },
  },
  methods: {
    click() {
      this.$emit('add-commit');
    },
    goToProfile() {
      this.$store.commit('SELECTED_USER_ID', this.user.nickname);
      this.$router.push({ name: 'MyPage' });
    },
  },
};
</script>

<style scoped>
.my_location {
  display: flex;
  align-items: center;
}
.commit_input {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  align-content: center;
}

.buttons {
  display: flex;
  flex-direction: row;
}

.commit_input .vs-input {
  width: 50vh;
}

@media (max-width: 450px) {
  .commit_input .vs-input {
    width: 25vh;
  }
}
</style>
