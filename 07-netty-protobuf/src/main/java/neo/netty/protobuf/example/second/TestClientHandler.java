package neo.netty.protobuf.example.second;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import neo.netty.protobuf.example.protobuf.Message;

import java.util.Random;

/**
 * @Classname: TestClientHandler
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/2 13:36
 * @Version: 1.0
 */
public class TestClientHandler extends SimpleChannelInboundHandler<Message.MessageData> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message.MessageData msg) throws Exception {

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int random = new Random().nextInt(3);
        System.out.println("随机数：" + random);
        if (random == 0) {
            Message.MessageData message = Message.MessageData.newBuilder().setMessageType(Message.MessageData.MessageType.PersonType)
                    .setPerson(
                            Message.Person.newBuilder().setName("张三").setAge(25).setAddress("上海").build()
                    ).build();
            ctx.channel().writeAndFlush(message);
        } else if (random == 1) {
            Message.MessageData message = Message.MessageData.newBuilder().setMessageType(Message.MessageData.MessageType.DogType)
                    .setDog(
                            Message.Dog.newBuilder().setName("拉布拉多").setAddress("上海").build()
                    ).build();
            ctx.channel().writeAndFlush(message);
        } else {
            Message.MessageData message = Message.MessageData.newBuilder().setMessageType(Message.MessageData.MessageType.CatType)
                    .setCat(
                            Message.Cat.newBuilder().setName("咖菲猫").setColor("咖啡色").build()
                    ).build();
            ctx.channel().writeAndFlush(message);
        }
    }
}
