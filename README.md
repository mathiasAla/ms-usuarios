# ms-usuarios

Microservicio de Gestión de Usuarios — Biblioteca Digital EDUCA+
Asignatura: Java: Diseño y Construcción de Soluciones Nativas en Nube

## Propósito general
Gestionar el registro, autenticación y perfiles de los usuarios del sistema de estudiantes, docentes y bibliotecarios.

## Funciones clave
- Registrar usuarios
- realizar un inicio de sesión seguro
- gestionar los roles y permisos de estudiantes, docentes y bibliotecarios.

## Interacciones con otros microservicios
Es consultado por ms-prestamos y ms-recursos-digitales para verificar la identidad y los permisos antes de realizar una acción.

## Justificación del aislamiento
Contiene datos sensibles, como credenciales e información personal, por lo que requiere alta seguridad y aislarlo permite proteger estos datos y controlar mejor el acceso.

## Tecnología
Java 17 + Spring Boot + Maven
