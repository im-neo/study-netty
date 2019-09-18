package neo.nio.example;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Classname: NioTest9
 * @Description: MappedByteBuffer
 * @Author: Neo
 * @Date: 2019/9/18 21:47
 * @Version: 1.0
 */
public class NioTest9 {

    public static void main(String[] args) throws Exception {
        // 运行后在文件管理器中查看结果
        RandomAccessFile randomAccessFile = new RandomAccessFile("./11-nio/NioTest9.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte) 'a');
        mappedByteBuffer.put(3, (byte) 'b');

        randomAccessFile.close();

    }
}
