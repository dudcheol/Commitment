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

function totalRadiusBoardList(lat, lng, radius, page, size, sort, success, fail) {
  instance
    .get('sns/radius', { params: { lat, lng, radius, sort, page, size } })
    .then(success)
    .catch(fail);
}

function boardDetail ( id, success, fail) { 
  instance
  .get(`sns/${id}`)
  .then(success)
  .catch(fail)
}

export { myBoardList, followingBoardList, totalBoadList, totalRadiusBoardList, boardDetail};
