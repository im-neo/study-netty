package neo.nio.example;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @Classname: Test1
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/8 16:02
 * @Version: 1.0
 */
public class NioTest1 {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);


        for (int i = 0; i < buffer.capacity(); i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }
        
        buffer.flip();
        
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}
