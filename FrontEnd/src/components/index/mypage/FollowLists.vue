<template>
  <div class="follow">
    <div class="row">
      <div class="mr-2" v-if="img">
        <vs-avatar circle size="36">
          <img :src="img" alt="" />
        </vs-avatar>
      </div>
      <div class="mt-auto mb-auto text-start">
        <div>{{ nickname }}</div>
        <div class="text-caption d-inline-block text-truncate">
          {{ intro }}
        </div>
      </div>
      <v-spacer></v-spacer>
      <vs-button
        flat
        :active="active == 0"
        @click="active = 0"
      >
        팔로우
      </vs-button>
    </div>
  </div>
</template>

<script>
export default {
  props: ['nickname', 'intro', 'img'],
};
</script>

<style scoped>
.follow{
  margin-left: 3%;
  margin-top: 3%;
}  
</style>
