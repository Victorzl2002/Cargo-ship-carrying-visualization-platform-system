package com.ruoyi.task.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.task.mapper.BTaskMapper;
import com.ruoyi.task.domain.BTask;
import com.ruoyi.task.service.IBTaskService;

/**
 * 货车计划管理Service业务层处理
 * 
 * @author victorzl
 * @date 2024-01-24
 */
@Service
public class BTaskServiceImpl implements IBTaskService 
{
    @Autowired
    private BTaskMapper bTaskMapper;

    /**
     * 查询货车计划管理
     * 
     * @param taskId 货车计划管理主键
     * @return 货车计划管理
     */
    @Override
    public BTask selectBTaskByTaskId(Long taskId)
    {
        return bTaskMapper.selectBTaskByTaskId(taskId);
    }

    /**
     * 查询货车计划管理列表
     * 
     * @param bTask 货车计划管理
     * @return 货车计划管理
     */
    @Override
    public List<BTask> selectBTaskList(BTask bTask)
    {
        return bTaskMapper.selectBTaskList(bTask);
    }

    /**
     * 新增货车计划管理
     * 
     * @param bTask 货车计划管理
     * @return 结果
     */
    @Override
    public int insertBTask(BTask bTask)
    {
        return bTaskMapper.insertBTask(bTask);
    }

    /**
     * 修改货车计划管理
     * 
     * @param bTask 货车计划管理
     * @return 结果
     */
    @Override
    public int updateBTask(BTask bTask)
    {
        return bTaskMapper.updateBTask(bTask);
    }

    /**
     * 批量删除货车计划管理
     * 
     * @param taskIds 需要删除的货车计划管理主键
     * @return 结果
     */
    @Override
    public int deleteBTaskByTaskIds(Long[] taskIds)
    {
        return bTaskMapper.deleteBTaskByTaskIds(taskIds);
    }

    /**
     * 删除货车计划管理信息
     * 
     * @param taskId 货车计划管理主键
     * @return 结果
     */
    @Override
    public int deleteBTaskByTaskId(Long taskId)
    {
        return bTaskMapper.deleteBTaskByTaskId(taskId);
    }
}
