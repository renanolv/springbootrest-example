docker run -d \
--restart=always \
--name=kafka \
-v /etc/localtime:/etc/localtime:ro \
-p 2181:2181 -p 3030:3030 -p 8081:8081 \
-p 8082:8082 -p 8083:8083 -p 9092:9092 \
-e ADV_HOST=127.0.0.1 \
landoop/fast-data-dev:latest

docker run -d \
--restart=always \
--name mariadb \
-p 3306:3306 \
-v /etc/localtime:/etc/localtime:ro \
-v /storage/mariadb:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=root \
mariadb:latest

docker run --name myadmin -d \
--restart=always \
-v /etc/localtime:/etc/localtime:ro \
--link mariadb:db  \
-p 8181:80 \
phpmyadmin/phpmyadmin



