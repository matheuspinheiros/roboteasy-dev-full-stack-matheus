import axios from 'axios';

const baseUrl = 'http://localhost:8082/api/';
const Api_Path = `${baseUrl}/`;

const api = axios.create({
  baseURL: Api_Path

});

api.interceptors.request.use(
  (config) => {
    // const token = localStorage.getItem('token');
    const token = sessionStorage.getItem('token');
    if (token) {
      config.headers['Authorization'] = token;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default api;
