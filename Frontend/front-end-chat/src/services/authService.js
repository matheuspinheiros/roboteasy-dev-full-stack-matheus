import api from './api';

export default {
  login (nome, senha) {
    return api.post('/auth', { nome, senha });
  },

  register (nome, senha) {
    return api.post('/auth/registro', { nome, senha });
  }
};
