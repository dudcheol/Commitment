import { findByToken, login, setAuthTokenToHeader } from '../api/account';

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
        context.commit('GET_MEMBER_INFO', { token, user: response.data.user });
      },
      (error) => {
        console.log('%cactions.js line:26 error', 'color: #007acc;', error);
      }
    );
  },
  //   LOGOUT(context) {
  //     context.commit('LOGOUT');
  //     axios.defaults.headers.common['auth-token'] = undefined;
  //   },
};
