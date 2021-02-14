import Vue from 'vue';
import Vuex from 'vuex';
import getters from './getters';
import actions from './actions';
import mutations from './mutations';

Vue.use(Vuex);

const SEARCHING_POSITION_TEXT = '위치 찾는 중...';

const state = {
  userInfo: {},
  following: [],
  empCommits: [],
  authToken: null,
  currentLatlng: null,
  currentAddress: SEARCHING_POSITION_TEXT,
  title: 'Timer',
  timer: null,
  totalTime: 0,
  commitBtnState: true,
  minutes: 0,
  seconds: 0,
  boardDetail: null,
  writeDialog: false,
  commitDialog: false,
};

export default new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
});
