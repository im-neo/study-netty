// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Student.proto

package neo.grpc.proto;

public interface StudentResponseListOrBuilder extends
    // @@protoc_insertion_point(interface_extends:neo.grpc.proto.StudentResponseList)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .neo.grpc.proto.StudentResponse studentResponse = 1;</code>
   */
  java.util.List<StudentResponse> 
      getStudentResponseList();
  /**
   * <code>repeated .neo.grpc.proto.StudentResponse studentResponse = 1;</code>
   */
  StudentResponse getStudentResponse(int index);
  /**
   * <code>repeated .neo.grpc.proto.StudentResponse studentResponse = 1;</code>
   */
  int getStudentResponseCount();
  /**
   * <code>repeated .neo.grpc.proto.StudentResponse studentResponse = 1;</code>
   */
  java.util.List<? extends StudentResponseOrBuilder> 
      getStudentResponseOrBuilderList();
  /**
   * <code>repeated .neo.grpc.proto.StudentResponse studentResponse = 1;</code>
   */
  StudentResponseOrBuilder getStudentResponseOrBuilder(
          int index);
}
