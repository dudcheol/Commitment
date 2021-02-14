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
  START_TIMER(state, payload) {
    state.timer = payload;
    state.totalTime = 1 * 60 * 10;
  },
  COMMITBTN_STATE_CHANGER(state, payload) {
    state.commitBtnState = payload;
  },
  STOP_TIMER(state) {
    clearInterval(state.timer);
    state.timer = null;
  },
  TOTAL_TIME(state) {
    if (state.totalTime >= 1) {
      state.totalTime--;
    } else {
      state.totalTime = 0;
    }
    state.minutes = Math.floor(state.totalTime / 60);
    state.seconds = state.totalTime - state.minutes * 60;
  },
  GET_FOLLOWING_LIST(state, payload) {
    state.following = payload;
  },
};
