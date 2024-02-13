package com.ruoyi.containerassembly.mapper;

import java.util.List;
import com.ruoyi.containerassembly.domain.BContainerassembly;

/**
 * 集装箱装配Mapper接口
 * 
 * @author victorzl
 * @date 2024-02-12
 */
public interface BContainerassemblyMapper 
{
    /**
     * 查询集装箱装配
     * 
     * @param assemblyId 集装箱装配主键
     * @return 集装箱装配
     */
    public BContainerassembly selectBContainerassemblyByAssemblyId(Long assemblyId);

    /**
     * 查询集装箱装配列表
     * 
     * @param bContainerassembly 集装箱装配
     * @return 集装箱装配集合
     */
    public List<BContainerassembly> selectBContainerassemblyList(BContainerassembly bContainerassembly);

    /**
     * 新增集装箱装配
     * 
     * @param bContainerassembly 集装箱装配
     * @return 结果
     */
    public int insertBContainerassembly(BContainerassembly bContainerassembly);

    /**
     * 修改集装箱装配
     * 
     * @param bContainerassembly 集装箱装配
     * @return 结果
     */
    public int updateBContainerassembly(BContainerassembly bContainerassembly);

    /**
     * 删除集装箱装配
     * 
     * @param assemblyId 集装箱装配主键
     * @return 结果
     */
    public int deleteBContainerassemblyByAssemblyId(Long assemblyId);

    /**
     * 批量删除集装箱装配
     * 
     * @param assemblyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBContainerassemblyByAssemblyIds(Long[] assemblyIds);
}
