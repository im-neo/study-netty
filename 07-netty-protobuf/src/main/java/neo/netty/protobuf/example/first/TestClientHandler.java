package neo.netty.protobuf.example.first;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import neo.netty.protobuf.example.protobuf.DataInfo;

/**
 * @Classname: TestClientHandler
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/1 21:47
 * @Version: 1.0
 */
public class TestClientHandler extends SimpleChannelInboundHandler<DataInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("张三").setAge(25).setAddress("上海").build();
        ctx.channel().writeAndFlush(student);
    }
}
