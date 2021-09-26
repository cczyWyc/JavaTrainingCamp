# 使用压测工具对不同实现的httpserver进行测试
> 这三个都是使用BIO的方式实现了一个HttpServer，不同的是：</br>
> HttpServer01--单线程</br>
> HttPServer02--多线程</br>
> HttpServer03--线程池</br>
> 三者均设置Xmx512m启动，然后使用压测工具压测

## HttpServer01
```shell
Finished at 2021/9/26 星期日 21:35:31 (took 00:00:36.9964209)
Status 200:    137230
Status 303:    1757

RPS: 4471.6 (requests/second)
Max: 130ms
Min: 0ms
Avg: 1ms

  50%   below 0ms
  60%   below 1ms
  70%   below 1ms
  80%   below 1ms
  90%   below 2ms
  95%   below 5ms
  98%   below 9ms
  99%   below 13ms
99.9%   below 27ms
```

## HttpServer02
```shell
Finished at 2021/9/26 星期日 21:39:09 (took 00:00:37.4261703)
Status 200:    126913
Status 303:    2228

RPS: 4150.3 (requests/second)
Max: 212ms
Min: 0ms
Avg: 1.6ms

  50%   below 1ms
  60%   below 1ms
  70%   below 1ms
  80%   below 2ms
  90%   below 3ms
  95%   below 5ms
  98%   below 9ms
  99%   below 11ms
99.9%   below 25ms
```

## HttpServer03
```shell
133569  (RPS: 3604.1)                   Finished at 2021/9/26 星期日 21:43:49 (took 00:00:37.1667999)
133579  (RPS: 3604.4)                   Status 200:    129233
Status 303:    4347

RPS: 4303.9 (requests/second)
Max: 140ms
Min: 0ms
Avg: 0.6ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 1ms
  95%   below 2ms
  98%   below 9ms
  99%   below 11ms
99.9%   below 27ms
```

## 分析
压测的结果在课上老师已经讲了，也解释了httpserver在改进后性能的提升，大致的原因就是从httpserver01到httpserver03是由单线程到多线程再到线程池，这样对于线程这种宝贵资源的复用来提升网络io的性能。
这里我之所以还贴上这题的原因就是我本地压测的结果发现，httpserver01的性能始终是最好的，我主要关注的指标就是每秒可以处理的请求数，这一点我一直无法理解，所以这里麻烦老师帮我分析分析原因。