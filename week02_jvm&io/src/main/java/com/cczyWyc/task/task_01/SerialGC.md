# 使用串行GC演练GCLogAnalysis的例子

前面使用了并行GC来模拟堆中对象的创建过程，现在在用串行GC模拟这个过程，视图比较不同GC策略的区别
```shell
java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseSerialGC GCLogAnalysis
```
运行程序的GC日志为：
```shell
executing...
2021-09-26T01:09:56.101+0800: [GC (Allocation Failure) 2021-09-26T01:09:56.101+0800: [DefNew: 279616K->34943K(314560K), 0.0488259 secs] 279616K->89022K(1013632K), 0.0489224 secs] [Times: user=0.03 sys=0.02, real=0.05 secs] 
2021-09-26T01:09:56.214+0800: [GC (Allocation Failure) 2021-09-26T01:09:56.215+0800: [DefNew: 314559K->34943K(314560K), 0.0629317 secs] 368638K->171775K(1013632K), 0.0630101 secs] [Times: user=0.02 sys=0.05, real=0.06 secs] 
2021-09-26T01:09:56.335+0800: [GC (Allocation Failure) 2021-09-26T01:09:56.335+0800: [DefNew: 314559K->34943K(314560K), 0.0584204 secs] 451391K->250426K(1013632K), 0.0584802 secs] [Times: user=0.03 sys=0.03, real=0.06 secs] 
2021-09-26T01:09:56.452+0800: [GC (Allocation Failure) 2021-09-26T01:09:56.452+0800: [DefNew: 314559K->34944K(314560K), 0.0500371 secs] 530042K->328996K(1013632K), 0.0500984 secs] [Times: user=0.05 sys=0.02, real=0.05 secs] 
2021-09-26T01:09:56.590+0800: [GC (Allocation Failure) 2021-09-26T01:09:56.590+0800: [DefNew: 314560K->34943K(314560K), 0.0684350 secs] 608612K->418142K(1013632K), 0.0685208 secs] [Times: user=0.08 sys=0.00, real=0.07 secs] 
2021-09-26T01:09:56.763+0800: [GC (Allocation Failure) 2021-09-26T01:09:56.763+0800: [DefNew: 314559K->34944K(314560K), 0.0644158 secs] 697758K->499803K(1013632K), 0.0645109 secs] [Times: user=0.03 sys=0.05, real=0.07 secs] 
2021-09-26T01:09:56.894+0800: [GC (Allocation Failure) 2021-09-26T01:09:56.895+0800: [DefNew: 314560K->34943K(314560K), 0.0675724 secs] 779419K->579179K(1013632K), 0.0676875 secs] [Times: user=0.01 sys=0.05, real=0.07 secs] 
Executed finish,The number of generate objects is:7390
Heap
 def new generation   total 314560K, used 46364K [0x00000000c0000000, 0x00000000d5550000, 0x00000000d5550000)
  eden space 279616K,   4% used [0x00000000c0000000, 0x00000000c0b27578, 0x00000000d1110000)
  from space 34944K,  99% used [0x00000000d3330000, 0x00000000d554fc78, 0x00000000d5550000)
  to   space 34944K,   0% used [0x00000000d1110000, 0x00000000d1110000, 0x00000000d3330000)
 tenured generation   total 699072K, used 544236K [0x00000000d5550000, 0x0000000100000000, 0x0000000100000000)
   the space 699072K,  77% used [0x00000000d5550000, 0x00000000f68cb120, 0x00000000f68cb200, 0x0000000100000000)
 Metaspace       used 2704K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 290K, capacity 386K, committed 512K, reserved 1048576K
```
## 分析过程
1. 这里通过观察串行GC的日志发现，整个生成对象的个数明显减少，这是因为串行化GC它是单线程的，处理GC的效率跟其他的GC策略相比要低，所以GC的整体时间要长，因为它在固定的时间内生成的对象就相对来说较少。
2. 这里在配置堆内存为1g的情况下，我使用串行GC始终没有看到进行FullGC的日志，一直进行的是youngGC，这里我的理解是堆内存一直是够用的，下面我降堆内存的大小缩小至256m，观察gc日志如下：
```shell
executing...
2021-09-26T01:20:52.059+0800: [GC (Allocation Failure) 2021-09-26T01:20:52.059+0800: [DefNew: 69952K->8704K(78656K), 0.0257838 secs] 69952K->26401K(253440K), 0.0259148 secs] [Times: user=0.02 sys=0.02, real=0.03 secs] 
2021-09-26T01:20:52.117+0800: [GC (Allocation Failure) 2021-09-26T01:20:52.117+0800: [DefNew: 78656K->8703K(78656K), 0.0189849 secs] 96353K->49070K(253440K), 0.0190540 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
2021-09-26T01:20:52.153+0800: [GC (Allocation Failure) 2021-09-26T01:20:52.153+0800: [DefNew: 78655K->8698K(78656K), 0.0137744 secs] 119022K->70433K(253440K), 0.0138383 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2021-09-26T01:20:52.182+0800: [GC (Allocation Failure) 2021-09-26T01:20:52.182+0800: [DefNew: 78650K->8703K(78656K), 0.0153760 secs] 140385K->95925K(253440K), 0.0154409 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2021-09-26T01:20:52.211+0800: [GC (Allocation Failure) 2021-09-26T01:20:52.212+0800: [DefNew: 78655K->8699K(78656K), 0.0135219 secs] 165877K->117017K(253440K), 0.0135930 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2021-09-26T01:20:52.240+0800: [GC (Allocation Failure) 2021-09-26T01:20:52.240+0800: [DefNew: 78651K->8703K(78656K), 0.0146753 secs] 186969K->140088K(253440K), 0.0147404 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
2021-09-26T01:20:52.279+0800: [GC (Allocation Failure) 2021-09-26T01:20:52.279+0800: [DefNew: 78418K->8694K(78656K), 0.0243562 secs] 209803K->160298K(253440K), 0.0244648 secs] [Times: user=0.00 sys=0.03, real=0.02 secs] 
2021-09-26T01:20:52.337+0800: [GC (Allocation Failure) 2021-09-26T01:20:52.337+0800: [DefNew: 78528K->78528K(78656K), 0.0000392 secs]2021-09-26T01:20:52.337+0800: [Tenured: 151603K->155619K(174784K), 0.0391813 secs] 230131K->155619K(253440K), [Metaspace: 2697K->2697K(1056768K)], 0.0393415 secs] [Times: user=0.03 sys=0.02, real=0.04 secs] 
2021-09-26T01:20:52.404+0800: [GC (Allocation Failure) 2021-09-26T01:20:52.404+0800: [DefNew: 69952K->69952K(78656K), 0.0000220 secs]2021-09-26T01:20:52.404+0800: [Tenured: 155619K->167466K(174784K), 0.0383160 secs] 225571K->167466K(253440K), [Metaspace: 2697K->2697K(1056768K)], 0.0384166 secs] [Times: user=0.03 sys=0.02, real=0.04 secs] 
2021-09-26T01:20:52.471+0800: [GC (Allocation Failure) 2021-09-26T01:20:52.471+0800: [DefNew: 69952K->69952K(78656K), 0.0000390 secs]2021-09-26T01:20:52.471+0800: [Tenured: 167466K->174650K(174784K), 0.0511615 secs] 237418K->182313K(253440K), [Metaspace: 2697K->2697K(1056768K)], 0.0513106 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2021-09-26T01:20:52.555+0800: [Full GC (Allocation Failure) 2021-09-26T01:20:52.555+0800: [Tenured: 174722K->174569K(174784K), 0.0521765 secs] 253331K->183188K(253440K), [Metaspace: 2697K->2697K(1056768K)], 0.0522759 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2021-09-26T01:20:52.638+0800: [Full GC (Allocation Failure) 2021-09-26T01:20:52.638+0800: [Tenured: 174775K->174557K(174784K), 0.0216127 secs] 253392K->210578K(253440K), [Metaspace: 2697K->2697K(1056768K)], 0.0217379 secs] [Times: user=0.03 sys=0.00, real=0.02 secs] 
2021-09-26T01:20:52.679+0800: [Full GC (Allocation Failure) 2021-09-26T01:20:52.679+0800: [Tenured: 174557K->174772K(174784K), 0.0420956 secs] 252855K->216366K(253440K), [Metaspace: 2697K->2697K(1056768K)], 0.0421989 secs] [Times: user=0.05 sys=0.00, real=0.04 secs] 
2021-09-26T01:20:52.737+0800: [Full GC (Allocation Failure) 2021-09-26T01:20:52.737+0800: [Tenured: 174772K->174597K(174784K), 0.0339772 secs] 253350K->224865K(253440K), [Metaspace: 2697K->2697K(1056768K)], 0.0340413 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2021-09-26T01:20:52.783+0800: [Full GC (Allocation Failure) 2021-09-26T01:20:52.783+0800: [Tenured: 174597K->174755K(174784K), 0.0504201 secs] 253218K->223971K(253440K), [Metaspace: 2697K->2697K(1056768K)], 0.0504806 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2021-09-26T01:20:52.847+0800: [Full GC (Allocation Failure) 2021-09-26T01:20:52.847+0800: [Tenured: 174755K->174755K(174784K), 0.0140146 secs] 253408K->228687K(253440K), [Metaspace: 2697K->2697K(1056768K)], 0.0140763 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2021-09-26T01:20:52.873+0800: [Full GC (Allocation Failure) 2021-09-26T01:20:52.873+0800: [Tenured: 174755K->174755K(174784K), 0.0151302 secs] 253279K->235553K(253440K), [Metaspace: 2697K->2697K(1056768K)], 0.0151897 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2021-09-26T01:20:52.895+0800: [Full GC (Allocation Failure) 2021-09-26T01:20:52.895+0800: [Tenured: 174755K->174711K(174784K), 0.0153954 secs] 253342K->238694K(253440K), [Metaspace: 2697K->2697K(1056768K)], 0.0154492 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2021-09-26T01:20:52.917+0800: [Full GC (Allocation Failure) 2021-09-26T01:20:52.917+0800: [Tenured: 174747K->174699K(174784K), 0.0528419 secs] 253399K->232914K(253440K), [Metaspace: 2697K->2697K(1056768K)], 0.0528911 secs] [Times: user=0.06 sys=0.00, real=0.05 secs] 
2021-09-26T01:20:52.980+0800: [Full GC (Allocation Failure) 2021-09-26T01:20:52.980+0800: [Tenured: 174699K->174699K(174784K), 0.0158630 secs] 253143K->237462K(253440K), [Metaspace: 2697K->2697K(1056768K)], 0.0159171 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
Executed finish,The number of generate objects is:3972
Heap
 def new generation   total 78656K, used 65001K [0x00000000f0000000, 0x00000000f5550000, 0x00000000f5550000)
  eden space 69952K,  92% used [0x00000000f0000000, 0x00000000f3f7a670, 0x00000000f4450000)
  from space 8704K,   0% used [0x00000000f4cd0000, 0x00000000f4cd0000, 0x00000000f5550000)
  to   space 8704K,   0% used [0x00000000f4450000, 0x00000000f4450000, 0x00000000f4cd0000)
 tenured generation   total 174784K, used 174699K [0x00000000f5550000, 0x0000000100000000, 0x0000000100000000)
   the space 174784K,  99% used [0x00000000f5550000, 0x00000000fffead60, 0x00000000fffeae00, 0x0000000100000000)
 Metaspace       used 2704K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 290K, capacity 386K, committed 512K, reserved 1048576K
```
3. 会发现调整了堆内存大小为512m以后，生成对象的个数进一步减少，只有3972个，原因是在串行化GC策略下，更多的时间都用来做GC了。
4. 同时会发现，在前面进行young以后，后面不断频繁的进行FullGC，通过看GC发现，每一次FullGC的时候，old区的内存基本上没有被回收，而且基本上快吧old区占满了，这里是因为整个堆内存太小，不断的有young区的对象晋升，所以old基本上处理满的状态，所以频繁fullGC。