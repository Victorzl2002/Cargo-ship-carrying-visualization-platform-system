package com.ruoyi.terminal.utils;

import com.ruoyi.terminal.domain.TerminalMessage;

/**
 * @BelongsProject: cargo_porject
 * @BelongsPackage: com.ruoyi.terminal.utils
 * @Author: VictorZl
 * @CreateTime: 2024-03-31  10:03
 * @Description: TODO
 * @Version: 1.0
 */
public class TerminalMessageParser {
    public TerminalMessage parseMessage(String message) {
        if (!isValidMessage(message)) {
            return null;
        }
        TerminalMessage terminalMessage = new TerminalMessage();
        terminalMessage.setHeader(message.substring(0, 1));
        terminalMessage.setTerminalId(message.substring(1, 5));
        terminalMessage.setCommand(message.substring(5, 6));
        terminalMessage.setData(message.substring(6, message.length() - 3)); // 修改这里
        terminalMessage.setFooter(message.substring(message.length() - 3, message.length() - 2)); // 修改这里
        terminalMessage.setChecksum(message.substring(message.length() - 2, message.length()));
        return terminalMessage;
    }

    private boolean isValidMessage(String message) {
        // 检查报文长度是否合法
        if (message.length() < 9) { // 修改这里
            return false;
        }
        // 检查检验和是否正确
        String checksum = message.substring(message.length() - 2);
        String content = message.substring(1, message.length() - 3); // 修改这里
        if (!calculateChecksum(content).equals(checksum)) {
            return false;
        }
        return true;
    }

    private String calculateChecksum(String content) {
        // 计算检验和
        int sum = 0;
        for (char c : content.toCharArray()) {
            sum += (int) c;
        }
        int checksum = sum & 0xFF; // 取低字节
        String checksumHex = Integer.toHexString(checksum).toUpperCase();
        // 如果checksumHex的长度为1，则在前面补0，使其成为两位数
        if (checksumHex.length() == 1) {
            checksumHex = "0" + checksumHex;
        }
        return checksumHex;
    }

}

