# Hello_1.class文件字节码分析

* javac -g Hello_1.java得到Hello_1.class文件
* javap -c -verbose Hello_1.class得到class文件的字节码如下：</br>
```text
  Classfile /E:/JavaCode/JavaTrainingCamp/week01_jvm/src/main/java/com/cczyWyc/task/task_01/Hello_1.class
  Last modified 2021-9-16; size 802 bytes
  MD5 checksum b8ff17391399f2d34ee2dbc7b6f9eb19
  Compiled from "Hello_1.java"
public class com.cczyWyc.task.task_01.Hello_1
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #7.#28         // java/lang/Object."<init>":()V
   #2 = Fieldref           #6.#29         // com/cczyWyc/task/task_01/Hello_1.numbers:[I
   #3 = Fieldref           #30.#31        // java/lang/System.out:Ljava/io/PrintStream;
   #4 = String             #32            // The number is bigger than 5
   #5 = Methodref          #33.#34        // java/io/PrintStream.println:(Ljava/lang/String;)V
   #6 = Class              #35            // com/cczyWyc/task/task_01/Hello_1
   #7 = Class              #36            // java/lang/Object
   #8 = Utf8               numbers
   #9 = Utf8               [I
  #10 = Utf8               <init>
  #11 = Utf8               ()V
  #12 = Utf8               Code
  #13 = Utf8               LineNumberTable
  #14 = Utf8               LocalVariableTable
  #15 = Utf8               this
  #16 = Utf8               Lcom/cczyWyc/task/task_01/Hello_1;
  #17 = Utf8               main
  #18 = Utf8               ([Ljava/lang/String;)V
  #19 = Utf8               number
  #20 = Utf8               I
  #21 = Utf8               args
  #22 = Utf8               [Ljava/lang/String;
  #23 = Utf8               StackMapTable
  #24 = Class              #9             // "[I"
  #25 = Utf8               <clinit>
  #26 = Utf8               SourceFile
  #27 = Utf8               Hello_1.java
  #28 = NameAndType        #10:#11        // "<init>":()V
  #29 = NameAndType        #8:#9          // numbers:[I
  #30 = Class              #37            // java/lang/System
  #31 = NameAndType        #38:#39        // out:Ljava/io/PrintStream;
  #32 = Utf8               The number is bigger than 5
  #33 = Class              #40            // java/io/PrintStream
  #34 = NameAndType        #41:#42        // println:(Ljava/lang/String;)V
  #35 = Utf8               com/cczyWyc/task/task_01/Hello_1
  #36 = Utf8               java/lang/Object
  #37 = Utf8               java/lang/System
  #38 = Utf8               out
  #39 = Utf8               Ljava/io/PrintStream;
  #40 = Utf8               java/io/PrintStream
  #41 = Utf8               println
  #42 = Utf8               (Ljava/lang/String;)V
{
  public com.cczyWyc.task.task_01.Hello_1();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 6: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/cczyWyc/task/task_01/Hello_1;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=5, args_size=1
         0: getstatic     #2                  // Field numbers:[I
         3: astore_1
         4: aload_1
         5: arraylength
         6: istore_2
         7: iconst_0
         8: istore_3
         9: iload_3
        10: iload_2
        11: if_icmpge     39
        14: aload_1
        15: iload_3
        16: iaload
        17: istore        4
        19: iload         4
        21: iconst_5
        22: if_icmple     33
        25: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
        28: ldc           #4                  // String The number is bigger than 5
        30: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        33: iinc          3, 1
        36: goto          9
        39: return
      LineNumberTable:
        line 10: 0
        line 11: 19
        line 12: 25
        line 10: 33
        line 15: 39
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
           19      14     4 number   I
            0      40     0  args   [Ljava/lang/String;
      StackMapTable: number_of_entries = 3
        frame_type = 254 /* append */
          offset_delta = 9
          locals = [ class "[I", int, int ]
        frame_type = 23 /* same */
        frame_type = 248 /* chop */
          offset_delta = 5

  static {};
    descriptor: ()V
    flags: ACC_STATIC
    Code:
      stack=4, locals=0, args_size=0
         0: iconst_4
         1: newarray       int
         3: dup
         4: iconst_0
         5: iconst_1
         6: iastore
         7: dup
         8: iconst_1
         9: iconst_3
        10: iastore
        11: dup
        12: iconst_2
        13: bipush        7
        15: iastore
        16: dup
        17: iconst_3
        18: bipush        9
        20: iastore
        21: putstatic     #2                  // Field numbers:[I
        24: return
      LineNumberTable:
        line 7: 0
}
SourceFile: "Hello_1.java"
```
## 分析过程
1. 首先看编译后的major version: 52和minor version: 0代表了对应的编译版本号为52.0，即为jdk1.8
2. 下面的flags: ACC_PUBLIC, ACC_SUPER代表类的属性是public并且它有父类
3. 在看Constant pool，它代表的是本地变量表。
4. aload_0，代表把本地变量表中的第0个位置的变量加载到栈上面来，a表示引用类型
5. Invokespecial指令一般用来调用构造函数，用来初始化，也可以调用同一个类中的private以及可见的超类的方法。这里调用了当前类的父类Object类的初始化方法。#1代表常量池的标号位置为1的产量，见上面的常量池。
6. 下面再来分析main方法具体的执行过程。首先看到0位置上的操作码是getstatic表示获取静态字段，对应的操作数是#2，再回到常量池中2的位置，可以看到表示一个定义的静态的数组number，它的长度是两个字节。
7. 再看3和4分别是astore_1和aload_1，分别表示将静态数组的值存在常量池中和压入栈中。
8. 下面arrayLength表示回去数组的长度，下面的istore_2表示将这个数组的长度store到2这个位置。
9. 下面是一个iconst_0表示为for循环开始i=0，然后istore_3表示将其store到3
10. 再iload_2，前面说到2这个位置表示的是数组的长度
11. 然后if_icmpge表示一个if判断，表示拿0和数组的长度比较，如果大于等于数组的长度，将直接走到39位置，即for循环结束，如果不满足则表示数组的循环遍历还在继续，继续下一步（这里也可以翻译为int i = 0; i < arrlength(); i ++）。
12. 如果不满足条件，继续for循环的执行
13. 然后store和load4这个位置，也就是我们定义的数组的第一个元素
14. iconst_5声明一个常量5
15. if_icmple将数组的第一个元素和常量5进行比较，如果第一个元素小于等于5，满足条件，直接跳转到33位置，进行下一次for循环计算。iinc表示i的自增，然后for循环从9的位置继续循环
16. 如果不满足条件，继续执行，也就是数组的元素大于5，这时获取静态变量输出流（system.out是静态的），然后打印string常量The number is bigger than 5
17. 以上就是就是main方法中for循环和if的判断执行控制流程，通过LineNumberTable也能看到对应的代码在10、11、12、15行
18. 在往下看，会发现还有一个静态的static过程，通过看LineNumberTable发现它就对于Hello_1.java的第7行，其实就是一个int类型的静态数组初始化过程。
19. 整个int类型的static数组初始化包含了new一个数组对象，然后数组的元素不断的压栈出栈。