// import { API_BASE_URL } from '../config';
import { findByToken, login, setAuthTokenToHeader } from '../api/account';
// import router from '../router';

export default {
  async LOGIN(context, user) {
    await login(
      user,
      (response) => {
        if (response.data.data === 'success') {
          context.commit('LOGIN', response.data);
          localStorage.setItem('auth-token', response.data['auth-token']);
          setAuthTokenToHeader(response.data['auth-token']);
          return true;
        }
        console.log('%cactions.js line:16 fail', 'color: #007acc;');
        return false;
      },
      (error) => {
        console.log('%cactions.js line:13 error', 'color: #007acc;', error);
        return false;
      }
    );
    return false;
  },
  async GET_MEMBER_INFO(context, token) {
    await findByToken(
      token,
      (response) => {
        context.commit('LOGIN', response.data);
      },
      (error) => {
        console.log('%cactions.js line:26 error', 'color: #007acc;', error);
      }
    );
  },
  //   LOGIN(context, user) {
  //     return axios.get(`${API_BASE_URL}/account/login`, user).then((response) => {
  //       context.commit('LOGIN', response.data);
  //       axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
  //     });
  //   },
  //   LOGOUT(context) {
  //     context.commit('LOGOUT');
  //     axios.defaults.headers.common['auth-token'] = undefined;
  //   },
};
