<template>
    <div class="login-container">
      <h1>Login</h1>
      <form @submit.prevent="login">
        <input v-model="nome" type="text" placeholder="Usuário" required />
        <input v-model="senha" type="password" placeholder="Senha" required />
        <button type="submit">Entrar</button>
      </form>
      <p v-if="erro" class="erro">{{ erro }}</p>
      <p>
        Não tem conta?
        <router-link to="/register">Cadastre-se</router-link>
      </p>
    </div>
  </template>
  
  <script>
  import api from '../services/api';
  
  export default {
    name: 'Login',
    data() {
      return {
        nome: '',
        senha: '',
        erro: null,
      };
    },
    methods: {
      async login() {
        this.erro = null;
        try {
          const res = await api.post('/auth', {
            nome: this.nome,
            senha: this.senha,
          });
          const token = res.headers['authorization'];
          if (token) {
            sessionStorage.setItem('token', token);
            sessionStorage.setItem('nome', this.nome);
            this.$router.push('/usuarios');
          } else {
            this.erro = 'Token não recebido';
          }
        } catch (error) {
          this.erro = 'Usuário ou senha inválidos';
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .login-container {
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
  .erro {
    color: red;
  }
  </style>

  