# 🎓 Instituto Pacífico — DSY1103 Desarrollo FullStack 1

Sistema de gestión académica basado en arquitectura de microservicios desarrollado para el Instituto Pacífico.  
El proyecto busca digitalizar y automatizar procesos críticos de la institución, permitiendo una gestión académica moderna, escalable y desacoplada.

---

# 📌 Descripción del Proyecto

La plataforma permite administrar procesos académicos como:

- Matrículas
- Evaluaciones
- Asistencia
- Finanzas
- Prácticas profesionales
- Titulación
- Gestión de estudiantes y docentes

La solución implementa una arquitectura basada en microservicios usando Spring Boot, permitiendo independencia entre módulos, comunicación mediante APIs REST y aplicación de reglas de negocio distribuidas.

---

# 👥 Equipo de Desarrollo

| Nombre | GitHub |
|--------|--------|
| Sebastián Barros | @sebarros |
| Alonso Contreras | @thealonsiiniix |
| Sebastián Mansilla | @seba1384 |

---

# 🏗️ Arquitectura de Microservicios

| # | Microservicio | Puerto | Descripción |
|---|---------------|--------|-------------|
| 1 | `ms-estudiantes` | 8081 | Gestión de estudiantes y datos personales |
| 2 | `ms-docentes` | 8082 | Gestión de docentes, contratos y especialidades |
| 3 | `ms-academico` | 8083 | Carreras, asignaturas, mallas y prerrequisitos |
| 4 | `ms-matriculas` | 8084 | Matrícula e inscripción de estudiantes |
| 5 | `ms-evaluacion` | 8085 | Evaluaciones, notas y reglas de aprobación |
| 6 | `ms-asistencia` | 8086 | Registro y control de asistencia |
| 7 | `ms-finanzas` | 8087 | Aranceles, becas y pagos |
| 8 | `ms-practicas` | 8088 | Gestión de prácticas profesionales |
| 9 | `ms-empresas` | 8089 | Empresas con convenio institucional |
| 10 | `ms-titulacion` | 8090 | Proceso de titulación y estado final |

---

# 🔄 Comunicación Entre Microservicios

El sistema implementa integración entre servicios mediante `WebClient`.

## Relaciones implementadas

- `ms-matriculas`
    - consume `ms-estudiantes`
    - consume `ms-academico`

- `ms-practicas`
    - consume `ms-estudiantes`
    - consume `ms-empresas`

- `ms-titulacion`
    - consume `ms-evaluacion`

---

# 🛠️ Tecnologías Utilizadas

## Backend
- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- Hibernate
- WebClient

## Base de Datos
- H2 Database

## Herramientas
- Maven
- Postman
- Git & GitHub
- IntelliJ IDEA

---

# 🔐 Seguridad

Cada microservicio cuenta con autenticación básica utilizando Spring Security.

## Usuarios de prueba

| Usuario | Contraseña | Rol |
|---------|-------------|-----|
| admin | 1234 | ADMIN |
| user | 1234 | USER |

---

# 📂 Estructura General

```bash
ms-estudiantes/
 ├── controller/
 ├── service/
 ├── repository/
 ├── model/
 ├── config/
 └── resources/

ms-matriculas/
 ├── controller/
 ├── service/
 ├── dto/
 ├── repository/
 ├── model/
 └── config/
```

# 📌 Estado Actual del Proyecto

| Estado | Descripción |
|--------|-------------|
| 🔄 En desarrollo | EP2 — Arquitectura y microservicios funcionales |

---

# 📖 Objetivo Académico

Proyecto desarrollado para la asignatura:

## DSY1103 — Desarrollo FullStack 1

Instituto Pacífico — 2026