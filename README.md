# Laboratorio VI

Laboratorio práctico de diseño y desarrollo de APIs REST con Spring Boot.

## Estructura

| Carpeta | Contenido |
|---|---|
| [Ejercicio1](Ejercicio1) | Diseño OpenAPI — API de Biblioteca |
| [Ejercicio2](Ejercicio2) | Diseño OpenAPI — API de Cursos Universitarios |
| [Ejercicio3](Ejercicio3) | Diseño OpenAPI — API de Reservas de Hotel |
| [Ejercicio4](Ejercicio4) | Desarrollo Spring Boot — API de Biblioteca |
| [Ejercicio5](Ejercicio5) | Desarrollo Spring Boot — API de Cursos Universitarios |
| [Ejercicio6](Ejercicio6) | Desarrollo Spring Boot — API de Reservas de Hotel |

## Ejercicios de diseño (1-3)

Cada carpeta contiene un archivo `.yaml` con la especificación OpenAPI 3.0 del API correspondiente (rutas, request/response, códigos de estado). Puede visualizarse pegando el contenido en [editor.swagger.io](https://editor.swagger.io).

## Ejercicios de desarrollo (4-6)

Cada carpeta es un proyecto Spring Boot independiente (Maven), con arquitectura por capas:

```
controller/   -> expone los endpoints REST
service/      -> lógica de negocio (interfaz + implementación)
repository/   -> almacenamiento en memoria (listas)
model/        -> entidades de dominio
dto/          -> objetos de request/response
exception/    -> excepciones y manejo global de errores
```

Los datos se almacenan en listas en memoria (sin base de datos) y el intercambio de información es JSON.

### Cómo ejecutar cada API

Desde la carpeta del ejercicio correspondiente:

```bash
./mvnw spring-boot:run
```

| Ejercicio | Puerto | Base path |
|---|---|---|
| Ejercicio4 (Biblioteca) | 8081 | `/api/libros` |
| Ejercicio5 (Cursos) | 8082 | `/api/cursos` |
| Ejercicio6 (Hotel) | 8083 | `/api/reservas` |

Cada API fue probada manualmente con `curl` ejercitando el flujo completo (crear, consultar, actualizar, eliminar/cancelar) y los casos de error (404, 400, 409).
