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
        System.out.println("capacity :" + buffer.capacity());

        for (int i = 0; i < 5; i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        System.out.println("before limit :" + buffer.limit());

        buffer.flip();

        System.out.println("before limit :" + buffer.limit());

        System.out.println("enter while loop");

        while (buffer.hasRemaining()) {
            System.out.println("position：" + buffer.position());
            System.out.println("limit：" + buffer.limit());
            System.out.println("capacity：" + buffer.capacity());


            System.out.println(buffer.get());
        }
    }
}
