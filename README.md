# ms-usuarios

Microservicio de Gestión de Usuarios - Biblioteca Digital EDUCA+
Asignatura: Java: Diseño y Construcción de Soluciones Nativas en Nube

## Propósito
Gestionar el registro, la autenticación y los perfiles de los usuarios del
sistema (estudiantes, docentes y bibliotecarios).

## Tecnologías
- Java 21
- Spring Boot 3.3.0 (Web, Data JPA, Validation)
- Spring Security Crypto (cifrado BCrypt de contraseñas)
- MySQL 8
- Maven

## Estructura
```
src/main/java/com/educa/msusuarios/
├── config/       Configuración (cifrado de contraseñas)
├── controller/   Endpoints REST
├── service/      Lógica de negocio
├── repository/   Acceso a datos (Spring Data JPA)
├── model/        Entidad Usuario, enum RolUsuario y DTO de login
└── exception/    Manejo global de errores
```

## Requisitos previos
- JDK 21
- Maven 3.9 o superior
- MySQL 8 en ejecución
- Git

## Cómo ejecutarlo

1. Clonar el repositorio:
```bash
git clone https://github.com/mathiasAla/ms-usuarios.git
cd ms-usuarios
```

2. Crear la base de datos en MySQL:
```sql
CREATE DATABASE db_usuarios;
```

3. Editar `src/main/resources/application.properties` y colocar el usuario y
la contraseña de MySQL:
```properties
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD
```

4. Compilar y empaquetar (genera `target/ms-usuarios-1.0.0.jar`):
```bash
mvn clean package
```

5. Ejecutar:
```bash
java -jar target/ms-usuarios-1.0.0.jar
```
También se puede usar `mvn spring-boot:run`. El servicio queda en `http://localhost:8081`.
Las tablas se crean automáticamente al iniciar.

6. Probar: importar `docs/ms-usuarios.postman_collection.json` en Postman y
ejecutar las peticiones. Para crear un usuario de ejemplo:
```json
POST /api/usuarios
{
  "nombre": "Camila Rojas",
  "correo": "camila.rojas@educa.cl",
  "password": "clave123",
  "rol": "ESTUDIANTE"
}
```

## Endpoints

| Método | Ruta | Descripción | Código |
|---|---|---|---|
| GET | `/api/usuarios` | Listar usuarios | 200 |
| GET | `/api/usuarios/{id}` | Obtener un usuario | 200 / 404 |
| POST | `/api/usuarios` | Registrar usuario | 201 / 400 / 409 |
| PUT | `/api/usuarios/{id}` | Actualizar usuario | 200 / 400 / 404 / 409 |
| DELETE | `/api/usuarios/{id}` | Eliminar usuario | 204 / 404 |
| POST | `/api/usuarios/login` | Validar credenciales | 200 / 400 / 401 |

Roles válidos: `ESTUDIANTE`, `DOCENTE`, `BIBLIOTECARIO`.

## Seguridad
- Las contraseñas se almacenan cifradas con BCrypt y nunca se devuelven en las respuestas.
- El login responde con el mismo mensaje si falla el correo o la contraseña,
  para no revelar cuál de los dos datos es incorrecto.

## Control de versiones
Ramas: `main` (versión estable), `develop` (integración) y `feature/*`
(cada funcionalidad), integradas mediante Pull Request.