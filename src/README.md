# java-spring-boot-3-III-clinic-manager

This project was generated with [Spring Initializr](https://start.spring.io/) version 3.2.1.

## Dependencies

- Spring Boot DevTools
- Lombok
- Spring Web
- Validation
- MySQL Driver
- Spring Data JPA
- Flyway Migration
- Spring Security
- JWT
- Springdoc-openapi

## Tools

- [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/);
- [Insomnia](https://app.insomnia.rest/);
- [MySQL](https://www.mysql.com/downloads/).

## Run on Terminal

```sh 
java -Dspring.profiles.active=prod -DDATASOURCE_URL=jdbc:mysql://127.0.0.1:3306/vollmed_api -DDATASOURCE_USERNAME=root -DDATASOURCE_PASSWORD=root -jar target/api-0.0.1-SNAPSHOT.jar
```

> Note: Caso haja problemas no reconhecimento dos parâmetros no Windows, utilizar aspas duplas conforme exemplo:

```sh
java "-Dspring.profiles.active=prod" -jar target/api-0.0.1-SNAPSHOT.jar
```