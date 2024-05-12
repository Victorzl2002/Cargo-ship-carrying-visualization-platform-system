package com.ruoyi.envirinfo.service;

import java.util.List;
import com.ruoyi.envirinfo.domain.BMornite;

/**
 * 监测信息反馈Service接口
 * 
 * @author victorzl
 * @date 2024-05-11
 */
public interface IBMorniteService 
{
    /**
     * 查询监测信息反馈
     * 
     * @param morniteId 监测信息反馈主键
     * @return 监测信息反馈
     */
    public BMornite selectBMorniteByMorniteId(Long morniteId);

    /**
     * 查询监测信息反馈列表
     * 
     * @param bMornite 监测信息反馈
     * @return 监测信息反馈集合
     */
    public List<BMornite> selectBMorniteList(BMornite bMornite);

    /**
     * 新增监测信息反馈
     * 
     * @param bMornite 监测信息反馈
     * @return 结果
     */
    public int insertBMornite(BMornite bMornite);

    /**
     * 修改监测信息反馈
     * 
     * @param bMornite 监测信息反馈
     * @return 结果
     */
    public int updateBMornite(BMornite bMornite);

    /**
     * 批量删除监测信息反馈
     * 
     * @param morniteIds 需要删除的监测信息反馈主键集合
     * @return 结果
     */
    public int deleteBMorniteByMorniteIds(Long[] morniteIds);

    /**
     * 删除监测信息反馈信息
     * 
     * @param morniteId 监测信息反馈主键
     * @return 结果
     */
    public int deleteBMorniteByMorniteId(Long morniteId);
}
