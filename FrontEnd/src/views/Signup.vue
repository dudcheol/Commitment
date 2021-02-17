<template>
  <div>
    <div id="videoBd">
      <video
        id="videoBG"
        poster="../assets/img/login/poster.jpg"
        autoplay
        muted
        loop
      >
        <source src="../assets/img/login/nightviewseoul.mp4" type="video/mp4" />
      </video>
    </div>
    <div class="login_form">
      <vs-card class="card">
        <template #title>
          <h3 class="not-margin d-flex justify-center">
            <b>Commmitment</b> 회원가입
          </h3>
        </template>
        <!-- 이메일 지역 닉네임 비밀번호 전화번호 나이 성별 이메일 인증여부-->
        <template #text>
          <div class="con-form">
            <div class="d-flex mb-4">
              <div class="d-flex align-center mr-3">
                📧
                <div class="signup_text">이메일</div>
              </div>
              <vs-input v-model="email" placeholder="이메일">
                <template #icon>
                  @
                </template>
              </vs-input>
              <div class="d-flex align-center mr-1">
                <vs-button block @click="idcheck()">
                  check
                </vs-button>
              </div>
            </div>

            <div class="d-flex mb-4">
              <div class="d-flex align-center mr-3">
                🔒
                <div class="signup_text">비밀번호</div>
              </div>
              <vs-input
                type="password"
                v-model="password"
                placeholder="비밀번호"
              >
                <template #icon>
                  <i class="bx bx-lock-open-alt"></i>
                </template>
              </vs-input>
            </div>

            <div class="d-flex mb-4">
              <div class="d-flex align-center mr-3">
                🔒
                <div class="signup_text">비밀번호 확인</div>
              </div>
              <vs-input
                type="password"
                v-model="passwordConfirm"
                placeholder="비밀번호 확인"
              >
                <template #icon>
                  <i class="bx bx-lock-open-alt"></i>
                </template>
              </vs-input>
            </div>

            <div class="d-flex mb-4">
              <div class="d-flex align-center mr-3">
                😀
                <div class="signup_text">닉네임</div>
              </div>
              <div>
                <vs-input
                  v-model="nickname"
                  placeholder="3글자이상 작성해주세요"
                >
                  <template #icon>
                    <i class="bx bx-user"></i>
                  </template>
                </vs-input>
              </div>
              <div class="d-flex align-center mr-1">
                <vs-button block @click="nick()">
                  check
                </vs-button>
              </div>
            </div>

            <div class="d-flex mb-4">
              <div class="d-flex align-center mr-3">
                🏷️
                <div class="signup_text">한줄소개</div>
              </div>
              <vs-input
                v-model="mystory"
                maxlength
                placeholder="나만의 한줄 소개 !"
              >
                <template #icon>
                  <i class="bx bx-comment-detail"></i>
                </template>
              </vs-input>
            </div>

            <div class="d-flex mb-4">
              <div class="d-flex align-center mr-3">
                📞
                <div class="signup_text">전화번호</div>
              </div>
              <vs-input v-model="tel" maxlength="11" placeholder="전화번호">
                <template #icon>
                  <i class="bx bx-phone"></i>
                </template>
              </vs-input>
            </div>

            <div class="d-flex mb-4">
              <div class="d-flex align-center mr-3">
                🧑‍🤝‍🧑
                <div class="signup_text">성별</div>
              </div>
              <div class="d-flex justify-center">
                <vs-select placeholder="당신의 성별은?" v-model="gender">
                  <vs-option label="Man" value="m">
                    Man
                  </vs-option>
                  <vs-option label="Woman" value="w">
                    Woman
                  </vs-option>
                </vs-select>
              </div>
            </div>

            <div class="d-flex mb-4">
              <div class="d-flex align-center mr-3">
                🍂
                <div class="signup_text">나이</div>
              </div>
              <vs-input type="number" v-model="age" />
            </div>
          </div>
          <!-- <input v-model="isTerm" type="checkbox" id="term" />
          <span>약관에 동의합니다</span> -->
          <div class="footer-dialog">
            <vs-button block @click="submit">
              회원가입
            </vs-button>
          </div>
        </template>
      </vs-card>
      <EmailDialog
        @yes="close()"
        :content="dialog.content"
        :dialog="dialog.activation"
      >
      </EmailDialog>
    </div>
  </div>
</template>

<script>
// import axios from 'axios'
import { mapActions } from 'vuex';
import EmailDialog from '../components/common/dialog/EmailDialog.vue';
import { nickNameCheck, emailCheck } from '../api/account';

