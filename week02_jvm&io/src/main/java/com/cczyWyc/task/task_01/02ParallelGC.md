# 使用并行GC演练GCLogAnalysis的例子
本例中GCLogAnalysis是老师上课举例的案例，模拟了在短时间内年轻代和老年代对象创建的过程，此部分将使用并发GC的方式对启动此并且在配置不同呢的参数的情况下对此案例进行分析

使用如下的命令启动GCLogAnalysis
```shell
java -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
```
对于上述命令的说明：这里第一次启动这个java类，我本地环境变量使用的jdk版本是jdk1.8，因此默认的GC策略是Parallel GC。
我没有指定最大堆内存和初始堆内存的大小，由于我主机的内存是16G，因此这里默认使用我物理机内存的1/4，也就是4G。打印的GC日志如下：
```shell
executing...
2021-09-25T16:41:36.180+0800: [GC (Allocation Failure) [PSYoungGen: 65291K->10737K(76288K)] 65291K->19648K(251392K), 0.0061695 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T16:41:36.220+0800: [GC (Allocation Failure) [PSYoungGen: 76273K->10743K(141824K)] 85184K->43616K(316928K), 0.0071686 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T16:41:36.283+0800: [GC (Allocation Failure) [PSYoungGen: 141815K->10742K(141824K)] 174688K->89629K(316928K), 0.0121622 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T16:41:36.331+0800: [GC (Allocation Failure) [PSYoungGen: 141670K->10747K(272896K)] 220556K->136189K(448000K), 0.0111615 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T16:41:36.343+0800: [Full GC (Ergonomics) [PSYoungGen: 10747K->0K(272896K)] [ParOldGen: 125442K->117407K(264704K)] 136189K->117407K(537600K), [Metaspace: 2697K->2697K(1056768K)], 0.0212622 secs] [Times: user=0.20 sys=0.00, real=0.02 secs] 
2021-09-25T16:41:36.465+0800: [GC (Allocation Failure) [PSYoungGen: 262144K->10743K(272896K)] 379551K->200260K(537600K), 0.0185329 secs] [Times: user=0.03 sys=0.17, real=0.02 secs] 
2021-09-25T16:41:36.484+0800: [Full GC (Ergonomics) [PSYoungGen: 10743K->0K(272896K)] [ParOldGen: 189517K->169856K(377856K)] 200260K->169856K(650752K), [Metaspace: 2697K->2697K(1056768K)], 0.0292229 secs] [Times: user=0.41 sys=0.00, real=0.03 secs] 
2021-09-25T16:41:36.572+0800: [GC (Allocation Failure) [PSYoungGen: 262144K->81164K(482304K)] 432000K->251021K(860160K), 0.0147049 secs] [Times: user=0.08 sys=0.13, real=0.01 secs] 
2021-09-25T16:41:36.707+0800: [GC (Allocation Failure) [PSYoungGen: 482060K->105968K(520704K)] 651917K->344436K(898560K), 0.0308246 secs] [Times: user=0.03 sys=0.38, real=0.03 secs] 
2021-09-25T16:41:36.837+0800: [GC (Allocation Failure) [PSYoungGen: 520688K->158703K(738304K)] 759156K->434209K(1116160K), 0.0322607 secs] [Times: user=0.08 sys=0.33, real=0.03 secs] 
2021-09-25T16:41:36.992+0800: [GC (Allocation Failure) [PSYoungGen: 738221K->202229K(781824K)] 1013727K->521192K(1159680K), 0.0391027 secs] [Times: user=0.08 sys=0.33, real=0.04 secs] 
2021-09-25T16:41:37.031+0800: [Full GC (Ergonomics) [PSYoungGen: 202229K->0K(781824K)] [ParOldGen: 318962K->325859K(558080K)] 521192K->325859K(1339904K), [Metaspace: 2697K->2697K(1056768K)], 0.0527718 secs] [Times: user=0.77 sys=0.03, real=0.05 secs] 
Executed finish,The number of generate objects is:9726
Heap
 PSYoungGen      total 781824K, used 250111K [0x000000076b180000, 0x00000007ba600000, 0x00000007c0000000)
  eden space 579584K, 43% used [0x000000076b180000,0x000000077a5bfdb8,0x000000078e780000)
  from space 202240K, 0% used [0x000000078e780000,0x000000078e780000,0x000000079ad00000)
  to   space 253952K, 0% used [0x00000007aae00000,0x00000007aae00000,0x00000007ba600000)
 ParOldGen       total 558080K, used 325859K [0x00000006c1400000, 0x00000006e3500000, 0x000000076b180000)
  object space 558080K, 58% used [0x00000006c1400000,0x00000006d5238e20,0x00000006e3500000)
 Metaspace       used 2704K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 290K, capacity 386K, committed 512K, reserved 1048576K
```
## 分析过程
1. 可以看到，这里一共生成对象的次数是9726次
2. 对于一行GC日志来看，前面一部分表示young区内存的大小，PSYoungGen表示发生了一次youngGc，将young区的内存从65291k减小到10737k；后面一部分表示整个堆的大小，可以看到整个堆的大小由65291k减小到19648k，而且我们还可以注意到的就是在最开始整个young区对象占用的内存和整个堆中对象占用的内存是一样的，都是65291k，这说明最开始还没有对象晋升到old区，0.061695s表示第一次执行gc的时间。
3. 还是继续来看第一行的日志，young内存的回收65291k-10737k，减小了54554k的内存，再看整个堆的内存65291k-19648k，一共减小了45643k的内存，说明大约还有54554k-45643k=8911k大小的内存还没有回收掉，那么这一部分内存所属的对象就晋升到了old区。
4. 最后，通过第一行的日志还可以看到整个堆的大小在一直不断的增加，这是因为在启动参数里面没有指定初始堆的大小，导致初始堆在不断的分配大小。
5. 后面继续执行了几次young GC，紧接着可以看到执行了一FullGC，FullGC是对整个堆内存垃圾进行回收，包括young区和old区。
6. 然后分析到这里我就发现事情的不对劲了，为啥在第五条日志和第七条日志突然发生了两次连续的FullGC呢？而且还发现这最大的堆内存根本没有4G啊，内存一直很小，所以才会频繁发生FullGC。
7. 经过一番查证后得知，我在没有指定xmx和xms的情况下，idea里面会默认指定，只有几百兆，所以导致了其实堆内存很小，才会频繁的GC。

