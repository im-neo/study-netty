package neo.netty.example;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Classname: MyChatClientHandler
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/8/31 11:21
 * @Version: 1.0
 */
public class MyChatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
