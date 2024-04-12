package com.ruoyi.driver.service;

import java.util.List;
import com.ruoyi.driver.domain.BDriver;

/**
 * driverService接口
 * 
 * @author victorzl
 * @date 2024-04-07
 */
public interface IBDriverService 
{
    /**
     * 查询driver
     * 
     * @param driverId driver主键
     * @return driver
     */
    public BDriver selectBDriverByDriverId(Long driverId);

    /**
     * 查询driver列表
     * 
     * @param bDriver driver
     * @return driver集合
     */
    public List<BDriver> selectBDriverList(BDriver bDriver);

    /**
     * 新增driver
     * 
     * @param bDriver driver
     * @return 结果
     */
    public int insertBDriver(BDriver bDriver);

    /**
     * 修改driver
     * 
     * @param bDriver driver
     * @return 结果
     */
    public int updateBDriver(BDriver bDriver);

    /**
     * 批量删除driver
     * 
     * @param driverIds 需要删除的driver主键集合
     * @return 结果
     */
    public int deleteBDriverByDriverIds(Long[] driverIds);

    /**
     * 删除driver信息
     * 
     * @param driverId driver主键
     * @return 结果
     */
    public int deleteBDriverByDriverId(Long driverId);
}
