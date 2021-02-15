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
        <h2 class="not-margin">
          {{ alertTitle }}
        </h2>
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
        <h3 class="not-margin">
          <b>{{ confirmTitle }}</b>
        </h3>
      </template>
      <template>
        <center>
        <div class="con-content">
          <h2 style="color:red">
            {{ badgeTitle[badgename] }}
          </h2>
          <br>
          <img :src="path" id="badgeimg"  width="180px" height="180px" style="margin:20px"/>
          <br>
          <h4>
            {{ confirmContent }}
          </h4>
        </div>
        </center>
      </template>
      <template #footer>
        <div class="con-footer">
          <vs-button @click="close" transparent>
            확인
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
          <vs-button @click="close" transparent>
            확인
          </vs-button>
        </div>
      </template>
    </vs-dialog>
  </div>
</template>

<script>
export default {
  props: [
    "alert",
    "confirm",
    "prompt",
    "alertTitle",
    "alertContent",
    "confirmTitle",
    "confirmContent",
    "promptTitle",
    "path",
    "badgename",
  ],
  data() {
    return {
      promptInput: "",
      badgeTitle:{
        "first_commit":"첫 커밋",
        "first_following":"첫 팔로잉",
        "total_commit":"전국 커밋",
        "seoul":"서울 홀릭",
        "gangwondo":"강원도 홀릭",
        "busan":"부산 홀릭",
        "gyeonggido":"경기도 홀릭",
        "ulsan":"울산 홀릭",
        "dokdo":"독도 홀릭",

      }
    };
  },
  created() {},
  methods: {
    close() {
      this.$emit("close");
    },
    confirmOk() {
      this.$emit("confirm-ok");
      this.close();
    },
    promptOk() {
      this.$emit("prompt-ok", { msg: this.promptInput });
      this.close();
      this.promptInput = "";
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
<style scoped>
#badgeimg{
     
      margin:"20px";
    }
</style>