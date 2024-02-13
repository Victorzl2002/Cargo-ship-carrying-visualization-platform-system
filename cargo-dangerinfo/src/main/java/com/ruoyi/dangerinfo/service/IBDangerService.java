package com.ruoyi.dangerinfo.service;

import java.util.List;
import com.ruoyi.dangerinfo.domain.BDanger;

/**
 * 危险品识别信息管理Service接口
 * 
 * @author victorzl
 * @date 2024-01-07
 */
public interface IBDangerService 
{
    /**
     * 查询危险品识别信息管理
     * 
     * @param dangerId 危险品识别信息管理主键
     * @return 危险品识别信息管理
     */
    public BDanger selectBDangerByDangerId(Long dangerId);

    /**
     * 查询危险品识别信息管理列表
     * 
     * @param bDanger 危险品识别信息管理
     * @return 危险品识别信息管理集合
     */
    public List<BDanger> selectBDangerList(BDanger bDanger);

    /**
     * 新增危险品识别信息管理
     * 
     * @param bDanger 危险品识别信息管理
     * @return 结果
     */
    public int insertBDanger(BDanger bDanger);

    /**
     * 修改危险品识别信息管理
     * 
     * @param bDanger 危险品识别信息管理
     * @return 结果
     */
    public int updateBDanger(BDanger bDanger);

    /**
     * 批量删除危险品识别信息管理
     * 
     * @param dangerIds 需要删除的危险品识别信息管理主键集合
     * @return 结果
     */
    public int deleteBDangerByDangerIds(Long[] dangerIds);

    /**
     * 删除危险品识别信息管理信息
     * 
     * @param dangerId 危险品识别信息管理主键
     * @return 结果
     */
    public int deleteBDangerByDangerId(Long dangerId);
}
