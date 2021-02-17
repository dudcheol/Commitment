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

function searchUserByEmail(keyword, success, fail) {
  instance
    .get('search/email', { params: keyword })
    .then(success)
    .catch(fail);
}

function searchUserByNickname(keyword, success, fail) {
  instance
    .get('search/nickname', { params: keyword })
    .then(success)
    .catch(fail);
}
function nickNameCheck(nickname, success, fail) {
  instance
    .get('/account/nickCheck', { params: { nickname } })
    .then(success)
    .catch(fail);
}
function emailCheck(email, success, fail) {
  instance
    .get('/account/emailCheck', { params: { email } })
    .then(success)
    .catch(fail);
}

function editProfile(param, success, fail) {
  instance
    .post('profile/upload', param)
    .then(success)
    .catch(fail);
}

// 이메일, 닉네임, 비밀번호, 전화, 나의한마디, 성별, 생일, 지역, 나이
function signup(userInfo, success, fail) {
  instance
    .post('account/signup', userInfo)
    .then(success)
    .catch(fail);
}

function smtp(userInfo, success, fail) {
  const params = {
    email: userInfo,
  };
  instance
    .get('account/smtp', { params })
    .then(success)
    .catch(fail);
}

async function googleLogin(userInfo, success, fail) {
  await instance
    .post('account/login', userInfo)
    .then(success)
    .catch(fail);
}

function getUserInfoByNickname(nickname, success, fail) {
  instance
    .get('user/nickname', { params: { nickname } })
    .then(success)
    .catch(fail);
}

function setMyCommitMap(email, region, success, fail) {
  instance
    .get('user/map', { params: { email, region } })
    .then(success)
    .catch(fail);
}

export {
  login,
  findByToken,
  setAuthTokenToHeader,
  logout,
  signup,
  smtp,
  googleLogin,
  searchUserByEmail,
  searchUserByNickname,
  editProfile,
  nickNameCheck,
  emailCheck,
  getUserInfoByNickname,
  setMyCommitMap,
};
