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
登录信息
```
Host:           localhost
Port:           1433
Database:       book_center
Authentication: SQL Server Authentication
Username:       SA
Password:       <YourStrong!Passw0rd>
```
