package com.ruoyi.spacenote.service;

import java.util.List;
import com.ruoyi.spacenote.domain.BSpacenote;

/**
 * 集装箱空间利用率Service接口
 * 
 * @author victorzl
 * @date 2024-01-19
 */
public interface IBSpacenoteService 
{
    /**
     * 查询集装箱空间利用率
     * 
     * @param spaceutilizationrateId 集装箱空间利用率主键
     * @return 集装箱空间利用率
     */
    public BSpacenote selectBSpacenoteBySpaceutilizationrateId(Long spaceutilizationrateId);

    /**
     * 查询集装箱空间利用率列表
     * 
     * @param bSpacenote 集装箱空间利用率
     * @return 集装箱空间利用率集合
     */
    public List<BSpacenote> selectBSpacenoteList(BSpacenote bSpacenote);

    /**
     * 新增集装箱空间利用率
     * 
     * @param bSpacenote 集装箱空间利用率
     * @return 结果
     */
    public int insertBSpacenote(BSpacenote bSpacenote);

    /**
     * 修改集装箱空间利用率
     * 
     * @param bSpacenote 集装箱空间利用率
     * @return 结果
     */
    public int updateBSpacenote(BSpacenote bSpacenote);

    /**
     * 批量删除集装箱空间利用率
     * 
     * @param spaceutilizationrateIds 需要删除的集装箱空间利用率主键集合
     * @return 结果
     */
    public int deleteBSpacenoteBySpaceutilizationrateIds(Long[] spaceutilizationrateIds);

    /**
     * 删除集装箱空间利用率信息
     * 
     * @param spaceutilizationrateId 集装箱空间利用率主键
     * @return 结果
     */
    public int deleteBSpacenoteBySpaceutilizationrateId(Long spaceutilizationrateId);
}
