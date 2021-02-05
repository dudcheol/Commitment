export default {
  LOGIN(state, payload) {
    state.authToken = payload['auth-token'];
    state.userInfo = { payload };
    console.log('%cmutations.js line:5 payload', 'color: #007acc;', payload);
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
};
