package com.ruoyi.pile.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.pile.mapper.BPileMapper;
import com.ruoyi.pile.domain.BPile;
import com.ruoyi.pile.service.IBPileService;

/**
 * 货堆管理Service业务层处理
 * 
 * @author victorzl
 * @date 2024-01-24
 */
@Service
public class BPileServiceImpl implements IBPileService 
{
    @Autowired
    private BPileMapper bPileMapper;

    /**
     * 查询货堆管理
     * 
     * @param pileId 货堆管理主键
     * @return 货堆管理
     */
    @Override
    public BPile selectBPileByPileId(Long pileId)
    {
        return bPileMapper.selectBPileByPileId(pileId);
    }

    /**
     * 查询货堆管理列表
     * 
     * @param bPile 货堆管理
     * @return 货堆管理
     */
    @Override
    public List<BPile> selectBPileList(BPile bPile)
    {
        return bPileMapper.selectBPileList(bPile);
    }

    /**
     * 新增货堆管理
     * 
     * @param bPile 货堆管理
     * @return 结果
     */
    @Override
    public int insertBPile(BPile bPile)
    {
        return bPileMapper.insertBPile(bPile);
    }

    /**
     * 修改货堆管理
     * 
     * @param bPile 货堆管理
     * @return 结果
     */
    @Override
    public int updateBPile(BPile bPile)
    {
        return bPileMapper.updateBPile(bPile);
    }

    /**
     * 批量删除货堆管理
     * 
     * @param pileIds 需要删除的货堆管理主键
     * @return 结果
     */
    @Override
    public int deleteBPileByPileIds(Long[] pileIds)
    {
        return bPileMapper.deleteBPileByPileIds(pileIds);
    }

    /**
     * 删除货堆管理信息
     * 
     * @param pileId 货堆管理主键
     * @return 结果
     */
    @Override
    public int deleteBPileByPileId(Long pileId)
    {
        return bPileMapper.deleteBPileByPileId(pileId);
    }
}
