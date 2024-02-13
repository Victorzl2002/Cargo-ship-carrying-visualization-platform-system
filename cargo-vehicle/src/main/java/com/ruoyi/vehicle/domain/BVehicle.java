package com.ruoyi.vehicle.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 货车管理对象 b_vehicle
 * 
 * @author victorzl
 * @date 2024-01-24
 */
public class BVehicle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车辆编号 */
    private String vehicleId;

    /** 车辆类型 */
    @Excel(name = "车辆类型")
    private String vehicleType;

    /** 车辆状态 */
    @Excel(name = "车辆状态")
    private String vehicleStatus;

    /** 车辆图片 */
    @Excel(name = "车辆图片")
    private String vehicleImage;

    public void setVehicleId(String vehicleId) 
    {
        this.vehicleId = vehicleId;
    }

    public String getVehicleId() 
    {
        return vehicleId;
    }
    public void setVehicleType(String vehicleType) 
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() 
    {
        return vehicleType;
    }
    public void setVehicleStatus(String vehicleStatus) 
    {
        this.vehicleStatus = vehicleStatus;
    }

    public String getVehicleStatus() 
    {
        return vehicleStatus;
    }
    public void setVehicleImage(String vehicleImage) 
    {
        this.vehicleImage = vehicleImage;
    }

    public String getVehicleImage() 
    {
        return vehicleImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("vehicleId", getVehicleId())
            .append("vehicleType", getVehicleType())
            .append("vehicleStatus", getVehicleStatus())
            .append("vehicleImage", getVehicleImage())
            .toString();
    }
}
