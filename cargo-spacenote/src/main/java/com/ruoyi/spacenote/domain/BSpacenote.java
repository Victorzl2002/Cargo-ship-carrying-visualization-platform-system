package com.ruoyi.spacenote.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 集装箱空间利用率对象 b_spacenote
 * 
 * @author victorzl
 * @date 2024-01-19
 */
public class BSpacenote extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 集装箱空间利用率记录id */
    private Long spaceutilizationrateId;

    /** 航运计划id */
    @Excel(name = "航运计划id")
    private Long scheduleId;

    /** 集装箱id */
    @Excel(name = "集装箱id")
    private Long containerId;

    /** 空间利用率 */
    @Excel(name = "空间利用率")
    private String rate;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdate;

    public void setSpaceutilizationrateId(Long spaceutilizationrateId) 
    {
        this.spaceutilizationrateId = spaceutilizationrateId;
    }

    public Long getSpaceutilizationrateId() 
    {
        return spaceutilizationrateId;
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
    public void setRate(String rate) 
    {
        this.rate = rate;
    }

    public String getRate() 
    {
        return rate;
    }
    public void setCreatedate(Date createdate) 
    {
        this.createdate = createdate;
    }

    public Date getCreatedate() 
    {
        return createdate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("spaceutilizationrateId", getSpaceutilizationrateId())
            .append("scheduleId", getScheduleId())
            .append("containerId", getContainerId())
            .append("rate", getRate())
            .append("createdate", getCreatedate())
            .toString();
    }
}
