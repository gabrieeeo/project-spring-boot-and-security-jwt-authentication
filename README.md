# Project: Spring Boot and Security JWT Authentication

This is a project that implements a complete authentication system using Spring Boot, Spring Security, JWT and MySQL.

- [Overview](#overview)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Security](#security)

<br>
<br>

## Overview

This application provides a complete authentication system with:

- ✅ User registration
- ✅ Login with JWT token generation and expiration
- ✅ JWT token validation
- ✅ Role-based access control (USER, ADMIN)
- ✅ Password encryption with BCrypt
- ✅ Swagger/OpenAPI for interactive documentation

<br>
<br>

## Technologies
- Java 25;
- Spring Boot;
- Spring Security;
- Spring Data JPA;
- MySQL;
- Auth0 Java JWT;
- SpringDoc OpenAPI;

<br>
<br>

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java 25 (or higher)**
  ```bash
  java -version
  ```
  
- **MySQL 8.0.0 or higher**
  ```bash
  mysql --version
  ```

- **Maven 3.6+** (optional, included in the project via `mvnw`)
  ```bash
  ./mvnw --version  # Windows: mvnw.cmd --version
  ```

<br>
<br>

## Installation

### 1. Clone the repository

```bash
git clone <url>
cd project-spring-boot-and-security-jwt-authentication
```

### 2. Create the MySQL database

```sql
CREATE DATABASE authentication_db;
```

> **Note:** The database will be created automatically if you use `createDatabaseIfNotExist=true` in the configuration.

### 3. Configure the environment variables

Create a `.env` file in the root of the project or edit the `application.properties`. (don't include information sensitive in 'application.properties' or in codebase if you are sharing the code publicly)

### 4. Compile and run the application

```bash
# Windows
mvnw.cmd clean install
mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw clean install
./mvnw spring-boot:run
```

The application will start at `http://localhost:8082`

<br>
<br>

## Security

### JWT

The JWT contain the following claims:

```json
{
  "iss": "authentication-api",     // Emissor
  "sub": "gabriel",                 // Subject
  "exp": 1678923456,                // Expiration (2 hours)
  "iat": 1678916256                 // Issued at
}
```

<br>
<br>

## Swagger/OpenAPI

Access the interactive API documentation at:

```
http://localhost:8082/swagger-ui.html
```

Or via JSON:

```
http://localhost:8082/v3/api-docs
```
