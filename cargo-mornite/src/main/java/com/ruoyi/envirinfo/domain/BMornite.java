package com.ruoyi.envirinfo.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 监测信息反馈对象 b_mornite
 * 
 * @author victorzl
 * @date 2024-05-11
 */
public class BMornite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 监测记录id */
    private Long morniteId;

    /** 终端id */
    @Excel(name = "终端id")
    private String terminalId;

    /** 经度 */
    @Excel(name = "经度")
    private String longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private String dimension;

    /** 温度 */
    @Excel(name = "温度")
    private String temperature;

    /** 湿度 */
    @Excel(name = "湿度")
    private String humidity;

    /** 烟雾状态 */
    @Excel(name = "烟雾状态")
    private String fumesStatus;

    /** 监测时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "监测时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date morniteTime;

    public void setMorniteId(Long morniteId) 
    {
        this.morniteId = morniteId;
    }

    public Long getMorniteId() 
    {
        return morniteId;
    }
    public void setTerminalId(String terminalId) 
    {
        this.terminalId = terminalId;
    }

    public String getTerminalId() 
    {
        return terminalId;
    }
    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }

    public String getLongitude() 
    {
        return longitude;
    }
    public void setDimension(String dimension) 
    {
        this.dimension = dimension;
    }

    public String getDimension() 
    {
        return dimension;
    }
    public void setTemperature(String temperature) 
    {
        this.temperature = temperature;
    }

    public String getTemperature() 
    {
        return temperature;
    }
    public void setHumidity(String humidity) 
    {
        this.humidity = humidity;
    }

    public String getHumidity() 
    {
        return humidity;
    }
    public void setFumesStatus(String fumesStatus) 
    {
        this.fumesStatus = fumesStatus;
    }

    public String getFumesStatus() 
    {
        return fumesStatus;
    }
    public void setMorniteTime(Date morniteTime) 
    {
        this.morniteTime = morniteTime;
    }

    public Date getMorniteTime() 
    {
        return morniteTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("morniteId", getMorniteId())
            .append("terminalId", getTerminalId())
            .append("longitude", getLongitude())
            .append("dimension", getDimension())
            .append("temperature", getTemperature())
            .append("humidity", getHumidity())
            .append("fumesStatus", getFumesStatus())
            .append("morniteTime", getMorniteTime())
            .toString();
    }
}
