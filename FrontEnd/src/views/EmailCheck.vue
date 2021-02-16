<template>
  <div>
    <vs-card>
      <template #header>
        <h4 class="not-margin">Welcome to <b>Commitment</b></h4>
      </template>

      <div class="con-form">
        회원가입이 완료되었습니다
      </div>

      <template #footer>
        <div class="footer-dialog">
          <vs-button block @click="mainlink">
            Home
          </vs-button>
        </div>
      </template>
    </vs-card>
  </div>
</template>

<script>
export default {
  name: 'EmailCheck',
  data() {
    return {};
  },
  created() {},
  methods: {
    mainlink() {
      this.$router.push({ name: 'Login' });
    },
  },
};
</script>

<style scoped></style>
