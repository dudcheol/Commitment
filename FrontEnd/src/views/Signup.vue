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
        지역
        <div>
        <vs-select placeholder="Select" v-model="region">
        <vs-option label="서울" value="1">
          서울
        </vs-option>
        <vs-option label="경기도" value="2">
          경기도
        </vs-option>
        <vs-option label="광주" value="3">
          광주
        </vs-option>
        <vs-option label="울산" value="4">
          울산
        </vs-option>
        <vs-option label="부산" value="5">
          부산
        </vs-option>
        <vs-option label="강원도" value="6">
          강원도
        </vs-option>
      </vs-select>
      </div>

        </div>

        <div class="footer-dialog">
          <vs-button block>
            회원가입
          </vs-button>
        </div>
      </template>
    </vs-card>
  </div>
</template>

<script>
// import axios from 'axios'

export default {
  data () {
    return {
      signupdata: {
        active: true,
        email: '',
        password: '',
        passwordConfirm: '',
        tel: '',
        mystory: '',
        gender: '',
        birth: '',
        value2: '',
        remember: false,
        region: '',
        nickname: '',
      },

      
    }
  },

  methods: {

  }
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
