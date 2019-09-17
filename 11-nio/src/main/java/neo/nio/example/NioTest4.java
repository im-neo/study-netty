package neo.nio.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Classname: NioTest4
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/17 21:16
 * @Version: 1.0
 */
public class NioTest4 {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("./11-nio/input.txt");
        FileOutputStream outputStream = new FileOutputStream("./11-nio/output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(4);

        int read;
        do {
            // 如果注释该行代码会发生什么情况?
            buffer.clear();

            // read = buffer.limit - buffer.position
            read = inputChannel.read(buffer);

            System.out.println("read:"+read);

            buffer.flip();

            outputChannel.write(buffer);
        } while (-1 != read);

        inputChannel.close();
        outputChannel.close();
    }
}
