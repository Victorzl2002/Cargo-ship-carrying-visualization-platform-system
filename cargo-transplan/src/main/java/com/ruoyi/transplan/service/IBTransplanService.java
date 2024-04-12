package com.ruoyi.transplan.service;

import java.util.List;
import com.ruoyi.transplan.domain.BTransplan;

/**
 * transplanService接口
 * 
 * @author victorzl
 * @date 2024-04-07
 */
public interface IBTransplanService 
{
    /**
     * 查询transplan
     * 
     * @param planId transplan主键
     * @return transplan
     */
    public BTransplan selectBTransplanByPlanId(Long planId);

    /**
     * 查询transplan列表
     * 
     * @param bTransplan transplan
     * @return transplan集合
     */
    public List<BTransplan> selectBTransplanList(BTransplan bTransplan);

    /**
     * 新增transplan
     * 
     * @param bTransplan transplan
     * @return 结果
     */
    public int insertBTransplan(BTransplan bTransplan);

    /**
     * 修改transplan
     * 
     * @param bTransplan transplan
     * @return 结果
     */
    public int updateBTransplan(BTransplan bTransplan);

    /**
     * 批量删除transplan
     * 
     * @param planIds 需要删除的transplan主键集合
     * @return 结果
     */
    public int deleteBTransplanByPlanIds(Long[] planIds);

    /**
     * 删除transplan信息
     * 
     * @param planId transplan主键
     * @return 结果
     */
    public int deleteBTransplanByPlanId(Long planId);
}
