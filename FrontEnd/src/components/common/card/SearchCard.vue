<template>
  <v-btn
    class="d-flex justify-start px-3"
    color="white"
    elevation="0"
    large
    :ripple="false"
    block
    @click="clicked"
  >
    <div class="mr-2" v-if="!onlyAddress">
      <v-avatar circle size="36" v-if="img">
        <img :src="img.filePath" />
      </v-avatar>
      <v-avatar
        v-else
        circle
        size="36"
        color="blue-grey"
        class="font-weight-medium display-2"
        ><v-icon color="white">mdi-emoticon-happy</v-icon></v-avatar
      >
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
  props: ["username", "address", "img", "only-address"],
  methods: {
    clicked() {
      this.$emit("click");
    },
  },
};
</script>

<style scoped>
.theme--light.v-btn:hover::before {
  background-color: lightblue !important;
}
</style>
