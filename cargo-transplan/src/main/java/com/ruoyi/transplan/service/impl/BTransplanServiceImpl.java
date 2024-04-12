package com.ruoyi.transplan.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.transplan.mapper.BTransplanMapper;
import com.ruoyi.transplan.domain.BTransplan;
import com.ruoyi.transplan.service.IBTransplanService;

/**
 * transplanService业务层处理
 * 
 * @author victorzl
 * @date 2024-04-07
 */
@Service
public class BTransplanServiceImpl implements IBTransplanService 
{
    @Autowired
    private BTransplanMapper bTransplanMapper;

    /**
     * 查询transplan
     * 
     * @param planId transplan主键
     * @return transplan
     */
    @Override
    public BTransplan selectBTransplanByPlanId(Long planId)
    {
        return bTransplanMapper.selectBTransplanByPlanId(planId);
    }

    /**
     * 查询transplan列表
     * 
     * @param bTransplan transplan
     * @return transplan
     */
    @Override
    public List<BTransplan> selectBTransplanList(BTransplan bTransplan)
    {
        return bTransplanMapper.selectBTransplanList(bTransplan);
    }

    /**
     * 新增transplan
     * 
     * @param bTransplan transplan
     * @return 结果
     */
    @Override
    public int insertBTransplan(BTransplan bTransplan)
    {
        return bTransplanMapper.insertBTransplan(bTransplan);
    }

    /**
     * 修改transplan
     * 
     * @param bTransplan transplan
     * @return 结果
     */
    @Override
    public int updateBTransplan(BTransplan bTransplan)
    {
        return bTransplanMapper.updateBTransplan(bTransplan);
    }

    /**
     * 批量删除transplan
     * 
     * @param planIds 需要删除的transplan主键
     * @return 结果
     */
    @Override
    public int deleteBTransplanByPlanIds(Long[] planIds)
    {
        return bTransplanMapper.deleteBTransplanByPlanIds(planIds);
    }

    /**
     * 删除transplan信息
     * 
     * @param planId transplan主键
     * @return 结果
     */
    @Override
    public int deleteBTransplanByPlanId(Long planId)
    {
        return bTransplanMapper.deleteBTransplanByPlanId(planId);
    }
}
