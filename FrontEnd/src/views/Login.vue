<template>
  <div class="login_form">
    <vs-card>
      <template #title>
        <h4 class="not-margin"><b>Commmitment</b> 오신것을 환영합니다</h4>
      </template>

      <template #text>
        <div class="con-form" @keyup.enter="confirm">
          <div class="email_input">
          <vs-input v-model="email" placeholder="Email">
            <template #icon>
              @
            </template>
          </vs-input>
          </div>
          <div class="password_input">
          <vs-input
            type="password"
            v-model="pass"
            placeholder="영문, 숫자 혼용 8글자이상"
          >
            <template #icon>
              <i class="bx bxs-lock"></i>
            </template>
          </vs-input>
          </div>
          <div class="flex">
            <vs-checkbox v-model="remember">아이디 저장</vs-checkbox>
            <a href="#">비밀번호를 잊어버리셨나요?</a>
          </div>
        </div>

        <div class="footer-dialog">
          <vs-button block @click="confirm" :loading="loading">
            로그인
          </vs-button>

          <div class="new">아직 처음이신가요? <a @click="signuplink">회원가입</a></div>
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
    signuplink() {
       this.$router.push({ name: 'Signup' })
    }
  },
};
</script>

<style  scoped>

.email_input {
  display: flex;
  justify-content: center;
  margin: 10px 10px;
}

.password_input {
  display: flex;
  justify-content: center;
  margin: 10px 10px;
}

.login_form {
  display: flex;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate( -50% , -50% ) 
  }

/* @media screen and (min-width: 600px)
    width 30vmax } */
        
.not-margin {
  margin: 3px;
  font-weight: normal;
  padding: 10px; 
  display: flex;
  justify-content: center;
  }

.con-form
  { width: 100% 
  }
.con-form  .flex {
    display: flex;
    align-items: center;
    justify-content: space-between;
    }
.con-form .flex a {
      font-size: .8rem;
      opacity: 0.7 }
.con-form .flex a:hover
{
        opacity: 1  }


.vs-checkbox-label
    {font-size: .8rem}
.vs-input-content
    {margin: 10px 10px}
  
.footer-dialog 
  {display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  width: calc(100%);
  }

.new
  { margin: 0px;
    margin-top: 20px;
    padding: 0px;
    font-size: .7rem }

.footer-dialog .new a {
      margin-left: 6px }
.footer-dialog .new a:hover{
        text-decoration: underline}

  .vs-button {

    margin: 0px }

</style>
