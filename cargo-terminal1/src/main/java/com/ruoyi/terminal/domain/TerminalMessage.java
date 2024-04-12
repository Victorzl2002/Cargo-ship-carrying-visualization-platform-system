package com.ruoyi.terminal.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: cargo_porject
 * @BelongsPackage: com.ruoyi.terminal.domain
 * @Author: VictorZl
 * @CreateTime: 2024-03-31  09:59
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminalMessage
{
    String header;
    String terminalId;
    String command;
    String data;
    String footer;
    String checksum;
}

