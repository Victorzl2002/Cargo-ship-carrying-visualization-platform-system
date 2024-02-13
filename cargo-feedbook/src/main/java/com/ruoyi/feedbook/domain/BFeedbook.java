package com.ruoyi.feedbook.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户反馈对象 b_feedbook
 * 
 * @author victorzl
 * @date 2024-01-09
 */
public class BFeedbook extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户反馈id */
    private Long feedId;

    /** 用户反馈内容 */
    @Excel(name = "用户反馈内容")
    private String feedContent;

    /** 用户反馈时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "用户反馈时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date feedDate;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    public void setFeedId(Long feedId) 
    {
        this.feedId = feedId;
    }

    public Long getFeedId() 
    {
        return feedId;
    }
    public void setFeedContent(String feedContent) 
    {
        this.feedContent = feedContent;
    }

    public String getFeedContent() 
    {
        return feedContent;
    }
    public void setFeedDate(Date feedDate) 
    {
        this.feedDate = feedDate;
    }

    public Date getFeedDate() 
    {
        return feedDate;
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
            .append("feedId", getFeedId())
            .append("feedContent", getFeedContent())
            .append("feedDate", getFeedDate())
            .append("userId", getUserId())
            .toString();
    }
}
