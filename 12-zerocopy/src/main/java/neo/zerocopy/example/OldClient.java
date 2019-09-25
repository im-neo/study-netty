package neo.zerocopy.example;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

public class OldClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8899);

        String filePath = "D:\\tools\\jdk-8u221-windows-x64.exe";
        FileInputStream inputStream = new FileInputStream(filePath);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        long readCount = 0;
        long total = 0;

        long startTime = System.currentTimeMillis();

        while ((readCount = inputStream.read(buffer)) >= 0) {
            total += readCount;
            dataOutputStream.write(buffer);
        }

        System.out.println("发送总字节：" + total + "，耗时：" + (System.currentTimeMillis() - startTime));
    }
}
