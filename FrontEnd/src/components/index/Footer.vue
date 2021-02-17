<template>
  <v-bottom-navigation :value="value" color="primary" grow style="position:fixed; z-index:11;">
    <v-btn v-for="(item, index) in items" :key="'footer' + index" :ripple="false" :to="item.route">
      <v-icon>{{ item.icon }}</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
export default {
  data: () => ({
    value: 1,
    items: [
      { icon: 'mdi-home', route: '/' },
      { icon: 'mdi-map-marker', route: '/sns' },
      { icon: 'mdi-medal', route: '/rank' },
      { icon: 'mdi-heart', route: '/likes' },
    ],
  }),
};
</script>
