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

function writeBoard(param, success, fail) {
  instance
    .post('sns', param)
    .then(success)
    .catch(fail);
}

function imageUpload(param, snsId, success, fail) {
  instance
    .post(`image/${snsId}`, param)
    .then(success)
    .catch(fail);
}

async function boardDetail(id, success, fail) {
  await instance
    .get(`sns/${id}`)
    .then(success)
    .catch(fail);
}

function removeBoard(sns_id, success, fail) {
  instance
    .delete('sns', { params: { sns_id } })
    .then(success)
    .catch(fail);
}

// async function BoardDetail(id, param, success, fail) {
//   await instance
//     .get(`sns/${id}`, { params: { param } })
//     .then(success)
//     .catch(fail);
// }

export {
  removeBoard,
  myBoardList,
  followingBoardList,
  totalBoadList,
  totalRadiusBoardList,
  boardDetail,
  writeBoard,
  imageUpload,
  // BoardDetail,
};
