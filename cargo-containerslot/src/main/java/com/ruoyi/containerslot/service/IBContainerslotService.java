package com.ruoyi.containerslot.service;

import java.util.List;
import com.ruoyi.containerslot.domain.BContainerslot;

/**
 * 箱位管理Service接口
 * 
 * @author victorzl
 * @date 2024-02-12
 */
public interface IBContainerslotService 
{
    /**
     * 查询箱位管理
     * 
     * @param slotId 箱位管理主键
     * @return 箱位管理
     */
    public BContainerslot selectBContainerslotBySlotId(Long slotId);

    /**
     * 查询箱位管理列表
     * 
     * @param bContainerslot 箱位管理
     * @return 箱位管理集合
     */
    public List<BContainerslot> selectBContainerslotList(BContainerslot bContainerslot);

    /**
     * 新增箱位管理
     * 
     * @param bContainerslot 箱位管理
     * @return 结果
     */
    public int insertBContainerslot(BContainerslot bContainerslot);

    /**
     * 修改箱位管理
     * 
     * @param bContainerslot 箱位管理
     * @return 结果
     */
    public int updateBContainerslot(BContainerslot bContainerslot);

    /**
     * 批量删除箱位管理
     * 
     * @param slotIds 需要删除的箱位管理主键集合
     * @return 结果
     */
    public int deleteBContainerslotBySlotIds(Long[] slotIds);

    /**
     * 删除箱位管理信息
     * 
     * @param slotId 箱位管理主键
     * @return 结果
     */
    public int deleteBContainerslotBySlotId(Long slotId);
}