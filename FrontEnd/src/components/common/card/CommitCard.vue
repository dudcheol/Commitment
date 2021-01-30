<template>
  <v-btn class="d-flex justify-start" color="white" elevation="0" large rounded="lg">
    <div class="row">
      <div class="mr-2">
        <vs-avatar circle size="36">
          <img src="https://picsum.photos/200/300" alt="" />
        </vs-avatar>
      </div>
      <div class="mt-auto mb-auto text-start">
        <div>{{ info.username }}</div>
        <div class="text-caption d-inline-block text-truncate">
          {{ info.address }}
        </div>
      </div>
    </div>
  </v-btn>
</template>

<script>
export default {
  props: ['info'],
};
</script>

<style scoped></style>
