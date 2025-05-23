import api from './api';

const ChatService = {
  buscarMensagens (usuario1, usuario2) {
    return api.get('/conversas', {
      params: {
        usuario1,
        usuario2
      }
    });
  }
};

export default ChatService;
