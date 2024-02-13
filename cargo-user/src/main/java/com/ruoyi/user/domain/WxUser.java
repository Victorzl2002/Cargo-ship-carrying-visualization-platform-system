package com.ruoyi.user.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * wxuser对象 wx_user
 * 
 * @author Victorzl
 * @date 2023-12-08
 */
public class WxUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private String openId;

    /** 用户名
 */
    @Excel(name = "用户名 ")
    private String userName;

    /** 身份 */
    @Excel(name = "身份")
    private String userIdentity;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phoneNumber;

    /** 余额 */
    @Excel(name = "余额")
    private Long walletBalance;

    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserIdentity(String userIdentity) 
    {
        this.userIdentity = userIdentity;
    }

    public String getUserIdentity() 
    {
        return userIdentity;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setWalletBalance(Long walletBalance) 
    {
        this.walletBalance = walletBalance;
    }

    public Long getWalletBalance() 
    {
        return walletBalance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("openId", getOpenId())
            .append("userName", getUserName())
            .append("userIdentity", getUserIdentity())
            .append("phoneNumber", getPhoneNumber())
            .append("walletBalance", getWalletBalance())
            .toString();
    }
}
