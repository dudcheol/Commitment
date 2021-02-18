<template>
  <v-row>
    <v-col
      v-for="(item, index) in boardList"
      :key="'mbimg' + index"
      class="d-flex child-flex"
      cols="4"
    >
      <v-img
        :src="item.image[0].filePath"
        :lazy-src="item.image[0].filePath"
        aspect-ratio="1"
        class="grey lighten-2"
        @click="moveToDetail(item.id)"
      >
        <template v-slot:placeholder>
          <v-row class="fill-height ma-0" align="center" justify="center">
            <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
          </v-row>
        </template>
      </v-img>
    </v-col>
  </v-row>
</template>

<script scoped>
import { mapGetters } from 'vuex';
import { getUserInfoByNickname } from '../../../api/account';
import { timelineInfo } from '../../../api/timeline';
export default {
  data() {
    return {
      boardList: [],
      email: '',
    };
  },
  activated() {
    this.boardList = [];
    getUserInfoByNickname(
      this.userId,
      (response) => {
        const content = response.data;
        this.email = content.user.email;
        timelineInfo(
          this.email,
          (response) => {
            const res = response.data;
            for (let i = 0; i < res.length; i++) {
              const item = res[i];
              if (item.image[0] == null) {
                continue;
              }
              this.boardList.push(item);
            }
          },
          (error) => {
            console.log('timeline에러' + error);
          }
        );
      },
      (error) => {
        console.log('timeMobile에러' + error);
      }
    );
  },
  deactivated() {
    this.boardList = [];
  },
  methods: {
    moveToDetail(data) {
      this.$store.commit('SELECTED_BOARD_ID', data);
      this.$router.push({ name: 'Detail' });
    },
  },
  computed: {
    ...mapGetters({
      userId: ['getSelectedUserId'],
    }),
  },
};
</script>

<style scoped></style>
