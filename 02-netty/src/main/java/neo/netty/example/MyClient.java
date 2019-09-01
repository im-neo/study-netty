package neo.netty.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * @Classname: MyClient
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/8/30 23:33
 * @Version: 1.0
 */
public class MyClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientInitializer());
            ChannelFuture future = bootstrap.connect("localhost", 8880).sync();

       
            
            future.channel().closeFuture().sync();

        } finally {
            eventLoopGroup.shutdownGracefully();
        }


    }


}
