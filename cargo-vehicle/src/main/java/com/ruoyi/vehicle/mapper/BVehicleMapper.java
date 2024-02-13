package com.ruoyi.vehicle.mapper;

import java.util.List;
import com.ruoyi.vehicle.domain.BVehicle;

/**
 * 货车管理Mapper接口
 * 
 * @author victorzl
 * @date 2024-01-24
 */
public interface BVehicleMapper 
{
    /**
     * 查询货车管理
     * 
     * @param vehicleId 货车管理主键
     * @return 货车管理
     */
    public BVehicle selectBVehicleByVehicleId(String vehicleId);

    /**
     * 查询货车管理列表
     * 
     * @param bVehicle 货车管理
     * @return 货车管理集合
     */
    public List<BVehicle> selectBVehicleList(BVehicle bVehicle);

    /**
     * 新增货车管理
     * 
     * @param bVehicle 货车管理
     * @return 结果
     */
    public int insertBVehicle(BVehicle bVehicle);

    /**
     * 修改货车管理
     * 
     * @param bVehicle 货车管理
     * @return 结果
     */
    public int updateBVehicle(BVehicle bVehicle);

    /**
     * 删除货车管理
     * 
     * @param vehicleId 货车管理主键
     * @return 结果
     */
    public int deleteBVehicleByVehicleId(String vehicleId);

    /**
     * 批量删除货车管理
     * 
     * @param vehicleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBVehicleByVehicleIds(String[] vehicleIds);
}
