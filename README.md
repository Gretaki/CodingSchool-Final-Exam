# Chess club

This application is for chess club administrators. It allows:

* register new members
* view list of existing members
* edit members
* delete members

Application has one member controller with CRUD operations.
API call examples are in postman collection file named: ***

## Technologies used:

* HTML
* CSS
* Javascript
* Java 17
* JUnit 5
* MySQL
* Spring Boot

## Preparations:

* need to create database named: chess-club
* root user password: 12345678

or run this command:

```shell
docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=12345678 -e MYSQL_DATABASE=chess-club bitnami/mysql:latest
```

* populate DB from file: fake_data.sql
