在docker中建立sql server环境
```
sudo docker pull microsoft/mssql-server-linux:2017-latest
```
在docker中运行sql server
```
docker run -e 'ACCEPT_EULA=Y' -e 'MSSQL_SA_PASSWORD=<YourStrong!Passw0rd>' -p 1433:1433 --name mssql -v sqlvolume:/var/opt/mssql -d microsoft/mssql-server-linux:2017-latest
```
新建数据库
```
docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P '<YourStrong!Passw0rd>' -Q 'CREATE DATABASE [BookCenter] COLLATE Chinese_PRC_CI_AS'
```
数据库登录信息
```
Host:           localhost
Port:           1433
Database:       book_center
Authentication: SQL Server Authentication
Username:       SA
Password:       <YourStrong!Passw0rd>
```
运行
```
./mvnw -Pprod
```
服务地址

[http://localhost:8080](http://localhost:8080)

[http://127.0.0.1:8080](http://127.0.0.1:8080)

注意！

首次运行时将向数据库插入基础数据，约耗时5分钟，请耐心等待。

运行成功后控制台将显示如下信息：
```
	Application 'BookCenter' is running! Access URLs:
	Local: 		http://localhost:8080
	External: 	http://127.0.0.1:8080
	Profile(s): 	[prod]
```
