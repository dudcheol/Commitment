import axios from 'axios';
import https from 'https';
import { API_BASE_URL } from '../config';

const agent = new https.Agent({
  rejectUnauthorized: false,
});

function createInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      'Content-Type': 'application/json',
    },
    httpsAgent: agent,
  });
  return instance;
}

export { createInstance };
