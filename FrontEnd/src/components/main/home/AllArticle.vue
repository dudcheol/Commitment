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

    <div class="my-4" v-for="content in contents" :key="content">
      <main-card></main-card>
      <!-- <vs-card>
        <template #title>
          <h3>Pot with a plant</h3>
        </template>
        <template #img>
          <img
            src="https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/22QT/image/EW3X6DmVUUWRGYtbepS674avKcM.jpg"
            alt=""
          />
        </template>
        <template #text>
          <p>
            Lorem ipsum dolor sit amet consectetur, adipisicing elit.
          </p>
        </template>
        <template #interactions>
          <vs-button danger icon>
            <i class="bx bx-heart"></i>
          </vs-button>
          <vs-button class="btn-chat" shadow primary>
            <i class="bx bx-chat"></i>
            <span class="span">
              54
            </span>
          </vs-button>
        </template>
      </vs-card> -->
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

<style>
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
