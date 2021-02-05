import { findByToken, login, setAuthTokenToHeader, logout } from '../api/account';
import { latlngToAddress } from '../api/commit';
import router from '../router';

export default {
  async LOGIN(context, user) {
    let result = false;
    await login(
      user,
      (response) => {
        if (response.data.data === 'success') {
          // context.commit('LOGIN', response.data);
          localStorage.setItem('auth-token', response.data['auth-token']);
          setAuthTokenToHeader(response.data['auth-token']);
          context.dispatch('GET_MEMBER_INFO', response.data['auth-token']);
          result = true;
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
          if (error.code == error.PERMISSION_DENIED) {
            router.replace('/permission');
          }
        }
      );
    } else {
      /* 위치정보 사용 불가능 */
      console.log('%cactions.js line:50 위치정보를 사용할 수 없음.', 'color: #007acc;');
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
};
