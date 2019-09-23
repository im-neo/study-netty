package neo.nio.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Classname: NioTest13Client
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/21 15:40
 * @Version: 1.0
 */
public class NioTest13Client {


    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();

        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("localhost", 8899));

        while (true) {
            selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            selectionKeys.forEach(selectionKey -> {
                try {
                    if (selectionKey.isConnectable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();

                        if (client.isConnectionPending()) {
                            client.finishConnect();

                            ByteBuffer writeBuffer = ByteBuffer.allocate(512);

                            writeBuffer.put((LocalDateTime.now() + " 连接成功").getBytes());
                            writeBuffer.flip();
                            client.write(writeBuffer);


                            ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());

                            executorService.execute(() -> {
                                while (true) {
                                    try {
                                        writeBuffer.clear();
                                        InputStreamReader input = new InputStreamReader(System.in);
                                        BufferedReader reader = new BufferedReader(input);

                                        String sendMessage = reader.readLine();

                                        writeBuffer.put(sendMessage.getBytes());
                                        writeBuffer.flip();
                                        client.write(writeBuffer);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            client.register(selector, SelectionKey.OP_READ);
                        }
                    } else if (selectionKey.isReadable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        
                        int read = client.read(byteBuffer);
                        
                        if(read > 0){
                            String receivedMessage = new String(byteBuffer.array(),0,read);
                            System.out.println(receivedMessage);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                selectionKeys.clear();

            });


        }


    }

}
