import api from './api';

const UserService = {
  listarUsuarios () {
    return api.get('/usuarios');
  },

  buscarUsuariosOnline () {
    return api.get('/usuarios/online');
  }
};

export default UserService;
