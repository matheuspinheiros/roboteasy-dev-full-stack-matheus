<template>
  <div class="usuarios-container">
    <h1>Usuários Online</h1>

    <ul v-if="usuarios.length">
      <li
        v-for="usuario in usuarios"
        :key="usuario.nome"
        @click="irParaChat(usuario.nome)"
      >
        {{ usuario.nome }}
      </li>
    </ul>

    <p v-else>Nenhum usuário online no momento.</p>

    <p v-if="erro" class="erro">{{ erro }}</p>

    <button @click="logout">Logout</button>
  </div>
</template>

<script>
import UserService from '../services/userService';
import SocketService from '../services/socketService';

export default {
  name: 'UsuariosOnline',
  data() {
    return {
      usuarios: [],
      erro: null,
      subscription: null,
    };
  },
  methods: {
      async carregarUsuarios() {
        try {
          const res = await UserService.buscarUsuariosOnline();
          this.usuarios = res.data;
        } catch (error) {
          this.erro = 'Erro ao buscar usuários online';
        }
      },
      irParaChat(destinatario) {
        const usuarioLogado = sessionStorage.getItem('nome');
        if (destinatario === usuarioLogado) {
          alert('Não é possível abrir um chat consigo mesmo.');
          return;
        }
        this.$router.push({ name: 'Chat', params: { destinatario } });
      },
      logout() {
          fetch('http://localhost:8082/api/usuarios/logout', {
            method: 'POST',
            headers: {
              Authorization: sessionStorage.getItem('token'),
              'Content-Type': 'application/json',
            },
          })
          .then(() => {
            SocketService.desconectar();
            sessionStorage.clear();
            this.$router.push('/');
          })
          .catch(() => {
            alert('Erro ao deslogar.');
          });
      },
  },
  async mounted() {
    await this.carregarUsuarios();
  },
  beforeDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  },
};
</script>

<style>
.usuarios-container {
  max-width: 600px;
  margin: auto;
  text-align: center;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  padding: 10px;
  margin: 5px;
  background-color: #f1f1f1;
  cursor: pointer;
  border-radius: 5px;
}

li:hover {
  background-color: #ddd;
}

.erro {
  color: red;
}
</style>
