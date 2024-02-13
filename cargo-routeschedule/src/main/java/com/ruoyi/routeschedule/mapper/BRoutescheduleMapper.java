package com.ruoyi.routeschedule.mapper;

import java.util.List;
import com.ruoyi.routeschedule.domain.BRouteschedule;

/**
 * 航运计划Mapper接口
 * 
 * @author victorzl
 * @date 2023-12-16
 */
public interface BRoutescheduleMapper 
{
    /**
     * 查询航运计划
     * 
     * @param routescheduleId 航运计划主键
     * @return 航运计划
     */
    public BRouteschedule selectBRoutescheduleByRoutescheduleId(Long routescheduleId);

    /**
     * 查询航运计划列表
     * 
     * @param bRouteschedule 航运计划
     * @return 航运计划集合
     */
    public List<BRouteschedule> selectBRoutescheduleList(BRouteschedule bRouteschedule);

    public List<BRouteschedule> selectBRoutescheduleListdate(BRouteschedule bRouteschedule);

    /**
     * 新增航运计划
     * 
     * @param bRouteschedule 航运计划
     * @return 结果
     */
    public int insertBRouteschedule(BRouteschedule bRouteschedule);

    /**
     * 修改航运计划
     * 
     * @param bRouteschedule 航运计划
     * @return 结果
     */
    public int updateBRouteschedule(BRouteschedule bRouteschedule);

    /**
     * 删除航运计划
     * 
     * @param routescheduleId 航运计划主键
     * @return 结果
     */
    public int deleteBRoutescheduleByRoutescheduleId(Long routescheduleId);

    /**
     * 批量删除航运计划
     * 
     * @param routescheduleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBRoutescheduleByRoutescheduleIds(Long[] routescheduleIds);
}