package com.ruoyi.allocation.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 货物分配对象 b_allocation
 * 
 * @author victorzl
 * @date 2024-01-19
 */
public class BAllocation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分配记录id */
    private Long allocationId;

    /** 航运计划id */
    @Excel(name = "航运计划id")
    private Long scheduleId;

    /** 货物id */
    @Excel(name = "货物id")
    private Long cargoId;

    /** 集装箱id */
    @Excel(name = "集装箱id")
    private Long containerId;

    /** 分配时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "分配时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date allocationDate;

    public void setAllocationId(Long allocationId) 
    {
        this.allocationId = allocationId;
    }

    public Long getAllocationId() 
    {
        return allocationId;
    }
    public void setScheduleId(Long scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() 
    {
        return scheduleId;
    }
    public void setCargoId(Long cargoId) 
    {
        this.cargoId = cargoId;
    }

    public Long getCargoId() 
    {
        return cargoId;
    }
    public void setContainerId(Long containerId) 
    {
        this.containerId = containerId;
    }

    public Long getContainerId() 
    {
        return containerId;
    }
    public void setAllocationDate(Date allocationDate) 
    {
        this.allocationDate = allocationDate;
    }

    public Date getAllocationDate() 
    {
        return allocationDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("allocationId", getAllocationId())
            .append("scheduleId", getScheduleId())
            .append("cargoId", getCargoId())
            .append("containerId", getContainerId())
            .append("allocationDate", getAllocationDate())
            .toString();
    }
}
