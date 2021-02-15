
import {createInstance} from './index.js';

const instance = createInstance();

function follow(from, to, success, fail) {
  instance
    .get('profile/follow', { params: { from, to } })
    .then(success)
    .catch(fail);
}

function getFollowerList(email, success, fail) {
  instance
    .get('profile/follower', { params: { email } })
    .then(success)
    .catch(fail);
}

function getFollowerCnt(email, success, fail) {
  instance
    .get('profile/followerCnt', { params: { email } })
    .then(success)
    .catch(fail);
}

function searchFollowers(email, success, fail){
    instance
        .get('profile/follower',{params:{email}})
        .then(success)
        .catch(fail);
}

function searchFollowings(email, success, fail){
    instance
        .get('profile/following',{params:{email}})
        .then(success)
        .catch(fail);
}

function followerCount(email, success, fail) {
    instance
      .get('profile/followerCnt', { params: { email } })
      .then(success)
      .catch(fail);
  }
  
async function getFollowingList(email, success, fail) {
  await instance
    .get('profile/following', { params: { email } })
    .then(success)
    .catch(fail);
}

export { follow, getFollowerList, getFollowerCnt, getFollowingList, searchFollowers, searchFollowings, followerCount };
