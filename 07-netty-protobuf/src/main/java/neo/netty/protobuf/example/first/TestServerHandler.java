package neo.netty.protobuf.example.first;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import neo.netty.protobuf.example.protobuf.DataInfo;

/**
 * @Classname: TestServerHandler
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/1 21:39
 * @Version: 1.0
 */
public class TestServerHandler extends SimpleChannelInboundHandler<DataInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {
        System.out.println(msg.getName());
        System.out.println(msg.getAge());
        System.out.println(msg.getAddress());
    }
}
