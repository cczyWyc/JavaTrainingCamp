# week07 作业说明

- [x] 第二题：按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
本题主要是使用不同的方式插入100w订单，然后对比不同的方式插入效率
> 最初我是在docker里面跑的mysql，后来发现实在是太慢太慢，这里不作为测试结果报告，由于我一直喜欢linux的操作，后来在服务器上直接安装了mysql来测试，服务器本身的硬盘和网络带宽比较垃圾，可能没有本地固态硬盘跑的数据好</br>
> 最近两周学习mysql相关的比较吃力，目前毕业一年，在公司使用的是pg，公司使用的jpa本身不咋写sql，都是框架已经完成了，也没有电商等大型库表的开发经验，上周设计的电商DDL本身很简陋，很多概念和常识也不知道，一些方法的使用心里也没有什么概念。希望老师这里可以给我一些学习数据库的建议
1. 循环一条条执行：参考OrderDaoImpl类中的insert方法，这种方式没有使用预处理，插入100w使用了2h5min
2. 使用prepareStatement预处理，等所有的sql预处理完了只执行提交一次，参考OrderDaoImpl类中insetBatch方法，耗时40min
3. 使用prepareStatement预处理，但是没10000条sql执行提交一次，参考OrderDaoImpl类中的insertBatchMultiple方法，耗时10s
4. 使用springboot和spring-data-jpa整合，详见本周文件夹下面springboot项目orm_insert_batch，使用分别配置druid和hikari连接池，直接使用原生saveAll接口插入，发现两者耗时差不多，使用druid比hikari要稍好，druid耗时：1h40min
   1. 使用orm方式批量插入的耗时结果有点出乎我的意料，我感觉应该是我哪里没有使用好，这应该是有优化的手段的
   2. 具体的优化放在后面的研究吧