package com.ruoyi.envirinfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.envirinfo.mapper.BMorniteMapper;
import com.ruoyi.envirinfo.domain.BMornite;
import com.ruoyi.envirinfo.service.IBMorniteService;

/**
 * 监测信息反馈Service业务层处理
 * 
 * @author victorzl
 * @date 2024-05-11
 */
@Service
public class BMorniteServiceImpl implements IBMorniteService 
{
    @Autowired
    private BMorniteMapper bMorniteMapper;

    /**
     * 查询监测信息反馈
     * 
     * @param morniteId 监测信息反馈主键
     * @return 监测信息反馈
     */
    @Override
    public BMornite selectBMorniteByMorniteId(Long morniteId)
    {
        return bMorniteMapper.selectBMorniteByMorniteId(morniteId);
    }

    /**
     * 查询监测信息反馈列表
     * 
     * @param bMornite 监测信息反馈
     * @return 监测信息反馈
     */
    @Override
    public List<BMornite> selectBMorniteList(BMornite bMornite)
    {
        return bMorniteMapper.selectBMorniteList(bMornite);
    }

    /**
     * 新增监测信息反馈
     * 
     * @param bMornite 监测信息反馈
     * @return 结果
     */
    @Override
    public int insertBMornite(BMornite bMornite)
    {
        return bMorniteMapper.insertBMornite(bMornite);
    }

    /**
     * 修改监测信息反馈
     * 
     * @param bMornite 监测信息反馈
     * @return 结果
     */
    @Override
    public int updateBMornite(BMornite bMornite)
    {
        return bMorniteMapper.updateBMornite(bMornite);
    }

    /**
     * 批量删除监测信息反馈
     * 
     * @param morniteIds 需要删除的监测信息反馈主键
     * @return 结果
     */
    @Override
    public int deleteBMorniteByMorniteIds(Long[] morniteIds)
    {
        return bMorniteMapper.deleteBMorniteByMorniteIds(morniteIds);
    }

    /**
     * 删除监测信息反馈信息
     * 
     * @param morniteId 监测信息反馈主键
     * @return 结果
     */
    @Override
    public int deleteBMorniteByMorniteId(Long morniteId)
    {
        return bMorniteMapper.deleteBMorniteByMorniteId(morniteId);
    }
}
