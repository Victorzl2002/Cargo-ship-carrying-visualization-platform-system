package com.ruoyi.pile.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 货堆管理对象 b_pile
 * 
 * @author victorzl
 * @date 2024-01-24
 */
public class BPile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 货堆id */
    private Long pileId;

    /** 货堆x坐标 */
    @Excel(name = "货堆x坐标")
    private String pileX;

    /** 货堆y坐标 */
    @Excel(name = "货堆y坐标")
    private String pileY;

    /** 货堆容量 */
    @Excel(name = "货堆容量")
    private String pileCapacity;

    /** 当前堆积数量 */
    @Excel(name = "当前堆积数量")
    private Long pileCurrentnum;

    /** 货堆状态 */
    @Excel(name = "货堆状态")
    private String pileStatus;

    public void setPileId(Long pileId) 
    {
        this.pileId = pileId;
    }

    public Long getPileId() 
    {
        return pileId;
    }
    public void setPileX(String pileX) 
    {
        this.pileX = pileX;
    }

    public String getPileX() 
    {
        return pileX;
    }
    public void setPileY(String pileY) 
    {
        this.pileY = pileY;
    }

    public String getPileY() 
    {
        return pileY;
    }
    public void setPileCapacity(String pileCapacity) 
    {
        this.pileCapacity = pileCapacity;
    }

    public String getPileCapacity() 
    {
        return pileCapacity;
    }
    public void setPileCurrentnum(Long pileCurrentnum) 
    {
        this.pileCurrentnum = pileCurrentnum;
    }

    public Long getPileCurrentnum() 
    {
        return pileCurrentnum;
    }
    public void setPileStatus(String pileStatus) 
    {
        this.pileStatus = pileStatus;
    }

    public String getPileStatus() 
    {
        return pileStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("pileId", getPileId())
            .append("pileX", getPileX())
            .append("pileY", getPileY())
            .append("pileCapacity", getPileCapacity())
            .append("pileCurrentnum", getPileCurrentnum())
            .append("pileStatus", getPileStatus())
            .toString();
    }
}
