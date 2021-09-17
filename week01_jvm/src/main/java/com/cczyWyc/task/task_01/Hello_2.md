# 四则运算的java字节码分析

* 和上面类似，还是用javac和javap命令编译和获取java源文件的字节码，如下所示
```text
Classfile /E:/JavaCode/JavaTrainingCamp/week01_jvm/src/main/java/com/cczyWyc/task/task_01/Hello_2.class
  Last modified 2021-9-17; size 434 bytes
  MD5 checksum 0c9c90ddab7302a4e3c3303a81f9c6a4
  Compiled from "Hello_2.java"
public class com.cczyWyc.task.task_01.Hello_2
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #7.#16         // java/lang/Object."<init>":()V
   #2 = Class              #17            // com/cczyWyc/task/task_01/Operation
   #3 = Methodref          #2.#16         // com/cczyWyc/task/task_01/Operation."<init>":()V
   #4 = Methodref          #2.#18         // com/cczyWyc/task/task_01/Operation.submit:(D)V
   #5 = Methodref          #2.#19         // com/cczyWyc/task/task_01/Operation.getAvg:()D
   #6 = Class              #20            // com/cczyWyc/task/task_01/Hello_2
   #7 = Class              #21            // java/lang/Object
   #8 = Utf8               <init>
   #9 = Utf8               ()V
  #10 = Utf8               Code
  #11 = Utf8               LineNumberTable
  #12 = Utf8               main
  #13 = Utf8               ([Ljava/lang/String;)V
  #14 = Utf8               SourceFile
  #15 = Utf8               Hello_2.java
  #16 = NameAndType        #8:#9          // "<init>":()V
  #17 = Utf8               com/cczyWyc/task/task_01/Operation
  #18 = NameAndType        #22:#23        // submit:(D)V
  #19 = NameAndType        #24:#25        // getAvg:()D
  #20 = Utf8               com/cczyWyc/task/task_01/Hello_2
  #21 = Utf8               java/lang/Object
  #22 = Utf8               submit
  #23 = Utf8               (D)V
  #24 = Utf8               getAvg
  #25 = Utf8               ()D
{
  public com.cczyWyc.task.task_01.Hello_2();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 6: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=3, locals=6, args_size=1
         0: new           #2                  // class com/cczyWyc/task/task_01/Operation
         3: dup
         4: invokespecial #3                  // Method com/cczyWyc/task/task_01/Operation."<init>":()V
         7: astore_1
         8: iconst_2
         9: istore_2
        10: iconst_4
        11: istore_3
        12: aload_1
        13: iload_2
        14: i2d
        15: invokevirtual #4                  // Method com/cczyWyc/task/task_01/Operation.submit:(D)V
        18: aload_1
        19: iload_3
        20: i2d
        21: invokevirtual #4                  // Method com/cczyWyc/task/task_01/Operation.submit:(D)V
        24: aload_1
        25: invokevirtual #5                  // Method com/cczyWyc/task/task_01/Operation.getAvg:()D
        28: dstore        4
        30: return
      LineNumberTable:
        line 8: 0
        line 9: 8
        line 10: 10
        line 11: 12
        line 12: 18
        line 13: 24
        line 14: 30
}
SourceFile: "Hello_2.java"
Classfile /E:/JavaCode/JavaTrainingCamp/week01_jvm/src/main/java/com/cczyWyc/task/task_01/Operation.class
  Last modified 2021-9-17; size 485 bytes
  MD5 checksum b25feb6f5dfbc982dfebd6e10b7fe656
  Compiled from "Operation.java"
public class com.cczyWyc.task.task_01.Operation
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #5.#21         // java/lang/Object."<init>":()V
   #2 = Fieldref           #4.#22         // com/cczyWyc/task/task_01/Operation.count:I
   #3 = Fieldref           #4.#23         // com/cczyWyc/task/task_01/Operation.sum:D
   #4 = Class              #24            // com/cczyWyc/task/task_01/Operation
   #5 = Class              #25            // java/lang/Object
   #6 = Utf8               count
   #7 = Utf8               I
   #8 = Utf8               sum
   #9 = Utf8               D
  #10 = Utf8               <init>
  #11 = Utf8               ()V
  #12 = Utf8               Code
  #13 = Utf8               LineNumberTable
  #14 = Utf8               submit
  #15 = Utf8               (D)V
  #16 = Utf8               getAvg
  #17 = Utf8               ()D
  #18 = Utf8               StackMapTable
  #19 = Utf8               SourceFile
  #20 = Utf8               Operation.java
  #21 = NameAndType        #10:#11        // "<init>":()V
  #22 = NameAndType        #6:#7          // count:I
  #23 = NameAndType        #8:#9          // sum:D
  #24 = Utf8               com/cczyWyc/task/task_01/Operation
  #25 = Utf8               java/lang/Object
{
  public com.cczyWyc.task.task_01.Operation();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: iconst_0
         6: putfield      #2                  // Field count:I
         9: aload_0
        10: dconst_0
        11: putfield      #3                  // Field sum:D
        14: return
      LineNumberTable:
        line 6: 0
        line 7: 4
        line 8: 9

  public void submit(double);
    descriptor: (D)V
    flags: ACC_PUBLIC
    Code:
      stack=5, locals=3, args_size=2
         0: aload_0
         1: dup
         2: getfield      #2                  // Field count:I
         5: iconst_1
         6: iadd
         7: putfield      #2                  // Field count:I
        10: aload_0
        11: dup
        12: getfield      #3                  // Field sum:D
        15: dload_1
        16: dadd
        17: putfield      #3                  // Field sum:D
        20: return
      LineNumberTable:
        line 11: 0
        line 12: 10
        line 13: 20

  public double getAvg();
    descriptor: ()D
    flags: ACC_PUBLIC
    Code:
      stack=4, locals=1, args_size=1
         0: aload_0
         1: getfield      #2                  // Field count:I
         4: ifne          12
         7: aload_0
         8: getfield      #3                  // Field sum:D
        11: dreturn
        12: aload_0
        13: getfield      #3                  // Field sum:D
        16: aload_0
        17: getfield      #2                  // Field count:I
        20: i2d
        21: ddiv
        22: dreturn
      LineNumberTable:
        line 16: 0
        line 17: 7
        line 19: 12
      StackMapTable: number_of_entries = 1
        frame_type = 12 /* same */
}
SourceFile: "Operation.java"
```
## 分析过程
1. 上面的java编译版本和类的属性说明还是和Hello_1里面的分析类似，这里不重复分析，本篇着重分析四则运算的过程和基本类型转换。
2. 本实例其实和老师讲课的例子一样，这里拿出来重新自己分析。由于抽出了两个类，所以生成的java字节码较长。
3. 直接看Hello_2.class对应的字节码，首先new一个Operation对象，初始化该对象，并将其引用类型压入栈顶。
4. 后面紧跟着dup，会发现所有的new后面都会跟一个dup，其含义是复制栈顶的数值并将复制值压入栈顶，这里我的理解是如果不使用dup复制，被构造函数指令使用后，将无法返回实例引用。
5. 然后astore_1表示将上面new出来的对象的引用实例存储在1这个位置。
6. 后面iconst_2、iconst_4表示定义两个常量2和4，istore_3和istore_3表示将两个int的值分别存在2和3两个位置。
7. 然后aload_1表示加载Operation引用实例进栈，准备调用它的实例方法submit()方法。
8. iload_2表示加载2这个常量。
9. i2d表示类型转换，将int型转为double类型。
10. 然后调用submit方法，翻译一下就是submit(2)
11. 下面两个类型，加载Operation对象的引用，加载常量4，然后类型转换，int转为double，然后调用submit方法。
12. 下面调用getAvg方法。