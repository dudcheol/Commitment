<template>
  <div class="center">
    <vs-button
      icon
      color="#78909C"
      border
      v-bind="attrs"
      v-on="on"
      :active="active == 2"
      @click="active = !active"
    >
      <i class="bx bxs-cog"></i>
    </vs-button>
    <vs-dialog blur scroll overflow-hidden not-close v-model="active" width="400px">
      <template #header>
        <h3>
          회원정보 수정
        </h3>
      </template>
      <div class="con-content">
        <div class="con-form">
          📧 이메일
          <vs-input icon-before v-model="email" placeholder="Email" readonly>
            <template #icon>
              @
            </template>
          </vs-input>
          🔒 비밀번호
          <vs-input type="password" icon-before placeholder="Password" v-model="password">
            <template #icon>
              <i class="bx bx-lock-open-alt"></i>
            </template>
          </vs-input>
          🔒 비밀번호 확인
          <vs-input type="password" icon-before placeholder="Password" v-model="passwordConfirm">
            <template #icon>
              <i class="bx bx-lock-open-alt"></i>
            </template>
          </vs-input>
          😀 닉네임
          <vs-input icon-before placeholder="3글자이상" v-model="nickname">
            <template #icon>
              <i class="bx bx-user"></i>
            </template>
          </vs-input>
          <div class="d-flex align-center mr-1">
            <vs-button @click="nick">
              check
            </vs-button>
          </div>
          🏷️ 한줄소개
          <vs-input icon-before placeholder="한줄소개" v-model="mystory">
            <template #icon>
              <i class="bx bx-comment-detail"></i>
            </template>
          </vs-input>
          📞 전화번호
          <vs-input icon-before v-model="tel">
            <template #icon>
              <i class="bx bx-phone"></i>
            </template>
          </vs-input>
        </div>

        <div class="footer-dialog">
          <vs-button block @click="submit">
            수정
          </vs-button>
        </div>
        <!--  -->
      </div>
    </vs-dialog>
  </div>
</template>
<script scoped>
import { mapGetters, mapActions } from 'vuex';
import { searchUserByEmail } from '../../../api/account';
import { nickNameCheck } from '../../../api/account';

export default {
  components: {},
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
      region: '',
      nickname: '',
      nickflag: false,
      nickcheckflag: false,
      emailflag: false,
      emailcheckflag: false,
      age: '',
      resultsignup: false,
      userInfo: [],
      dialog: {
        content: { title: 'Commitment', text: '', yes: '확인' },
        activation: false,
      },
    };
  },
  watch: {
    nickname() {
      this.nickcheckflag = false;
    },
    email() {
      this.emailcheckflag = false;
    },
  },
  computed: {
    ...mapGetters({
      user: ['getUserInfo'],
      userId: ['getSelectedUserId'],
    }),
  },
  created() {
    searchUserByEmail(
      { keyword: this.user.email },
      (response) => {
        const profile = response.data.content;
        const item = profile[0];
        this.userInfo.push(item);
        this.email = item.email;
        this.nickname = item.nickname;
        this.region = item.region_name;
        this.age = item.age;
        this.mystory = item.mystory;
      },
      (error) => {
        console.log('edit에러' + error);
      }
    );
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
          this.showDialog('수정에 성공하셨습니다.');
        } else {
          this.showDialog('수정에 실패하였습니다');
        }
      }
    },
    check() {
      let passRule = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d$@$!%*#?&]{8,}$/;
      let telRule = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;

      this.resultsignup = false;
      if (this.email && this.nickname && this.password && this.passwordConfirm) {
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
          } else if (
            response.data.data == 'success' ||
            this.userInfo[0].nickname == this.nickname
          ) {
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
    showDialog(message) {
      this.dialog.activation = true;
      this.dialog.content.text = message;
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
  .con-form{
    width 100%

  }
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
