package com.ruoyi.driver.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * driver对象 b_driver
 * 
 * @author victorzl
 * @date 2024-04-07
 */
public class BDriver extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 司机Id */
    private Long driverId;

    /** 司机姓名 */
    @Excel(name = "司机姓名")
    private String driverName;

    /** 身份证 */
    @Excel(name = "身份证")
    private String identitiyNumber;

    /** 驾驶证 */
    @Excel(name = "驾驶证")
    private String licenseNumber;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String driverPhone;

    /** 驾驶状态 */
    @Excel(name = "驾驶状态")
    private String drivingStatus;

    /** 是否在线 */
    @Excel(name = "是否在线")
    private String onlineStatus;

    /** 所属公司 */
    @Excel(name = "所属公司")
    private String dirverCompany;

    public void setDriverId(Long driverId) 
    {
        this.driverId = driverId;
    }

    public Long getDriverId() 
    {
        return driverId;
    }
    public void setDriverName(String driverName) 
    {
        this.driverName = driverName;
    }

    public String getDriverName() 
    {
        return driverName;
    }
    public void setIdentitiyNumber(String identitiyNumber) 
    {
        this.identitiyNumber = identitiyNumber;
    }

    public String getIdentitiyNumber() 
    {
        return identitiyNumber;
    }
    public void setLicenseNumber(String licenseNumber) 
    {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() 
    {
        return licenseNumber;
    }
    public void setDriverPhone(String driverPhone) 
    {
        this.driverPhone = driverPhone;
    }

    public String getDriverPhone() 
    {
        return driverPhone;
    }
    public void setDrivingStatus(String drivingStatus) 
    {
        this.drivingStatus = drivingStatus;
    }

    public String getDrivingStatus() 
    {
        return drivingStatus;
    }
    public void setOnlineStatus(String onlineStatus) 
    {
        this.onlineStatus = onlineStatus;
    }

    public String getOnlineStatus() 
    {
        return onlineStatus;
    }
    public void setDirverCompany(String dirverCompany) 
    {
        this.dirverCompany = dirverCompany;
    }

    public String getDirverCompany() 
    {
        return dirverCompany;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("driverId", getDriverId())
            .append("driverName", getDriverName())
            .append("identitiyNumber", getIdentitiyNumber())
            .append("licenseNumber", getLicenseNumber())
            .append("driverPhone", getDriverPhone())
            .append("drivingStatus", getDrivingStatus())
            .append("onlineStatus", getOnlineStatus())
            .append("dirverCompany", getDirverCompany())
            .toString();
    }
}
