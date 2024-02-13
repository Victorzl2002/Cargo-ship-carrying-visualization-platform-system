package com.ruoyi.allocation.service;

import java.util.List;
import com.ruoyi.allocation.domain.BAllocation;

/**
 * 货物分配Service接口
 * 
 * @author victorzl
 * @date 2024-01-19
 */
public interface IBAllocationService 
{
    /**
     * 查询货物分配
     * 
     * @param allocationId 货物分配主键
     * @return 货物分配
     */
    public BAllocation selectBAllocationByAllocationId(Long allocationId);

    /**
     * 查询货物分配列表
     * 
     * @param bAllocation 货物分配
     * @return 货物分配集合
     */
    public List<BAllocation> selectBAllocationList(BAllocation bAllocation);

    /**
     * 新增货物分配
     * 
     * @param bAllocation 货物分配
     * @return 结果
     */
    public int insertBAllocation(BAllocation bAllocation);

    /**
     * 修改货物分配
     * 
     * @param bAllocation 货物分配
     * @return 结果
     */
    public int updateBAllocation(BAllocation bAllocation);

    /**
     * 批量删除货物分配
     * 
     * @param allocationIds 需要删除的货物分配主键集合
     * @return 结果
     */
    public int deleteBAllocationByAllocationIds(Long[] allocationIds);

    /**
     * 删除货物分配信息
     * 
     * @param allocationId 货物分配主键
     * @return 结果
     */
    public int deleteBAllocationByAllocationId(Long allocationId);
}
