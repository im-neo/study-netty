package neo.nio.example;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @Classname: NioTest13Server
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/21 14:41
 * @Version: 1.0
 */
public class NioTest13Server {


    public static BiMap<String, SocketChannel> clientMap = HashBiMap.create();

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(selectionKey -> {
                final SocketChannel client;
                try {
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);

                        String key = "[" + UUID.randomUUID().toString() + "]";
                        clientMap.put(key, client);

                    } else if (selectionKey.isReadable()) {
                        client = (SocketChannel) selectionKey.channel();
                        while (true) {
                            ByteBuffer buffer = ByteBuffer.allocate(512);
                            int read = client.read(buffer);

                            if (read > 0) {
                                buffer.flip();

                                Charset charset = Charset.forName("UTF-8");
                                String receivedMessage = String.valueOf(charset.decode(buffer).array());

                                System.out.println(client + ":" + receivedMessage);

                                String senderKey = clientMap.inverse().get(client);

                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    SocketChannel value = entry.getValue();

                                    ByteBuffer writeBuffer = ByteBuffer.allocate(512);

                                    writeBuffer.put((senderKey + ":" + receivedMessage + "\r\n").getBytes());

                                    writeBuffer.flip();

                                    value.write(writeBuffer);
                                }
                            }
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            selectionKeys.clear();


        }


    }

}
