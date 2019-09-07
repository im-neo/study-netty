package neo.grpc.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @Classname: GrpcServer
 * 
 * 生成的代码报 UnusedPrivateParameter 相关的错误解决方案
 * 1.首先通过 Maven “Show Dependencies” 检查包依赖
 * 2.删除低版本的 grpc-netty、grpc-protobuf 
 * 3.采用 3.8.0 及以上的 grpc-netty、grpc-protobuf 
 * 
 * @Author: Neo
 * @Date: 2019/9/6 12:28
 * @Version: 1.0
 */
public class GrpcServer {
    private Server server;
    
    private void start() throws IOException{
         this.server = ServerBuilder.forPort(8899).addService(new StudentServiceImpl()).build().start();
        System.out.println("server started");
        
        
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("关闭jvm");
            GrpcServer.this.stop();
        }));
        
    }
    
    private void stop(){
        if(null != this.server){
            this.server.shutdown();
        }
    }
    
    private void awaitTermination() throws InterruptedException{
        if(null != this.server){
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception{
        GrpcServer server = new GrpcServer();
        
        server.start();
        server.awaitTermination();
    }
    
}
