<template>
  <v-bottom-navigation grow color="primary" style="position:fixed; z-index:11;">
    <v-btn v-for="(item, index) in items" :key="'footer' + index" :ripple="false" :to="item.route">
      <v-icon>{{ item.icon }}</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
export default {
  data: () => ({
    items: [
      { icon: 'mdi-home', route: '/' },
      { icon: 'mdi-map-marker', route: '/sns' },
      { icon: 'mdi-trophy', route: '/rank' },
      { icon: 'mdi-medal', route: '/badge' },
      { icon: 'mdi-heart', route: '/likes' },
    ],
  }),
};
</script>
