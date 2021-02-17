<template>
  <div>
    <vs-dialog width="550px" not-center v-model="confirm" v-on:close="close" not-close>
      <template #header>
        <h4 class="not-margin">
          <b>{{ confirmTitle }}</b>
        </h4>
      </template>

      <div class="con-content">
        <div class="d-flex justify-center">
          <component
            :is="mapType"
            :size="5"
            :borderRadius="5"
            :spacing="1"
            :datas="datas"
            :key="'commit' + new Date()"
          ></component>
        </div>
        <p class="pt-4 text-center">
          {{ confirmContent }}
        </p>
      </div>

      <template #footer>
        <div class="con-footer">
          <vs-button @click="confirmOk" transparent>
            Ok
          </vs-button>
          <vs-button @click="close" dark transparent>
            Cancel
          </vs-button>
        </div>
      </template>
    </vs-dialog>
  </div>
</template>

<script>
export default {
  props: ['confirm', 'confirmTitle', 'confirmContent', 'region', 'datas'],
  watch: {
    region: {
      immediate: true,
      handler() {
        this.renderMap();
      },
    },
  },
  data() {
    return {
      mapType: null,
    };
  },
  computed: {
    loader() {
      return () =>
        import(
          `../../../components/common/map/Map${this.region.replace(/\b[a-z]/, (letter) =>
            letter.toUpperCase()
          )}`
        );
    },
  },
  methods: {
    close() {
      this.$emit('close');
    },
    confirmOk() {
      this.$emit('confirm-ok');
      this.close();
    },
    renderMap() {
      this.loader()
        .then(() => {
          this.mapType = () => this.loader();
        })
        .catch(() => {});
    },
  },
};
</script>

<style lang="" scoped></style>
