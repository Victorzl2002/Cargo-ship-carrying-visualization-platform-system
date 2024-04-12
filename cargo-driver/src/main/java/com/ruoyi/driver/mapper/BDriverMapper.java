package com.ruoyi.driver.mapper;

import java.util.List;
import com.ruoyi.driver.domain.BDriver;

/**
 * driverMapper接口
 * 
 * @author victorzl
 * @date 2024-04-07
 */
public interface BDriverMapper 
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
     * 删除driver
     * 
     * @param driverId driver主键
     * @return 结果
     */
    public int deleteBDriverByDriverId(Long driverId);

    /**
     * 批量删除driver
     * 
     * @param driverIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBDriverByDriverIds(Long[] driverIds);
}
