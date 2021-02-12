<template>
<div>
  <div>
    <!-- <v-img v-scrollanimation
      :width="window.width"
      src="../assets/img/login/koreatower.jpg"
    ></v-img> -->
    <Logo id="logo"></Logo>
    
    <div id="videoBd">
    <video id="videoBG" poster="../assets/img/login/poster.jpg" autoplay muted loop>
      <source src="../assets/img/login/nightviewseoul.mp4" type="video/mp4">
    </video>
    </div>
  </div>
  <div class="login_form">
    <vs-card>
      <template #title>
        <h4 class="not-margin">Welcome to Commitment</h4>
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
          <!-- <div class="flex">
            <vs-checkbox v-model="remember">아이디 저장</vs-checkbox>
            <a href="#">비밀번호를 잊어버리셨나요?</a>
          </div> -->
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
</div>
</template>

<script>
import { mapActions } from 'vuex';
import DialogVue from '../components/common/dialog/Dialog.vue';
import Logo from '../components/login/Logo.vue'

export default {
  components: {
    DialogVue, Logo
  },
  data: () => ({
    window: {
            width: 0,
            height: 0
      },
    active: true,
    email: '',
    pass: '',
    remember: false,
    alert: false,
    title: '오류',
    content: '로그인에 실패했습니다',
    loading: false,
  }),
  created() {
        window.addEventListener('resize', this.handleResize);
        this.handleResize();
    },
  destroyed() {
      window.removeEventListener('resize', this.handleResize);
  },
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
    },
    
    handleResize() {
            this.window.width = window.innerWidth;
            this.window.height = window.innerHeight;
        }
  },
};
</script>


<style scoped>
#logo {
  position: fixed;
  z-index: 1;
  top: 10%;
  right: 10%;
}

#videoBd  {
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

@media (max-width: 767px) {
      #videoBG {
          display: none;
      }
      #videoBd {
        background: url('../assets/img/login/poster.jpg') !important;
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

.login_form {
  position: fixed;
  top: 10%;
  left: 10%;
  
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

.vs-card {
  background-color: green !important
}

</style>