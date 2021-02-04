import { findByToken, login, setAuthTokenToHeader, logout } from '../api/account';

export default {
  async LOGIN(context, user) {
    let result = false;
    await login(
      user,
      (response) => {
        if (response.data.data === 'success') {
          context.commit('LOGIN', response.data);
          localStorage.setItem('auth-token', response.data['auth-token']);
          setAuthTokenToHeader(response.data['auth-token']);
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
};
