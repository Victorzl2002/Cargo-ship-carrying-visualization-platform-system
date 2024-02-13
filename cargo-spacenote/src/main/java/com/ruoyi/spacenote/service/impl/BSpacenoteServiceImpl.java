package com.ruoyi.spacenote.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.spacenote.mapper.BSpacenoteMapper;
import com.ruoyi.spacenote.domain.BSpacenote;
import com.ruoyi.spacenote.service.IBSpacenoteService;

/**
 * 集装箱空间利用率Service业务层处理
 * 
 * @author victorzl
 * @date 2024-01-19
 */
@Service
public class BSpacenoteServiceImpl implements IBSpacenoteService 
{
    @Autowired
    private BSpacenoteMapper bSpacenoteMapper;

    /**
     * 查询集装箱空间利用率
     * 
     * @param spaceutilizationrateId 集装箱空间利用率主键
     * @return 集装箱空间利用率
     */
    @Override
    public BSpacenote selectBSpacenoteBySpaceutilizationrateId(Long spaceutilizationrateId)
    {
        return bSpacenoteMapper.selectBSpacenoteBySpaceutilizationrateId(spaceutilizationrateId);
    }

    /**
     * 查询集装箱空间利用率列表
     * 
     * @param bSpacenote 集装箱空间利用率
     * @return 集装箱空间利用率
     */
    @Override
    public List<BSpacenote> selectBSpacenoteList(BSpacenote bSpacenote)
    {
        return bSpacenoteMapper.selectBSpacenoteList(bSpacenote);
    }

    /**
     * 新增集装箱空间利用率
     * 
     * @param bSpacenote 集装箱空间利用率
     * @return 结果
     */
    @Override
    public int insertBSpacenote(BSpacenote bSpacenote)
    {
        return bSpacenoteMapper.insertBSpacenote(bSpacenote);
    }

    /**
     * 修改集装箱空间利用率
     * 
     * @param bSpacenote 集装箱空间利用率
     * @return 结果
     */
    @Override
    public int updateBSpacenote(BSpacenote bSpacenote)
    {
        return bSpacenoteMapper.updateBSpacenote(bSpacenote);
    }

    /**
     * 批量删除集装箱空间利用率
     * 
     * @param spaceutilizationrateIds 需要删除的集装箱空间利用率主键
     * @return 结果
     */
    @Override
    public int deleteBSpacenoteBySpaceutilizationrateIds(Long[] spaceutilizationrateIds)
    {
        return bSpacenoteMapper.deleteBSpacenoteBySpaceutilizationrateIds(spaceutilizationrateIds);
    }

    /**
     * 删除集装箱空间利用率信息
     * 
     * @param spaceutilizationrateId 集装箱空间利用率主键
     * @return 结果
     */
    @Override
    public int deleteBSpacenoteBySpaceutilizationrateId(Long spaceutilizationrateId)
    {
        return bSpacenoteMapper.deleteBSpacenoteBySpaceutilizationrateId(spaceutilizationrateId);
    }
}
