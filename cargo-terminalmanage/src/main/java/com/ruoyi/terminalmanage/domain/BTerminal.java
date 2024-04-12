package com.ruoyi.terminalmanage.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * terminalmanage对象 b_terminal
 * 
 * @author victorzl
 * @date 2024-04-07
 */
public class BTerminal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 终端编号 */
    private String terminalId;

    /** 终端类型 */
    @Excel(name = "终端类型")
    private String terminalType;

    /** 终端状态 */
    @Excel(name = "终端状态")
    private String terminalStatus;

    /** 终端位置 */
    @Excel(name = "终端位置")
    private String terminalLoacation;

    /** 安装日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "安装日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date installationDate;

    public void setTerminalId(String terminalId) 
    {
        this.terminalId = terminalId;
    }

    public String getTerminalId() 
    {
        return terminalId;
    }
    public void setTerminalType(String terminalType) 
    {
        this.terminalType = terminalType;
    }

    public String getTerminalType() 
    {
        return terminalType;
    }
    public void setTerminalStatus(String terminalStatus) 
    {
        this.terminalStatus = terminalStatus;
    }

    public String getTerminalStatus() 
    {
        return terminalStatus;
    }
    public void setTerminalLoacation(String terminalLoacation) 
    {
        this.terminalLoacation = terminalLoacation;
    }

    public String getTerminalLoacation() 
    {
        return terminalLoacation;
    }
    public void setInstallationDate(Date installationDate) 
    {
        this.installationDate = installationDate;
    }

    public Date getInstallationDate() 
    {
        return installationDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("terminalId", getTerminalId())
            .append("terminalType", getTerminalType())
            .append("terminalStatus", getTerminalStatus())
            .append("terminalLoacation", getTerminalLoacation())
            .append("installationDate", getInstallationDate())
            .toString();
    }
}
