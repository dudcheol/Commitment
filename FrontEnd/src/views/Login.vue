<template>
  <div class="login_form">
    <vs-card>
      <template #title>
        <h4 class="not-margin"><b>Commmitment</b> 오신것을 환영합니다</h4>
      </template>

      <template #text>
        <div class="con-form">
          <vs-input v-model="email" placeholder="Email">
            <template #icon>
              @
            </template>
          </vs-input>
          <vs-input
            type="password"
            v-model="pass"
            placeholder="영문, 숫자 혼용 8글자이상"
          >
            <template #icon>
              <i class="bx bxs-lock"></i>
            </template>
          </vs-input>
          <div class="flex">
            <vs-checkbox v-model="remember">아이디 저장</vs-checkbox>
            <a href="#">비밀번호를 잊어버리셨나요?</a>
          </div>
        </div>

        <div class="footer-dialog">
          <vs-button block @click="confirm" :loading="loading">
            로그인
          </vs-button>

          <div class="new">아직 처음이신가요? <a href="#">회원가입</a></div>
        </div>
      </template>
    </vs-card>
    <DialogVue
      :alert="alert"
      :alertTitle="title"
      :alertContent="content"
      @close="alert = !alert"
    ></DialogVue>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
import DialogVue from '../components/common/dialog/Dialog.vue';

export default {
  components: {
    DialogVue,
  },
  data: () => ({
    active: true,
    email: '',
    pass: '',
    remember: false,
    alert: false,
    title: '오류',
    content: '로그인에 실패했습니다',
    loading: false,
  }),
  methods: {
    ...mapActions(['LOGIN']),
    async confirm() {
      this.loading = true;

      const result = await this.LOGIN({
        email: this.email,
        pass: this.pass,
      });
      this.loading = false;
      if (!result) {
        this.alert = true;
      } else {
        this.$router.push({ name: 'Main' });
      }
    },
  },
};
</script>

<style lang="stylus" scoped>
  getColor(vsColor, alpha = 1)
      unquote("rgba(var(--vs-"+vsColor+"), "+alpha+")")
  getVar(var)
      unquote("var(--vs-"+var+")")
  .login_form
    display flex
    justify-content center
    position: absolute;
    left: 50%
    top: 50%
    transform: translate( -50%, -50% )
.vs-card-content
    width 40vmax
    @media screen and (min-width: 600px)
        width 30vmax

  .not-margin
    margin 0px
    font-weight normal
    padding 10px
  .con-form
    width 100%
    .flex
      display flex
      align-items center
      justify-content space-between
      a
        font-size .8rem
        opacity .7
        &:hover
          opacity 1
    .vs-checkbox-label
      font-size .8rem
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
