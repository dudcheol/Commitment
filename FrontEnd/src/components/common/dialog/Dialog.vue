<template>
  <div>
    <vs-dialog
      width="550px"
      not-center
      v-model="alert"
      v-on:close="close"
      not-close
    >
      <template #header>
        <h4 class="not-margin">
          <b>{{ alertTitle }}</b>
        </h4>
      </template>

      <div class="con-content">
        <p>
          {{ alertContent }}
        </p>
      </div>

      <template #footer>
        <div class="con-footer">
          <vs-button @click="close" transparent>
            Ok
          </vs-button>
        </div>
      </template>
    </vs-dialog>
    <vs-dialog
      width="550px"
      not-center
      v-model="confirm"
      v-on:close="close"
      not-close
    >
      <template #header>
        <h4 class="not-margin">
          <b>{{ confirmTitle }}</b>
        </h4>
      </template>

      <div class="con-content">
        <p>
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
    <vs-dialog
      width="300px"
      not-center
      v-model="prompt"
      v-on:close="close"
      not-close
    >
      <template #header>
        <h4 class="not-margin">
          <b>{{ promptTitle }}</b>
        </h4>
      </template>

      <div class="con-content">
        <vs-input v-model="promptInput" placeholder="Name"></vs-input>
      </div>

      <template #footer>
        <div class="con-footer">
          <vs-button @click="promptOk" transparent>
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
  props: [
    'alert',
    'confirm',
    'prompt',
    'alertTitle',
    'alertContent',
    'confirmTitle',
    'confirmContent',
    'promptTitle',
  ],
  data() {
    return {
      promptInput: '',
    };
  },
  methods: {
    close() {
      this.$emit('close');
    },
    confirmOk() {
      this.$emit('confirm-ok');
      this.close();
    },
    promptOk() {
      this.$emit('prompt-ok', { msg: this.promptInput });
      this.close();
      this.promptInput = '';
    },
  },
};
</script>

<style lang="stylus">
getColor(vsColor, alpha = 1)
    unquote("rgba(var(--vs-"+vsColor+"), "+alpha+")")
getVar(var)
    unquote("var(--vs-"+var+")")
.con-footer
  display flex
  align-items center
  justify-content flex-end
  .vs-button
    margin 0px
    .vs-button__content
      padding 10px 30px
    ~ .vs-button
      margin-left 10px
.not-margin
  margin 0px
  font-weight normal
  padding 10px
  padding-bottom 0px
.con-content
  width 100%
  p
    font-size .8rem
    padding 0px 10px
  .vs-checkbox-label
    font-size .8rem
  .vs-input-parent
    width 100%
  .vs-input-content
    margin 10px 0px
    width calc(100%)
    .vs-input
      width 100%
.footer-dialog
  display flex
  align-items center
  justify-content center
  flex-direction column
  width calc(100%)
  .new
    margin 0px
    margin-top 20px
    padding: 0px
    font-size .7rem
    a
      color getColor('primary') !important
      margin-left 6px
      &:hover
        text-decoration underline
  .vs-button
    margin 0px
</style>
