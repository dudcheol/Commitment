<template>
  <v-sheet
    block
    class="pa-5 mx-auto d-flex align-center justify-center"
    rounded="xl"
    color="rgb(224,229,231)"
    style="max-width:680px;"
  >
    <div class="font-weight-medium d-flex flex-column">
      <v-icon large class="blue-grey--text text--lighten-3">mdi-{{ icon }}</v-icon>
      <h4 class="blue-grey--text text--lighten-3">{{ text }}</h4>
    </div>
  </v-sheet>
</template>

<script>
export default {
  props: ['text', 'icon'],
};
</script>

<style scoped></style>
