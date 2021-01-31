<template>
  <v-btn
    class="d-flex justify-start"
    color="white"
    elevation="0"
    large
    rounded="lg"
    :ripple="false"
  >
    <div class="row">
      <div class="mr-2" v-if="img">
        <vs-avatar circle size="36">
          <img :src="img" alt="" />
        </vs-avatar>
      </div>
      <div class="mt-auto mb-auto text-start">
        <div>{{ username }}</div>
        <div class="text-caption d-inline-block text-truncate">
          {{ address }}
        </div>
      </div>
    </div>
  </v-btn>
</template>

<script>
export default {
  props: ['username', 'address', 'img'],
};
</script>

<style scoped></style>
