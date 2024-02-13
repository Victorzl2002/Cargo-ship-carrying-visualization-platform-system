package com.ruoyi.container.service;

import java.util.List;
import com.ruoyi.container.domain.BContainer;

/**
 * 集装箱管理Service接口
 * 
 * @author victorzl
 * @date 2024-01-18
 */
public interface IBContainerService 
{
    /**
     * 查询集装箱管理
     * 
     * @param containerId 集装箱管理主键
     * @return 集装箱管理
     */
    public BContainer selectBContainerByContainerId(Long containerId);

    /**
     * 查询集装箱管理列表
     * 
     * @param bContainer 集装箱管理
     * @return 集装箱管理集合
     */
    public List<BContainer> selectBContainerList(BContainer bContainer);

    /**
     * 新增集装箱管理
     * 
     * @param bContainer 集装箱管理
     * @return 结果
     */
    public int insertBContainer(BContainer bContainer);

    /**
     * 修改集装箱管理
     * 
     * @param bContainer 集装箱管理
     * @return 结果
     */
    public int updateBContainer(BContainer bContainer);

    /**
     * 批量删除集装箱管理
     * 
     * @param containerIds 需要删除的集装箱管理主键集合
     * @return 结果
     */
    public int deleteBContainerByContainerIds(Long[] containerIds);

    /**
     * 删除集装箱管理信息
     * 
     * @param containerId 集装箱管理主键
     * @return 结果
     */
    public int deleteBContainerByContainerId(Long containerId);
}