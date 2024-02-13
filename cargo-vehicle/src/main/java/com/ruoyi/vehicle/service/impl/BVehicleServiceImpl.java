package com.ruoyi.vehicle.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.vehicle.mapper.BVehicleMapper;
import com.ruoyi.vehicle.domain.BVehicle;
import com.ruoyi.vehicle.service.IBVehicleService;

/**
 * 货车管理Service业务层处理
 * 
 * @author victorzl
 * @date 2024-01-24
 */
@Service
public class BVehicleServiceImpl implements IBVehicleService 
{
    @Autowired
    private BVehicleMapper bVehicleMapper;

    /**
     * 查询货车管理
     * 
     * @param vehicleId 货车管理主键
     * @return 货车管理
     */
    @Override
    public BVehicle selectBVehicleByVehicleId(String vehicleId)
    {
        return bVehicleMapper.selectBVehicleByVehicleId(vehicleId);
    }

    /**
     * 查询货车管理列表
     * 
     * @param bVehicle 货车管理
     * @return 货车管理
     */
    @Override
    public List<BVehicle> selectBVehicleList(BVehicle bVehicle)
    {
        return bVehicleMapper.selectBVehicleList(bVehicle);
    }

    /**
     * 新增货车管理
     * 
     * @param bVehicle 货车管理
     * @return 结果
     */
    @Override
    public int insertBVehicle(BVehicle bVehicle)
    {
        return bVehicleMapper.insertBVehicle(bVehicle);
    }

    /**
     * 修改货车管理
     * 
     * @param bVehicle 货车管理
     * @return 结果
     */
    @Override
    public int updateBVehicle(BVehicle bVehicle)
    {
        return bVehicleMapper.updateBVehicle(bVehicle);
    }

    /**
     * 批量删除货车管理
     * 
     * @param vehicleIds 需要删除的货车管理主键
     * @return 结果
     */
    @Override
    public int deleteBVehicleByVehicleIds(String[] vehicleIds)
    {
        return bVehicleMapper.deleteBVehicleByVehicleIds(vehicleIds);
    }

    /**
     * 删除货车管理信息
     * 
     * @param vehicleId 货车管理主键
     * @return 结果
     */
    @Override
    public int deleteBVehicleByVehicleId(String vehicleId)
    {
        return bVehicleMapper.deleteBVehicleByVehicleId(vehicleId);
    }
}
