# PicPay Simplificado

Projeto baseado no PicPay de forma simplificada, com o objetivo de praticar desenvolvimento de APIs RESTful com Java e integração com serviços externos.

## Descrição

O **PicPay Simplificado API** é uma aplicação backend que simula uma plataforma de pagamentos entre usuários.  
A API permite cadastro de usuários, depósito e saque de valores e transferências entre contas, respeitando regras de negócio específicas.

Existem dois tipos de usuários:

- **Usuário comum (COMMON)** – pode enviar e receber dinheiro
- **Lojista (SHOPKEEPER)** – pode apenas receber transferências

A aplicação também integra com serviços externos para autorização de transações e envio de notificações.

## Tecnologias utilizadas

- **Java** – Linguagem principal
- **Spring Boot** – Framework para construção da API
- **Spring Data JPA / Hibernate** – Mapeamento objeto-relacional
- **H2** – Banco de dados em memória
- **RestTemplate** – Consumo de serviços externos
- **Maven** – Gerenciador de dependências

## Como rodar o projeto

### 1. Clone o repositório

```bash
git clone https://github.com/JessicaLorenzon/picpaysimplificado.git
```

### 2. Instale as dependências com o Maven

### 3. Inicie a aplicação com o Maven

A API estará acessível em http://localhost:8080

## Endpoints disponíveis

### 1. Registro de usuário

```http
POST /users
```

#### Payload (JSON):

```json
{
  "fullName": "John Doe",
  "CPF": "12345678912",
  "email": "john@john.com",
  "password": "123456",
  "userType": "COMMON"
}
```

### 2. Listar todos os usuários

```http
GET /users
```

### 3. Depósito

```http
POST /deposit
```

#### Payload (JSON):

```json
{
  "affectedUserId": 1,
  "amount": 100.0
}
```

### 4. Saque

```http
POST /withdraw
```

#### Payload (JSON):

```json
{
  "affectedUserId": 1,
  "amount": 50.0
}
```

### 5. Transferência

```http
POST /transfer
```

#### Payload (JSON):

```json
{
  "amount": 40.0,
  "senderId": 1,
  "recieverId": 2
}
```