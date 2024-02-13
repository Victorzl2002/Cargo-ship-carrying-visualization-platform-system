package com.ruoyi.task.mapper;

import java.util.List;
import com.ruoyi.task.domain.BTask;

/**
 * 货车计划管理Mapper接口
 * 
 * @author victorzl
 * @date 2024-01-24
 */
public interface BTaskMapper 
{
    /**
     * 查询货车计划管理
     * 
     * @param taskId 货车计划管理主键
     * @return 货车计划管理
     */
    public BTask selectBTaskByTaskId(Long taskId);

    /**
     * 查询货车计划管理列表
     * 
     * @param bTask 货车计划管理
     * @return 货车计划管理集合
     */
    public List<BTask> selectBTaskList(BTask bTask);

    /**
     * 新增货车计划管理
     * 
     * @param bTask 货车计划管理
     * @return 结果
     */
    public int insertBTask(BTask bTask);

    /**
     * 修改货车计划管理
     * 
     * @param bTask 货车计划管理
     * @return 结果
     */
    public int updateBTask(BTask bTask);

    /**
     * 删除货车计划管理
     * 
     * @param taskId 货车计划管理主键
     * @return 结果
     */
    public int deleteBTaskByTaskId(Long taskId);

    /**
     * 批量删除货车计划管理
     * 
     * @param taskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBTaskByTaskIds(Long[] taskIds);
}
