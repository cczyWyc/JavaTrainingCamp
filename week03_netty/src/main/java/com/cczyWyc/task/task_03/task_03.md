# gateway2.0

> 基于Netty的网关2.0实现

## 网关实现
在网关1.0的基础上增加了过滤器
http request的过滤器，可以拦截请求、以及对请求做增强或者添加安全校验机制等
http response的过滤器，同样可以增强给客户端的响应，或者拦截到一部分的响应等

## 业务流程图
![avatar](gateway2.0.png)