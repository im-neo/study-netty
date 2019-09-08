package neo.nio.example;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Classname: NioTest2
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/8 21:30
 * @Version: 1.0
 */
public class NioTest2 {


    public static void main(String[] args) throws Exception {
        // 11-nio/NioTest2.txt
        FileInputStream fileInputStream = new FileInputStream("./11-nio/NioTest2.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);
        byteBuffer.flip();

        while (byteBuffer.hasRemaining()) {
            byte b = byteBuffer.get();
            System.out.println("Character:" + (char) b);
        }

        fileInputStream.close();
    }
}
