# 🛒 Order Management API

API REST para gerenciamento de pedidos, usuários e produtos, desenvolvida com **Spring Boot 4** e **PostgreSQL**, utilizando autenticação segura via **JWT**.

---

## 🚀 Stack Técnica

* **Linguagem:** Java 17
* **Framework:** Spring Boot 4.0.3 (Data JPA, Security, Validation)
* **Segurança:** JSON Web Token (JWT)
* **Banco de Dados:** PostgreSQL
* **Documentação:** Swagger UI (OpenAPI 3)
* **Mapeamento:** MapStruct & Lombok

---

## 🔑 Autenticação e Uso

1. **Cadastro:** `POST /users`

2. **Login:** `POST /auth/login` → retorna o token JWT

3. **Autorização:**
   No Swagger, clique em **Authorize** e insira:

```http
Insira apenas o valor do token JWT (sem 'Bearer ')
```

4. **Acesso:**
   http://localhost:8080/swagger-ui.html

---

## 📂 Organização (Camadas)

* `controller`: Endpoints REST
* `business`: Regras de negócio e Services
* `mapper`: Conversão entre DTOs e entidades (MapStruct)
* `infrastructure`: Entidades, Repositórios e Segurança

---

## ⚙️ Configuração

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/order_management
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

---

## 💡 Objetivo

Projeto desenvolvido para consolidar conhecimentos em construção de APIs REST, autenticação com JWT, arquitetura em camadas e boas práticas utilizadas no mercado backend Java.


## 👨‍💻 Autor

Desenvolvido por Diego Duarte
