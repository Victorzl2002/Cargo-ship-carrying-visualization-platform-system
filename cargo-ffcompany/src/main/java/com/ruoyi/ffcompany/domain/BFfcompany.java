package com.ruoyi.ffcompany.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 货代公司管理对象 b_ffcompany
 * 
 * @author victorzl
 * @date 2024-01-15
 */
public class BFfcompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 货代公司id */
    private Long ffcompanyId;

    /** 货代公司名称 */
    @Excel(name = "货代公司名称")
    private String ffcompanyName;

    /** 货代公司注册地点 */
    @Excel(name = "货代公司注册地点")
    private String ffregisteredLocation;

    /** 货代公司联系地址 */
    @Excel(name = "货代公司联系地址")
    private String ffcontactAddress;

    /** 货代公司联系电话 */
    @Excel(name = "货代公司联系电话")
    private String ffcontactPhone;

    /** 货代公司电子邮件 */
    @Excel(name = "货代公司电子邮件")
    private String ffEmail;

    /** 货代公司网站地址 */
    @Excel(name = "货代公司网站地址")
    private String ffWebsite;

    /** 公司实景图片 */
    @Excel(name = "公司实景图片")
    private String ffImage;

    public void setFfcompanyId(Long ffcompanyId) 
    {
        this.ffcompanyId = ffcompanyId;
    }

    public Long getFfcompanyId() 
    {
        return ffcompanyId;
    }
    public void setFfcompanyName(String ffcompanyName) 
    {
        this.ffcompanyName = ffcompanyName;
    }

    public String getFfcompanyName() 
    {
        return ffcompanyName;
    }
    public void setFfregisteredLocation(String ffregisteredLocation) 
    {
        this.ffregisteredLocation = ffregisteredLocation;
    }

    public String getFfregisteredLocation() 
    {
        return ffregisteredLocation;
    }
    public void setFfcontactAddress(String ffcontactAddress) 
    {
        this.ffcontactAddress = ffcontactAddress;
    }

    public String getFfcontactAddress() 
    {
        return ffcontactAddress;
    }
    public void setFfcontactPhone(String ffcontactPhone) 
    {
        this.ffcontactPhone = ffcontactPhone;
    }

    public String getFfcontactPhone() 
    {
        return ffcontactPhone;
    }
    public void setFfEmail(String ffEmail) 
    {
        this.ffEmail = ffEmail;
    }

    public String getFfEmail() 
    {
        return ffEmail;
    }
    public void setFfWebsite(String ffWebsite) 
    {
        this.ffWebsite = ffWebsite;
    }

    public String getFfWebsite() 
    {
        return ffWebsite;
    }
    public void setFfImage(String ffImage) 
    {
        this.ffImage = ffImage;
    }

    public String getFfImage() 
    {
        return ffImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("ffcompanyId", getFfcompanyId())
            .append("ffcompanyName", getFfcompanyName())
            .append("ffregisteredLocation", getFfregisteredLocation())
            .append("ffcontactAddress", getFfcontactAddress())
            .append("ffcontactPhone", getFfcontactPhone())
            .append("ffEmail", getFfEmail())
            .append("ffWebsite", getFfWebsite())
            .append("ffImage", getFfImage())
            .toString();
    }
}
