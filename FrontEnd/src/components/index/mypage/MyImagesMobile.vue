<template>
  <v-row>
    <v-col v-for="item in boardList" :key="item" class="d-flex child-flex" cols="4">
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
import { searchUserByNickname } from '../../../api/account';
import { timelineInfo } from '../../../api/timeline';
export default {
  data() {
    return {
      boardList: [],
      email: '',
    };
  },
  created() {
    searchUserByNickname(
      { keyword: this.userId },
      (response) => {
        const content = response.data.content[0];
        this.email = content.email;
        console.log('email', this.email);
        timelineInfo(
          this.email,
          (response) => {
            const res = response.data;
            console.log('res', res);
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
  methods: {
    moveToDetail(data) {
      console.log('이동할 게시글 id ', data);
      this.$router.push({ name: 'Detail', params: { id: data } });
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
