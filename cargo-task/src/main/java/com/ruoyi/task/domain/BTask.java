package com.ruoyi.task.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 货车计划管理对象 b_task
 * 
 * @author victorzl
 * @date 2024-01-24
 */
public class BTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 货车任务id */
    private Long taskId;

    /** 货车编号 */
    @Excel(name = "货车编号")
    private String truckId;

    /** 集装箱id */
    @Excel(name = "集装箱id")
    private Long containerId;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private String taskStatus;

    /** 任务类型 */
    @Excel(name = "任务类型")
    private String taskType;

    /** 任务起点 */
    @Excel(name = "任务起点")
    private String taskStart;

    /** 任务终点 */
    @Excel(name = "任务终点")
    private String taskEnd;

    /** 任务时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskTime;

    /** 任务备注 */
    @Excel(name = "任务备注")
    private String taskNotes;

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setTruckId(String truckId) 
    {
        this.truckId = truckId;
    }

    public String getTruckId() 
    {
        return truckId;
    }
    public void setContainerId(Long containerId) 
    {
        this.containerId = containerId;
    }

    public Long getContainerId() 
    {
        return containerId;
    }
    public void setTaskStatus(String taskStatus) 
    {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus() 
    {
        return taskStatus;
    }
    public void setTaskType(String taskType) 
    {
        this.taskType = taskType;
    }

    public String getTaskType() 
    {
        return taskType;
    }
    public void setTaskStart(String taskStart) 
    {
        this.taskStart = taskStart;
    }

    public String getTaskStart() 
    {
        return taskStart;
    }
    public void setTaskEnd(String taskEnd) 
    {
        this.taskEnd = taskEnd;
    }

    public String getTaskEnd() 
    {
        return taskEnd;
    }
    public void setTaskTime(Date taskTime) 
    {
        this.taskTime = taskTime;
    }

    public Date getTaskTime() 
    {
        return taskTime;
    }
    public void setTaskNotes(String taskNotes) 
    {
        this.taskNotes = taskNotes;
    }

    public String getTaskNotes() 
    {
        return taskNotes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("truckId", getTruckId())
            .append("containerId", getContainerId())
            .append("taskStatus", getTaskStatus())
            .append("taskType", getTaskType())
            .append("taskStart", getTaskStart())
            .append("taskEnd", getTaskEnd())
            .append("taskTime", getTaskTime())
            .append("taskNotes", getTaskNotes())
            .toString();
    }
}
