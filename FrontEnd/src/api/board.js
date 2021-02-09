import { createInstance } from './index.js';

const instance = createInstance();

function myBoardList(param, success, fail) {
  instance
    .get('sns/followingboard', { params: param })
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

export { myBoardList, followingBoardList, totalBoadList };
