package com.ruoyi.container.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 集装箱管理对象 b_container
 * 
 * @author victorzl
 * @date 2024-01-18
 */
public class BContainer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 集装箱id */
    private Long containerId;

    /** 集装箱类型 */
    @Excel(name = "集装箱类型")
    private String containerType;

    /** 集装箱现在重量 */
    @Excel(name = "集装箱现在重量")
    private Double currentWeight;

    /** 集装箱现在容积 */
    @Excel(name = "集装箱现在容积")
    private Double currentVolume;

    /** 最大承重 */
    @Excel(name = "最大承重")
    private Double maxWeight;

    /** 最大容器 */
    @Excel(name = "最大容器")
    private Double maxVolume;

    /** 集装箱状态 */
    @Excel(name = "集装箱位置")
    private String containerLocation;
    /** 集装箱状态 */

    @Excel(name = "集装箱状态")
    private String containerStatus;

    /** 集装箱温度 */
    @Excel(name = "集装箱温度")
    private Long containerTemperature;

    /** 集装箱湿度 */
    @Excel(name = "集装箱湿度")
    private Long containerHumidity;

    /** 集装箱经度 */
    @Excel(name = "集装箱经度")
    private String containerLongitude;

    /** 集装箱纬度 */
    @Excel(name = "集装箱纬度")
    private String containerLatitude;

    /** 货轮id */
    @Excel(name = "货轮id")
    private Long shipId;

    /** 船舱id */
    @Excel(name = "船舱id")
    private Long cabinId;

    /** 集装箱图片 */
    @Excel(name = "集装箱图片")
    private String containerImage;

    public String getContainerLocation() {
        return containerLocation;
    }

    public void setContainerLocation(String containerLocation) {
        this.containerLocation = containerLocation;
    }

    public void setContainerId(Long containerId)
    {
        this.containerId = containerId;
    }

    public Long getContainerId() 
    {
        return containerId;
    }
    public void setContainerType(String containerType) 
    {
        this.containerType = containerType;
    }

    public String getContainerType() 
    {
        return containerType;
    }
    public void setCurrentWeight(Double currentWeight)
    {
        this.currentWeight = currentWeight;
    }

    public Double getCurrentWeight()
    {
        return currentWeight;
    }
    public void setCurrentVolume(Double currentVolume)
    {
        this.currentVolume = currentVolume;
    }

    public Double getCurrentVolume()
    {
        return currentVolume;
    }
    public void setMaxWeight(Double maxWeight)
    {
        this.maxWeight = maxWeight;
    }

    public Double getMaxWeight()
    {
        return maxWeight;
    }
    public void setMaxVolume(Double maxVolume)
    {
        this.maxVolume = maxVolume;
    }

    public Double getMaxVolume()
    {
        return maxVolume;
    }
    public void setContainerStatus(String containerStatus) 
    {
        this.containerStatus = containerStatus;
    }

    public String getContainerStatus() 
    {
        return containerStatus;
    }
    public void setContainerTemperature(Long containerTemperature) 
    {
        this.containerTemperature = containerTemperature;
    }

    public Long getContainerTemperature() 
    {
        return containerTemperature;
    }
    public void setContainerHumidity(Long containerHumidity) 
    {
        this.containerHumidity = containerHumidity;
    }

    public Long getContainerHumidity() 
    {
        return containerHumidity;
    }
    public void setContainerLongitude(String containerLongitude) 
    {
        this.containerLongitude = containerLongitude;
    }

    public String getContainerLongitude() 
    {
        return containerLongitude;
    }
    public void setContainerLatitude(String containerLatitude) 
    {
        this.containerLatitude = containerLatitude;
    }

    public String getContainerLatitude() 
    {
        return containerLatitude;
    }
    public void setShipId(Long shipId) 
    {
        this.shipId = shipId;
    }

    public Long getShipId() 
    {
        return shipId;
    }
    public void setCabinId(Long cabinId) 
    {
        this.cabinId = cabinId;
    }

    public Long getCabinId() 
    {
        return cabinId;
    }
    public void setContainerImage(String containerImage) 
    {
        this.containerImage = containerImage;
    }

    public String getContainerImage() 
    {
        return containerImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("containerId", getContainerId())
            .append("containerType", getContainerType())
            .append("currentWeight", getCurrentWeight())
            .append("currentVolume", getCurrentVolume())
            .append("maxWeight", getMaxWeight())
            .append("maxVolume", getMaxVolume())
                .append("containerLocation", getContainerLocation())
            .append("containerStatus", getContainerStatus())
            .append("containerTemperature", getContainerTemperature())
            .append("containerHumidity", getContainerHumidity())
            .append("containerLongitude", getContainerLongitude())
            .append("containerLatitude", getContainerLatitude())
            .append("shipId", getShipId())
            .append("cabinId", getCabinId())
            .append("containerImage", getContainerImage())
            .toString();
    }
}
