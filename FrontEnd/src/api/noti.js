import { createInstance } from './index.js';

const instance = createInstance();

function createNoti(from, to, dataId, isRead, type, success, fail) {
  instance
    .post(`api/noti?nickname=${from}`, { to, dataId, isRead, type })
    .then(success)
    .catch(fail);
}

function clickNoti(notiId, nickname, success, fail) {
  instance
    .delete(`api/noti/${notiId}`, { params: { nickname } })
    .then(success)
    .catch(fail);
}

function deleteNoti(objectId, type, nickname, success, fail) {
  instance
    .delete(`api/noti/notidel/${objectId}`, { params: { type, nickname } })
    .then(success)
    .catch(fail);
}

export { createNoti, deleteNoti, clickNoti };
