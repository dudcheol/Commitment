import { createInstance } from './index.js';

const READ_PERMISSION_OK = 1;
const READ_PERMISSION_DENIED = 0;
const instance = createInstance();

function latlngToAddress(latlng, success, fail) {
  instance
    .get('commit/location', { params: latlng })
    .then(success)
    .catch(fail);
}

function addCommit(email, lat, lng, open, success, fail) {
  instance
    .post(`commit/${open}`, { email, lat, lng })
    .then(success)
    .catch(fail);
}

function emptyCommit(email, success, fail) {
  instance
    .get('commit/noboard', { params: { email } })
    .then(success)
    .catch(fail);
}

function followCommitMap(email, success, fail) {
  instance
    .get('sns/followmap', { params: { email } })
    .then(success)
    .catch(fail);
}

function userCommitMap(email, success, fail) {
  instance
    .get('commit/usermap', { params: { email } })
    .then(success)
    .catch(fail);
}

export {
  userCommitMap,
  followCommitMap,
  latlngToAddress,
  addCommit,
  emptyCommit,
  READ_PERMISSION_DENIED,
  READ_PERMISSION_OK,
};
