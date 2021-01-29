<template>
  <div>
    <div class="filtering">
      <vs-button-group>
        <vs-button flat icon>
          ALL
        </vs-button>
        <vs-button flat icon>
          ME
        </vs-button>
        <vs-button flat icon>
          FOLLOW
        </vs-button>
      </vs-button-group>
      <!-- <v-sheet class="pa-3 " rounded="xl">
        <div>
          <h3>피드 필터링</h3>
        </div>
      </v-sheet> -->
    </div>

    <div class="mt-4" v-for="content in contents" :key="content">
      <main-card></main-card>
    </div>
  </div>
</template>

<script>
import MainCard from '../../common/card/MainCard.vue';

export default {
  components: { MainCard },

  data: () => ({
    contents: ['1', '2', '3', '4', '5'],
  }),
};
</script>

<style scoped>
.vs-card {
  max-width: 50vh !important;
  max-height: 50vh !important;
}

/* .filtering {
  display: flex;
  justify-content: space-around;
}
.filtering .vs-button__content {
  width: 20vh !important;
} */
</style>
