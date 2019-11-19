Build Package:
mvn clean install -P <env> -DskipTests
e.g. --> mvn clean install -P dev -DskipTests

Docker MySQL:
1. docker pull mysql/mysql-server:5.7
2. docker run -p 3301:3306 --name=mysql1 -d mysql/mysql-server:5.7
3. To Find default password:
 shell> docker logs mysql1 2>&1 | grep GENERATED
4. To run mysql prompt
docker exec -it mysql1 mysql -uroot -p
5. To change root user password
ALTER USER 'root'@'localhost' IDENTIFIED BY 'password';
6. Grant privilage to access from any host
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'password' WITH GRANT OPTION;