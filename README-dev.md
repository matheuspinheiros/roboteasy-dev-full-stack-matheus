# **📌 Desafio Técnico – Desenvolvedor Full Stack**

Adicionei um arquivo separado com instruções de execução, para não sobrescrever o README principal do projeto.

## 🚀 Tecnologias Utilizadas

###  Backend
- **Java 17**
- **Spring Boot 3.4.5**
- **Spring Security 6.2.5**
- **JWT (com biblioteca JJWT)**
- **WebSocket com STOMP**
- **Lombok**
- **PostgreSQL** (via Docker)

###  Frontend
- **Vue.js**
- **Axios** (para requisições HTTP)
- **StompJS** (para conexão WebSocket via protocolo STOMP)

---

## 📦 Pré-requisitos

- [Docker](https://www.docker.com/) instalado
- [Docker Compose](https://docs.docker.com/compose/) instalado

---

## 🔧 Como rodar o projeto (Docker)

1. Clone este repositório

2. Execute o Docker compose: docker compose up --build

3. Acesse o frontend no navegador: http://localhost:3000

## 🔧 Informações adicionais

Containers e portas:
Frontend: 3000
Backend: 8082
PostgreSQL: 5433

## **Objetivo**
Criar um **chat em tempo real** com autenticação de usuários, listagem de usuários disponíveis e trocas de mensagens.

## 📌 Como Participar
1. **Fork** este repositório para a sua conta do GitHub.
2. Desenvolva a solução no seu fork.
3. Após finalizar, **abra um Pull Request (PR)** para este repositório.
4. Aguarde o feedback da equipe.

## **🎯 Requisitos do Desafio**

### **1️⃣ Backend**
Criar uma **API REST + WebSockets** utilizando **C# (.NET)** ou **Java (Spring Boot)** com as seguintes funcionalidades:
- **Autenticação e Registro de Usuários**  
  - Criar um endpoint para **login** e outro para **cadastro de usuários**.  
  - Utilizar **JWT** para autenticação.  
- **Listagem de Usuários Online**  
  - Criar um endpoint que retorna os usuários conectados.  
- **Mensagens em Tempo Real**  
  - Implementar **WebSockets** para o envio e recebimento de mensagens.  
  - Criar um **histórico de mensagens** (armazenar em MongoDB ou outro banco de sua escolha).  

---

### **2️⃣ Frontend**
Criar uma **aplicação web** utilizando **Vue.js** com três telas:
- **Tela de Login**
  - Input de **usuário e senha**.
  - Botão para **cadastrar-se**.
- **Tela de Usuários Disponíveis**
  - Listagem dos usuários conectados.
  - Clique no usuário para iniciar um chat.
- **Tela de Conversa**
  - Exibir **histórico de mensagens**.
  - Permitir envio de mensagens em tempo real via **WebSockets**.

---

### **3️⃣ Docker**
Criar um **Dockerfile e um docker-compose.yml** para subir a aplicação de forma rápida.

- O **backend** deve rodar no **.NET Core** ou **Spring Boot**.
- O **frontend** deve rodar no Vue.js
- Banco de dados pode ser **MongoDB, PostgreSQL ou outro**.
- Criar um **arquivo README.md** com instruções para rodar o projeto.

---

## **🛠 Tecnologias Sugeridas**
### **Backend**
✅ **C# com .NET Core** (ou) **Java 17+ com Spring Boot**  
✅ **Autenticação com JWT**  
✅ **WebSockets para mensagens em tempo real**  
✅ **Banco de dados** (MongoDB, PostgreSQL, ou outro de sua escolha)  
✅ **Docker para containerização**

### **Frontend**
✅ **Vue.js**  
✅ **Consumo de APIs via Axios ou Fetch**  
✅ **Uso de WebSockets para chat em tempo real**  

---

## **📌 O que será avaliado?**
✔ **Código bem estruturado e organizado**  
✔ **Boas práticas de desenvolvimento** (Clean Code, SOLID, etc.)  
✔ **Segurança na autenticação e API**  
✔ **Uso correto de WebSockets**  
✔ **Uso eficiente do banco de dados**  
✔ **Documentação clara para rodar a aplicação**  


---

## ** ⏳ Prazo **
- **5** dias.
