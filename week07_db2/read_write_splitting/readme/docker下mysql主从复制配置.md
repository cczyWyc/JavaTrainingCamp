# 使用docker配置MySQL主从复制的记录

1. 先起两个mysql容器，一个作为主，一个作为从
```shell
docker run --name master -p 3316:3306 -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_ROOT_HOST=% -d mysql:latest
```
```shell
docker run --name slave -p 3326:3306 -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_ROOT_HOST=% -d mysql:latest
```

2. 分别进入两个docker容器中
```shell
docker exec -it master bash
```
```shell
docker exec -it slave bash
```

3. 修改配置文件，由于docker容器里面没有vi命令，可以采用如下的方式修改：
   1. 主
   ```shell
   echo '[mysqld]
   pid-file        = /var/run/mysqld/mysqld.pid
   socket          = /var/run/mysqld/mysqld.sock
   datadir         = /var/lib/mysql
   secure-file-priv= NULL
   symbolic-links=0
   #启用二进制日志
   log-bin=mysql-bin
   #设置服务器唯一Id，这里省事直接写1
   server-id=1
   !includedir /etc/mysql/conf.d/' > /etc/mysql/my.cnf
   ```
   2. 从
   ```shell
   echo '[mysqld]
   pid-file        = /var/run/mysqld/mysqld.pid
   socket          = /var/run/mysqld/mysqld.sock
   datadir         = /var/lib/mysql
   secure-file-priv= NULL
   symbolic-links=0
   #启用二进制日志
   log-bin=mysql-bin
   #设置服务器唯一Id
   server-id=2
   !includedir /etc/mysql/conf.d/' > /etc/mysql/my.cnf
   ```

4. 宿主机上，重启docker容器
```shell
docker restart msater slave
```

5. 配置同步
   1. 进入master
   ```shell
   docker exec -it master bash
   mysql -uroot -p123456
   show master status;
   +------------------+----------+--------------+------------------+-------------------+
   | File             | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
   +------------------+----------+--------------+------------------+-------------------+
   | mysql-bin.000001 |      155 |              |                  |                   |
   +------------------+----------+--------------+------------------+-------------------+
   1 row in set (0.00 sec)
   ```
   这里需要记住File和Position，后续配置slave需要用到
   2. 宿主机上查看master ip
   ```shell
   docker inspect master | grep IPA
   ```
   3. slave上
   ```shell
   mysql> change master to master_host='[masterIP]',master_user='root',master_log_file='mysql-bin.000001',master_log_pos=155,master_port=3306,master_password='123456';
   mysql> start slave;
   mysql> show slave status\G
   ```
   执行完上述命令，可以看到slave状态，如果是mysql8.0以上的版本，应该是会有一个如下的报错：
   ```shell
   Last_IO_Error: error connecting to master 'root@172.17.0.2:3306' - retry-time: 60 retries: 1 message: Authentication plugin 'caching_sha2_password' reported error: Authentication requires secure connection.
   ```
   我去MySQL官网看到说明，大致意思是要现在slave连接master获取public-key，命令如下：
   ```shell
   mysql --ssl-mode=DISABLED -h [masterIP] -uroot -p123456 --get-server-public-key
   ```
   4. 然后重新回到slave，重新连接slave数据库，执行如下：
   ```shell
   show slave status\G
   ```
   看到类似如下的回显，说明配置成功：
   ```shell
   *************************** 1. row ***************************
               Slave_IO_State: Waiting for source to send event
                  Master_Host: 172.17.0.2
                  Master_User: root
                  Master_Port: 3306
                Connect_Retry: 60
              Master_Log_File: mysql-bin.000001
          Read_Master_Log_Pos: 156
               Relay_Log_File: b893f3c915ec-relay-bin.000003
                Relay_Log_Pos: 324
        Relay_Master_Log_File: mysql-bin.000001
             Slave_IO_Running: Yes
            Slave_SQL_Running: Yes
              Replicate_Do_DB: 
          Replicate_Ignore_DB: 
           Replicate_Do_Table: 
       Replicate_Ignore_Table: 
      Replicate_Wild_Do_Table: 
   Replicate_Wild_Ignore_Table:
                   Last_Errno: 0
                   Last_Error:
                 Skip_Counter: 0
          Exec_Master_Log_Pos: 156
              Relay_Log_Space: 708
              Until_Condition: None
               Until_Log_File:
                Until_Log_Pos: 0
           Master_SSL_Allowed: No
           Master_SSL_CA_File:
           Master_SSL_CA_Path:
              Master_SSL_Cert:
            Master_SSL_Cipher:
               Master_SSL_Key:
        Seconds_Behind_Master: 0
   Master_SSL_Verify_Server_Cert: No
                Last_IO_Errno: 0
                Last_IO_Error:
               Last_SQL_Errno: 0
               Last_SQL_Error:
   Replicate_Ignore_Server_Ids:
             Master_Server_Id: 1
   Master_UUID: aabe0678-3fd6-11ec-b005-0242ac110002
   Master_Info_File: mysql.slave_master_info
                    SQL_Delay: 0
          SQL_Remaining_Delay: NULL
      Slave_SQL_Running_State: Replica has read all relay log; waiting for more updates
           Master_Retry_Count: 86400
                  Master_Bind:
      Last_IO_Error_Timestamp:
     Last_SQL_Error_Timestamp:
               Master_SSL_Crl:
           Master_SSL_Crlpath:
           Retrieved_Gtid_Set:
            Executed_Gtid_Set:
                Auto_Position: 0
         Replicate_Rewrite_DB:
                 Channel_Name:
           Master_TLS_Version:
       Master_public_key_path:
        Get_master_public_key: 0
            Network_Namespace:
   1 row in set, 1 warning (0.00 sec)
   ```