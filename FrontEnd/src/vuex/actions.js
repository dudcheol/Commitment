import {
  findByToken,
  login,
  setAuthTokenToHeader,
  logout,
  signup,
  smtp,
  googleLogin,
  getUserInfoByNickname,
} from '../api/account';
import { emptyCommit, latlngToAddress } from '../api/commit';
import { boardDetail } from '../api/board';
import router from '../router';
import { getFollowingList } from '../api/follow';
import firebase from 'firebase/app';
import 'firebase/database';

export default {
  async LOGIN(context, user) {
    let result = '';
    await login(
      user,
      (response) => {
        context.commit('LOGIN', response.data.data);
        if (response.data.data === 'success') {
          localStorage.setItem('auth-token', response.data['auth-token']);
          setAuthTokenToHeader(response.data['auth-token']);
          context.dispatch('GET_MEMBER_INFO', response.data['auth-token']);
          result = response.data;
        }
      },
      (error) => {
        console.log('%cactions.js line:13 error', 'color: #007acc;', error);
      }
    );
    return result;
  },
  async GET_MEMBER_INFO(context, token) {
    await findByToken(
      token,
      (response) => {
        let user = response.data.user;
        user.badgeCnt = response.data.badgeCnt;
        user.commitCnt = response.data.commitCnt;
        user.followerCnt = response.data.followerCnt;
        context.dispatch('GET_FOLLOWING_LIST', user.email);
        context.dispatch('GET_EMPCOMMIT_LIST', user.email);
        context.commit('GET_MEMBER_INFO', { token, user });
      },
      (error) => {
        console.log('%cactions.js line:26 error', 'color: #007acc;', error);
      }
    );
  },
  LOGOUT(context) {
    context.commit('LOGOUT');
    localStorage.removeItem('auth-token');
    logout();
    router.replace('/login');
  },
  CURRENT_LATLNG(context) {
    if ('geolocation' in navigator) {
      /* 위치정보 사용 가능 */
      navigator.geolocation.watchPosition(
        (position) => {
          const latlng = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };
          context.commit('CURRENT_LATLNG', latlng);
          context.dispatch('LATLNG_TO_ADDRESS', latlng);
        },
        (error) => {
          console.log(
            '%cerror actions.js line:60 ',
            'color: red; display: block; width: 100%;',
            error
          );
          router.replace('/permission');
          context.commit('CURRENT_LATLNG', null);
          context.commit('LATLNG_TO_ADDRESS', null);
        }
      );
    } else {
      /* 위치정보 사용 불가능 */
      router.replace('/permission');
    }
  },
  LATLNG_TO_ADDRESS(context, latlng) {
    latlngToAddress(
      latlng,
      (response) => {
        context.commit('LATLNG_TO_ADDRESS', response.data);
      },
      (error) => {
        console.log('%cactions.js line:74 error', 'color: #007acc;', error);
        context.commit(
          'LATLNG_TO_ADDRESS',
          this.currentAddress == this.SEARCHING_POSITIOND_TEXT || this.currentAddress.trim
            ? this.SEARCHING_POSITIOND_TEXT
            : this.currentAddress
        );
      }
    );
  },
  SIGNUP(context, payload) {
    // payload가 user 정보가 담겨져있음
    let result = false;
    signup(
      payload,
      (response) => {
        context.commit('SIGNUP', payload);
        // 여기서 다시 SMTP 호출하고싶은경우?
        context.dispatch('SMTP', response.data);
        result = true;
      },
      (error) => {
        console.log('Signup Error', error);
        result = false;
        console.log('SIGNUP actionjs line107');
      }
    );
    return result;
  },
  SMTP(context, payload) {
    smtp(
      payload.email,
      (response) => {
        context.commit('SMTP', response.data);
      },
      (error) => {
        console.log('SMTP ERROR' + error);
      }
    );
  },
  async GOOGLE_LOGIN(context, payload) {
    var socialresult = false;
    await googleLogin(
      payload,
      (response) => {
        localStorage.setItem('auth-token', response.data['auth-token']);
        setAuthTokenToHeader(response.data['auth-token']);
        context.dispatch('GET_MEMBER_INFO', response.data['auth-token']);
        socialresult = true;
      },
      (error) => {
        console.log('google login Error', error);
        socialresult = false;
      }
    );
    return socialresult;
  },
  FIRST_START_TIMER(store) {
    store.commit(
      'FIRST_START_TIMER',
      setInterval(() => store.dispatch('COUNTDOWN'), 1000)
    );
    store.commit('COMMITBTN_STATE_CHANGER', false);
  },
  START_TIMER: (store) => {
    store.commit(
      'START_TIMER',
      setInterval(() => store.dispatch('COUNTDOWN'), 1000)
    );
    store.commit('COMMITBTN_STATE_CHANGER', false);
  },
  STOP_TIMER: (store) => {
    store.commit('STOP_TIMER');
    store.commit('COMMITBTN_STATE_CHANGER', true);
  },
  COUNTDOWN: (store) => {
    store.commit('TOTAL_TIME');
  },
  GET_FOLLOWING_LIST: (store, payload) => {
    getFollowingList(
      payload,
      (response) => {
        store.commit('GET_FOLLOWING_LIST', response.data);
      },
      (error) => {
        console.log(
          '%cerror actions.js line:38 ',
          'color: red; display: block; width: 100%;',
          error
        );
      }
    );
  },
  async BOARDDETAIL(context, payload) {
    await boardDetail(
      payload,
      (response) => {
        context.commit('BOARDDETAIL', response.data);
      },
      (error) => {
        console.log(error);
      }
    );
  },
  GET_EMPCOMMIT_LIST(store, payload) {
    emptyCommit(
      payload,
      (response) => {
        store.commit('ADD_EMPCOMMIT', response.data);
      },
      (error) => {
        console.log(
          '%cerror actions.js line:224 ',
          'color: red; display: block; width: 100%;',
          error
        );
      }
    );
  },
  UPDATE_USERINFO_BY_NICKNAME(store, payload) {
    getUserInfoByNickname(
      payload,
      (response) => {
        let user = response.data.user;
        user.badgeCnt = response.data.badgeCnt;
        user.commitCnt = response.data.commitCnt;
        user.followerCnt = response.data.followerCnt;
        store.commit('UPDATE_USERINFO', user);
      },
      (error) => {
        console.log(
          '%cerror MapList.vue line:222 ',
          'color: red; display: block; width: 100%;',
          error
        );
      }
    );
  },
  GET_REALTIME_COMMIT_LIST(store) {
    firebase
      .database()
      .ref('noti/all')
      .limitToLast(15)
      .on('value', (snap) => {
        let res = snap.val();
        const tmp = [];
        for (const idx in res) {
          res[idx].id = idx;
          tmp.unshift({
            username: res[idx].from,
            address: res[idx].dataId,
            img: res[idx].profile,
            email: res[idx].userEmail,
          });
        }
        store.commit('GET_REALTIME_COMMIT_LIST', tmp);
      });
  },
};
