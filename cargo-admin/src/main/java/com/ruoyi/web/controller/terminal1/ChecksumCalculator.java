package com.ruoyi.web.controller.terminal1;

/**
 * @BelongsProject: cargo_porject
 * @BelongsPackage: com.ruoyi.web.controller.terminal1
 * @Author: VictorZl
 * @CreateTime: 2024-05-11  21:08
 * @Description: TODO
 * @Version: 1.0
 */
public class ChecksumCalculator {
    public void ChecksumCalculator()
    {

    }
    public static String calculateChecksum(String message) {
        int sum = 0;

        // 遍历消息中的每个字符，计算其ASCII码值的累加和
        for (char c : message.toCharArray()) {
            sum += c;
        }

        // 取低字节，即sum的最后两位的十六进制表示
        String checksum = Integer.toHexString(sum & 0xFF).toUpperCase();

        // 如果checksum是单字符，则在前面补0
        if (checksum.length() == 1) {
            checksum = "0" + checksum;
        }

        return checksum;
    }

    public static void main(String[] args) {
        String message = "0001P3435.3810,11912.4602";
        String checksum = calculateChecksum(message);
        System.out.println("计算得到的检查和（kk）: " + checksum);
    }
}
