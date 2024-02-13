package com.ruoyi.containerunloading.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 集装箱卸载管理对象 b_containerunloading
 * 
 * @author victorzl
 * @date 2024-02-13
 */
public class BContainerunloading extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 集装箱卸载id */
    private Long unloadingId;

    /** 航运计划id */
    @Excel(name = "航运计划id")
    private Long scheduleId;

    /** 箱位id */
    @Excel(name = "箱位id")
    private Long slotId;

    /** 货堆id */
    @Excel(name = "货堆id")
    private Long pileId;

    /** 集装箱id */
    @Excel(name = "集装箱id")
    private Long containerId;

    /** 卸箱时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "卸箱时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date unloadingTime;

    public void setUnloadingId(Long unloadingId) 
    {
        this.unloadingId = unloadingId;
    }

    public Long getUnloadingId() 
    {
        return unloadingId;
    }
    public void setScheduleId(Long scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() 
    {
        return scheduleId;
    }
    public void setSlotId(Long slotId) 
    {
        this.slotId = slotId;
    }

    public Long getSlotId() 
    {
        return slotId;
    }
    public void setPileId(Long pileId) 
    {
        this.pileId = pileId;
    }

    public Long getPileId() 
    {
        return pileId;
    }
    public void setContainerId(Long containerId) 
    {
        this.containerId = containerId;
    }

    public Long getContainerId() 
    {
        return containerId;
    }
    public void setUnloadingTime(Date unloadingTime) 
    {
        this.unloadingTime = unloadingTime;
    }

    public Date getUnloadingTime() 
    {
        return unloadingTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("unloadingId", getUnloadingId())
            .append("scheduleId", getScheduleId())
            .append("slotId", getSlotId())
            .append("pileId", getPileId())
            .append("containerId", getContainerId())
            .append("unloadingTime", getUnloadingTime())
            .toString();
    }
}