所以我在idea里面重新指定了启动参数
```shell
java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g GCLogAnalysis
```
再次启动这个程序，GC的日志如下：
```shell
executing...
2021-09-26T01:00:33.579+0800: [GC (Allocation Failure) [PSYoungGen: 262144K->43507K(305664K)] 262144K->79998K(1005056K), 0.0151454 secs] [Times: user=0.00 sys=0.02, real=0.02 secs] 
2021-09-26T01:00:33.661+0800: [GC (Allocation Failure) [PSYoungGen: 305651K->43505K(305664K)] 342142K->154335K(1005056K), 0.0206535 secs] [Times: user=0.05 sys=0.16, real=0.02 secs] 
2021-09-26T01:00:33.744+0800: [GC (Allocation Failure) [PSYoungGen: 305649K->43519K(305664K)] 416479K->224887K(1005056K), 0.0195669 secs] [Times: user=0.03 sys=0.17, real=0.02 secs] 
2021-09-26T01:00:33.836+0800: [GC (Allocation Failure) [PSYoungGen: 305663K->43514K(305664K)] 487031K->307029K(1005056K), 0.0208585 secs] [Times: user=0.03 sys=0.17, real=0.02 secs] 
2021-09-26T01:00:33.915+0800: [GC (Allocation Failure) [PSYoungGen: 305658K->43516K(305664K)] 569173K->390053K(1005056K), 0.0215936 secs] [Times: user=0.20 sys=0.00, real=0.02 secs] 
2021-09-26T01:00:34.002+0800: [GC (Allocation Failure) [PSYoungGen: 305660K->43516K(160256K)] 652197K->465865K(859648K), 0.0195226 secs] [Times: user=0.03 sys=0.17, real=0.02 secs] 
2021-09-26T01:00:34.045+0800: [GC (Allocation Failure) [PSYoungGen: 159907K->74564K(232960K)] 582256K->502907K(932352K), 0.0124229 secs] [Times: user=0.20 sys=0.00, real=0.01 secs] 
2021-09-26T01:00:34.080+0800: [GC (Allocation Failure) [PSYoungGen: 191300K->96381K(232960K)] 619643K->532886K(932352K), 0.0154895 secs] [Times: user=0.14 sys=0.06, real=0.02 secs] 
2021-09-26T01:00:34.135+0800: [GC (Allocation Failure) [PSYoungGen: 213117K->111360K(232960K)] 649622K->566951K(932352K), 0.0185485 secs] [Times: user=0.19 sys=0.01, real=0.02 secs] 
2021-09-26T01:00:34.178+0800: [GC (Allocation Failure) [PSYoungGen: 228096K->76230K(232960K)] 683687K->594356K(932352K), 0.0197989 secs] [Times: user=0.06 sys=0.14, real=0.02 secs] 
2021-09-26T01:00:34.221+0800: [GC (Allocation Failure) [PSYoungGen: 192966K->39266K(232960K)] 711092K->625268K(932352K), 0.0170809 secs] [Times: user=0.14 sys=0.06, real=0.02 secs] 
2021-09-26T01:00:34.238+0800: [Full GC (Ergonomics) [PSYoungGen: 39266K->0K(232960K)] [ParOldGen: 586002K->329066K(699392K)] 625268K->329066K(932352K), [Metaspace: 2697K->2697K(1056768K)], 0.0545359 secs] [Times: user=0.61 sys=0.00, real=0.06 secs] 
2021-09-26T01:00:34.317+0800: [GC (Allocation Failure) [PSYoungGen: 116736K->41552K(232960K)] 445802K->370618K(932352K), 0.0071834 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-26T01:00:34.372+0800: [GC (Allocation Failure) [PSYoungGen: 158288K->40338K(232960K)] 487354K->403295K(932352K), 0.0126223 secs] [Times: user=0.20 sys=0.00, real=0.01 secs] 
2021-09-26T01:00:34.423+0800: [GC (Allocation Failure) [PSYoungGen: 156919K->39604K(232960K)] 519877K->436198K(932352K), 0.0118109 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
Executed finish,The number of generate objects is:9493
Heap
 PSYoungGen      total 232960K, used 44553K [0x00000000eab00000, 0x0000000100000000, 0x0000000100000000)
  eden space 116736K, 4% used [0x00000000eab00000,0x00000000eafd5528,0x00000000f1d00000)
  from space 116224K, 34% used [0x00000000f8e80000,0x00000000fb52d210,0x0000000100000000)
  to   space 116224K, 0% used [0x00000000f1d00000,0x00000000f1d00000,0x00000000f8e80000)
 ParOldGen       total 699392K, used 396593K [0x00000000c0000000, 0x00000000eab00000, 0x00000000eab00000)
  object space 699392K, 56% used [0x00000000c0000000,0x00000000d834c730,0x00000000eab00000)
 Metaspace       used 2704K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 290K, capacity 386K, committed 512K, reserved 1048576K
```
会发现这一次的GC日志以及整个堆中垃圾回收的过程都是比较符合预期的。
如果我在把xmx和xms参数调整到4g，整个堆的内存进一步变大，主要带来的变化是：由于整体堆内存变大，所以可以容纳的对象进一步增多，会发现GC的次数减少，但是每一次GC的时候回收的堆内存相较于之前明显增加，所以GC的时间也相应增加。
所以得到的一个结论就是不是说设定的堆内存越大就越好，具体业务中应该具体分析，找到一个相对合适的值。