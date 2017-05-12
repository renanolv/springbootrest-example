# SpringBootRestExample


##Setting up Mariadb on Docker

docker run -d \
--restart=always \
--name mariadb \
-p 3306:3306 \
-v /etc/localtime:/etc/localtime:ro \
-v /storage/mariadb:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=root \
mariadb:latest

##Setting up PhpAdmin on Docker

docker run --name myadmin -d \
--restart=always \
-v /etc/localtime:/etc/localtime:ro \
--link mariadb:db  \
-p 8181:80 \
phpmyadmin/phpmyadmin
