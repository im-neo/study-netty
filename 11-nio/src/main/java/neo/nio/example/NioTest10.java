package neo.nio.example;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @Classname: NioTest10
 * @Description: FileLock
 * @Author: Neo
 * @Date: 2019/9/18 21:53
 * @Version: 1.0
 */
public class NioTest10 {

    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("./11-nio/NioTest10.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        FileLock fileLock = fileChannel.lock(3, 6, true);
        
        System.out.println("有效性：" + fileLock.isValid());
        System.out.println("类型：" + fileLock.isShared());

        fileLock.release();

        System.out.println("有效性：" + fileLock.isValid());
        
        randomAccessFile.close();
        
    }
}
