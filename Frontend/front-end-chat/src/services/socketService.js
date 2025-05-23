import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

class SocketService {
  constructor () {
    this.socket = null;
    this.stompClient = null;
  }

  conectar (token) {
    this.socket = new SockJS('http://localhost:8082/ws');
    this.stompClient = Stomp.over(this.socket);

    return new Promise((resolve, reject) => {
      this.stompClient.connect(
        { Authorization: token },
        () => {
          resolve();
        },
        (err) => {
          reject(err);
        }
      );
    });
  }

  desconectar () {
    if (this.stompClient) {
      this.stompClient.disconnect();
    }
  }

  subscribe (usuarioLogado, callback) {
    return this.stompClient.subscribe(
      `/user/${usuarioLogado}/topic/mensagens`,
      (message) => {
        callback(JSON.parse(message.body));
      }
    );
  }

  enviarMensagem (destinatario, conteudo) {
    this.stompClient.send('/app/chat/enviar', {}, JSON.stringify({
      destinatarioNome: destinatario,
      conteudo: conteudo
    }));
  }
}

export default new SocketService();
