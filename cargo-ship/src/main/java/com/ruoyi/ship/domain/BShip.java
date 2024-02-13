package com.ruoyi.ship.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 船舶管理对象 b_ship
 * 
 * @author victorzl
 * @date 2023-12-16
 */
public class BShip extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 货轮id */
    private Long shipId;

    /** 船运公司id */
    @Excel(name = "船运公司id")
    private Long companyId;

    /** 船舶名称 */
    @Excel(name = "船舶名称")
    private String shipName;

    /** 船舶类型 */
    @Excel(name = "船舶类型")
    private String shipType;

    @Excel(name = "货轮箱位")
    private String shipBox;

    /** 吃水 */
    @Excel(name = "吃水")
    private Long shipDraft;

    /** 净重 */
    @Excel(name = "净重")
    private Long shipNetweight;

    /** 总吨 */
    @Excel(name = "总吨")
    private Long shipWeight;

    /** MMSI */
    @Excel(name = "MMSI")
    private String shipMmsi;

    /** 船籍港 */
    @Excel(name = "船籍港")
    private String shipPortofregistry;

    /** 呼号 */
    @Excel(name = "呼号")
    private String shipCallsign;

    /** 国籍 */
    @Excel(name = "国籍")
    private String shipNationality;

    /** IMO号 */
    @Excel(name = "IMO号")
    private String shipImo;

    /** 船长 */
    @Excel(name = "船长")
    private String shipLength;

    /** 船舶图片 */
    @Excel(name = "船舶图片")
    private String shipImage;

    /** 船舶状态 */
    @Excel(name = "船舶状态")
    private String shipStatus;

    public void setShipId(Long shipId) 
    {
        this.shipId = shipId;
    }

    public Long getShipId() 
    {
        return shipId;
    }
    public void setCompanyId(Long companyId) 
    {
        this.companyId = companyId;
    }

    public Long getCompanyId() 
    {
        return companyId;
    }
    public void setShipName(String shipName) 
    {
        this.shipName = shipName;
    }


    public String getShipBox() {
        return shipBox;
    }

    public void setShipBox(String shipBox) {
        this.shipBox = shipBox;
    }
    public String getShipName() 
    {
        return shipName;
    }
    public void setShipType(String shipType) 
    {
        this.shipType = shipType;
    }

    public String getShipType() 
    {
        return shipType;
    }
    public void setShipDraft(Long shipDraft) 
    {
        this.shipDraft = shipDraft;
    }

    public Long getShipDraft() 
    {
        return shipDraft;
    }
    public void setShipNetweight(Long shipNetweight) 
    {
        this.shipNetweight = shipNetweight;
    }

    public Long getShipNetweight() 
    {
        return shipNetweight;
    }
    public void setShipWeight(Long shipWeight) 
    {
        this.shipWeight = shipWeight;
    }

    public Long getShipWeight() 
    {
        return shipWeight;
    }
    public void setShipMmsi(String shipMmsi) 
    {
        this.shipMmsi = shipMmsi;
    }

    public String getShipMmsi() 
    {
        return shipMmsi;
    }
    public void setShipPortofregistry(String shipPortofregistry) 
    {
        this.shipPortofregistry = shipPortofregistry;
    }

    public String getShipPortofregistry() 
    {
        return shipPortofregistry;
    }
    public void setShipCallsign(String shipCallsign) 
    {
        this.shipCallsign = shipCallsign;
    }

    public String getShipCallsign() 
    {
        return shipCallsign;
    }
    public void setShipNationality(String shipNationality) 
    {
        this.shipNationality = shipNationality;
    }

    public String getShipNationality() 
    {
        return shipNationality;
    }
    public void setShipImo(String shipImo) 
    {
        this.shipImo = shipImo;
    }

    public String getShipImo() 
    {
        return shipImo;
    }
    public void setShipLength(String shipLength)
    {
        this.shipLength = shipLength;
    }

    public String getShipLength()
    {
        return shipLength;
    }
    public void setShipImage(String shipImage) 
    {
        this.shipImage = shipImage;
    }

    public String getShipImage() 
    {
        return shipImage;
    }
    public void setShipStatus(String shipStatus) 
    {
        this.shipStatus = shipStatus;
    }

    public String getShipStatus() 
    {
        return shipStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("shipId", getShipId())
            .append("companyId", getCompanyId())
            .append("shipName", getShipName())
            .append("shipType", getShipType())
            .append("shipDraft", getShipDraft())
            .append("shipNetweight", getShipNetweight())
            .append("shipWeight", getShipWeight())
            .append("shipMmsi", getShipMmsi())
            .append("shipPortofregistry", getShipPortofregistry())
            .append("shipCallsign", getShipCallsign())
            .append("shipNationality", getShipNationality())
            .append("shipImo", getShipImo())
            .append("shipLength", getShipLength())
            .append("shipImage", getShipImage())
            .append("shipStatus", getShipStatus())
            .toString();
    }
}
