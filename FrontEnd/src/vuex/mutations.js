export default {
  LOGIN(state, payload) {
    state.authToken = payload['auth-token'];
    state.userInfo = { payload };
  },
  LOGOUT(state) {
    state.authToken = null;
    state.userId = '';
    state.userName = '';
  },
};
