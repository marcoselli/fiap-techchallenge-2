# 🚗 Tech Challenge – Vehicle Sale API

Este projeto é uma API backend desenvolvida em **Spring Boot + Kotlin**, seguindo boas práticas de arquitetura, versionamento de banco de dados e deploy containerizado, conforme solicitado no **Tech Challenge**.

A aplicação expõe endpoints REST para gestão de veículos, utilizando **JPA/Hibernate**, **Flyway** para migrations e **MySQL** como banco principal.

---

## 🛠️ Stack Tecnológica

- **Java 21**
- **Kotlin**
- **Spring Boot 3**
    - Spring Web (MVC)
    - Spring Data JPA
    - Spring Actuator
- **Flyway** (versionamento de schema)
- **MySQL 8**
- **Docker / Docker Compose**
- **Kubernetes (Minikube)**
- **Gradle**
- **OpenAPI / Swagger**

---

## 📁 Estrutura do Projeto

```text
.
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-dev.yml
│   │       ├── application-prod.yml
│   │       └── db/migration/
│   └── test/
├── k8s/
│   ├── namespace.yaml
│   ├── mysql.yaml
│   ├── deployment.yaml
│   ├── service.yaml
│   ├── configmap.yaml
│   ├── secret.yaml
│   └── hpa.yaml
├── docker-compose.yml
├── Dockerfile
├── deploy.sh
└── README.md
```

## ▶️ Rodando o projeto em DEV local (sem Docker)
Pré-requisitos:
- **Java 21**
- **Gradle**
- **IDE (IntelliJ recomendado)**

Rode:

./gradlew bootRun

Banco de dados

H2 em memória

Dados de exemplo podem ser carregados apenas no profile dev

Acesso

API: http://localhost:8080

Swagger: http://localhost:8080/swagger-ui.html

## 🐳 Rodando com Docker Compose
Pré-requisitos:
- **Docker**
- **Docker Compose**

Subindo a aplicação:

```
docker-compose up --build
```

Isso irá subir:

- **MySQL**
- **API Spring Boot (profile prod)**

Observações importantes

O schema do banco é criado via Flyway
Não existem inserts automáticos em produção
O banco persiste via volume Docker

Acesso

API: http://localhost:8080

Swagger: http://localhost:8080/swagger-ui.html

## ☸️ Rodando com Kubernetes (Minikube)
Pré-requisitos:
- **Docker**
- **kubectl**
- **Minikube**

🚀 Deploy automático via script

O projeto possui um script que:
- **Inicializa o Minikube**
- **Builda a imagem da aplicação**
- **Aplica todos os manifestos na ordem correta**

Execute:
./k8s/deploy-minikube.sh

O que o script faz:
- **Inicia o Minikube**
- **Usa o Docker do Minikube**
- **Builda a imagem tech-challenge:latest**

Cria:

```text
Namespace
MySQL
ConfigMaps e Secrets
Deployment da aplicação
Service
HPA
```

Aguarda os pods ficarem prontos

Acessando a aplicação no Kubernetes
```
minikube service tech-challenge-service -n tech-challenge
```

O navegador será aberto automaticamente.
