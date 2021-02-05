<template>
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
          54
        </span>
      </vs-button>
    </template>
  </vs-card>
</template>

<script>
export default {
  name: 'MainPageCard',
};
</script>

<style scoped></style>
