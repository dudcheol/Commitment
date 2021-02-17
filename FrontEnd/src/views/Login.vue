<template>
  <div>
    <div>
      <Logo id="logo"></Logo>
      <div id="videoBd">
        <video id="videoBG" poster="../assets/img/login/poster.jpg" autoplay muted loop>
          <source src="../assets/img/login/nightviewseoul.mp4" type="video/mp4" />
        </video>
      </div>
    </div>
    <div class="login_form">
      <vs-card type="3">
        <template #title>
          <div class="d-flex flex-column justify-center align-center pt-2">
            <span class="text-caption">Commit Your Moment</span>
            <span class="text-h2 font-weight-black">Commitment</span>
          </div>
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
            <div class="password_input mb-5">
              <vs-input type="password" v-model="pass" placeholder="영문, 숫자 혼용 8글자이상">
                <template #icon>
                  <i class="bx bxs-lock"></i>
                </template>
              </vs-input>
            </div>

            <div class="google__button mb-4">
              <GoogleLogin
                :params="params"
                :onSuccess="GoogleLoginSuccess"
                :renderParams="renderParams"
                style="border-radius:30px"
              ></GoogleLogin>
            </div>
          </div>

          <div class="footer-dialog">
            <vs-button block @click="confirm" :loading="loading">
              Login
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
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import DialogVue from '../components/common/dialog/Dialog.vue';
import Logo from '../components/login/Logo.vue';
import GoogleLogin from 'vue-google-login';
import GoogleSignInButton from 'vue-google-signin-button-directive';

export default {
  directives: {
    GoogleSignInButton,
  },
  components: {
    DialogVue,
    Logo,
    GoogleLogin,
  },
  computed: {
    ...mapGetters({ user: ['getUserInfo'] }),
  },
  data: () => ({
    window: {
      width: 0,
      height: 0,
    },
    active: true,
    email: '',
    pass: '',
    remember: false,
    alert: false,
    title: '오류',
    content: '로그인에 실패했습니다',
    loading: false,
    params: {
      cliend_id: '265137181932-gh7omk39se04nearqok9pdinleer99ur.apps.googleusercontent.com',
    },
    renderParams: {
      width: 240,
      height: 40,
      longtitle: true,
    },
    clientId: '265137181932-gh7omk39se04nearqok9pdinleer99ur.apps.googleusercontent.com',
  }),
  created() {
    window.addEventListener('resize', this.handleResize);
    this.handleResize();
  },
  destroyed() {
    window.removeEventListener('resize', this.handleResize);
  },
  methods: {
    ...mapActions(['LOGIN', 'GOOGLE_LOGIN']),
    async confirm() {
      this.loading = true;

      const result = await this.LOGIN({
        email: this.email,
        pass: this.pass,
      });
      this.loading = false;
      if (result.data != 'success') {
        this.title = '오류';
        this.content = '로그인에 실패했습니다';
        this.alert = true;
      } else {
        if (result.auth != 1) {
          this.title = '알림';
          this.content = '이메일 인증을 먼저 진행해주세요';
          this.alert = true;
        } else {
          this.$router.push({ name: 'Main' });
        }
      }
      this.email = '';
      this.pass = '';
    },

    signuplink() {
      this.$router.push({ name: 'Signup' });
    },

    handleResize() {
      this.window.width = window.innerWidth;
      this.window.height = window.innerHeight;
    },

    async GoogleLoginSuccess(googleUser) {
      this.loading = true;
      const profile = googleUser.getBasicProfile();
      const result = await this.GOOGLE_LOGIN({
        email: googleUser.getBasicProfile().getEmail(),
        pass: null,
        image: profile.getImageUrl(),
        token: googleUser.getAuthResponse().id_token,
        name: profile.getName(),
      });
      this.loading = false;
      if (!result) {
        this.title = '오류';
        this.content = '로그인에 실패했습니다';
        this.alert = true;
      } else {
        this.email = '';
        this.pass = '';
        this.$router.push({ name: 'Main' });
      }
    },
  },
};
</script>

<style scoped>
.google__button {
  display: flex;
  justify-content: center;
}
.login_form {
  position: fixed;
  top: 10%;
  left: 10%;
}

#logo {
  position: fixed;
  z-index: 1;
  top: 10%;
  right: 8%;
}

#videoBd {
  width: 100vw;
  height: 100vh;
  margin: 0;
}
#videoBG {
  position: fixed;
  z-index: 0;
}
@media (min-aspect-ratio: 16/9) {
  #videoBG {
    width: 100%;
    height: auto;
  }
}

@media (max-aspect-ratio: 16/9) {
  #videoBG {
    width: auto;
    height: 100%;
  }
}
@media (max-width: 1000px) {
  #logo {
    display: none;
  }
}
@media (max-width: 700px) {
  #logo {
    display: none;
  }
  .login_form {
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
  }
  #videoBG {
    display: none;
  }
  #videoBd {
    background: url('../assets/img/login/poster.jpg') no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
  }
}
.before-enter {
  opacity: 0;
  transform: translateY(100px);
  transition: all 1s ease-out;
}

.enter {
  opacity: 1;
  transform: translateY(0px);
}

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

.not-margin {
  margin: 3px;
  font-weight: normal;
  padding: 10px;
  display: flex;
  justify-content: center;
}

.con-form {
  width: 100%;
}
.con-form .flex {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.con-form .flex a {
  font-size: 0.8rem;
  opacity: 0.7;
}
.con-form .flex a:hover {
  opacity: 1;
}

.vs-checkbox-label {
  font-size: 0.8rem;
}
.vs-input-content {
  margin: 10px 10px;
}

.footer-dialog {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  width: calc(100%);
  font-size: 1.5em;
}

.new {
  margin: 0px;
  margin-top: 20px;
  padding: 0px;
  font-size: 0.7rem;
}

.footer-dialog .new a {
  margin-left: 6px;
}
.footer-dialog .new a:hover {
  text-decoration: underline;
}

.vs-button {
  margin: 0px;
}

.vs-card {
  background-color: green !important;
}

.google-signin-button {
  color: white;
  background-color: red;
  height: 50px;
  font-size: 16px;
  border-radius: 10px;
  padding: 10px 20px 25px 20px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
</style>
