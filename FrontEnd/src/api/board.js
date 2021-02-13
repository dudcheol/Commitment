import { createInstance } from './index.js';

const instance = createInstance();

function myBoardList(param, success, fail) {
  instance
    .get('mysns', { params: param })
    .then(success)
    .catch(fail);
}

function followingBoardList(param, success, fail) {
  instance
    .get('sns/followingboard', { params: param })
    .then(success)
    .catch(fail);
}

function totalBoadList(param, success, fail) {
  instance
    .get('sns/totalboard', { params: param })
    .then(success)
    .catch(fail);
}

function totalRadiusBoardList(lat, lng, radius, page, size, success, fail) {
  instance
    .get('sns/radius', { params: { lat, lng, radius, page, size } })
    .then(success)
    .catch(fail);
}

function BoardDetail ( id, param, success, fail) { 
  instance
  .get(`sns/${id}`, { params: { param }})
  .then(success)
  .catch(fail)
}

export { myBoardList, followingBoardList, totalBoadList, totalRadiusBoardList, BoardDetail};
