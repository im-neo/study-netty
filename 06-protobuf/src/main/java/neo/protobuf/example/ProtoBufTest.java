package neo.protobuf.example;

/**
 * @Classname: ProtoBufTest
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/1 21:03
 * @Version: 1.0
 */
public class ProtoBufTest {

    public static void main(String[] args) throws Exception {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("张三")
                .setAge(25)
                .setAddress("上海")
                .build();

        byte[] studentByteArray = student.toByteArray();

        DataInfo.Student parseStudent = DataInfo.Student.parseFrom(studentByteArray);

        System.out.println(parseStudent.getName());
        System.out.println(parseStudent.getAge());
        System.out.println(parseStudent.getAddress());
        System.out.println("==========================");
        System.out.println(parseStudent);
    }
}
