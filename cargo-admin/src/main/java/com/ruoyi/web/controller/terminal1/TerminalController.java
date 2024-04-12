package com.ruoyi.web.controller.terminal1;
//package com.ruoyi.web.controller.terminal;
//import com.ruoyi.common.core.controller.BaseController;
//import com.ruoyi.terminal.domain.TerminalMessage;
//import com.ruoyi.terminal.utils.TerminalMessageParser;
//import org.springframework.boot.SpringApplication;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @BelongsProject: cargo_porject
// * @BelongsPackage: com.ruoyi.web.controller.terminal
// * @Author: VictorZl
// * @CreateTime: 2024-03-31  10:04
// * @Description: TODO
// * @Version: 1.0
// */
//@RestController
//@RequestMapping("/terminal")
//public class TerminalController extends BaseController {
//    private TerminalMessageParser parser = new TerminalMessageParser();
//
//    @GetMapping("/receive")
//    public ResponseEntity<String> receiveMessage(String message) {
//        TerminalMessage terminalMessage = parser.parseMessage(message);
//        System.out.println(terminalMessage);
//        if (terminalMessage == null) {
//            return ResponseEntity.badRequest().body("Invalid message format");
//        }
//        // 根据命令类型执行相应操作
//        switch (terminalMessage.getCommand()) {
//            case "1":
//                // 处理排班数据
//                // 这里做相应处理逻辑
//                return ResponseEntity.ok("Scheduling data will push");
//            case "2":
//                // 处理任务数据
//                // 这里做相应处理逻辑
//                return ResponseEntity.ok("Task data processed successfully");
//            case "A":
//                // 处理确认收到
//                // 这里做相应处理逻辑
//                return ResponseEntity.ok("Acknowledgement received");
//            case "B":
//                // 正在途中
//                // 这里做相应处理逻辑
//                return ResponseEntity.ok("Acknowledgement received");
//            case "C":
//                // 已到达
//                // 这里做相应处理逻辑
//                return ResponseEntity.ok("Acknowledgement received");
//            case "P":
//                // 处理位置信息数据
//                // 这里做相应处理逻辑
//                return ResponseEntity.ok("Location data processed successfully");
//            case "E":
//                // 处理温度、湿度、报警状态数据
//                // 这里做相应处理逻辑
//                return ResponseEntity.ok("Temperature, humidity, and alarm status data processed successfully");
//            // 其他命令类型的处理
//            default:
//                return ResponseEntity.badRequest().body("Invalid command");
//        }
//
//    }
//}


import org.springframework.boot.SpringApplication;
import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@SpringBootApplication
public class TerminalController {

    private final int SERVER_PORT = 7000;
    private final ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        SpringApplication.run(TerminalController.class, args);
    }

    @PostConstruct
    public void init() {
        startSocketServer();
    }

    private void startSocketServer() {
        threadPool.execute(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(SERVER_PORT,0, InetAddress.getByName("0.0.0.0"));
                System.out.println("ServerSocket bound to IP address: " + serverSocket.getInetAddress());
                System.out.println("ServerSocket bound to port: " + serverSocket.getLocalPort());
                System.out.println("Socket服务器已启动，监听端口：" + SERVER_PORT);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("客户端已连接：" + clientSocket.getInetAddress());

                    threadPool.execute(() -> {
                        try {
                            InputStream inputStream = clientSocket.getInputStream();
                            byte[] buffer = new byte[1024]; // 设置缓冲区大小为 1024 字节，可以根据需求调整
                            int bytesRead;
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                String message = new String(buffer, 0, bytesRead);
                                System.out.println("接收到客户端消息：" + message);
                                System.out.println(1);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                clientSocket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
