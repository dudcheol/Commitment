export default {
  getAuthToken(state) {
    return state.authToken;
  },
  getUserInfo(state) {
    return state.userInfo;
  },
  getCurrentLatlng(state) {
    return state.currentLatlng;
  },
  getCurrentAddress(state) {
    return state.currentAddress;
  },
  getTotalTime(state) {
    return state.totalTime;
  },
  getMinutes(state) {
    return state.minutes;
  },
  getSeconds(state) {
    return state.seconds;
  },
  getFollowingList(state) {
    return state.following;
  },
  getEmpCommitList(state) {
    return state.empCommits;
  },
  getWriteDialogState(state) {
    return state.writeDialog;
  },
  getCommitDialogState(state) {
    return state.commitDialog;
  },
  getBoardDetail(state) {
    return state.boardDetail;
  },
  getBoardRefresh(state) {
    return state.boardRefresh;
  },
  getCommitId(state) {
    return state.commitId;
  },
  getCommitAddress(state) {
    return state.commitAddress;
  },
  getSelectedBoardId(state) {
    return state.selectedBoardId;
  },
  getSelectedUserId(state) {
    return state.selectedUserId;
  },
  getRealtimeCommitList(state) {
    return state.realtimeCommitList;
  },
};
