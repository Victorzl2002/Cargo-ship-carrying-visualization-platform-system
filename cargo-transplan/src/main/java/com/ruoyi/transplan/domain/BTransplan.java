package com.ruoyi.transplan.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * transplan对象 b_transplan
 * 
 * @author victorzl
 * @date 2024-04-07
 */
public class BTransplan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 运输计划id */
    private Long planId;

    /** 运输货物id */
    @Excel(name = "运输货物id")
    private Long cargoId;

    /** 起始地点 */
    @Excel(name = "起始地点")
    private String startLocation;

    /** 目的地 */
    @Excel(name = "目的地")
    private String endLocation;

    /** 预计发车时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "预计发车时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date departureTime;

    /** 预计到达时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "预计到达时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date arrivalTime;

    /** 货车司机id */
    @Excel(name = "货车司机id")
    private Long driverId;

    /** 货车车牌号 */
    @Excel(name = "货车车牌号")
    private String licensePlate;

    /** 运输状态 */
    @Excel(name = "运输状态")
    private String transportationStatus;

    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
    }
    public void setCargoId(Long cargoId) 
    {
        this.cargoId = cargoId;
    }

    public Long getCargoId() 
    {
        return cargoId;
    }
    public void setStartLocation(String startLocation) 
    {
        this.startLocation = startLocation;
    }

    public String getStartLocation() 
    {
        return startLocation;
    }
    public void setEndLocation(String endLocation) 
    {
        this.endLocation = endLocation;
    }

    public String getEndLocation() 
    {
        return endLocation;
    }
    public void setDepartureTime(Date departureTime) 
    {
        this.departureTime = departureTime;
    }

    public Date getDepartureTime() 
    {
        return departureTime;
    }
    public void setArrivalTime(Date arrivalTime) 
    {
        this.arrivalTime = arrivalTime;
    }

    public Date getArrivalTime() 
    {
        return arrivalTime;
    }
    public void setDriverId(Long driverId) 
    {
        this.driverId = driverId;
    }

    public Long getDriverId() 
    {
        return driverId;
    }
    public void setLicensePlate(String licensePlate) 
    {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() 
    {
        return licensePlate;
    }
    public void setTransportationStatus(String transportationStatus) 
    {
        this.transportationStatus = transportationStatus;
    }

    public String getTransportationStatus() 
    {
        return transportationStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("planId", getPlanId())
            .append("cargoId", getCargoId())
            .append("startLocation", getStartLocation())
            .append("endLocation", getEndLocation())
            .append("departureTime", getDepartureTime())
            .append("arrivalTime", getArrivalTime())
            .append("driverId", getDriverId())
            .append("licensePlate", getLicensePlate())
            .append("transportationStatus", getTransportationStatus())
            .toString();
    }
}
