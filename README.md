# Instituto Pacifico — DSY1103 Desarrollo FullStack 1

## Descripción
Sistema de gestión académica basado en arquitectura de microservicios para el Instituto Pacífico, orientado a digitalizar y automatizar procesos críticos como matrícula, evaluación, asistencia, aranceles y prácticas profesionales. La solución permite el registro en tiempo real de notas y asistencia con trazabilidad de cambios, integración entre servicios y aplicación de reglas de negocio, mejorando la eficiencia operativa y reduciendo errores en la gestión institucional.

## Equipo
| Nombre | GitHub |
|--------|--------|
| Sebastián Barros | @sebarros |
| Alonso Contreras | @thealonsiiniix |
| Sebastián Mansilla | @seba1384 |

## Microservicios Implementados
| # | Microservicio | Puerto | Descripción |
|---|---------------|--------|-------------|
| 1 | ms-estudiantes | 8081 | Gestión de estudiantes (datos personales, estado académico) |
| 2 | ms-docentes | 8082 | Gestión de docentes, especialidades y contratos |
| 3 | ms-academico | 8083 | (Fusión) Carreras + asignaturas + mallas + prerrequisitos |
| 4 | ms-matriculas | 8084 | Matrícula, inscripción a secciones |
| 5 | ms-evaluacion | 8085 | (Fusión) Notas + evaluaciones + reglas de aprobación |
| 6 | ms-asistencia | 8086 | Registro de asistencia y regla del 25%. |
| 7 | ms-finanzas | 8087 | (Fusión) Aranceles + becas + convenios de pago |
| 8 | ms-practicas | 8088 | Gestión de prácticas profesionales y validaciones |
| 9 | ms-empresas | 8089 | Empresas con convenio y contactos |
| 10 | ms-titulación | 8090 | Proceso de titulación y estado final del estudiante |

## Tecnologías Utilizadas
- Java 17 / Spring Boot 3.x
- JPA + Hibernate
- MySQL / PostgreSQL
- WebClient / Feign Client
- SLF4J para logs

## Cómo Ejecutar el Proyecto
1. Clonar el repositorio: `git clone [URL]`
2. Configurar la base de datos en `application.properties`
3. Ejecutar cada microservicio: `./mvnw spring-boot:run`

## Estado del Proyecto
🔄 En desarrollo — EP2 2025