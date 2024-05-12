package com.ruoyi.web.controller.terminal1;

import com.ruoyi.dangerinfo.domain.BDanger;
import com.ruoyi.driver.domain.BDriver;
import com.ruoyi.driver.service.IBDriverService;
import com.ruoyi.envirinfo.domain.BMornite;
import com.ruoyi.envirinfo.service.IBMorniteService;
import com.ruoyi.terminal.domain.TerminalMessage;
import com.ruoyi.terminal.utils.TerminalMessageParser;
import com.ruoyi.terminalmanage.service.IBTerminalService;
import com.ruoyi.transplan.domain.BTransplan;
import com.ruoyi.transplan.service.IBTransplanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.ruoyi.common.core.domain.AjaxResult.success;

@Component
public class TerminalController {

    @Autowired
    IBMorniteService ibMorniteService;

    @Autowired
    IBTerminalService ibTerminalService;

    @Autowired
    IBTransplanService ibTransplanService;

    @Autowired
    private IBTransplanService bTransplanService;

    @Autowired
    private IBDriverService ibDriverService;

    @Autowired
    private MessageConverter messageConverter;

    private final int SERVER_PORT = 7000;
    private final ExecutorService threadPool = Executors.newCachedThreadPool();
    private TerminalMessageParser parser = new TerminalMessageParser();


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
                                TerminalMessage terminalMessage = parser.parseMessage(message);
                                System.out.println(terminalMessage);
                                handleTerminalMessage(clientSocket,terminalMessage);
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
    private void sendDataToClient(Socket clientSocket, String data) {
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(data.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void handleTerminalMessage(Socket clientSocket, TerminalMessage terminalMessage) throws IOException {
        if (terminalMessage == null) {
            System.out.println("Invalid message format");
            return;
        }
        switch (terminalMessage.getCommand()) {
            case "1":
                //发送数据到终端
                BTransplan bTransplan10=new BTransplan();
                String che=ibTerminalService.selectBTerminalByTerminalId(terminalMessage.getTerminalId()).getTerminalLoacation();
                bTransplan10.setLicensePlate(che);
                List<BTransplan> bTransplan11=ibTransplanService.selectBTransplanList(bTransplan10);
                BTransplan[] bTransplan12 = new BTransplan[1]; // 声明为数组，包装变量
                bTransplan12[0] = null; // 初始化为 null
                bTransplan11.forEach(s->{
                    if (s.getTransportationStatus().length()==0)
                    {
                        bTransplan12[0]=s;
                    }
                });
                ;
                String message=MessageConverter.convertToMessage(ibDriverService.selectBDriverByDriverId( bTransplan12[0].getDriverId()).getDriverName(),"运输","2小时");
                sendDataToClient(clientSocket, message);
                System.out.println("发送运输班次信息成功");
                break;
            case "A":
            case "B":
            case "C":
                BTransplan bTransplan=new BTransplan();
                String chepai=ibTerminalService.selectBTerminalByTerminalId(terminalMessage.getTerminalId()).getTerminalLoacation();
                bTransplan.setLicensePlate(chepai);
                BTransplan bTransplan1=ibTransplanService.selectBTransplanList(bTransplan).get(0);
                if (terminalMessage.getCommand().equals("A"))
                {
                    bTransplan1.setTransportationStatus("确认收到");
                }
                if (terminalMessage.getCommand().equals("B"))
                {
                    bTransplan1.setTransportationStatus("正在途中");
                }
                if (terminalMessage.getCommand().equals("C"))
                {
                    bTransplan1.setTransportationStatus("已到达");
                }
                ibTransplanService.updateBTransplan(bTransplan1);
                System.out.println("Acknowledgement received");
                break;
            case "P":
                // 处理位置信息数据
                // 这里做相应处理逻辑
                BMornite bMornite=new BMornite();
                bMornite.setTerminalId(terminalMessage.getTerminalId());
                List<BMornite> bMornite1=ibMorniteService.selectBMorniteList(bMornite);
                if (bMornite1==null)
                {
                    String[] strs=terminalMessage.getData().split(",");
                    bMornite.setLongitude(strs[1]);
                    bMornite.setDimension(strs[0]);
                    bMornite.setMorniteTime(new Date());
                    ibMorniteService.insertBMornite(bMornite);
                }
                else
                {
                    String[] strs=terminalMessage.getData().split(",");
                    bMornite.setLongitude(strs[1]);
                    bMornite.setDimension(strs[0]);
                    bMornite.setMorniteTime(new Date());
                    bMornite.setMorniteId(bMornite1.get(0).getMorniteId());
                    ibMorniteService.updateBMornite(bMornite);
                }
                System.out.println("Location data processed successfully");
                break;
            case "E":
                // 处理温度、湿度、报警状态数据
                // 这里做相应处理逻辑
                BMornite bMornite2= new BMornite();
                bMornite2.setTerminalId(terminalMessage.getTerminalId());
                List<BMornite> bMornite3=ibMorniteService.selectBMorniteList(bMornite2);
                if (bMornite2==null)
                {
                    String[] strs=terminalMessage.getData().split(",");
                    bMornite2.setTemperature(strs[0]);
                    bMornite2.setHumidity(strs[1]);
                    bMornite2.setFumesStatus(strs[2]);
                    bMornite2.setMorniteTime(new Date());
                    ibMorniteService.insertBMornite(bMornite2);
                }
                else
                {
                    String[] strs=terminalMessage.getData().split(",");
                    bMornite2.setTemperature(strs[0]);
                    bMornite2.setHumidity(strs[1]);
                    bMornite2.setFumesStatus(strs[2]);
                    bMornite2.setMorniteId(bMornite3.get(0).getMorniteId());
                    bMornite2.setMorniteTime(new Date());
                    ibMorniteService.updateBMornite(bMornite2);
                }
                System.out.println("Temperature, humidity, and alarm status data processed successfully");
                break;
            default:
                System.out.println("Invalid command");
        }
    }
}
