package neo.nio.example;

import java.nio.ByteBuffer;

/**
 * @Classname: NioTest5
 * @Description: ByteBuffer 类型化的 put 与 get 方法
 * @Author: Neo
 * @Date: 2019/9/17 22:00
 * @Version: 1.0
 */
public class NioTest5 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.putInt(15);
        buffer.putLong(5000000L);
        buffer.putDouble(12.34567);
        buffer.putChar('中');
        buffer.putShort((short) 2);
        buffer.putChar('国');
        
        buffer.flip();

        // 按照写入的类型顺序读取
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());
        
    }
}
