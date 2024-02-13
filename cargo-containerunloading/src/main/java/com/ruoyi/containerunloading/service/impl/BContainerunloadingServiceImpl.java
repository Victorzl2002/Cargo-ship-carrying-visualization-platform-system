package com.ruoyi.containerunloading.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.containerunloading.mapper.BContainerunloadingMapper;
import com.ruoyi.containerunloading.domain.BContainerunloading;
import com.ruoyi.containerunloading.service.IBContainerunloadingService;

/**
 * 集装箱卸载管理Service业务层处理
 * 
 * @author victorzl
 * @date 2024-02-13
 */
@Service
public class BContainerunloadingServiceImpl implements IBContainerunloadingService 
{
    @Autowired
    private BContainerunloadingMapper bContainerunloadingMapper;

    /**
     * 查询集装箱卸载管理
     * 
     * @param unloadingId 集装箱卸载管理主键
     * @return 集装箱卸载管理
     */
    @Override
    public BContainerunloading selectBContainerunloadingByUnloadingId(Long unloadingId)
    {
        return bContainerunloadingMapper.selectBContainerunloadingByUnloadingId(unloadingId);
    }

    /**
     * 查询集装箱卸载管理列表
     * 
     * @param bContainerunloading 集装箱卸载管理
     * @return 集装箱卸载管理
     */
    @Override
    public List<BContainerunloading> selectBContainerunloadingList(BContainerunloading bContainerunloading)
    {
        return bContainerunloadingMapper.selectBContainerunloadingList(bContainerunloading);
    }

    /**
     * 新增集装箱卸载管理
     * 
     * @param bContainerunloading 集装箱卸载管理
     * @return 结果
     */
    @Override
    public int insertBContainerunloading(BContainerunloading bContainerunloading)
    {
        return bContainerunloadingMapper.insertBContainerunloading(bContainerunloading);
    }

    /**
     * 修改集装箱卸载管理
     * 
     * @param bContainerunloading 集装箱卸载管理
     * @return 结果
     */
    @Override
    public int updateBContainerunloading(BContainerunloading bContainerunloading)
    {
        return bContainerunloadingMapper.updateBContainerunloading(bContainerunloading);
    }

    /**
     * 批量删除集装箱卸载管理
     * 
     * @param unloadingIds 需要删除的集装箱卸载管理主键
     * @return 结果
     */
    @Override
    public int deleteBContainerunloadingByUnloadingIds(Long[] unloadingIds)
    {
        return bContainerunloadingMapper.deleteBContainerunloadingByUnloadingIds(unloadingIds);
    }

    /**
     * 删除集装箱卸载管理信息
     * 
     * @param unloadingId 集装箱卸载管理主键
     * @return 结果
     */
    @Override
    public int deleteBContainerunloadingByUnloadingId(Long unloadingId)
    {
        return bContainerunloadingMapper.deleteBContainerunloadingByUnloadingId(unloadingId);
    }
}
