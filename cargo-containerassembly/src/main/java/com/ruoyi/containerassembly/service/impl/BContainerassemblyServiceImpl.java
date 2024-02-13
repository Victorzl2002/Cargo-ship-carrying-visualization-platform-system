package com.ruoyi.containerassembly.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.containerassembly.mapper.BContainerassemblyMapper;
import com.ruoyi.containerassembly.domain.BContainerassembly;
import com.ruoyi.containerassembly.service.IBContainerassemblyService;

/**
 * 集装箱装配Service业务层处理
 * 
 * @author victorzl
 * @date 2024-02-12
 */
@Service
public class BContainerassemblyServiceImpl implements IBContainerassemblyService 
{
    @Autowired
    private BContainerassemblyMapper bContainerassemblyMapper;

    /**
     * 查询集装箱装配
     * 
     * @param assemblyId 集装箱装配主键
     * @return 集装箱装配
     */
    @Override
    public BContainerassembly selectBContainerassemblyByAssemblyId(Long assemblyId)
    {
        return bContainerassemblyMapper.selectBContainerassemblyByAssemblyId(assemblyId);
    }

    /**
     * 查询集装箱装配列表
     * 
     * @param bContainerassembly 集装箱装配
     * @return 集装箱装配
     */
    @Override
    public List<BContainerassembly> selectBContainerassemblyList(BContainerassembly bContainerassembly)
    {
        return bContainerassemblyMapper.selectBContainerassemblyList(bContainerassembly);
    }

    /**
     * 新增集装箱装配
     * 
     * @param bContainerassembly 集装箱装配
     * @return 结果
     */
    @Override
    public int insertBContainerassembly(BContainerassembly bContainerassembly)
    {
        return bContainerassemblyMapper.insertBContainerassembly(bContainerassembly);
    }

    /**
     * 修改集装箱装配
     * 
     * @param bContainerassembly 集装箱装配
     * @return 结果
     */
    @Override
    public int updateBContainerassembly(BContainerassembly bContainerassembly)
    {
        return bContainerassemblyMapper.updateBContainerassembly(bContainerassembly);
    }

    /**
     * 批量删除集装箱装配
     * 
     * @param assemblyIds 需要删除的集装箱装配主键
     * @return 结果
     */
    @Override
    public int deleteBContainerassemblyByAssemblyIds(Long[] assemblyIds)
    {
        return bContainerassemblyMapper.deleteBContainerassemblyByAssemblyIds(assemblyIds);
    }

    /**
     * 删除集装箱装配信息
     * 
     * @param assemblyId 集装箱装配主键
     * @return 结果
     */
    @Override
    public int deleteBContainerassemblyByAssemblyId(Long assemblyId)
    {
        return bContainerassemblyMapper.deleteBContainerassemblyByAssemblyId(assemblyId);
    }
}
