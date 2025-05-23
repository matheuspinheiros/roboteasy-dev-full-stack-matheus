import Vue from 'vue';
import Router from 'vue-router';
import Login from '../components/Login.vue';
import Register from '../components/Register.vue';
import UsuariosOnline from '../components/UsuariosOnline.vue';
import Chat from '../components/Chat.vue';

Vue.use(Router);

const router = new Router({
  mode: 'history', // opcional, deixa URL sem #
  routes: [
    { path: '/', name: 'Login', component: Login },
    { path: '/register', name: 'Register', component: Register },
    { path: '/usuarios', name: 'Usuarios', component: UsuariosOnline },
    { path: '/chat/:destinatario', name: 'Chat', component: Chat, props: true }
  ]
});

export default router;
