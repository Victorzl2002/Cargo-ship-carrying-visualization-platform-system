package com.ruoyi.web.controller.terminal1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @BelongsProject: cargo_porject
 * @BelongsPackage: com.ruoyi.web.controller.terminal1
 * @Author: VictorZl
 * @CreateTime: 2024-05-11  21:00
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class MessageConverter {

    public static String convertToMessage(String driverName, String shiftInfo, String workload) {
        // 将司机姓名、当班信息、工作量转换成GB2312编码的十六进制字符串
        String encodedDriverName = encodeToGB2312(driverName);
        String encodedShiftInfo = encodeToGB2312(shiftInfo);
        String encodedWorkload = encodeToGB2312(workload);

        // 将转换后的内容拼接成报文格式
        return String.format("$00011%s,%s,%s#%s", encodedDriverName, encodedShiftInfo, encodedWorkload, calculateCheckSum(encodedDriverName + "," + encodedShiftInfo + "," + encodedWorkload));
    }

    private static String encodeToGB2312(String input) {
        // 对输入字符串进行GB2312编码转换
        try {
            byte[] bytes = input.getBytes("GB2312");
            StringBuilder builder = new StringBuilder();
            for (byte b : bytes) {
                builder.append(String.format("%02X", b));
            }
            return builder.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String calculateCheckSum(String input) {
        // 计算检查和
        int sum = 0;
        for (char c : input.toCharArray()) {
            sum += c;
        }
        return Integer.toHexString(sum & 0xFF).toUpperCase();
    }

//    public static void main(String[] args) {
//        String driverName = "张三";
//        String shiftInfo = "值班";
//        String workload = "2小时";
//        String message = convertToMessage(driverName, shiftInfo, workload);
//        System.out.println("生成的报文：" + message);
//    }
}

