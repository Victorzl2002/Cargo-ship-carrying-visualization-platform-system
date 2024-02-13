package com.ruoyi.containerassembly.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 集装箱装配对象 b_containerassembly
 * 
 * @author victorzl
 * @date 2024-02-12
 */
public class BContainerassembly extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 装配id */
    private Long assemblyId;

    /** 航运计划id */
    @Excel(name = "航运计划id")
    private Long scheduleId;

    /** 集装箱id */
    @Excel(name = "集装箱id")
    private Long containerId;

    /** 船箱位id */
    @Excel(name = "船箱位id")
    private Long slotId;

    /** 装配时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "装配时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date assemblyTime;

    public void setAssemblyId(Long assemblyId) 
    {
        this.assemblyId = assemblyId;
    }

    public Long getAssemblyId() 
    {
        return assemblyId;
    }
    public void setScheduleId(Long scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() 
    {
        return scheduleId;
    }
    public void setContainerId(Long containerId) 
    {
        this.containerId = containerId;
    }

    public Long getContainerId() 
    {
        return containerId;
    }
    public void setSlotId(Long slotId) 
    {
        this.slotId = slotId;
    }

    public Long getSlotId() 
    {
        return slotId;
    }
    public void setAssemblyTime(Date assemblyTime) 
    {
        this.assemblyTime = assemblyTime;
    }

    public Date getAssemblyTime() 
    {
        return assemblyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("assemblyId", getAssemblyId())
            .append("scheduleId", getScheduleId())
            .append("containerId", getContainerId())
            .append("slotId", getSlotId())
            .append("assemblyTime", getAssemblyTime())
            .toString();
    }
}
