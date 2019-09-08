package neo.nio.example;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Classname: NioTest3
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/8 21:43
 * @Version: 1.0
 */
public class NioTest3 {


    public static void main(String[] args) throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream("./11-nio/NioTest3.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        byte[] message = "hello world  welcome java nio".getBytes();

        for (byte b : message) {
            byteBuffer.put(b);
        }
        
        byteBuffer.flip();
        
        fileChannel.write(byteBuffer);
        
        fileOutputStream.close();
    }
}
