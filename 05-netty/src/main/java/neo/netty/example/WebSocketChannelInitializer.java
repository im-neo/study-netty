package neo.netty.example;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Classname: WebSocketChannelInitializer
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/8/31 22:35
 * @Version: 1.0
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        /**
         * 【重要对象】：将请求块聚合为一个完整的FullHttpRequest | FullHttpResponse
         */
        pipeline.addLast(new HttpObjectAggregator(8192));
        /**
         * ws://address:port/content_path
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new TextWebSocketFrameHandler());
        
    }
}
