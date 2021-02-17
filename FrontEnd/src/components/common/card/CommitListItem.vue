<template>
  <div style="max-height:200px;">
    <div
      v-if="data.board.length"
      class="d-flex flex-column"
      @click="goToDetail"
      style="cursor:pointer"
    >
      <div class="white rounded-lg px-3 py-2">
        <v-icon size="30" color="primary" class="pr-1">mdi-pencil-circle</v-icon>
        <strong>{{ data.address }}</strong
        >에서 커밋
      </div>
      <div class="d-flex justify-space-between rounded-lg pa-4 blue-grey lighten-4">
        <h3>
          {{
            data.board[0].content.length > textMaxSize
              ? data.board[0].content.substring(0, textMaxSize - 1) + '..'
              : data.board[0].content
          }}
        </h3>
        <div v-if="data.board[0].image.length">
          <v-img
            style="border-radius:10px"
            height="80"
            width="80"
            :src="data.board[0].image[0].filePath"
          ></v-img>
        </div>
      </div>
    </div>
    <div v-else class="white rounded-lg px-3 py-2" @click="showWriteDialog">
      <v-icon size="30" color="primary" class="pr-1">mdi-pencil-circle-outline</v-icon>
      <strong>{{ data.address }}</strong
      >에서 커밋
    </div>
  </div>
</template>

<script>
export default {
  props: ['data'],
  data() {
    return {
      textMaxSize: 30,
    };
  },
  methods: {
    goToDetail() {
      this.$store.commit('SELECTED_BOARD_ID', this.data.board[0].id);
      this.$router.push({ name: 'Detail' });
    },
    showWriteDialog() {},
  },
};
</script>

<style scoped></style>
