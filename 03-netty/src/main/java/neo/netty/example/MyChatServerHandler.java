package neo.netty.example;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Classname: MyChatHandler
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/8/31 10:19
 * @Version: 1.0
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 用于保存 Channel
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.forEach(c -> {
                    if (c != channel) {
                        c.writeAndFlush(channel.remoteAddress() + "发送的消息：" + msg + "\n");
                    } else {
                        c.writeAndFlush("【自己】" + msg + "\n");
                    }
                }

        );
    }

    /**
     * 建立连接
     *
     * @Author: Neo
     * @Date: 2019/8/31 10:35
     * @Version: 1.0
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】 - " + channel.remoteAddress() + "加入\n");
        channelGroup.add(channel);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "【上线】");
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "【下线】");
    }

    /**
     * 断开连接
     *
     * @Author: Neo
     * @Date: 2019/8/31 10:43
     * @Version: 1.0
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】 - " + channel.remoteAddress() + "离开\n");
        System.out.println(channelGroup.size());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}
