# Google OAuth2 + JWT Demo 🔑

Este proyecto es una **aplicación fullstack** que demuestra cómo integrar la autenticación con **Google OAuth2** en un sistema moderno basado en **Angular (frontend)** y **Spring Boot (backend)**.  
El backend se encarga de **intercambiar el código de autorización por un access token**, obtener los datos del usuario desde la API de Google y generar un **JWT (JSON Web Token)** para gestionar la sesión de forma segura.  
El frontend consume el backend, maneja el flujo de login y persiste el JWT para acceder a recursos protegidos.


## 🚀 Tecnologías utilizadas
- **Frontend**: Angular 17 (con manejo de OAuth2 y JWT en el cliente)  
- **Backend**: Spring Boot 3 + Spring Security (con soporte para OAuth2 y JWT)  
- **Base de Datos**: MySQL  (persistencia de usuarios y tokens)  
- **OAuth2 Provider**: Google Identity Platform (login con Google)  
- **JWT**: Tokens firmados para autenticación stateless (sin sesiones en servidor)

- ## 📦 Dependencias principales

### 🔹 Backend (Spring Boot)
- `spring-boot-starter-web` → para construir API REST.
- `spring-boot-starter-security` → seguridad y configuración de OAuth2.
- `spring-boot-starter-oauth2-client` → integración con Google OAuth2.
- `spring-boot-starter-data-jpa` → acceso a base de datos con JPA/Hibernate.
- `mysql-connector-j` → conector para MySQL 8.
- `jjwt` (io.jsonwebtoken) → generación y validación de tokens JWT.
- `spring-boot-devtools` → recarga en caliente para desarrollo.

### 🔹 Frontend (Angular)
- `@angular/core` y módulos base de Angular.
- `@angular/router` → manejo de rutas.
- `@angular/common/http` → para llamadas al backend.
- `rxjs` → manejo de asincronía y streams.
