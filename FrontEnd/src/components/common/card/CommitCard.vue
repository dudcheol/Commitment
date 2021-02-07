<template>
  <v-btn
    class="d-flex justify-start px-3"
    color="white"
    elevation="0"
    large
    rounded="circle"
    :ripple="false"
    block
  >
    <div class="mr-2" v-if="img">
      <vs-avatar circle size="36">
        <img :src="img" alt="" />
      </vs-avatar>
    </div>
    <div class="text-start font-weight-bold">
      <div class="d-block text-truncate">{{ username }}</div>
      <div class="text-caption">
        {{ address }}
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
