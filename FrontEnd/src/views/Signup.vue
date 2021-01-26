<template>
<div class="grid-container">
  <div class="signup">
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
      <vs-button type="flat">Login</vs-button>
    </v-list-item>
  </div>
</div>
</template>



<script>
export default {
  name: "Signup",
  components: {},
  data() {
    return {
    };
  }
}
</script>

<style>

.vs-button__content {
    width: 100vh;
}

.grid-container {
  display: grid;
  grid-template-columns: 1fr 6fr 6fr 1fr;
  grid-template-rows: 1fr 6fr 6fr 1fr;
  gap: 0px 0px;
  grid-template-areas:
    ". . . ."
    ". signup signup ."
    ". signup signup ."
    ". . . .";
}

.signup { grid-area: signup; 
  display: flex;
  flex-direction: column;
  justify-content: center;
}

</style>