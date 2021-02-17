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
    // if (state.totalTime == 0) state.totalTime = 1 * 60 * 10;
    if (state.totalTime == 0) state.totalTime = 60;
  },
  FIRST_START_TIMER(state, payload) {
    state.timer = payload;
  },
  COMMITBTN_STATE_CHANGER(state, payload) {
    state.commitBtnState = payload;
  },
  STOP_TIMER(state) {
    clearInterval(state.timer);
    state.timer = null;
    state.totalTime = 0;
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
  SIGNUP(state, payload) {
    state.userInfo = { payload };
  },
  SMTP(state, payload) {
    state.userInfo = { payload };
  },
  BOARDDETAIL(state, payload) {
    state.boardDetail = payload;
  },
  ADD_EMPCOMMIT(state, payload) {
    state.empCommits = payload;
  },
  WRITE_DIALOG(state, payload) {
    state.writeDialog = payload.state;
    state.commitId = payload.id;
    state.commitAddress = payload.address;
  },
  COMMIT_DIALOG(state, payload) {
    state.commitDialog = payload;
  },
  BOARD_REFRESH(state) {
    state.boardRefresh += 1;
  },
  SELECTED_BOARD_ID(state, payload) {
    state.selectedBoardId = payload;
  },
  SELECTED_USER_ID(state, payload) {
    state.selectedUserId = payload;
  },
  UPDATE_USERINFO(state, payload) {
    state.userInfo = payload;
  },
  GET_REALTIME_COMMIT_LIST(state, payload) {
    state.realtimeCommitList = payload;
  },
};
