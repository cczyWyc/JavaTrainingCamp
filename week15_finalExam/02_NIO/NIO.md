# NIO

Java NIO全称java non-blocking IO，也叫非阻塞式IO。从JDK1.4开始，java提供了一系列改进的输入/输出的新特性，是同步非阻塞的，NIO是通过Selector来实现非阻塞的</br>
NIO有三大核心部分：Channel(缓冲区)、Buffer(缓存区)、Selector(选择器)</br>
和Java传统IO不一样，NIO是面向缓冲区，或者面向块编程的。数据读取到一个他稍后处理的缓冲区，需要是可在缓冲区中向前后移动，这就增加了处理过程中的灵活性，使用它可以提供非阻塞式的高延伸性网络。