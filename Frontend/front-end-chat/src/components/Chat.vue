<template>
  <div class="chat-container">
    <h2>Conversando com {{ destinatario }}</h2>
    <div class="mensagens">
      <div
        v-for="(msg, index) in mensagens"
        :key="index"
        :class="{ 'minha': msg.remetenteNome === usuarioLogado }"
      >
        <strong>{{ msg.remetenteNome }}:</strong> {{ msg.conteudo }}
      </div>
    </div>
    <form @submit.prevent="enviar">
      <input v-model="novaMensagem" placeholder="Digite sua mensagem" />
      <button type="submit">Enviar</button>
    </form>
  </div>
</template>

<script>
import ChatService from '../services/chatService';
import SocketService from '../services/socketService';

export default {
  name: 'Chat',
  props: ['destinatario'],
  data() {
    return {
      usuarioLogado: '',
      mensagens: [],
      novaMensagem: ''
    };
  },
  async created() {
    this.usuarioLogado = sessionStorage.getItem('nome');
    const token = sessionStorage.getItem('token');

    await SocketService.conectar(token);
    SocketService.subscribe(this.usuarioLogado, (msg) => {
      if (
        msg.remetenteNome === this.destinatario ||
        msg.destinatarioNome === this.destinatario
      ) {
        this.mensagens.push(msg);
      }
    });

    this.carregarMensagens();
  },
  watch: {
    destinatario: {
      immediate: true,
      handler() {
        this.carregarMensagens();
      }
    }
  },
  methods: {
    async carregarMensagens() {
      const res = await ChatService.buscarMensagens(
        this.usuarioLogado,
        this.destinatario
      );
      this.mensagens = res.data;
    },
    enviar() {
      if (this.novaMensagem.trim() !== '') {
        SocketService.enviarMensagem(this.destinatario, this.novaMensagem);
        this.novaMensagem = '';
      }
    }
  },
  beforeDestroy() {
    SocketService.desconectar();
  }
};
</script>


<style>
.chat-container {
  max-width: 600px;
  margin: auto;
}

.mensagens {
  border: 1px solid #ccc;
  height: 400px;
  overflow-y: auto;
  padding: 10px;
}

.mensagens .minha {
  background-color: #dcf8c6;
  padding: 5px;
  border-radius: 5px;
  margin: 2px 0;
}

.mensagens div {
  margin: 4px 0;
}
</style>
