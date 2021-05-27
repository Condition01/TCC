#!/bin/bash

docker run -d --name mysqltcc -p 3306:3306 -v ~/mysqltcc:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_ROOT_HOST="%" -e MYSQL_DATABASE="IFEIRA" mysql:8.0
