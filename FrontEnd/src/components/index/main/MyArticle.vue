<template>
  <div>
    <div
      class="mt-4 d-flex justify-center"
      v-for="content in contents"
      :key="content"
    >
      <vs-card>
        <template #title>
          <h3>Pot with a plant</h3>
        </template>
        <template #img>
          <img
            src="https://cdn.vuetifyjs.com/images/parallax/material.jpg"
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
              {{ content }}
            </span>
          </vs-button>
        </template>
      </vs-card>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
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

.filtering {
  display: flex;
  justify-content: space-around;
}
.filtering .vs-button__content {
  width: 20vh !important;
}
</style>
