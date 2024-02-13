package com.ruoyi.allocation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.allocation.mapper.BAllocationMapper;
import com.ruoyi.allocation.domain.BAllocation;
import com.ruoyi.allocation.service.IBAllocationService;

/**
 * 货物分配Service业务层处理
 * 
 * @author victorzl
 * @date 2024-01-19
 */
@Service
public class BAllocationServiceImpl implements IBAllocationService 
{
    @Autowired
    private BAllocationMapper bAllocationMapper;

    /**
     * 查询货物分配
     * 
     * @param allocationId 货物分配主键
     * @return 货物分配
     */
    @Override
    public BAllocation selectBAllocationByAllocationId(Long allocationId)
    {
        return bAllocationMapper.selectBAllocationByAllocationId(allocationId);
    }

    /**
     * 查询货物分配列表
     * 
     * @param bAllocation 货物分配
     * @return 货物分配
     */
    @Override
    public List<BAllocation> selectBAllocationList(BAllocation bAllocation)
    {
        return bAllocationMapper.selectBAllocationList(bAllocation);
    }

    /**
     * 新增货物分配
     * 
     * @param bAllocation 货物分配
     * @return 结果
     */
    @Override
    public int insertBAllocation(BAllocation bAllocation)
    {
        return bAllocationMapper.insertBAllocation(bAllocation);
    }

    /**
     * 修改货物分配
     * 
     * @param bAllocation 货物分配
     * @return 结果
     */
    @Override
    public int updateBAllocation(BAllocation bAllocation)
    {
        return bAllocationMapper.updateBAllocation(bAllocation);
    }

    /**
     * 批量删除货物分配
     * 
     * @param allocationIds 需要删除的货物分配主键
     * @return 结果
     */
    @Override
    public int deleteBAllocationByAllocationIds(Long[] allocationIds)
    {
        return bAllocationMapper.deleteBAllocationByAllocationIds(allocationIds);
    }

    /**
     * 删除货物分配信息
     * 
     * @param allocationId 货物分配主键
     * @return 结果
     */
    @Override
    public int deleteBAllocationByAllocationId(Long allocationId)
    {
        return bAllocationMapper.deleteBAllocationByAllocationId(allocationId);
    }
}
