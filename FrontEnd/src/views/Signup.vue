<template>
  <div class="login_form">
    <vs-card>
      <template #title>
        <h4 class="not-margin"><b>Commmitment</b> 회원가입</h4>
      </template>
        <!-- 이메일 지역 닉네임 비밀번호 전화번호 나이 성별 이메일 인증여부-->
      <template #text>
        <div class="con-form">
          이메일
          <vs-input icon-after v-model="email" placeholder="Email">
            <template #icon>
              @
            </template>
          </vs-input>
          비밀번호
          <vs-input type="password" v-model="password" icon-after placeholder="Password">
            <template #icon>
              <i class="bx bx-lock-open-alt"></i>
            </template>
          </vs-input>
          비밀번호 확인
          <vs-input type="password" v-model="passwordConfirm" icon-after placeholder="Password">
            <template #icon>
              <i class="bx bx-lock-open-alt"></i>
            </template>
          </vs-input>
          닉네임
          <vs-input icon-after v-model="nickname" placeholder="3글자이상">
            <template #icon>
              <i class="bx bx-user"></i>
            </template>
          </vs-input>
          한줄소개
          <vs-input icon-after v-model="mystory" placeholder="한줄소개">
            <template #icon>
              <i class="bx bx-comment-detail"></i>
            </template>
          </vs-input>          
          전화번호
          <vs-input icon-after v-model="tel" placeholder="전화번호">
            <template #icon>
              <i class="bx bx-phone"></i>
            </template>
          </vs-input>
          성별
          <div>
            <vs-select placeholder="Select" v-model="gender">
              <vs-option label="Man" value="man">
                Man
              </vs-option>
              <vs-option label="Woman" value="woman">
                Woman
              </vs-option>
            </vs-select>
          </div>
          생일
          <vs-input type="date" v-model="birth" icon-after> </vs-input>
          나이
          <vs-input type="number" v-model="age" />





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
    @yes="dialog.activation = false"
    :content="dialog.content"
    :dialog="dialog.activation"
  > </EmailDialog>
  </div>
</template>

<script>
// import axios from 'axios'
import { mapActions } from "vuex"
import EmailDialog from '../components/common/dialog/EmailDialog.vue'

export default {

  components: {
    EmailDialog
  },
  methods: {
    ...mapActions(["SIGNUP"]),
    submit() {
      if (this.check()) {
        const userData = {
          email: this.email,
          nickname: this.nickname,
          pass: this.password,
          tel: this.tel,
          mystory: this.mystory,
          gender: this.gender,
          birth: this.birth,
          region: this.region,
          age: this.age,
        };
        // 여기 고치기
        const result = this.SIGNUP(userData);
        if (result) {
          this.showDialog('가입에 성공했습니다')
        } else {
          console.log('가입에 실패했습니다')
        }
        // this.SIGNUP(params);
        // axios
        //   .post('https://i4a308.p.ssafy.io:8080/account/signup', params)
        //   .then((res) => {
        //     console.log(res);
        //     this.SIGNUP(params);
        //     // dialog 보여주기

        //     this.showDialog('가입에 성공했뜸');
        //     // this.$router.push({
        //     //   name: 'EmailCheck',
        //     //   params: { email: this.email },
        //     // });
        //   })
        //   .catch((err) => {
        //     console.error(err);
        //     console.log('가입에 실패하셨습니다.');
        //   });
      } else {
        console.log("항목중에서 하나 실패했으니깐 조심해보자");
      }
    },

     check() {
      let emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      let passRule = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d$@$!%*#?&]{8,}$/;
      if (
        this.email &&
        this.nickname &&
        this.password &&
        this.passwordConfirm
      ) {
        if (!emailRule.test(this.email)) {
          console.log('이메일 형식에 맞게 작성해주세요.');
          return false;
        }
        if (!passRule.test(this.password)) {
          console.log('비밀번호는 영문/숫자 포함 8자 이상이어야 합니다.');
          return;
        }
        if (this.password !== this.passwordConfirm) {
          console.log('비밀번호 입력이 다릅니다. 다시 확인해주세요.');
          return false;
        }
        // if (!this.isTerm) {
        //   console.log('약관에 동의하셔야 합니다.');
        //   return false;
        // }
        return true;
      }
      console.log('회원가입 양식을 모두 채워주세요.');
      return false;
    },
    showDialog(message) {
      this.dialog.activation = true;
      this.dialog.content.text = message;
    }

  },

    data () {
    return {
        active: true,
        email: '',
        password: '',
        passwordConfirm: '',
        tel: '',
        mystory: '',
        gender: '',
        birth: '',
        remember: false,
        region: 'national',
        nickname: '',
        age: '',
        dialog: {
          content: { title: '할롱', text: '', yes: '확인'},
          activation: false,
        },
    }
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
.vs-card__title
    margin-top 10px
    margin-bottom 20px
.vs-card-content
    margin-top 2px
    margin-bottom 10px
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
    margin-top 10px
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
