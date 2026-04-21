# Instituto Pacifico — DSY1103 Desarrollo FullStack 1

## Descripción
[Sistema de gestión académica que automatiza reglas clave como prerrequisitos, asistencia, notas y estado financiero de estudiantes,
utilizando una arquitectura de microservicios para garantizar control, consistencia y eficiencia en los procesos educativos.]

## Equipo
| Nombre | GitHub |
|--------|--------|
| Sebastián Barros | @sebarros |
| Alonso Contreras | @thealonsiiniix |
| Sebastián Mansilla | @seba1384 |

## Microservicios Implementados
| # | Microservicio | Puerto | Descripción |
|---|---------------|--------|-------------|
| 1 | ms-estudiantes | 8081 | Gestión del ciclo de vida y datos personales del alumno |
| 2 | ms-docentes | 8082 | Administración de perfiles académicos y especialidades |
| 3 | ms-carreras | 8082 | Definición de mallas curriculares y sedes regionales |
| 4 | ms-asignaturas | 8082 | Gestión de ramos, créditos y prerrequisitos |
| 5 | ms-notas | 8082 | Registro de calificaciones y promedio para aprobación |
| 6 | ms-asistencia | 8082 | Control diario de asistencia y cálculo de reprobación |
| 7 | ms-aranceles | 8082 | Gestión de pagos, becas FES y convenios Banco Estado     |
| 8 | ms-practicas | 8082 | Supervisión de pasantías y nexo con sector productivo |
| 9 | ms-empresas | 8082 | Administración de convenios con las empresas |
| 10 | ms-matriculas | 8082 | Control de procesos de inscripción anual y secciones |

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