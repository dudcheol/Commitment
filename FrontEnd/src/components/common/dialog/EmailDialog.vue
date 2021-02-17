<template>
  <vs-dialog width="300px" v-model="dialog" not-center>
    <template #header>
      <h4 class="not-margin">
        {{ content.title }}
      </h4>
    </template>

    <div class="con-content">
      {{ content.text }}
    </div>

    <template #footer>
      <div class="con-footer">
        <vs-button @click="yes" transparent>
          넵 !
        </vs-button>
      </div>
    </template>
  </vs-dialog>
</template>

<script>
export default {
  name: 'EmailDialog',
  components: {},
  props: ['content', 'dialog'],
  computed: {},
  created() {},
  watch: {},
  methods: {
    yes() {
      this.$emit('yes');
    },
  },
  data: function() {
    return {};
  },
};
</script>
