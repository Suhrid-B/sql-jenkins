#!/bin/bash

sql_user=$1
sql_pwd=$2
echo "getting table values for Employees"
mysql --user=$sql_user --password=$sql_pwd employees<<EOFMYSQL
select * from employees order by hire_date;
EOFMYSQL
