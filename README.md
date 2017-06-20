# Spring Boot Rest API - Example

Simple example of a Rest API using Spring Boot with these following HTTP methods : *POST, GET, DELETE* and *PUT*.

## Maven Dependencies

* SpringBoot 1.5.4
* MySQL
* JPA
* Hibernate


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
-v /storage/mariadb:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=root \
mariadb:latest``

*PhpAdmin (Optional)*

``docker run --name myadmin -d \
--restart=always \
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
- [x] Database
- [ ] Spring Rest Docs

## Kafka Development Environment with Landoop

*Running Kafka CLI*

``docker run --rm -it --net=host landoop/fast-data-dev bash``

*Creating a topic*

``kafk-topics --zookeeper 127.0.0.1:2181 --create --topic employee --partition 3 --replication-factor 1``

*Find more in [Landoop Documentation](https://github.com/Landoop/fast-data-dev/)*
