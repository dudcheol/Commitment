<template>
  <v-carousel 
  hide-delimiters
  show-arrows-on-hover
  v-model="model" height="40vh">
    <v-carousel-item
      v-for="(color, i) in colors"
      :key="color"
    >
      <v-sheet
        :color="color"
        height="100%"
        tile
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
          <div class="display-3">
            Slide {{ i + 1 }}
          </div>
        </v-row>
      </v-sheet>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
    data: () => ({
      model: 0,
      colors: [
        'grey',
        'orange',
        'yellow darken-2',
        'green',
        'blue ligthen-2',
      ],
    }),
}
</script>

<style>

</style>