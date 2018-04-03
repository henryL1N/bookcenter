在docker中建立sql server环境

$ sudo docker pull microsoft/mssql-server-linux:2017-latest

在docker中运行sql server

$ docker run -e 'ACCEPT_EULA=Y' -e 'MSSQL_SA_PASSWORD=<YourStrong!Passw0rd>' -e 'MSSQL_PID=Developer' -p 1433:1433 --name sql_server2017 -d microsoft/mssql-server-linux:2017-latest

更改密码

$ docker exec -it sql_server2017 /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P '<YourStrong!Passw0rd>' -Q 'ALTER LOGIN SA WITH PASSWORD="ScutBookStore1"'