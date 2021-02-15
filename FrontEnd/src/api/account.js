import { createInstance } from './index.js';

const instance = createInstance();

function setAuthTokenToHeader(token) {
  instance.defaults.headers.common['auth-token'] = token;
}

function login(user, success, fail) {
  // instance.defaults.headers['access-token'] = window.localStorage.getItem('access-token');
  const params = {
    email: user.email,
    pass: user.pass,
  };

  return instance
    .post('account/login', params)
    .then(success)
    .catch(fail);
}

async function findByToken(token, success, fail) {
  instance.defaults.headers['auth-token'] = token;
  await instance
    .get('account/info')
    .then(success)
    .catch(fail);
}

function logout() {
  instance.defaults.headers['auth-token'] = undefined;
}

function searchUserByEmail(keyword, success, fail){
  instance
      .get('search/email',{params:keyword})
      .then(success)
      .catch(fail);
}

function searchUserByNickname(keyword, success, fail){
  instance
      .get('search/nickname',{params:keyword})
      .then(success)
      .catch(fail);
}

function editProfile(param, success, fail){
  instance
      .post('profile/upload', param)
      .then(success)
      .catch(fail);
}

export { login, findByToken, setAuthTokenToHeader, logout, searchUserByEmail, searchUserByNickname, editProfile };
