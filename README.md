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
docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P '<YourStrong!Passw0rd>' -Q 'CREATE DATABASE [book_center]'
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
```DataModeler.ndm```需要使用[Navicat Data Modeler](https://www.navicat.com.cn/products#navicat-data-modeler)打开

使用Navicat Premium 12.0.15自带的Modeler在当前位置双击打开保存时闪退，复制到下面路径并重新启动Navicat，在Modeler选项卡内打开没有问题。
```
~/Library/Application Support/PremiumSoft CyberTech/Navicat CC/Navicat Premium/Profiles/
```

使用Modeler输出建表语句时不要选择生成Drop语句

生成mssql的建表语句后要手动将```GO```替换成```;```分号，否则会抛出sql语法错误（因为错误地使用```\n```换行符分割语句，另外```SQLServerDriver```不接受```GO```语句）