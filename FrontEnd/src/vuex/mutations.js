export default {
  LOGIN(state, payload) {
    state.authToken = payload['auth-token'];
    state.userInfo = { payload };
  },
  GET_MEMBER_INFO(state, payload) {
    state.authToken = payload.token;
    state.userInfo = payload.user;
  },
  LOGOUT(state) {
    state.authToken = null;
    state.userInfo = '';
  },
  CURRENT_LATLNG(state, payload) {
    state.currentLatlng = payload;
  },
  LATLNG_TO_ADDRESS(state, payload) {
    state.currentAddress = payload;
  },
  SIGNUP(state, payload) {
    state.userInfo = { payload };
    console.log("SIGNUP mutationjs" + payload)
  },
  SMTP(state, payload) {
    state.userInfo = { payload };
  }
};
