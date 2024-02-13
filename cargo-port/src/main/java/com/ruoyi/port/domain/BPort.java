package com.ruoyi.port.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * port对象 b_port
 * 
 * @author victorzl
 * @date 2023-12-06
 */
public class BPort extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 港口id */
    private Long portId;

    /** 港口名称 */
    @Excel(name = "港口名称")
    private String portName;

    /** 港口位置 */
    @Excel(name = "港口位置")
    private String portLocation;

    /** 港口设施描述 */
    @Excel(name = "港口设施描述")
    private String portFacilities;

    /** 港口联系方式 */
    @Excel(name = "港口联系方式")
    private String portContact;

    /** 港口预览 */
    @Excel(name = "港口预览")
    private String portImage;

    public void setPortId(Long portId) 
    {
        this.portId = portId;
    }

    public Long getPortId() 
    {
        return portId;
    }
    public void setPortName(String portName) 
    {
        this.portName = portName;
    }

    public String getPortName() 
    {
        return portName;
    }
    public void setPortLocation(String portLocation) 
    {
        this.portLocation = portLocation;
    }

    public String getPortLocation() 
    {
        return portLocation;
    }
    public void setPortFacilities(String portFacilities) 
    {
        this.portFacilities = portFacilities;
    }

    public String getPortFacilities() 
    {
        return portFacilities;
    }
    public void setPortContact(String portContact) 
    {
        this.portContact = portContact;
    }

    public String getPortContact() 
    {
        return portContact;
    }
    public void setPortImage(String portImage) 
    {
        this.portImage = portImage;
    }

    public String getPortImage() 
    {
        return portImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("portId", getPortId())
            .append("portName", getPortName())
            .append("portLocation", getPortLocation())
            .append("portFacilities", getPortFacilities())
            .append("portContact", getPortContact())
            .append("portImage", getPortImage())
            .toString();
    }
}
