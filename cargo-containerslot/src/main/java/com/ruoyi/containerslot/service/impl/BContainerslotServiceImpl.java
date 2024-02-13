package com.ruoyi.containerslot.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.containerslot.mapper.BContainerslotMapper;
import com.ruoyi.containerslot.domain.BContainerslot;
import com.ruoyi.containerslot.service.IBContainerslotService;

/**
 * 箱位管理Service业务层处理
 * 
 * @author victorzl
 * @date 2024-02-12
 */
@Service
public class BContainerslotServiceImpl implements IBContainerslotService 
{
    @Autowired
    private BContainerslotMapper bContainerslotMapper;

    /**
     * 查询箱位管理
     * 
     * @param slotId 箱位管理主键
     * @return 箱位管理
     */
    @Override
    public BContainerslot selectBContainerslotBySlotId(Long slotId)
    {
        return bContainerslotMapper.selectBContainerslotBySlotId(slotId);
    }

    /**
     * 查询箱位管理列表
     * 
     * @param bContainerslot 箱位管理
     * @return 箱位管理
     */
    @Override
    public List<BContainerslot> selectBContainerslotList(BContainerslot bContainerslot)
    {
        return bContainerslotMapper.selectBContainerslotList(bContainerslot);
    }

    /**
     * 新增箱位管理
     * 
     * @param bContainerslot 箱位管理
     * @return 结果
     */
    @Override
    public int insertBContainerslot(BContainerslot bContainerslot)
    {
        return bContainerslotMapper.insertBContainerslot(bContainerslot);
    }

    /**
     * 修改箱位管理
     * 
     * @param bContainerslot 箱位管理
     * @return 结果
     */
    @Override
    public int updateBContainerslot(BContainerslot bContainerslot)
    {
        return bContainerslotMapper.updateBContainerslot(bContainerslot);
    }

    /**
     * 批量删除箱位管理
     * 
     * @param slotIds 需要删除的箱位管理主键
     * @return 结果
     */
    @Override
    public int deleteBContainerslotBySlotIds(Long[] slotIds)
    {
        return bContainerslotMapper.deleteBContainerslotBySlotIds(slotIds);
    }

    /**
     * 删除箱位管理信息
     * 
     * @param slotId 箱位管理主键
     * @return 结果
     */
    @Override
    public int deleteBContainerslotBySlotId(Long slotId)
    {
        return bContainerslotMapper.deleteBContainerslotBySlotId(slotId);
    }
}
