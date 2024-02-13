package com.ruoyi.dangerinfo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dangerinfo.mapper.BDangerMapper;
import com.ruoyi.dangerinfo.domain.BDanger;
import com.ruoyi.dangerinfo.service.IBDangerService;

/**
 * 危险品识别信息管理Service业务层处理
 * 
 * @author victorzl
 * @date 2024-01-07
 */
@Service
public class BDangerServiceImpl implements IBDangerService 
{
    @Autowired
    private BDangerMapper bDangerMapper;

    /**
     * 查询危险品识别信息管理
     * 
     * @param dangerId 危险品识别信息管理主键
     * @return 危险品识别信息管理
     */
    @Override
    public BDanger selectBDangerByDangerId(Long dangerId)
    {
        return bDangerMapper.selectBDangerByDangerId(dangerId);
    }

    /**
     * 查询危险品识别信息管理列表
     * 
     * @param bDanger 危险品识别信息管理
     * @return 危险品识别信息管理
     */
    @Override
    public List<BDanger> selectBDangerList(BDanger bDanger)
    {
        return bDangerMapper.selectBDangerList(bDanger);
    }

    /**
     * 新增危险品识别信息管理
     * 
     * @param bDanger 危险品识别信息管理
     * @return 结果
     */
    @Override
    public int insertBDanger(BDanger bDanger)
    {
        return bDangerMapper.insertBDanger(bDanger);
    }

    /**
     * 修改危险品识别信息管理
     * 
     * @param bDanger 危险品识别信息管理
     * @return 结果
     */
    @Override
    public int updateBDanger(BDanger bDanger)
    {
        return bDangerMapper.updateBDanger(bDanger);
    }

    /**
     * 批量删除危险品识别信息管理
     * 
     * @param dangerIds 需要删除的危险品识别信息管理主键
     * @return 结果
     */
    @Override
    public int deleteBDangerByDangerIds(Long[] dangerIds)
    {
        return bDangerMapper.deleteBDangerByDangerIds(dangerIds);
    }

    /**
     * 删除危险品识别信息管理信息
     * 
     * @param dangerId 危险品识别信息管理主键
     * @return 结果
     */
    @Override
    public int deleteBDangerByDangerId(Long dangerId)
    {
        return bDangerMapper.deleteBDangerByDangerId(dangerId);
    }
}
