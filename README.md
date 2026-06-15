# Instituto Pacifico — DSY1103 Desarrollo FullStack I

## Descripción

Instituto Pacifico es un sistema de gestión académica basado en arquitectura de microservicios desarrollado para el Instituto Pacífico. El proyecto busca resolver problemas asociados a la gestión manual y descentralizada de procesos académicos, como matrículas, evaluaciones, asistencia, prácticas profesionales, finanzas y titulación.

La solución permite centralizar la información institucional, mejorar la trazabilidad de los procesos, reducir errores administrativos y facilitar la comunicación entre las distintas áreas académicas mediante servicios independientes e integrados.

---

## Equipo

| Nombre             | GitHub          |
| ------------------ | --------------- |
| Sebastián Barros   | @sebarros       |
| Alonso Contreras   | @thealonsiiniix |
| Sebastián Mansilla | @seba1384       |

---

## Microservicios Implementados

| #  | Microservicio  | Puerto | Descripción                                     |
| -- | -------------- | ------ | ----------------------------------------------- |
| 1  | ms-estudiantes | 8081   | Gestión de estudiantes y datos personales       |
| 2  | ms-docentes    | 8082   | Gestión de docentes, especialidades y contratos |
| 3  | ms-academico   | 8083   | Carreras, asignaturas, mallas y prerrequisitos  |
| 4  | ms-matriculas  | 8084   | Matrícula e inscripción de estudiantes          |
| 5  | ms-evaluacion  | 8085   | Evaluaciones, notas y reglas de aprobación      |
| 6  | ms-asistencia  | 8086   | Registro y control de asistencia                |
| 7  | ms-finanzas    | 8087   | Gestión de aranceles, becas y pagos             |
| 8  | ms-practicas   | 8088   | Gestión de prácticas profesionales              |
| 9  | ms-empresas    | 8089   | Empresas con convenio institucional             |
| 10 | ms-titulacion  | 8090   | Proceso de titulación y validación final        |

---

## API Gateway

Se implementó un API Gateway como punto único de entrada para los consumidores de las APIs. El Gateway centraliza el acceso a los distintos microservicios, simplificando la comunicación y mejorando la organización de la arquitectura.

Puerto del API Gateway:

| Servicio    | Puerto |
| ----------- | ------ |
| api-gateway | 8080   |

---

## Comunicación Entre Microservicios

El sistema implementa comunicación entre servicios mediante **Spring WebClient**, permitiendo validar información entre distintos módulos y aplicar reglas de negocio distribuidas.

Ejemplos de integración:

* Matrículas valida estudiantes y carreras antes de registrar una matrícula.
* Prácticas valida estudiantes, empresas y situación financiera.
* Titulación consulta evaluaciones para verificar requisitos académicos.

---

## Seguridad

Todos los microservicios se encuentran protegidos mediante **Spring Security** utilizando autenticación Basic Auth.

### Credenciales de prueba

| Usuario | Contraseña |
| ------- | ---------- |
| admin   | 1234       |
| user    | 1234       |

---

## Documentación de APIs

Cada microservicio incorpora documentación mediante **Swagger / OpenAPI**, permitiendo visualizar y probar los endpoints disponibles.

Ejemplo de acceso:

```text
http://localhost:8081/swagger
```

---

## Testing

Se implementaron pruebas unitarias utilizando:

* JUnit 5
* Mockito
* JaCoCo

Cobertura obtenida:

| Microservicio  | Cobertura |
| -------------- | --------- |
| ms-estudiantes | 100%      |
| ms-empresas    | 89%       |
| ms-matriculas  | 29%       |
| ms-titulacion  | 70%       |

---

## Tecnologías Utilizadas

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* H2 Database
* Spring WebClient
* Swagger / OpenAPI
* JUnit 5
* Mockito
* JaCoCo
* Docker
* Docker Compose
* Maven
* Postman
* Git
* GitHub

---

## Docker

El proyecto incorpora contenerización mediante Docker y Docker Compose para facilitar el despliegue y la ejecución de los servicios.

Ejemplo de arquitectura Docker:

```yaml
services:
  ms-productos:
    build: ./ms-productos

  ms-pedidos:
    build: ./ms-pedidos

  api-gateway:
    build: ./api-gateway
```

---

## Cómo Ejecutar el Proyecto

### Opción 1: IntelliJ IDEA

1. Clonar el repositorio.

```bash
git clone [URL_DEL_REPOSITORIO]
```

2. Abrir el proyecto en IntelliJ IDEA.

3. Ejecutar cada microservicio.

4. Acceder a Swagger para probar los endpoints.

---

### Opción 2: Docker Compose

Desde la raíz del proyecto:

```bash
docker-compose up --build
```

Esto levantará automáticamente los servicios configurados y el API Gateway.

---

## Arquitectura General

El proyecto está compuesto por microservicios independientes que exponen APIs REST y mantienen su propia persistencia de datos.

La comunicación entre servicios se realiza mediante WebClient, mientras que el API Gateway actúa como punto central de acceso. Además, la arquitectura incorpora seguridad mediante Spring Security, documentación con Swagger y pruebas unitarias con JUnit, Mockito y JaCoCo.

---

## Estado del Proyecto

✅ En Proceso — 2026