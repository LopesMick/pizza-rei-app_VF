# Pizzaria do Rei Full Stack

Projeto didático Full Stack usado em aula para ensinar, na prática, como conectar **Frontend (Angular)** + **Backend (Java/JAX-RS + Jetty)** + **Banco de Dados (MySQL via XAMPP/phpMyAdmin)**.

O foco do projeto é dominar **rotas**, **API REST**, **CORS**, **JWT**, **cadastro/login**, consumo de API no Angular e persistência no MySQL.

---

## Sumário

- [Arquitetura](#arquitetura)
- [Tecnologias](#tecnologias)
- [Estrutura do Repositório](#estrutura-do-repositório)
- [Pré-requisitos](#pré-requisitos)
- [Como Rodar o Projeto](#como-rodar-o-projeto)
  - [1) Banco de Dados (XAMPP/MySQL)](#1-banco-de-dados-xamppmysql)
  - [2) Backend (Java)](#2-backend-java)
  - [3) Frontend (Angular)](#3-frontend-angular)
- [Endpoints da API](#endpoints-da-api)
- [Fluxo de Autenticação (JWT)](#fluxo-de-autenticação-jwt)
- [Conectando Angular com a API](#conectando-angular-com-a-api)
- [Troubleshooting](#troubleshooting)
- [Objetivos didáticos](#objetivos-didáticos)
- [Licença](#licença)

---

## Arquitetura

**Browser (Angular)** → faz requisições HTTP → **API (Java/JAX-RS)** → persiste dados → **MySQL (XAMPP)**

- Angular: `http://localhost:4200`
- Backend: `http://localhost:8080`
- Banco: MySQL via XAMPP + phpMyAdmin

---

## Tecnologias

### Frontend

- Angular (Standalone)
- Reactive Forms
- HttpClient

### Backend

- Java
- JAX-RS (API REST)
- Jetty (servidor HTTP embutido)
- JPA/Hibernate (persistência)

### Banco

- MySQL (XAMPP)
- phpMyAdmin

---

## Estrutura do Repositório

```text
pizza-rei-app/
  backend/      # Java API (JAX-RS + Jetty + JPA)
  frontend/     # Angular App
```

Pré-requisitos

Node.js + npm - Angular CLI (opcional; pode usar npx) - Java JDK - Maven (se o backend usa Maven) - XAMPP (MySQL + Apache + phpMyAdmin)

Como Rodar o Projeto

1. Banco de Dados (XAMPP/MySQL)

Abra o XAMPP Control Panel - Clique em Start para:
-Apache
-MySQL - Abra o phpMyAdmin:
-http://localhost/phpmyadmin - Confirme que o banco existe (exemplo):
-pizzaria_do_rei
-Verifique se as tabelas estão criadas:
-usuario, pedido, produto, etc.

Observação: em muitos cenários de aula, o Hibernate pode criar/atualizar tabelas automaticamente (dependendo do hibernate.hbm2ddl.auto).

2. Backend (Java)

   1.Abra o projeto backend/ no Eclipse/IDEA
   2.Rode a classe principal (Main) que inicia o Jetty
   3.Confirme no console que o servidor subiu. Exemplo:
   -Servidor iniciado em http://localhost:8080/api

Teste rápido
Abra no browser/Insomnia/Postman uma rota pública (exemplo): - GET http://localhost:8080/api/health

3. Frontend (Angular)

No terminal:

```
cd frontend
npm install
npm start
```

Acesse:
http://localhost:4200
Importante: rode o comando dentro da pasta frontend/. Rodar ng serve fora do workspace gera erro.
