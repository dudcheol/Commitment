<template>
  <div class="user" id="join">
    <div class="wrapC table">
      <div class="middle">
        <v-container>
          <h1>{{ msg }}</h1>
          <v-row class="mb-2">
            <v-col>
              <v-progress-linear
                :active="loading"
                color="primary accent-4"
                indeterminate
                rounded
                height="6"
              ></v-progress-linear>
            </v-col>
          </v-row>
          <v-row no-gutters v-show="!loading">
            <v-col cols="9">
              <v-text-field
                v-model="verifyNum"
                solo
                :label="inputMsg"
                outlined
                dense
                flat
                :error="error"
              ></v-text-field>
            </v-col>
            <v-col cols="3">
              <v-btn
                :disabled="!verifyNum"
                color="primary"
                height="40px"
                @click="verify"
                :loading="loading2"
                flat
                >인증</v-btn
              >
            </v-col>
          </v-row>
        </v-container>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
// import constants from '@/lib/constants';
import { mapActions } from 'vuex';

export default {
  data() {
    return {
      msg: '인증 메일을 발송중입니다. 잠시만 기다려주세요.',
      loading: true,
      loading2: false,
      verifyNum: '',
      error: false,
      inputMsg: '인증번호 입력',
    };
  },
  created() {
    console.log('%cMailCheck.vue line:65 this.$route.params.email', 'color: #007acc;', this.$route.params.email);
    axios
      .get('https://i4a308.p.ssafy.io:8080/account/smtp?email=' + this.$route.params.email)
      .then((res) => {
        console.log(res);
        this.msg = '인증 메일이 발송되었습니다! 이메일을 확인해주세요.';
        this.loading = false;
      })
      .catch((err) => {
        console.error(err);
      });
  },
  methods: {
    ...mapActions(['SET_USER', 'SIGNUP']),
    verify() {
      this.loading2 = true;
      axios
        .get(
        //  'https://i4a308.p.ssafy.io:8080/signUpConfirm?authKey=' + `${this.verifyNum}` + "&email=" + `${this.$route.params.email}`
        
        )
        .then((res) => {
          console.log(res);
          alert('이메일 인증에 성공했습니다. 환영합니다!');
          this.$router.push({name: "Main"});
          this.$session.set('userinfo', res.data);
          this.SET_USER(res.data);
          this.error = false;
        })
        .catch((err) => {
          console.error(err);
          this.verifyNum = '';
          this.error = true;
          this.inputMsg = '잘못된 인증번호입니다';
          this.loading2 = false;
        });
    },
  },
};
</script>

<style scoped></style>