package com.ruoyi.cargoinfo.service;

import java.util.List;
import com.ruoyi.cargoinfo.domain.BCargo;
import com.ruoyi.container.domain.BContainer;
import com.ruoyi.order.domain.BOrder;

/**
 * 货物管理Service接口
 * 
 * @author victorzl
 * @date 2023-12-15
 */
public interface IBCargoService 
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
     * 批量删除货物管理
     * 
     * @param cargoIds 需要删除的货物管理主键集合
     * @return 结果
     */
    public int deleteBCargoByCargoIds(Long[] cargoIds);

    /**
     * 删除货物管理信息
     * 
     * @param cargoId 货物管理主键
     * @return 结果
     */
    public int deleteBCargoByCargoId(Long cargoId);

    // 在这里实现货物分配的逻辑
    public  void allocateCargoForShippingPlan(String shippingPlanId, List<BOrder> orders);

    public void allocatecargotype(String shippingPlanId,List<BContainer> bContainerList, List<BCargo> huowu,List<BOrder> orders);
}
