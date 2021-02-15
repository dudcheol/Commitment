import { createInstance } from './index.js';

const instance = createInstance();
//뱃지 수집률
function commitrate(email, success, fail) {
  instance
    .get('badge/commitrate', { params: { email } })
    .then(success)
    .catch(fail);
}
//뱃지 획득 가능 여부
function badgeCheck(email, success, fail) {
  instance
    .get('badge/check', { params: { email } })
    .then(success)
    .catch(fail);
}
//획득한 뱃지
function badgeList(email, success, fail) {
  instance
    .get('badge/list', { params: { email } })
    .then(success)
    .catch(fail);
}
//대표뱃지 설정
function mybadge(email,badge, success, fail) {
  instance
    .get('badge/user', { params: { email ,badge} })
    .then(success)
    .catch(fail);
}
//대표뱃지 불러오기
 async function mainbadge(email, success, fail) {
  await instance
    .get('badge/mybadge', { params: { email } })
    .then(success)
    .catch(fail);
}

export { commitrate,badgeCheck,badgeList,mybadge,mainbadge };
