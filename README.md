# EduCore — DSY1103 Desarrollo FullStack 1

## Descripción

EduCore es un sistema de gestión académica basado en arquitectura de microservicios desarrollado para el Instituto Pacífico. El proyecto busca resolver
problemas asociados a la gestión manual y descentralizada de procesos académicos, como matrículas, evaluaciones, asistencia, prácticas profesionales,
finanzas y titulación. La solución permite centralizar la información institucional, mejorar la trazabilidad de los procesos, reducir errores administrativos
y facilitar la comunicación entre las distintas áreas académicas mediante servicios independientes e integrados.

## Equipo

| Nombre | GitHub |
|--------|--------|
| Sebastián Barros | @sebarros |
| Alonso Contreras | @thealonsiiniix |
| Sebastián Mansilla | @seba1384 |

## Microservicios Implementados

| # | Microservicio | Puerto | Descripción |
|---|---------------|--------|-------------|
| 1 | ms-estudiantes | 8081 | Gestión de estudiantes y datos personales |
| 2 | ms-docentes | 8082 | Gestión de docentes, especialidades y contratos |
| 3 | ms-academico | 8083 | Carreras, asignaturas, mallas y prerrequisitos |
| 4 | ms-matriculas | 8084 | Matrícula e inscripción de estudiantes |
| 5 | ms-evaluacion | 8085 | Evaluaciones, notas y reglas de aprobación |
| 6 | ms-asistencia | 8086 | Registro y control de asistencia |
| 7 | ms-finanzas | 8087 | Gestión de aranceles, becas y pagos |
| 8 | ms-practicas | 8088 | Gestión de prácticas profesionales |
| 9 | ms-empresas | 8089 | Empresas con convenio institucional |
| 10 | ms-titulacion | 8090 | Proceso de titulación y validación final |

## Integración Entre Microservicios

El sistema implementa comunicación entre servicios mediante **Spring WebClient**.


## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- Hibernate
- H2 Database
- WebClient
- Maven
- Postman
- Git y GitHub

## Cómo Ejecutar el Proyecto

1. Clonar el repositorio:

```bash
git clone [URL_DEL_REPOSITORIO]
```

2. Abrir el proyecto en IntelliJ IDEA.

3. Ejecutar cada microservicio de manera independiente.

4. Verificar que los servicios estén disponibles en sus respectivos puertos.

5. Realizar pruebas mediante Postman utilizando autenticación Basic Auth.

### Credenciales de prueba

| Usuario | Contraseña |
|----------|------------|
| admin | 1234 |
| user | 1234 |

## Arquitectura General

El proyecto está compuesto por diez microservicios independientes que exponen APIs REST y mantienen su propia base de datos. La comunicación entre servicios se realiza mediante llamadas HTTP utilizando WebClient, permitiendo un bajo acoplamiento y una arquitectura distribuida.

## Estado del Proyecto

🔄 En desarrollo — EP2 2026