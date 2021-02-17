<template>
  <v-container>
    <v-row>
      <v-col class="d-flex align-center">
        <v-avatar
          v-if="data.user.profile"
          circle
          size="40"
          style="cursor:pointer"
          @click="goToProfile"
        >
          <img :src="data.user.profile.filePath" />
        </v-avatar>
        <v-avatar
          v-else
          circle
          size="40"
          color="blue-grey"
          class="font-weight-medium display-2"
          style="cursor:pointer"
          @click="goToProfile"
        >
          <v-icon color="white">mdi-emoticon-happy</v-icon>
        </v-avatar>
        <div class="d-flex">
          <h3 class="pl-2 pr-1" @click="goToProfile">
            <span style="cursor:pointer">{{ data.user.nickname }}</span>
          </h3>
          <v-icon v-if="user.email == data.email" color="error" style="cursor:pointer" @click="del"
            >mdi-close-circle</v-icon
          >
        </div>
      </v-col>
    </v-row>
    <v-row>
      <v-col
        class="rounded-xl py-2 px-4 blue-grey lighten-5 ml-14 display-1"
        cols="6"
        md="5"
        lg="4"
        xl="3"
      >
        {{ data.content }}
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  props: ['data'],
  computed: {
    ...mapGetters({ user: ['getUserInfo'] }),
  },
  methods: {
    del() {
      this.$emit('del');
    },
    goToProfile() {
      this.$store.commit('SELECTED_USER_ID', this.data.user.nickname);
      this.$router.push({ name: 'MyPage' });
    },
  },
};
</script>

<style scoped></style>
