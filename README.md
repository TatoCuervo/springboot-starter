# springboot-starter
Sample Spring Boot Project

## Courses Rest API
Rest API Services exposing CRUD Operations over Courses, Topics & Lessons.

## Database
Consists of a PostgreSQL database & Adminer DB client running on Docker compose

## Dev Dependencies
* Maven
* Java 11
* Docker
* Install IntelliJ plugins: Lombok

## Launch the services locally
Startup local postgres database & adminer client:
```bash
$ docker-compose up -d
```
### How to test the local postgres database
1. Open adminer at http://localhost:8081
2. Fill out

| Field |Value |
| --- | --- |
| System | PostgreSQL |
| Server | postgres |
| Username | admin |
| Password | password |
| Database | postgres |

These values are defined in [docker-compose.yml](./docker-compose.yml)