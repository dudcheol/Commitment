import { createInstance } from './index.js';

const instance = createInstance();

function latlngToAddress(latlng, success, fail) {
  instance
    .get('commit/location', { params: latlng })
    .then(success)
    .catch(fail);
}

function addCommit(email, lat, lng, open, success, fail) {
  instance
    .get('commit', { params: { email, lat, lng, open } })
    .then(success)
    .catch(fail);
}

export { latlngToAddress, addCommit };
