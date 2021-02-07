<template>
  <v-sheet
    block
    class="pa-5 mx-auto text-center"
    rounded="xl"
    color="blue-grey lighten-4"
    style="max-width:680px"
  >
    <div class="font-weight-medium">
      <v-icon large class="blue-grey--text text--lighten-2">mdi-{{ icon }}</v-icon>
      <h4 class="blue-grey--text text--lighten-2">{{ text }}</h4>
    </div>
  </v-sheet>
</template>

<script>
export default {
  props: ['text', 'icon'],
};
</script>

<style lang="" scoped></style>
