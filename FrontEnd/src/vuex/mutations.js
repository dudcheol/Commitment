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
  CURRENT_POSITION(state, payload) {
    state.currentPosition = payload;
  },
};
