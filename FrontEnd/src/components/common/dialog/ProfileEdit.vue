<template>
    <div class="center">
      <vs-button
            icon
            color="#78909C"
            border
            v-bind="attrs"
            v-on="on"
            :active="active == 2"
            @click="active=!active"
        >
            <i class="bx bxs-cog"></i> 
        </vs-button>
      <vs-dialog blur scroll overflow-hidden not-close v-model="active" width="400px">
        <template #header>
          <h3>
            프로필 수정
          </h3>
        </template>
        <div class="con-content">
          <div>
            <v-file-input
              label="File input"
              filled
              prepend-icon="mdi-camera"
            ></v-file-input>
          </div>
          <!-- 
           -->
          <div class="con-form">
            이메일
            <vs-input icon-before v-model="email" placeholder="Email" class="wide">
              <template #icon>
                @
              </template>
            </vs-input>
            비밀번호
            <vs-input type="password" icon-before placeholder="Password">
              <template #icon>
                <i class="bx bx-lock-open-alt"></i>
              </template>
            </vs-input>
            비밀번호 확인
            <vs-input type="password" icon-before placeholder="Password">
              <template #icon>
                <i class="bx bx-lock-open-alt"></i>
              </template>
            </vs-input>
            닉네임
            <vs-input icon-before placeholder="3글자이상">
              <template #icon>
                <i class="bx bx-user"></i>
              </template>
            </vs-input>
            한줄소개
            <vs-input icon-before placeholder="한줄소개">
              <template #icon>
                <i class="bx bx-comment-detail"></i>
              </template>
            </vs-input>
            성별
            <div>
              <vs-select placeholder="Select" v-model="value">
                <vs-option label="Vuesax" value="1">
                  Man
                </vs-option>
                <vs-option label="Vue" value="2">
                  Woman
                </vs-option>
              </vs-select>
            </div>
          생일
            <vs-input type="date" icon-after> </vs-input>
          </div>

          <div class="footer-dialog">
            <vs-button block>
              수정
            </vs-button>
          </div>
          <!--  -->
        </div>
      </vs-dialog>
    </div>
</template>
<script scoped>

  export default {
    components: {  },
    data: () => ({
    active: false,
    value: '',
    email: '',
    password: '',
    value2: '',
    remember: false,
  }),

  }
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
  .wide{
        
        
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
