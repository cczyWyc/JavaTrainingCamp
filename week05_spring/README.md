# 第五周作业说明

- [X] 第二题（必做）：写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）</br>
本题分别使用了xml、spring的自动注解以及手动java代码的方式实现了spring bean的装配，目录对应说明：
* week05_spring/src/main/java/com/cczyWyc/task/task_02/xml：xml方式装配
* week05_spring/src/main/java/com/cczyWyc/task/task_02/annotation：注解方式装配
* week05_spring/src/main/java/com/cczyWyc/task/task_02/customize：手动代码方式装配

- [X] 第十题（必做）：研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：</br>
  1）使用 JDBC 原生接口，实现数据库的增删改查操作。</br>
  2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。</br>
  3）配置 Hikari 连接池，改进上述操作。提交代码到 GitHub</br>
本题前两个小题采用了Java原生的JDBC接口的方式连接mysql数据库，代码位置：week05_spring/src/main/java/com/cczyWyc/task/task_10</br>

第3小问，要求使用hikari连接池的方式，由于在springboot2.x以后的版本，采用的默认数据库连接池就是Hikari，这里我单起了一个springboot项目，使用了springboot整合orm（jpa），采用了hikari连接池实现，顺带也联系了springboot整合orm的知识</br>
代码位置：week05_spring/springboot_orm_hikari/src/main/java

- [X] 第八题（必做）：实现自定义starter
本题stater代码由于我用gradle项目有点小问题，无奈我起了一个maven项目，位置是week05_spring/student_starter，然后使用mvn install将项目打包，得到target文件夹下面的student——starter-1.0.0.jar</br>
然后这个打好的jar包由于需要放在springboot项目里面作为一个starter测试，我这里直接复用了hikari连接池题目的springboot项目，在项目里面本地引入了这个jar，然后application.yml文件里面设置student属性，最后编写测试类，测试类位置week05_spring/springboot_orm_hikari/src/test/java/com/cczywyc/springboot_orm_hikari/task_08/test/StudentStarterTest.java，运行后成功输出hello cczyWyc，符合预期，所有的配置均生效。
