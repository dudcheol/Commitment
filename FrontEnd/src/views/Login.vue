<template>
  <div class="login_input">
    <v-list-item>
      로그인
    </v-list-item>
    <v-list-item>
        <vs-input
        label="Email"
        v-model="email"
        placeholder="이메일형식에 맞게 만들어주세요"
      />
    </v-list-item>

    <v-list-item>
        <vs-input
        label="Password"
        type="password"
        v-model="value"
        placeholder="숫자영문혼용 8글자 이상"
      />
    </v-list-item>

    <v-list-item>
        <vs-input
        label="PasswordConfirm"
        v-model="value"
        type="password"
        placeholder="숫자 영문 혼용 8글자 이상"
      />
    </v-list-item>
    <v-list-item>
      <vs-button size="large" type="flat">Login</vs-button>
    </v-list-item>
  </div>

</template>

<script>
export default {

}
</script>

<style>
.v-main__wrap {
  display: flex;
  justify-content: center;
}

.login_input {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.v-list-item.theme--light {
  display: flex;
  justify-content: center;
  max-height: 70px;
}
</style>