package com.ruoyi.containerunloading.service;

import java.util.List;
import com.ruoyi.containerunloading.domain.BContainerunloading;

/**
 * 集装箱卸载管理Service接口
 * 
 * @author victorzl
 * @date 2024-02-13
 */
public interface IBContainerunloadingService 
{
    /**
     * 查询集装箱卸载管理
     * 
     * @param unloadingId 集装箱卸载管理主键
     * @return 集装箱卸载管理
     */
    public BContainerunloading selectBContainerunloadingByUnloadingId(Long unloadingId);

    /**
     * 查询集装箱卸载管理列表
     * 
     * @param bContainerunloading 集装箱卸载管理
     * @return 集装箱卸载管理集合
     */
    public List<BContainerunloading> selectBContainerunloadingList(BContainerunloading bContainerunloading);

    /**
     * 新增集装箱卸载管理
     * 
     * @param bContainerunloading 集装箱卸载管理
     * @return 结果
     */
    public int insertBContainerunloading(BContainerunloading bContainerunloading);

    /**
     * 修改集装箱卸载管理
     * 
     * @param bContainerunloading 集装箱卸载管理
     * @return 结果
     */
    public int updateBContainerunloading(BContainerunloading bContainerunloading);

    /**
     * 批量删除集装箱卸载管理
     * 
     * @param unloadingIds 需要删除的集装箱卸载管理主键集合
     * @return 结果
     */
    public int deleteBContainerunloadingByUnloadingIds(Long[] unloadingIds);

    /**
     * 删除集装箱卸载管理信息
     * 
     * @param unloadingId 集装箱卸载管理主键
     * @return 结果
     */
    public int deleteBContainerunloadingByUnloadingId(Long unloadingId);
}
