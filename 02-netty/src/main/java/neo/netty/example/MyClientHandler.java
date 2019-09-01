package neo.netty.example;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * @Classname: MyClientHandler
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/8/30 23:41
 * @Version: 1.0
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + ":" + msg);
        System.out.print("请输入：");
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        
        ctx.writeAndFlush("from client:"+ message);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
        super.exceptionCaught(ctx, cause);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("lalala");
        super.channelActive(ctx);
    }
}
