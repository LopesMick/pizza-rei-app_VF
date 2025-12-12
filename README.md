# Pizzaria do Rei Full Stack

Projeto didático Full Stack usado em aula para ensinar, na prática, como conectar Frontend (Angular) + Backend (Java/JAX-RS + Jetty) + Banco de Dados (MySQL via XAMPP/phpMyAdmin).
O foco do projeto é dominar rotas, API REST, CORS, JWT, cadastro/login, consumo de API no Angular e persistência no MySQL.

Sumário

Arquitetura

Tecnologias

Estrutura do Repositório

Pré-requisitos

Como Rodar o Projeto

1. Banco de Dados (XAMPP/MySQL)

2. Backend (Java)

3. Frontend (Angular)

Endpoints da API

Fluxo de Autenticação (JWT)

Conectando Angular com a API

Troubleshooting

Objetivos didáticos

Arquitetura

Browser (Angular) → faz requisições HTTP → API (Java/JAX-RS) → persiste dados → MySQL (XAMPP)

O Angular roda em http://localhost:4200

O Backend roda em http://localhost:8080

O MySQL roda via XAMPP (phpMyAdmin para visualizar tabelas)

Tecnologias
Frontend

Angular (Standalone)

Reactive Forms

HttpClient

Backend

Java

JAX-RS (API REST)

Jetty (servidor HTTP embutido)

JPA/Hibernate (persistência)

Banco

MySQL (XAMPP)

phpMyAdmin

Estrutura do Repositório
pizza-rei-app/
backend/ # Java API (JAX-RS + Jetty + JPA)
frontend/ # Angular App

Pré-requisitos

Node.js + npm

Angular CLI (opcional, pode usar npx)

Java JDK

Maven (se o backend usa Maven)

XAMPP (MySQL + Apache + phpMyAdmin)

Como Rodar o Projeto

1. Banco de Dados (XAMPP/MySQL)

Abra o XAMPP Control Panel

Clique em Start para:

Apache

MySQL

Abra o phpMyAdmin:

http://localhost/phpmyadmin

Confirme que o banco existe (exemplo):

pizzaria_do_rei

Verifique se as tabelas estão criadas:

usuario, pedido, produto, etc.

Observação: em muitos cenários de aula, o Hibernate pode criar/atualizar tabelas automaticamente (dependendo do hibernate.hbm2ddl.auto).

2. Backend (Java)

Abra o projeto backend/ no Eclipse/IDEA

Rode a classe principal (Main) que inicia o Jetty

Confirme no console que o servidor subiu, exemplo:

Servidor iniciado em http://localhost:8080/api

Teste rápido

Abra no browser/Insomnia/Postman uma rota pública (exemplo):

http://localhost:8080/api/health

3. Frontend (Angular)

No terminal:

cd frontend
npm install
npm start

Acesse:

http://localhost:4200

Importante: rode o comando dentro da pasta frontend/. Rodar ng serve fora do workspace gera erro.

Endpoints da API

A API segue um padrão de rotas REST sob o prefixo:

Base URL: http://localhost:8080/api

Rotas públicas (sem token)

Essas rotas são liberadas no AuthFilter:

POST /api/usuario/salvar
Cadastro de usuário.

POST /api/usuario/login
Login do usuário (retorna JWT).

POST /api/contato/salvar
Fale Conosco.

GET /api/health
Health check da API.

Rotas protegidas (com token)

Todas as demais rotas exigem o header:

Authorization: Bearer SEU_TOKEN_AQUI

Fluxo de Autenticação (JWT)

O backend possui um filtro de autenticação:

AuthFilter (ContainerRequestFilter)

Responsável por:

Liberar OPTIONS (preflight CORS)

Liberar rotas públicas (login, salvar usuário, contato)

Exigir Authorization: Bearer <token> em rotas protegidas

Validar o token usando AuthService

Conectando Angular com a API

1. Habilitar HttpClient no Angular (Standalone)

No arquivo src/app/app.config.ts, garanta:

import { provideHttpClient } from '@angular/common/http';

export const appConfig = {
providers: [
provideHttpClient(),
],
};

Sem isso, chamadas HTTP não funcionam.

2. Configurar URL do Backend

No Angular, configure a URL base da API em src/environments/:

Exemplo:

export const environment = {
production: false,
apiUrl: 'http://localhost:8080'
};

3. Cadastro no Angular

A tela de cadastro dispara:

POST ${environment.apiUrl}/api/usuario/salvar

Campos típicos enviados:

nome

email

senha

(opcional) endereco, telefone se o backend aceitar

4. Login no Angular

A tela de login dispara:

POST ${environment.apiUrl}/api/usuario/login

Ao receber o token, o frontend deve salvar:

localStorage.setItem('token', token)

5. Interceptor (recomendado)

Para rotas protegidas, o Angular deve enviar automaticamente o token via interceptor:

Authorization: Bearer <token>

Isso garante que requisições para pedidos, carrinho e outras áreas privadas funcionem.

Troubleshooting
“Angular CLI outside a workspace”

Você está rodando fora de frontend/. Rode:

cd frontend
npm start

“No provider for HttpClient”

Falta provideHttpClient() no app.config.ts.

CORS bloqueando requisições

Confirme:

Backend liberando origem http://localhost:4200 no CORS

OPTIONS liberado no AuthFilter

404 em endpoints

Verifique:

Prefixo /api

Nome exato da rota no backend (ex: usuario/salvar)

Banco não atualiza

Verifique:

XAMPP MySQL rodando

credenciais/URL no persistence.xml

configuração hibernate.hbm2ddl.auto (se estiver usando)

Objetivos didáticos

Ao final do projeto, o aluno pratica:

Criação de rotas REST no backend

Uso de JAX-RS controllers

Persistência com JPA/Hibernate

Testes de API via Postman/Insomnia

Integração Angular com HttpClient

Reactive Forms (validações, submit, payload)

CORS (preflight OPTIONS)

Autenticação e autorização com JWT

Proteção de rotas com Filter

Licença

Projeto de uso educacional para fins de aprendizado em sala de aula.
