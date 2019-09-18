package neo.nio.example;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @Classname: NioTest11
 * @Description: 关于 Buffer 的 Scattering 与 Gathering
 * @Author: Neo
 * @Date: 2019/9/18 21:57
 * @Version: 1.0
 */
public class NioTest11 {


    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;

        ByteBuffer[] buffers = {ByteBuffer.allocate(2), ByteBuffer.allocate(3), ByteBuffer.allocate(4)};

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int bytesRead = 0;
            while (bytesRead >= 0 && bytesRead < messageLength) {
                long read = socketChannel.read(buffers);
                bytesRead += read;

                System.out.println("bytesRead:" + bytesRead);

                Arrays.asList(buffers).stream()
                        .map(buffer -> "position:" + buffer.position() + ",limit:" + buffer.limit())
                        .forEach(System.out::println);
                
            }

            Arrays.asList(buffers).forEach(buffer -> buffer.flip());

            long bytesWritten = 0;
            while (bytesWritten < messageLength) {
                long read = socketChannel.write(buffers);
                bytesWritten += read;
            }

            Arrays.asList(buffers).forEach(buffer -> buffer.clear());

            System.out.println("bytesRead:" + bytesRead + ",bytesWritten:" + bytesWritten + ",messageLength:" + messageLength);


        }


    }


}
