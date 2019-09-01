package neo.netty.example;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Scanner;
import java.util.UUID;

/**
 * @Classname: MyServerHandler
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/8/30 23:13
 * @Version: 1.0
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + ":" + msg);
        System.out.print("请输入：");
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        ctx.channel().writeAndFlush("from server:"+ message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
        super.exceptionCaught(ctx, cause);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
}
