import { createInstance } from './index.js';

const instance = createInstance();

function addComment(email, snsId, parent, content, success, fail) {
  instance
    .post('comment', { email, snsId, parent, content })
    .then(success)
    .catch(fail);
}

function getCommentList(sns_id, success, fail) {
  instance
    .get('comment', { params: { sns_id } })
    .then(success)
    .catch(fail);
}

function deleteComment(id, success, fail) {
  instance
    .delete('comment', { params: { id } })
    .then(success)
    .catch(fail);
}

export { addComment, getCommentList, deleteComment };
