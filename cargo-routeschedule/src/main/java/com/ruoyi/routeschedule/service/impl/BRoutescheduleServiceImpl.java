package com.ruoyi.routeschedule.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.routeschedule.mapper.BRoutescheduleMapper;
import com.ruoyi.routeschedule.domain.BRouteschedule;
import com.ruoyi.routeschedule.service.IBRoutescheduleService;

/**
 * 航运计划Service业务层处理
 * 
 * @author victorzl
 * @date 2023-12-16
 */
@Service
public class BRoutescheduleServiceImpl implements IBRoutescheduleService 
{
    @Autowired
    private BRoutescheduleMapper bRoutescheduleMapper;

    /**
     * 查询航运计划
     * 
     * @param routescheduleId 航运计划主键
     * @return 航运计划
     */
    @Override
    public BRouteschedule selectBRoutescheduleByRoutescheduleId(Long routescheduleId)
    {
        return bRoutescheduleMapper.selectBRoutescheduleByRoutescheduleId(routescheduleId);
    }

    /**
     * 查询航运计划列表
     * 
     * @param bRouteschedule 航运计划
     * @return 航运计划
     */
    @Override
    public List<BRouteschedule> selectBRoutescheduleList(BRouteschedule bRouteschedule)
    {
        return bRoutescheduleMapper.selectBRoutescheduleList(bRouteschedule);
    }

    @Override
    public List<BRouteschedule> selectBRoutescheduleListdate(BRouteschedule bRouteschedule) {
        return  bRoutescheduleMapper.selectBRoutescheduleListdate(bRouteschedule);
    }

    /**
     * 新增航运计划
     * 
     * @param bRouteschedule 航运计划
     * @return 结果
     */
    @Override
    public int insertBRouteschedule(BRouteschedule bRouteschedule)
    {
        return bRoutescheduleMapper.insertBRouteschedule(bRouteschedule);
    }

    /**
     * 修改航运计划
     * 
     * @param bRouteschedule 航运计划
     * @return 结果
     */
    @Override
    public int updateBRouteschedule(BRouteschedule bRouteschedule)
    {
        return bRoutescheduleMapper.updateBRouteschedule(bRouteschedule);
    }

    /**
     * 批量删除航运计划
     * 
     * @param routescheduleIds 需要删除的航运计划主键
     * @return 结果
     */
    @Override
    public int deleteBRoutescheduleByRoutescheduleIds(Long[] routescheduleIds)
    {
        return bRoutescheduleMapper.deleteBRoutescheduleByRoutescheduleIds(routescheduleIds);
    }

    /**
     * 删除航运计划信息
     * 
     * @param routescheduleId 航运计划主键
     * @return 结果
     */
    @Override
    public int deleteBRoutescheduleByRoutescheduleId(Long routescheduleId)
    {
        return bRoutescheduleMapper.deleteBRoutescheduleByRoutescheduleId(routescheduleId);
    }
}
