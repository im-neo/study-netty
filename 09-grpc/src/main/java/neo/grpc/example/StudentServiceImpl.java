package neo.grpc.example;

import io.grpc.stub.StreamObserver;
import neo.grpc.proto.MyRequest;
import neo.grpc.proto.MyResponse;
import neo.grpc.proto.StreamRequest;
import neo.grpc.proto.StreamResponse;
import neo.grpc.proto.StudentRequest;
import neo.grpc.proto.StudentResponse;
import neo.grpc.proto.StudentResponseList;
import neo.grpc.proto.StudentServiceGrpc;

import java.util.UUID;

/**
 * @Classname: StudentServiceImpl
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/6 21:44
 * @Version: 1.0
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息：" + request.getUsername());

        // 写一条消息
        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());

        // 将消息推送给客户端
        responseObserver.onCompleted();
    }


    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到客户端信息：" + request.getAge());

        responseObserver.onNext(StudentResponse.newBuilder().setName("张三").setAge(20).setCity("江西").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("李四").setAge(30).setCity("九江").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("赵六").setAge(40).setCity("湖口").build());

        responseObserver.onCompleted();
    }


    @Override
    public StreamObserver<StudentRequest> getStudentsWrapperByAge(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest studentRequest) {
                System.out.println("接收到客户端信息：" + studentRequest.getAge());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("系统异常#" + throwable);
            }

            @Override
            public void onCompleted() {
                StudentResponseList list = StudentResponseList.newBuilder()
                        .addStudentResponse(StudentResponse.newBuilder().setName("张三").setAge(20).setCity("江西").build())
                        .addStudentResponse(StudentResponse.newBuilder().setName("李四").setAge(30).setCity("九江").build())
                        .addStudentResponse(StudentResponse.newBuilder().setName("赵六").setAge(40).setCity("湖口").build())
                        .build();

                responseObserver.onNext(list);

                responseObserver.onCompleted();
            }
        };
    }


    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {

        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest streamRequest) {
                System.out.println("接收到客户端数据：" + streamRequest.getRequestInfo());
                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("系统异常#" + throwable);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };


    }
}
