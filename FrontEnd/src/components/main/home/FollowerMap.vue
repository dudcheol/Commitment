<template>
  <v-sheet class="mx-auto" max-width="800" color="transparent">
    <v-slide-group v-model="model" class="" active-class="success" show-arrows>
      <v-slide-item v-for="n in 15" :key="n" v-slot="{ active, toggle }">
        <v-card
          :color="active ? undefined : 'grey lighten-1'"
          class="ma-2"
          height="200"
          width="100"
          @click="toggle"
        >
          <v-row class="fill-height" align="center" justify="center">
            <v-scale-transition>
              <v-icon
                v-if="active"
                color="white"
                size="48"
                v-text="'mdi-close-circle-outline'"
              ></v-icon>
            </v-scale-transition>
          </v-row>
        </v-card>
      </v-slide-item>
    </v-slide-group>
  </v-sheet>
</template>

<script>
export default {
  data: () => ({
    model: 0,
    colors: ['grey', 'orange', 'yellow darken-2', 'green', 'blue ligthen-2'],
  }),
};
</script>

<style></style>
