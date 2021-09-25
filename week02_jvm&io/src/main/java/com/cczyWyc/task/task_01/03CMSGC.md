# 使用CMS GC演练演练GCLogAnalysis的例子

再次修改程序的启动参数：
```shell
java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseConcMarkSweepGC GCLogAnalysis
```
即指定GC策略为CMS GC，然后查看GC的日志：
```shell
executing...
2021-09-26T01:35:07.202+0800: [GC (Allocation Failure) 2021-09-26T01:35:07.202+0800: [ParNew: 279616K->34942K(314560K), 0.0171878 secs] 279616K->89153K(1013632K), 0.0173021 secs] [Times: user=0.13 sys=0.03, real=0.02 secs] 
2021-09-26T01:35:07.278+0800: [GC (Allocation Failure) 2021-09-26T01:35:07.278+0800: [ParNew: 314397K->34944K(314560K), 0.0229405 secs] 368608K->174804K(1013632K), 0.0230049 secs] [Times: user=0.11 sys=0.13, real=0.02 secs] 
2021-09-26T01:35:07.363+0800: [GC (Allocation Failure) 2021-09-26T01:35:07.363+0800: [ParNew: 314560K->34943K(314560K), 0.0595391 secs] 454420K->265537K(1013632K), 0.0596089 secs] [Times: user=0.80 sys=0.01, real=0.06 secs] 
2021-09-26T01:35:07.479+0800: [GC (Allocation Failure) 2021-09-26T01:35:07.479+0800: [ParNew: 314559K->34944K(314560K), 0.0499354 secs] 545153K->345430K(1013632K), 0.0500384 secs] [Times: user=0.56 sys=0.05, real=0.05 secs] 
2021-09-26T01:35:07.586+0800: [GC (Allocation Failure) 2021-09-26T01:35:07.586+0800: [ParNew: 314560K->34943K(314560K), 0.0504806 secs] 625046K->423446K(1013632K), 0.0505867 secs] [Times: user=0.53 sys=0.06, real=0.05 secs] 
2021-09-26T01:35:07.637+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 388502K(699072K)] 429202K(1013632K), 0.0003847 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-26T01:35:07.637+0800: [CMS-concurrent-mark-start]
2021-09-26T01:35:07.639+0800: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-26T01:35:07.639+0800: [CMS-concurrent-preclean-start]
2021-09-26T01:35:07.641+0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.03 sys=0.00, real=0.00 secs] 
2021-09-26T01:35:07.641+0800: [CMS-concurrent-abortable-preclean-start]
2021-09-26T01:35:07.693+0800: [GC (Allocation Failure) 2021-09-26T01:35:07.693+0800: [ParNew: 314559K->34943K(314560K), 0.0547638 secs] 703062K->508223K(1013632K), 0.0548710 secs] [Times: user=0.58 sys=0.03, real=0.05 secs] 
2021-09-26T01:35:07.804+0800: [GC (Allocation Failure) 2021-09-26T01:35:07.804+0800: [ParNew: 314488K->34943K(314560K), 0.0547484 secs] 787768K->589985K(1013632K), 0.0548497 secs] [Times: user=0.59 sys=0.01, real=0.06 secs] 
2021-09-26T01:35:07.913+0800: [GC (Allocation Failure) 2021-09-26T01:35:07.913+0800: [ParNew: 314559K->34943K(314560K), 0.0594387 secs] 869601K->669022K(1013632K), 0.0595022 secs] [Times: user=0.77 sys=0.05, real=0.06 secs] 
2021-09-26T01:35:08.030+0800: [GC (Allocation Failure) 2021-09-26T01:35:08.030+0800: [ParNew: 314509K->314509K(314560K), 0.0000482 secs]2021-09-26T01:35:08.030+0800: [CMS2021-09-26T01:35:08.030+0800: [CMS-concurrent-abortable-preclean: 0.011/0.389 secs] [Times: user=2.19 sys=0.09, real=0.39 secs] 
 (concurrent mode failure): 634079K->349759K(699072K), 0.0855174 secs] 948588K->349759K(1013632K), [Metaspace: 2697K->2697K(1056768K)], 0.0856935 secs] [Times: user=0.09 sys=0.00, real=0.09 secs] 
Executed finish,The number of generate objects is:9544
Heap
 par new generation   total 314560K, used 11213K [0x00000000c0000000, 0x00000000d5550000, 0x00000000d5550000)
  eden space 279616K,   4% used [0x00000000c0000000, 0x00000000c0af3418, 0x00000000d1110000)
  from space 34944K,   0% used [0x00000000d1110000, 0x00000000d1110000, 0x00000000d3330000)
  to   space 34944K,   0% used [0x00000000d3330000, 0x00000000d3330000, 0x00000000d5550000)
 concurrent mark-sweep generation total 699072K, used 349759K [0x00000000d5550000, 0x0000000100000000, 0x0000000100000000)
 Metaspace       used 2704K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 290K, capacity 386K, committed 512K, reserved 1048576K
```
## 分析过程
1. 通过查看CMS GC的日志，发现前五次都是发生的young Gc，并且它的垃圾回收器的名字叫ParNew，也就是对串行化GC做了升级改造，变成了一个多线程的垃圾回收器，其对young区的垃圾回收的过程和前面的类似，这里不做详细阐述。
2. 重点看下面CMS GC的过程，CMS GC是针对old区的。
3. 通过课程的学习我已经知道CMS分为了6个阶段，这里我从GC日志的第6行开始分析。