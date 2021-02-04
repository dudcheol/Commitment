<template>
  <v-list>
    <v-list-item>
      <v-list-item-avatar width="5px">
        <vs-avatar>
          <i class="bx bxs-hot"></i>
        </vs-avatar>
      </v-list-item-avatar>
      <v-list-item-content>
        <v-list-item-title>
          뱃지 수집률
        </v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <v-list-item>
      <div class="totalCollect mx-auto">
        <v-progress-circular
          :rotate="180"
          :size="100"
          :width="15"
          :value="70"
          color="pink"
        >
          70
        </v-progress-circular>
      </div>
    </v-list-item>
  </v-list>
</template>

<script>
export default {
  name: 'CommitNow',
  data() {
    return {
      interval: {},
      value: 0,
    };
  },
};
</script>

<style scoped>
.message {
  display: flex;
  width: min-content;
}

.vs-avatar-content {
  width: 30px;
  height: 30px;
}
/* .v-list-item__title {
    font-size: 30px;
} */
.totalCollect {
  text-align: center;
}
</style>
