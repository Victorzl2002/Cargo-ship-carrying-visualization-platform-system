package com.ruoyi.containerslot.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 箱位管理对象 b_containerslot
 * 
 * @author victorzl
 * @date 2024-02-12
 */
public class BContainerslot extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 船箱位 */
    private Long slotId;

    /** 船舶id */
    @Excel(name = "船舶id")
    private Long shipId;

    /** 船箱位的行号 */
    @Excel(name = "船箱位的行号")
    private Long rNumber;

    /** 船箱位的层号 */
    @Excel(name = "船箱位的层号")
    private Long tierNumber;

    /** 船箱位的列号 */
    @Excel(name = "船箱位的列号")
    private Long cNumber;

    /** 箱位状态 */
    @Excel(name = "箱位状态")
    private String status;

    public void setSlotId(Long slotId) 
    {
        this.slotId = slotId;
    }

    public Long getSlotId() 
    {
        return slotId;
    }
    public void setShipId(Long shipId) 
    {
        this.shipId = shipId;
    }

    public Long getShipId() 
    {
        return shipId;
    }
    public void setrNumber(Long rNumber) 
    {
        this.rNumber = rNumber;
    }

    public Long getrNumber() 
    {
        return rNumber;
    }
    public void setTierNumber(Long tierNumber) 
    {
        this.tierNumber = tierNumber;
    }

    public Long getTierNumber() 
    {
        return tierNumber;
    }
    public void setcNumber(Long cNumber) 
    {
        this.cNumber = cNumber;
    }

    public Long getcNumber() 
    {
        return cNumber;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("slotId", getSlotId())
            .append("shipId", getShipId())
            .append("rNumber", getrNumber())
            .append("tierNumber", getTierNumber())
            .append("cNumber", getcNumber())
            .append("status", getStatus())
            .toString();
    }
}
