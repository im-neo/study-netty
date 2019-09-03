package neo.netty.protobuf.example.second;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import neo.netty.protobuf.example.protobuf.Message;

/**
 * @Classname: TestServerHandler
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/2 11:04
 * @Version: 1.0
 */
public class TestServerHandler extends SimpleChannelInboundHandler<Message.MessageData> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message.MessageData msg) throws Exception {
        if (msg.hasPerson()) {
            Message.Person person = msg.getPerson();
            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println(person.getAddress());
        }else if(msg.hasDog()){
            Message.Dog dog = msg.getDog();
            System.out.println(dog.getName());
            System.out.println(dog.getAddress());
        }else if(msg.hasCat()){
            Message.Cat cat = msg.getCat();
            System.out.println(cat.getName());
            System.out.println(cat.getColor());
        }
    }
}
