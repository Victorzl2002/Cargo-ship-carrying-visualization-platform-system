package com.ruoyi.order.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;

/**
 * order对象 b_order
 * 
 * @author victorzl
 * @date 2023-12-30
 */
public class BOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    private String orderId;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 货物id */
    @Excel(name = "货物id")
    private Long cargoId;

    /** 航运计划id */
    @Excel(name = "航运计划id")
    private Long scheduleId;

    /** 交易金额 */
    @Excel(name = "交易金额")
    private Long orderMoney;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private String orderStatus;

    /** 交易备注 */
    @Excel(name = "交易备注")
    private String orderNotes;


    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setCargoId(Long cargoId)
    {
        this.cargoId = cargoId;
    }

    public Long getCargoId()
    {
        return cargoId;
    }
    public void setScheduleId(Long scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() 
    {
        return scheduleId;
    }
    public void setOrderMoney(Long orderMoney) 
    {
        this.orderMoney = orderMoney;
    }

    public Long getOrderMoney() 
    {
        return orderMoney;
    }
    public void setOrderStatus(String orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() 
    {
        return orderStatus;
    }
    public void setOrderNotes(String orderNotes) 
    {
        this.orderNotes = orderNotes;
    }

    public String getOrderNotes() 
    {
        return orderNotes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("userId", getUserId())
            .append("cargoId", getCargoId())
            .append("scheduleId", getScheduleId())
            .append("orderMoney", getOrderMoney())
            .append("orderStatus", getOrderStatus())
            .append("orderNotes", getOrderNotes())
            .append("createTime", getCreateTime())
            .toString();
    }
}
