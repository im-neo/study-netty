### java.io
> 1. java.io 中最为核心的一个概念就是流（Stream）, 所以 java.io 是面向流的编程
> 2. java 中，一个流要么是输入流，要么是输出流，不可能同时是输入流又是输出流

### java.nio
> 1. java.nio 有三个核心概念：Selector、Channel、Buffer，所以 java.nio 是面向块（block）或是缓冲区（buffer）编程
> 2. Buffer 本身就是一块内存，底层实现上就是数组。
> 3. java.nio 中数据的读写都是通过 Buffer 来实现的

#### Buffer
> 1. 除了数组之外，Buffer还提供了对于数据结构化访问方式，并且还可以追踪到系统的读写过程
> 2. java中的8种原生数据类型都有各自对应的 Buffer 类型，如 IntBuffer 、LongBuffer 、 ByteBuffer 、 CharBuffer 等

#### Channel
> 1. Channel 指的是可以向其写入数据或是从中读取数据的对象，类似于 java.io 中的 Stream
> 2. 所有数据的读写都是通过Buffer来进行的，永远不会出现直接向 Channel 读写数据的情况
> 3. 与Stream不同的是，Channel 是双向的，一个流只可能是 InputStream 或是 OutputStream , Channel 开打后则可以进行读取、写入、读写操作
> 4. 由于 Channel  是双向的，因此他能更好的反映出底层操作系统的真是情况。在Linux系统中，底层操作系统的通道就是双向的


