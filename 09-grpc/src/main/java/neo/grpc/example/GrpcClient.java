package neo.grpc.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import neo.grpc.proto.MyRequest;
import neo.grpc.proto.MyResponse;
import neo.grpc.proto.StreamRequest;
import neo.grpc.proto.StreamResponse;
import neo.grpc.proto.StudentRequest;
import neo.grpc.proto.StudentResponse;
import neo.grpc.proto.StudentResponseList;
import neo.grpc.proto.StudentServiceGrpc;

import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * @Classname: GrpcClient
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/6 21:57
 * @Version: 1.0
 */
public class GrpcClient {


    public static void main(String[] args) throws Exception {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).usePlaintext().build();

        //同步
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);

        // 异步
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);

        MyResponse myResponse = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zhangsan").build());

        System.out.println(myResponse.getRealname());

        System.out.println("------------------------");

        Iterator<StudentResponse> iterator = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
        iterator.forEachRemaining(s -> System.out.println(s.getName() + "-" + s.getAge() + "-" + s.getCity()));

        System.out.println("------------------------");

        StreamObserver<StudentResponseList> studentResponseListStreamObserver = new StreamObserver<StudentResponseList>() {
            @Override
            public void onNext(StudentResponseList studentResponseList) {
                studentResponseList.getStudentResponseList().forEach(s -> System.out.println(s.getName() + "-" + s.getAge() + "-" + s.getCity()));
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("系统异常#" + throwable);
            }

            @Override
            public void onCompleted() {
                System.out.println("Completed !");
            }
        };

        StreamObserver<StudentRequest> studentRequestStreamObserver = stub.getStudentsWrapperByAge(studentResponseListStreamObserver);
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(20).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(30).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(40).build());
        studentRequestStreamObserver.onCompleted();
        // 需要休眠一段时间等待服务端响应数据
        // 否则客户端直接终止，无法等到服务器端响应数据
        Thread.sleep(5000);


        System.out.println("------------------------");


        StreamObserver<StreamRequest> requestStreamObserver = stub.biTalk(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse streamResponse) {
                System.out.println("收到服务端数据：" + streamResponse.getResponseInfo());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("系统异常#" + throwable);
            }

            @Override
            public void onCompleted() {
                System.out.println("completed !");
            }
        });


        for (int i = 0; i < 10; i++) {
            requestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDateTime.now().toString()).build());
            Thread.sleep(1000);
        }

        Thread.sleep(5000);
    }
}
