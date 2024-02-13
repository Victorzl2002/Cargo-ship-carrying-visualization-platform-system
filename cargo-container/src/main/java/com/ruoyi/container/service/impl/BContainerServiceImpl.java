package com.ruoyi.container.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.container.mapper.BContainerMapper;
import com.ruoyi.container.domain.BContainer;
import com.ruoyi.container.service.IBContainerService;

/**
 * 集装箱管理Service业务层处理
 * 
 * @author victorzl
 * @date 2024-01-18
 */
@Service
public class BContainerServiceImpl implements IBContainerService 
{
    @Autowired
    private BContainerMapper bContainerMapper;

    /**
     * 查询集装箱管理
     * 
     * @param containerId 集装箱管理主键
     * @return 集装箱管理
     */
    @Override
    public BContainer selectBContainerByContainerId(Long containerId)
    {
        return bContainerMapper.selectBContainerByContainerId(containerId);
    }

    /**
     * 查询集装箱管理列表
     * 
     * @param bContainer 集装箱管理
     * @return 集装箱管理
     */
    @Override
    public List<BContainer> selectBContainerList(BContainer bContainer)
    {
        return bContainerMapper.selectBContainerList(bContainer);
    }

    /**
     * 新增集装箱管理
     * 
     * @param bContainer 集装箱管理
     * @return 结果
     */
    @Override
    public int insertBContainer(BContainer bContainer)
    {
        return bContainerMapper.insertBContainer(bContainer);
    }

    /**
     * 修改集装箱管理
     * 
     * @param bContainer 集装箱管理
     * @return 结果
     */
    @Override
    public int updateBContainer(BContainer bContainer)
    {
        return bContainerMapper.updateBContainer(bContainer);
    }

    /**
     * 批量删除集装箱管理
     * 
     * @param containerIds 需要删除的集装箱管理主键
     * @return 结果
     */
    @Override
    public int deleteBContainerByContainerIds(Long[] containerIds)
    {
        return bContainerMapper.deleteBContainerByContainerIds(containerIds);
    }

    /**
     * 删除集装箱管理信息
     * 
     * @param containerId 集装箱管理主键
     * @return 结果
     */
    @Override
    public int deleteBContainerByContainerId(Long containerId)
    {
        return bContainerMapper.deleteBContainerByContainerId(containerId);
    }
}
