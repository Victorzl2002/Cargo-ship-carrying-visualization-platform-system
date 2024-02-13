package com.ruoyi.cargoinfo.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 货物管理对象 b_cargo
 * 
 * @author victorzl
 * @date 2023-12-15
 */
public class BCargo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 货物id */
    private Long cargoId;

    /** 货物名称 */
    @Excel(name = "货物名称")
    private String cargoName;

    /** 货物类型 */
    @Excel(name = "货物类型")
    private String cargoType;

    /** 货物重量 */
    @Excel(name = "货物重量")
    private Double cargoWeight;

    /** 货物体积 */
    @Excel(name = "货物体积")
    private Double cargoVolume;

    /** 货物状态 */
    @Excel(name = "货物状态")
    private String cargoStatus;

    /** 货物图片 */
    @Excel(name = "货物图片")
    private String cargoImage;

    /** 外键关联用户表 */
    @Excel(name = "外键关联用户表")
    private String userId;

    public void setCargoId(Long cargoId) 
    {
        this.cargoId = cargoId;
    }

    public Long getCargoId() 
    {
        return cargoId;
    }
    public void setCargoName(String cargoName) 
    {
        this.cargoName = cargoName;
    }

    public String getCargoName() 
    {
        return cargoName;
    }
    public void setCargoType(String cargoType) 
    {
        this.cargoType = cargoType;
    }

    public String getCargoType() 
    {
        return cargoType;
    }
    public void setCargoWeight(Double cargoWeight)
    {
        this.cargoWeight = cargoWeight;
    }

    public Double getCargoWeight()
    {
        return cargoWeight;
    }
    public void setCargoVolume(Double cargoVolume)
    {
        this.cargoVolume = cargoVolume;
    }

    public Double getCargoVolume()
    {
        return cargoVolume;
    }
    public void setCargoStatus(String cargoStatus) 
    {
        this.cargoStatus = cargoStatus;
    }

    public String getCargoStatus() 
    {
        return cargoStatus;
    }
    public void setCargoImage(String cargoImage) 
    {
        this.cargoImage = cargoImage;
    }

    public String getCargoImage() 
    {
        return cargoImage;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("cargoId", getCargoId())
            .append("cargoName", getCargoName())
            .append("cargoType", getCargoType())
            .append("cargoWeight", getCargoWeight())
            .append("cargoVolume", getCargoVolume())
            .append("cargoStatus", getCargoStatus())
            .append("cargoImage", getCargoImage())
            .append("userId", getUserId())
            .toString();
    }
}
