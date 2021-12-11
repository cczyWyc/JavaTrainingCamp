# Redis集群的配置

设想的是redis集群一共6个节点，端口分别为6000,6001,6002,6003,6004,6005首先，下面的配置文件以6000为例，其他五个同理
```shell
bind 0.0.0.0
protected-mode no
port 6000
tcp-backlog 511
timeout 0
tcp-keepalive 300
daemonize no
supervised no
pidfile /var/run/redis_6000.pid
loglevel notice
logfile ""
databases 16
always-show-logo yes
save 900 1
save 300 10
save 60 10000
stop-writes-on-bgsave-error yes
rdbcompression yes
rdbchecksum yes
dbfilename dump.rdb
dir ./
replica-serve-stale-data yes
replica-read-only yes
repl-diskless-sync no
repl-diskless-sync-delay 5
repl-disable-tcp-nodelay no
replica-priority 100
lazyfree-lazy-eviction no
lazyfree-lazy-expire no
lazyfree-lazy-server-del no
replica-lazy-flush no
appendonly no
appendfilename "appendonly.aof"
appendfsync everysec
no-appendfsync-on-rewrite no
auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
aof-load-truncated yes
aof-use-rdb-preamble yes
lua-time-limit 5000
slowlog-log-slower-than 10000
slowlog-max-len 128
latency-monitor-threshold 0
notify-keyspace-events ""
hash-max-ziplist-entries 512
hash-max-ziplist-value 64
list-max-ziplist-size -2
list-compress-depth 0
set-max-intset-entries 512
zset-max-ziplist-entries 128
zset-max-ziplist-value 64
hll-sparse-max-bytes 3000
stream-node-max-bytes 4096
stream-node-max-entries 100
activerehashing yes
client-output-buffer-limit normal 0 0 0
client-output-buffer-limit replica 256mb 64mb 60
client-output-buffer-limit pubsub 32mb 8mb 60
hz 10
dynamic-hz yes
aof-rewrite-incremental-fsync yes
rdb-save-incremental-fsync yes
cluster-enabled yes
cluster-config-file nodes-6000.conf
cluster-require-full-coverage no
```

创建docker-compose.yml，太简单了，这里就不用k8s编排了
```shell
version: '3'
services:
  redis1:
    image: docker.io/redis:latest
    container_name: redis1
    ports:
      - 6000:6000
    command: redis-server /usr/local/etc/redis/redis.conf
    volumes:
      - redis1.conf:/usr/local/etc/redis/redis.conf

  redis2:
    image: docker.io/redis:latest
    container_name: redis2
    ports:
      - 6001:6001
    command: redis-server /usr/local/etc/redis/redis.conf
    volumes:
      - redis2.conf:/usr/local/etc/redis/redis.conf

  redis3:
    image: docker.io/redis:latest
    container_name: redis3
    ports:
      - 6002:6002
    command: redis-server /usr/local/etc/redis/redis.conf
    volumes:
      - redis3.conf:/usr/local/etc/redis/redis.conf
  
  redis4:
    image: docker.io/redis:latest
    container_name: redis4
    ports:
      - 6003:6003
    command: redis-server /usr/local/etc/redis/redis.conf
    volumes:
      - redis4.conf:/usr/local/etc/redis/redis.conf

  redis5:
    image: docker.io/redis:latest
    container_name: redis5
    ports:
      - 6004:6004
    command: redis-server /usr/local/etc/redis/redis.conf
    volumes:
      - redis5.conf:/usr/local/etc/redis/redis.conf

  redis6:
    image: docker.io/redis:latest
    container_name: redis6
    ports:
      - 6005:6005
    command: redis-server /usr/local/etc/redis/redis.conf
    volumes:
      - redis6.conf:/usr/local/etc/redis/redis.conf
```

启动docker-compose
```shell
docker-compose up docker-compose.yml
```

后续进入redis1容器，创建redis集群即可
```shell
docker exec -it redis bash
redis-cli --cluster create host:6000 host:6001 host:6002 host:6003 host:6004 host:6005 --cluster-replicas
```
> 注意：上述创建集群的命令中，host是容器各自的ip地址，这取决于docker启动的时候所用的网络，详情可以查阅docker网络的相关知识

然后连接至其中一个节点，执行cluster info即可看到redis集群的相关信息