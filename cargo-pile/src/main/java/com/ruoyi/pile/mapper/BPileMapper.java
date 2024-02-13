package com.ruoyi.pile.mapper;

import java.util.List;
import com.ruoyi.pile.domain.BPile;

/**
 * 货堆管理Mapper接口
 * 
 * @author victorzl
 * @date 2024-01-24
 */
public interface BPileMapper 
{
    /**
     * 查询货堆管理
     * 
     * @param pileId 货堆管理主键
     * @return 货堆管理
     */
    public BPile selectBPileByPileId(Long pileId);

    /**
     * 查询货堆管理列表
     * 
     * @param bPile 货堆管理
     * @return 货堆管理集合
     */
    public List<BPile> selectBPileList(BPile bPile);

    /**
     * 新增货堆管理
     * 
     * @param bPile 货堆管理
     * @return 结果
     */
    public int insertBPile(BPile bPile);

    /**
     * 修改货堆管理
     * 
     * @param bPile 货堆管理
     * @return 结果
     */
    public int updateBPile(BPile bPile);

    /**
     * 删除货堆管理
     * 
     * @param pileId 货堆管理主键
     * @return 结果
     */
    public int deleteBPileByPileId(Long pileId);

    /**
     * 批量删除货堆管理
     * 
     * @param pileIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBPileByPileIds(Long[] pileIds);
}
