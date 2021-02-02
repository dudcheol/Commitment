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
    .get('account/login', { params })
    .then(success)
    .catch(fail);
}

async function findByToken(token, success, fail) {
  instance.defaults.headers['access-token'] = token;
  await instance
    .get('account/info')
    .then(success)
    .catch(fail);
}

export { login, findByToken, setAuthTokenToHeader };
