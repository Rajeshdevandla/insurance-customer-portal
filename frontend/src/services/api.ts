import axios from 'axios';

const api = axios.create({
  baseURL: process.env.REACT_APP_API_URL || 'http://localhost:8080/api',
  headers: { 'Content-Type': 'application/json' }
});

api.interceptors.request.use(config => {
  const token = localStorage.getItem('jwt_token');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

api.interceptors.response.use(
  res => res,
  err => {
    if (err.response?.status === 401) {
      localStorage.removeItem('jwt_token');
      window.location.href = '/login';
    }
    return Promise.reject(err);
  }
);

export const getPolicies = () => api.get('/policies').then(r => r.data);
export const getPolicy = (id: string) => api.get(`/policies/${id}`).then(r => r.data);
export const getClaims = () => api.get('/claims').then(r => r.data);
export const submitClaim = (data: any) => api.post('/claims', data).then(r => r.data);
export const getBillingHistory = () => api.get('/billing/history').then(r => r.data);
export const renewPolicy = (id: string) => api.post(`/policies/${id}/renew`).then(r => r.data);
