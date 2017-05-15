# Spring Boot Rest API - Example

This example provides a Rest API using Spring Boot with HTTP methods like *POST, GET, DELETE* and *PUT*.

## Maven Dependencies

* SpringBoot 1.5.3
* MySQL
* JPA
* Hibernate


## Setting up Docker Environment

First, build the application

``./mvnw clean install -e ``

Next, launch the services using Docker Compose:

`` docker-compose -f dockerfile.yml build ``

## Configuration

|Spring Boot Configuration | Class or Java property files  |
|--------------------------|---|
|The Main Class | [EmployeeApplication](https://github.com/renancetauro/SpringBootRestExample/blob/master/src/main/java/employee/EmployeeApplication.java) |
|Properties Files | [application.properties](https://github.com/renancetauro/SpringBootRestExample/blob/master/src/main/resources/application.properties)

## Task List
- [x] SpringBoot
- [ ] Spring Rest Docs
- [x] Database
- [ ] Kafka

