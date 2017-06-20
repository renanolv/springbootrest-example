# Spring Boot Rest API - Example

Simple example of a Spring Boot Rest application with some CRUD operations.

## Maven Dependencies

* SpringBoot 1.5.4
* MySQL
* JPA
* Hibernate

## Setting up environment with Docker

First, build the application

``./mvnw clean install -e ``

Next, launch the services using Docker...

*Mariadb*

``docker run -d \
--restart=always \
--name mariadb \
-p 3306:3306 \
-v /storage/mariadb:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=root \
mariadb:latest``

*PhpAdmin (Optional)*

``docker run --name myadmin -d \
--restart=always \
--link mariadb:db  \
-p 8181:80 \
phpmyadmin/phpmyadmin``

## Other

|Spring Boot Configuration | Class or Java property files  |
|--------------------------|---|
|The Main Class | [EmployeeApplication](https://github.com/renancetauro/spring-boot-restapi/blob/master/src/main/java/example/CustomerApplication.java) |
|Properties Files | [application.properties](https://github.com/renancetauro/spring-boot-restapi/blob/master/src/main/resources/application.properties)
