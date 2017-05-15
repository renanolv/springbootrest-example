# Spring Boot Rest API - Example

This example provides a Rest API using Spring Boot with HTTP methods like *POST, GET, DELETE* and *PUT*.

## Maven Dependencies

* SpringBoot 1.5.3
* MySQL
* JPA
* Hibernate
* Kafka


## Setting up environment with Docker

First, build the application

``./mvnw clean install -e ``

Next, launch the services using Docker...

*Landoop Kafka Development Environment*
``docker run -d \
--restart=always \
--name=kafka \
-p 2181:2181 -p 3030:3030 -p 8081:8081 \
-p 8082:8082 -p 8083:8083 -p 9092:9092 \
-e ADV_HOST=127.0.0.1 \
landoop/fast-data-dev:latest``

*Mariadb*
``docker run -d \
--restart=always \
--name mariadb \
-p 3306:3306 \
-v /etc/localtime:/etc/localtime:ro \
-v /storage/mariadb:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=root \
mariadb:latest``

*PhpAdmin (Optional)*
``docker run --name myadmin -d \
--restart=always \
-v /etc/localtime:/etc/localtime:ro \
--link mariadb:db  \
-p 8181:80 \
phpmyadmin/phpmyadmin``

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
- [ ] JWT
