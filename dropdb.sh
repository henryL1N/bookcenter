#!/bin/sh

for i in $(docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P '<YourStrong!Passw0rd>' -h-1 -Q "SELECT session_id FROM sys.dm_exec_sessions WHERE database_id  = db_id('BookCenter')" | grep -E "^ +\d")
do
    $(docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P '<YourStrong!Passw0rd>' -Q "KILL $i")
done
$(docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P '<YourStrong!Passw0rd>' -Q "DROP DATABASE BookCenter")
$(docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P '<YourStrong!Passw0rd>' -Q 'CREATE DATABASE [BookCenter] COLLATE Chinese_PRC_CI_AS')