export default {
  components: {
    EmailDialog,
  },
  methods: {
    ...mapActions(['SIGNUP']),
    submit() {
      if (this.check()) {
        const userData = {
          email: this.email.trim(),
          nickname: this.nickname.trim(),
          pass: this.password.trim(),
          tel: this.tel.trim(),
          mystory: this.mystory,
          gender: this.gender.trim(),
          region: this.region.trim(),
          age: this.age.trim(),
        };
        // 여기 고치기
        const result = this.SIGNUP(userData);
        this.resultsignup = result;
        if (result) {
          this.showDialog(
            '가입에 성공했습니다. 가입하신 메일계정으로 메일이 발송되며, 메일을 확인하셔야 가입절차가 완료됩니다.'
          );
        } else {
          this.showDialog('가입에 실패하였습니다');
        }
      }
    },

    check() {
      let emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      let passRule = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d$@$!%*#?&]{8,}$/;
      let telRule = /^(010[1-9][0-9]{7})$/;
      this.resultsignup = false;
      if (
        this.email &&
        this.nickname &&
        this.password &&
        this.passwordConfirm
      ) {
        if (
          this.email.trim().length == 0 ||
          this.password.trim().length == 0 ||
          this.passwordConfirm.trim().length == 0 ||
          this.nickname.trim().length == 0 ||
          this.mystory.trim().length == 0 ||
          this.tel.trim().length == 0 ||
          this.age.trim().length == 0
        ) {
          this.showDialog('모든 항목을 기입해주세요.');
          return false;
        } else if (!emailRule.test(this.email)) {
          this.showDialog('이메일 형식에 맞게 작성해주세요.');
          return false;
        } else if (!this.emailcheckflag) {
          this.showDialog('이메일 중복확인을 해주세요');
          return false;
        } else if (!this.emailflag) {
          this.showDialog('이미 가입된 이메일 입니다.');
          return false;
        } else if (!passRule.test(this.password)) {
          this.showDialog('비밀번호는 영문/숫자 포함 8자 이상이어야 합니다.');
          return;
        } else if (this.password !== this.passwordConfirm) {
          this.showDialog('비밀번호 입력이 다릅니다. 다시 확인해주세요.');
          return false;
        } else if (this.email.trim().length < 3) {
          this.showDialog('닉네임을 3글자 이상 작성해주세요');
          return false;
        } else if (!this.nickcheckflag) {
          this.showDialog('닉네임 중복확인을 해주세요');
          return false;
        } else if (!this.nickflag) {
          this.showDialog('닉네임이 중복됩니다.');
          return false;
        } else if (!telRule.test(this.tel)) {
          this.showDialog('올바르지 않은 핸드폰 번호 입니다.');
          return false;
        }
        return true;
      }
      this.showDialog('회원가입 양식을 모두 채워주세요.');
      return false;
    },
    nick() {
      if (this.nickname.trim().length == 0) {
        this.showDialog('닉네임을 입력해주세요');
        return;
      }
      nickNameCheck(
        this.nickname,
        (response) => {
          this.nickcheckflag = true;
          if (this.nickname.trim().length < 3) {
            this.nickflag = true;
            this.showDialog('닉네임을 3글자 이상 작성해주세요');
          } else if (response.data.data == 'success') {
            this.nickflag = true;
            this.showDialog('사용 가능한 닉네임입니다.');
          } else {
            this.nickflag = false;
            this.showDialog('닉네임이 중복됩니다.');
          }
        },
        (error) => {
          this.nickflag = false;
          console.log(error);
        }
      );
    },
    idcheck() {
      emailCheck(
        this.email,
        (response) => {
          let emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

          this.emailcheckflag = true;
          if (this.email.trim() == 0) {
            this.emailflag = false;
            this.showDialog('이메일을 입력해주세요');
          } else if (response.data.data == 'success') {
            this.emailflag = true;
            this.showDialog('사용 가능한 이메일입니다.');
          } else if (!emailRule.test(this.email)) {
            this.emailflag = false;
            this.showDialog('이메일 형식에 맞게 작성해주세요.');
          } else {
            this.emailflag = false;
            this.showDialog('이미 가입된 이메일 입니다.');
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    showDialog(message) {
      this.dialog.activation = true;
      this.dialog.content.text = message;
    },
    close() {
      this.dialog.activation = false;
      if (this.resultsignup) {
        this.email = '';
        this.password = '';
        this.passwordConfirm = '';
        this.tel = '';
        this.mystory = '';
        this.gender = 'm';
        this.nickname = '';
        this.$router.push('/login');
      }
    },
  },
  watch: {
    nickname() {
      this.nickcheckflag = false;
    },
    email() {
      this.emailcheckflag = false;
    },
  },

  data() {
    return {
      active: true,
      email: '',
      password: '',
      passwordConfirm: '',
      tel: '',
      mystory: '',
      gender: 'm',
      remember: false,
      region: 'national',
      nickname: '',
      nickflag: false,
      nickcheckflag: false,
      emailflag: false,
      emailcheckflag: false,
      age: '',
      resultsignup: false,
      dialog: {
        content: { title: 'Commitment', text: '', yes: '확인' },
        activation: false,
      },
    };
  },
};
</script>

<style scoped>
/* image가 안들어가짐 */

.login_form {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.vs-input {
  width: 100% !important;
}

.vs-card {
  min-width: 30px;
  max-width: 500px;
}

.vs-card__title {
  margin-top: 10px;
  margin-bottom: 20px;
}

.vs-card-content {
  margin-top: 2px;
  margin-bottom: 10px;
}

.not-margin {
  margin: 0px;
  font-weight: normal;
  padding: 10px;
}

.con-form {
  display: flex;
  flex-direction: column;
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
  margin: 10px 0px;
  width: calc(100%);
}

.vs-input--has-icont {
  background-color: green !important;
}

.footer-dialog {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  margin-top: 10px;
  width: calc(100%);
}

.new {
  margin: 0px;
  margin-top: 20px;
  padding: 0px;
  font-size: 0.7rem;
}
.new a {
  margin-left: 6px;
}

.new a:hover {
  text-decoration: underline;
}

.vs-button {
  margin: 0px;
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

@media (max-width: 700px) {
  .signup_text {
    display: none;
  }

  #videoBG {
    display: none;
  }
  #videoBd {
    background: url('../assets/img/login/poster.jpg') no-repeat center center
      fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
  }
}
</style>
