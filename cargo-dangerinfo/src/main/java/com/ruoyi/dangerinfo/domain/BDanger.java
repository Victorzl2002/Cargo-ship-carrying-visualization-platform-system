package com.ruoyi.dangerinfo.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 危险品识别信息管理对象 b_danger
 * 
 * @author victorzl
 * @date 2024-01-07
 */
public class BDanger extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 危险品识别id */
    private Long dangerId;

    /** 识别前图片 */
    @Excel(name = "识别前图片")
    private String beforeImage;

    /** 识别结果图片 */
    @Excel(name = "识别结果图片")
    private String afterImage;

    /** 识别描述 */
    @Excel(name = "识别描述")
    private String dangerDescrition;

    /** 识别状态 */
    @Excel(name = "识别状态")
    private String dangerStatus;

    /** 识别时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "识别时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date dangerTime;

    public void setDangerId(Long dangerId) 
    {
        this.dangerId = dangerId;
    }

    public Long getDangerId() 
    {
        return dangerId;
    }
    public void setBeforeImage(String beforeImage) 
    {
        this.beforeImage = beforeImage;
    }

    public String getBeforeImage() 
    {
        return beforeImage;
    }
    public void setAfterImage(String afterImage) 
    {
        this.afterImage = afterImage;
    }

    public String getAfterImage() 
    {
        return afterImage;
    }
    public void setDangerDescrition(String dangerDescrition) 
    {
        this.dangerDescrition = dangerDescrition;
    }

    public String getDangerDescrition() 
    {
        return dangerDescrition;
    }
    public void setDangerStatus(String dangerStatus) 
    {
        this.dangerStatus = dangerStatus;
    }

    public String getDangerStatus() 
    {
        return dangerStatus;
    }
    public void setDangerTime(Date dangerTime) 
    {
        this.dangerTime = dangerTime;
    }

    public Date getDangerTime() 
    {
        return dangerTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("dangerId", getDangerId())
            .append("beforeImage", getBeforeImage())
            .append("afterImage", getAfterImage())
            .append("dangerDescrition", getDangerDescrition())
            .append("dangerStatus", getDangerStatus())
            .append("dangerTime", getDangerTime())
            .toString();
    }
}
