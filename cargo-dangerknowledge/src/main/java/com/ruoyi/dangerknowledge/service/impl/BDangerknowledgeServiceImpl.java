package com.ruoyi.dangerknowledge.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dangerknowledge.mapper.BDangerknowledgeMapper;
import com.ruoyi.dangerknowledge.domain.BDangerknowledge;
import com.ruoyi.dangerknowledge.service.IBDangerknowledgeService;

/**
 * 危险品知识库Service业务层处理
 * 
 * @author victorzl
 * @date 2024-01-22
 */
@Service
public class BDangerknowledgeServiceImpl implements IBDangerknowledgeService 
{
    @Autowired
    private BDangerknowledgeMapper bDangerknowledgeMapper;

    /**
     * 查询危险品知识库
     * 
     * @param dangerId 危险品知识库主键
     * @return 危险品知识库
     */
    @Override
    public BDangerknowledge selectBDangerknowledgeByDangerId(Long dangerId)
    {
        return bDangerknowledgeMapper.selectBDangerknowledgeByDangerId(dangerId);
    }

    /**
     * 查询危险品知识库列表
     * 
     * @param bDangerknowledge 危险品知识库
     * @return 危险品知识库
     */
    @Override
    public List<BDangerknowledge> selectBDangerknowledgeList(BDangerknowledge bDangerknowledge)
    {
        return bDangerknowledgeMapper.selectBDangerknowledgeList(bDangerknowledge);
    }

    /**
     * 新增危险品知识库
     * 
     * @param bDangerknowledge 危险品知识库
     * @return 结果
     */
    @Override
    public int insertBDangerknowledge(BDangerknowledge bDangerknowledge)
    {
        return bDangerknowledgeMapper.insertBDangerknowledge(bDangerknowledge);
    }

    /**
     * 修改危险品知识库
     * 
     * @param bDangerknowledge 危险品知识库
     * @return 结果
     */
    @Override
    public int updateBDangerknowledge(BDangerknowledge bDangerknowledge)
    {
        return bDangerknowledgeMapper.updateBDangerknowledge(bDangerknowledge);
    }

    /**
     * 批量删除危险品知识库
     * 
     * @param dangerIds 需要删除的危险品知识库主键
     * @return 结果
     */
    @Override
    public int deleteBDangerknowledgeByDangerIds(Long[] dangerIds)
    {
        return bDangerknowledgeMapper.deleteBDangerknowledgeByDangerIds(dangerIds);
    }

    /**
     * 删除危险品知识库信息
     * 
     * @param dangerId 危险品知识库主键
     * @return 结果
     */
    @Override
    public int deleteBDangerknowledgeByDangerId(Long dangerId)
    {
        return bDangerknowledgeMapper.deleteBDangerknowledgeByDangerId(dangerId);
    }
}
