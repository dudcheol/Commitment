import { createInstance } from './index.js';

const instance = createInstance();

function latlngToAddress(latlng, success, fail) {
  instance
    .get('commit/location', { params: latlng })
    .then(success)
    .catch(fail);
}

export { latlngToAddress };
