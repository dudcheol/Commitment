import axios from 'axios';
import { API_BASE_URL } from '../config';

function createInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      'Content-Type': 'application/json',
    },
  });
  return instance;
}

export { createInstance };
