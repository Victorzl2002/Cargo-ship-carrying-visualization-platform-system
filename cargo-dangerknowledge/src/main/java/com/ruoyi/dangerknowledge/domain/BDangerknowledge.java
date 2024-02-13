package com.ruoyi.dangerknowledge.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 危险品知识库对象 b_dangerknowledge
 * 
 * @author victorzl
 * @date 2024-01-22
 */
public class BDangerknowledge extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long dangerId;

    /** 危险品名称 */
    @Excel(name = "危险品名称")
    private String dangerName;

    /** 危险品分类 */
    @Excel(name = "危险品分类")
    private String dangerType;

    /** 危险品代码 */
    @Excel(name = "危险品代码")
    private String dangerNum;

    /** 应急措施 */
    @Excel(name = "应急措施")
    private String dangerAction;

    /** 检查方法 */
    @Excel(name = "检查方法")
    private String checkWay;

    /** 注意事项 */
    @Excel(name = "注意事项")
    private String securityNotes;

    /** 危险品图片 */
    @Excel(name = "危险品图片")
    private String dangerImage;

    public void setDangerId(Long dangerId) 
    {
        this.dangerId = dangerId;
    }

    public Long getDangerId() 
    {
        return dangerId;
    }
    public void setDangerName(String dangerName) 
    {
        this.dangerName = dangerName;
    }

    public String getDangerName() 
    {
        return dangerName;
    }
    public void setDangerType(String dangerType) 
    {
        this.dangerType = dangerType;
    }

    public String getDangerType() 
    {
        return dangerType;
    }
    public void setDangerNum(String dangerNum)
    {
        this.dangerNum = dangerNum;
    }

    public String getDangerNum()
    {
        return dangerNum;
    }
    public void setDangerAction(String dangerAction) 
    {
        this.dangerAction = dangerAction;
    }

    public String getDangerAction() 
    {
        return dangerAction;
    }
    public void setCheckWay(String checkWay) 
    {
        this.checkWay = checkWay;
    }

    public String getCheckWay() 
    {
        return checkWay;
    }
    public void setSecurityNotes(String securityNotes) 
    {
        this.securityNotes = securityNotes;
    }

    public String getSecurityNotes() 
    {
        return securityNotes;
    }
    public void setDangerImage(String dangerImage) 
    {
        this.dangerImage = dangerImage;
    }

    public String getDangerImage() 
    {
        return dangerImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("dangerId", getDangerId())
            .append("dangerName", getDangerName())
            .append("dangerType", getDangerType())
            .append("dangerNum", getDangerNum())
            .append("dangerAction", getDangerAction())
            .append("checkWay", getCheckWay())
            .append("securityNotes", getSecurityNotes())
            .append("dangerImage", getDangerImage())
            .toString();
    }
}
