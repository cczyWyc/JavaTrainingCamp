# Redis高可用

上一个文档中，只用salveof host port命令简单配置了redis的主流复制，一主一从，在这个题目中集合sentinel哨兵机制，重新来配置一个redis的主从，一主二从

使用docker启动三个redis的容器，其中一主两从
```shell
docker run -dit --name redis-master -p 6381:6379 -p 16381:16381 docker.io/redis:latest
docker run -dit --name redis-slave1 -p 6382:6379 -p 16382:16381 docker.io/redis:latest
docker run -dit --name redis-slave2 -p 6383:6379 -p 16383:16381 docker.io/redis:latest
```

然后分别在两个从的redis服务器上执行replicaof host port
```shell
docker exec -it redis-slave1 redis-cli
127.0.0.1:6379> replicaof 172.17.0.2 6379
OK
127.0.0.1:6379> info replication
# Replication
role:slave
master_host:172.17.0.2
master_port:6379
master_link_status:up
master_last_io_seconds_ago:8
master_sync_in_progress:0
slave_read_repl_offset:28
slave_repl_offset:28
slave_priority:100
slave_read_only:1
replica_announced:1
connected_slaves:0
master_failover_state:no-failover
master_replid:05aded2aead7ca054cb8c9297f9bdb6735931694
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:28
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:28

docker exec -it redis-slave2 redis-cli
127.0.0.1:6379> replicaof 172.17.0.2 6379
OK
127.0.0.1:6379> info replication
# Replication
role:slave
master_host:172.17.0.2
master_port:6379
master_link_status:up
master_last_io_seconds_ago:6
master_sync_in_progress:0
slave_read_repl_offset:294
slave_repl_offset:294
slave_priority:100
slave_read_only:1
replica_announced:1
connected_slaves:0
master_failover_state:no-failover
master_replid:05aded2aead7ca054cb8c9297f9bdb6735931694
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:294
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:267
repl_backlog_histlen:28
```

接下来配置sentinel哨兵
首先，先准备配置文件sentinel.conf
```editorconfig
port 16381
daemonize no
pidfile /var/run/redis-sentinel.pid
logfile ""
dir /tmp
sentinel monitor mymaster 192.168.101.104 6381 2
sentinel down-after-milliseconds mymaster 10000
sentinel failover-timeout mymaster 30000
sentinel parallel-syncs mymaster 1
```

然后通过docker cp命令将配置文件执行生效。需要先进入容器bash，再执行命令，不然会报错没有权限
```shell
docker cp sentinel.conf redis-master:/etc/sentinel.conf
docker exec -it redis-master bash
redis-sentinel /etc/sentinel.conf
```