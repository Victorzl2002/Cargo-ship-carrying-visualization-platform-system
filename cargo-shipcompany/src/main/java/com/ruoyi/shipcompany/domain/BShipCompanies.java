package com.ruoyi.shipcompany.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * shipcompany对象 b_ship_companies
 * 
 * @author victorzl
 * @date 2023-12-08
 */
public class BShipCompanies extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 船舶公司id */
    private Long companyId;

    /** 船舶公司名称 */
    @Excel(name = "船舶公司名称")
    private String conpanyName;

    /** 公司地址 */
    @Excel(name = "公司地址")
    private String companyAddress;

    /** 公司电话 */
    @Excel(name = "公司电话")
    private String companyPhone;

    /** 公司邮箱 */
    @Excel(name = "公司邮箱")
    private String companyEmail;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String contactPerson;

    /** 联系人邮箱 */
    @Excel(name = "联系人邮箱")
    private String contactEmail;

    /** 公司管理船舶数量 */
    @Excel(name = "公司管理船舶数量")
    private Long vesselsManaged;

    /** 公司描述 */
    @Excel(name = "公司描述")
    private String companyDescription;

    public void setCompanyId(Long companyId) 
    {
        this.companyId = companyId;
    }

    public Long getCompanyId() 
    {
        return companyId;
    }
    public void setConpanyName(String conpanyName) 
    {
        this.conpanyName = conpanyName;
    }

    public String getConpanyName() 
    {
        return conpanyName;
    }
    public void setCompanyAddress(String companyAddress) 
    {
        this.companyAddress = companyAddress;
    }

    public String getCompanyAddress() 
    {
        return companyAddress;
    }
    public void setCompanyPhone(String companyPhone) 
    {
        this.companyPhone = companyPhone;
    }

    public String getCompanyPhone() 
    {
        return companyPhone;
    }
    public void setCompanyEmail(String companyEmail) 
    {
        this.companyEmail = companyEmail;
    }

    public String getCompanyEmail() 
    {
        return companyEmail;
    }
    public void setContactPerson(String contactPerson) 
    {
        this.contactPerson = contactPerson;
    }

    public String getContactPerson() 
    {
        return contactPerson;
    }
    public void setContactEmail(String contactEmail) 
    {
        this.contactEmail = contactEmail;
    }

    public String getContactEmail() 
    {
        return contactEmail;
    }
    public void setVesselsManaged(Long vesselsManaged) 
    {
        this.vesselsManaged = vesselsManaged;
    }

    public Long getVesselsManaged() 
    {
        return vesselsManaged;
    }
    public void setCompanyDescription(String companyDescription) 
    {
        this.companyDescription = companyDescription;
    }

    public String getCompanyDescription() 
    {
        return companyDescription;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("companyId", getCompanyId())
            .append("conpanyName", getConpanyName())
            .append("companyAddress", getCompanyAddress())
            .append("companyPhone", getCompanyPhone())
            .append("companyEmail", getCompanyEmail())
            .append("contactPerson", getContactPerson())
            .append("contactEmail", getContactEmail())
            .append("vesselsManaged", getVesselsManaged())
            .append("companyDescription", getCompanyDescription())
            .toString();
    }
}
