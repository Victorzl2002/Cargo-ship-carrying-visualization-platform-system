package com.ruoyi.routeschedule.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 航运计划对象 b_routeschedule
 * 
 * @author victorzl
 * @date 2023-12-16
 */
public class BRouteschedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 航线计划id */
    private Long routescheduleId;

    /** 货轮id */
    @Excel(name = "货轮id")
    private Long shipId;

    /** 航线id */
    @Excel(name = "航线id")
    private Long routeId;


    @Excel(name = "货代公司id")
    private Long hdcompanyId;

    /** 出发港口 */
    @Excel(name = "出发港口")
    private String departurePort;


    /** 终点港口 */
    @Excel(name = "终点港口")
    private String endingPort;

    @Excel(name = "计划箱位")
    private int scheduleBox;

    /** 预计到港时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "预计到港时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date startTime;

    /** 预计出港时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "预计出港时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date endTime;
    /** 航线距离 */
    @Excel(name = "航线距离")
    private Double routeDistance;

    /** 预计航行时间 */
    @Excel(name = "预计航行时间")
    private Double estimatedDuration;

    public Long getHdcompanyId() {
        return hdcompanyId;
    }

    public void setHdcompanyId(Long hdcompanyId) {
        this.hdcompanyId = hdcompanyId;
    }

    public int getScheduleBox() {
        return scheduleBox;
    }

    public void setScheduleBox(int scheduleBox) {
        this.scheduleBox = scheduleBox;
    }
    public void setRoutescheduleId(Long routescheduleId) 
    {
        this.routescheduleId = routescheduleId;
    }

    public Long getRoutescheduleId() 
    {
        return routescheduleId;
    }
    public void setShipId(Long shipId) 
    {
        this.shipId = shipId;
    }

    public Long getShipId() 
    {
        return shipId;
    }
    public void setRouteId(Long routeId) 
    {
        this.routeId = routeId;
    }

    public Long getRouteId() 
    {
        return routeId;
    }
    public void setDeparturePort(String departurePort) 
    {
        this.departurePort = departurePort;
    }

    public String getDeparturePort() 
    {
        return departurePort;
    }
    public void setEndingPort(String endingPort) 
    {
        this.endingPort = endingPort;
    }

    public String getEndingPort() 
    {
        return endingPort;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setRouteDistance(Double routeDistance)
    {
        this.routeDistance = routeDistance;
    }

    public Double getRouteDistance()
    {
        return routeDistance;
    }
    public void setEstimatedDuration(Double estimatedDuration)
    {
        this.estimatedDuration = estimatedDuration;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getEstimatedDuration()
    {
        return estimatedDuration;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("routescheduleId", getRoutescheduleId())
            .append("shipId", getShipId())
            .append("routeId", getRouteId())
            .append("departurePort", getDeparturePort())
            .append("endingPort", getEndingPort())
                .append("scheduleBox",getScheduleBox())
            .append("startTime", getStartTime())
                .append("endTime", getEndTime())
            .append("routeDistance", getRouteDistance())
            .append("estimatedDuration", getEstimatedDuration())
            .toString();
    }
}
