<template>
  <div class="register-container">
    <h1>Registre-se</h1>
    <form @submit.prevent="handleRegister">
      <input v-model="nome" type="text" placeholder="Nome de usuário" required />
      <input v-model="senha" type="password" placeholder="Senha" required />
      <button type="submit">Registrar</button>
    </form>
    <p>
      Já tem conta?
      <router-link to="/">Entrar</router-link>
    </p>
  </div>
</template>

<script>
import authService from '../services/authService';

export default {
  name: 'Register',
  data() {
    return {
      nome: '',
      senha: ''
    };
  },
  methods: {
    handleRegister() {
      authService.register(this.nome, this.senha)
        .then(response => {
          const token = response.headers['authorization'];
          if (token) {
            sessionStorage.setItem('token', token);
            sessionStorage.setItem('nome', this.nome);
            this.$router.push('/usuarios');
          } else {
            alert('Token não recebido');
          }
        })
        .catch(error => {
          console.error('Erro ao registrar:', error);
          alert('Erro no registro. Verifique os dados.');
        });
    }
  }
};
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: 100px auto;
  padding: 2rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  text-align: center;
}
input {
  display: block;
  width: 100%;
  margin-bottom: 1rem;
  padding: 0.7rem;
  border-radius: 5px;
  border: 1px solid #aaa;
}
button {
  padding: 0.7rem 1.5rem;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>
