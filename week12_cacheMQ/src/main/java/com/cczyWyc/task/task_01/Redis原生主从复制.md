# Redis主从复制

使用docker启动两个redis容器
```shell
docker run -dit --name redis-master -p 6379:6379 docker.io/redis:latest
docker run -dit --name redis-master -p 6380:6379 docker.io/redis:latest
```

redis中有个slaveof host port命令，可以将自己设置为host上redis的从属(slave)</br>
在6380的docker容器上，进入redis自带的客户端的命令行，执行slave 172.17.0.2 6379,显示OK代表主从设置成功，接下来便可以输入info分别查看两边的信息
> 这里docker容器的ip地址可以通过docker inspect查看