package com.ruoyi.route.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * route对象 b_route
 * 
 * @author victorzl
 * @date 2023-12-08
 */
public class BRoute extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 航线id */
    private Long routeId;

    /** 航线名称 */
    @Excel(name = "航线名称")
    private String routeName;

    /** 挂靠港口 */
    @Excel(name = "挂靠港口")
    private String routeContent;

    public void setRouteId(Long routeId) 
    {
        this.routeId = routeId;
    }

    public Long getRouteId() 
    {
        return routeId;
    }
    public void setRouteName(String routeName) 
    {
        this.routeName = routeName;
    }

    public String getRouteName() 
    {
        return routeName;
    }
    public void setRouteContent(String routeContent) 
    {
        this.routeContent = routeContent;
    }

    public String getRouteContent() 
    {
        return routeContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("routeId", getRouteId())
            .append("routeName", getRouteName())
            .append("routeContent", getRouteContent())
            .toString();
    }
}
