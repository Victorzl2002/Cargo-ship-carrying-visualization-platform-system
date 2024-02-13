package com.ruoyi.cargoinfo.mapper;

import java.util.List;
import com.ruoyi.cargoinfo.domain.BCargo;

/**
 * 货物管理Mapper接口
 * 
 * @author victorzl
 * @date 2023-12-15
 */
public interface BCargoMapper 
{
    /**
     * 查询货物管理
     * 
     * @param cargoId 货物管理主键
     * @return 货物管理
     */
    public BCargo selectBCargoByCargoId(Long cargoId);

    /**
     * 查询货物管理列表
     * 
     * @param bCargo 货物管理
     * @return 货物管理集合
     */
    public List<BCargo> selectBCargoList(BCargo bCargo);

    /**
     * 新增货物管理
     * 
     * @param bCargo 货物管理
     * @return 结果
     */
    public int insertBCargo(BCargo bCargo);

    /**
     * 修改货物管理
     * 
     * @param bCargo 货物管理
     * @return 结果
     */
    public int updateBCargo(BCargo bCargo);

    /**
     * 删除货物管理
     * 
     * @param cargoId 货物管理主键
     * @return 结果
     */
    public int deleteBCargoByCargoId(Long cargoId);

    /**
     * 批量删除货物管理
     * 
     * @param cargoIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBCargoByCargoIds(Long[] cargoIds);
}
