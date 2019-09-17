package neo.nio.example;

import java.nio.ByteBuffer;

/**
 * @Classname: NioTest6
 * @Description: Since Buffer 与原有 Buffer 共享底层数组
 * @Author: Neo
 * @Date: 2019/9/17 22:10
 * @Version: 1.0
 */
public class NioTest6 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < 10; i++) {
            buffer.put((byte) i);
        }

        buffer.position(2);
        buffer.limit(6);

        ByteBuffer sinceBuffer = buffer.slice();

        for (int i = 0; i < sinceBuffer.capacity(); i++) {
            byte b = sinceBuffer.get(i);
            b *= 2;
            sinceBuffer.put(i, b);
        }
        
        buffer.position(0);
        buffer.limit(buffer.capacity());
        
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }

    }


}
