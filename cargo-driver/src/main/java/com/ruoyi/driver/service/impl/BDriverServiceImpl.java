package com.ruoyi.driver.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.driver.mapper.BDriverMapper;
import com.ruoyi.driver.domain.BDriver;
import com.ruoyi.driver.service.IBDriverService;

/**
 * driverService业务层处理
 * 
 * @author victorzl
 * @date 2024-04-07
 */
@Service
public class BDriverServiceImpl implements IBDriverService 
{
    @Autowired
    private BDriverMapper bDriverMapper;

    /**
     * 查询driver
     * 
     * @param driverId driver主键
     * @return driver
     */
    @Override
    public BDriver selectBDriverByDriverId(Long driverId)
    {
        return bDriverMapper.selectBDriverByDriverId(driverId);
    }

    /**
     * 查询driver列表
     * 
     * @param bDriver driver
     * @return driver
     */
    @Override
    public List<BDriver> selectBDriverList(BDriver bDriver)
    {
        return bDriverMapper.selectBDriverList(bDriver);
    }

    /**
     * 新增driver
     * 
     * @param bDriver driver
     * @return 结果
     */
    @Override
    public int insertBDriver(BDriver bDriver)
    {
        return bDriverMapper.insertBDriver(bDriver);
    }

    /**
     * 修改driver
     * 
     * @param bDriver driver
     * @return 结果
     */
    @Override
    public int updateBDriver(BDriver bDriver)
    {
        return bDriverMapper.updateBDriver(bDriver);
    }

    /**
     * 批量删除driver
     * 
     * @param driverIds 需要删除的driver主键
     * @return 结果
     */
    @Override
    public int deleteBDriverByDriverIds(Long[] driverIds)
    {
        return bDriverMapper.deleteBDriverByDriverIds(driverIds);
    }

    /**
     * 删除driver信息
     * 
     * @param driverId driver主键
     * @return 结果
     */
    @Override
    public int deleteBDriverByDriverId(Long driverId)
    {
        return bDriverMapper.deleteBDriverByDriverId(driverId);
    }
}
