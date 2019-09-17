package neo.nio.example;

import java.nio.ByteBuffer;

/**
 * @Classname: NioTest7
 * @Description: 只读 Buffer ：我们可以随时将一个普通 Buffer 调用 asReadOnlyBuffer 方法返回一个只读 Buffer 
 *               但不能讲一个只读 Buffer 转换为一个读写 Buffer
 * @Author: Neo
 * @Date: 2019/9/17 22:19
 * @Version: 1.0
 */
public class NioTest7 {
    
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        System.out.println(buffer.getClass());

        for (int i = 0; i < 10; i++) {
            buffer.put((byte)1);
        }
        
        ByteBuffer readonlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readonlyBuffer.getClass());
        
        // 尝试修改 readonlyBuffer 的内容
//        readonlyBuffer.position(0);
//        readonlyBuffer.put((byte)2);
    }
}
