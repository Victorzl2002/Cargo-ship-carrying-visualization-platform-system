package com.ruoyi.dangerknowledge.mapper;

import java.util.List;
import com.ruoyi.dangerknowledge.domain.BDangerknowledge;

/**
 * 危险品知识库Mapper接口
 * 
 * @author victorzl
 * @date 2024-01-22
 */
public interface BDangerknowledgeMapper 
{
    /**
     * 查询危险品知识库
     * 
     * @param dangerId 危险品知识库主键
     * @return 危险品知识库
     */
    public BDangerknowledge selectBDangerknowledgeByDangerId(Long dangerId);

    /**
     * 查询危险品知识库列表
     * 
     * @param bDangerknowledge 危险品知识库
     * @return 危险品知识库集合
     */
    public List<BDangerknowledge> selectBDangerknowledgeList(BDangerknowledge bDangerknowledge);

    /**
     * 新增危险品知识库
     * 
     * @param bDangerknowledge 危险品知识库
     * @return 结果
     */
    public int insertBDangerknowledge(BDangerknowledge bDangerknowledge);

    /**
     * 修改危险品知识库
     * 
     * @param bDangerknowledge 危险品知识库
     * @return 结果
     */
    public int updateBDangerknowledge(BDangerknowledge bDangerknowledge);

    /**
     * 删除危险品知识库
     * 
     * @param dangerId 危险品知识库主键
     * @return 结果
     */
    public int deleteBDangerknowledgeByDangerId(Long dangerId);

    /**
     * 批量删除危险品知识库
     * 
     * @param dangerIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBDangerknowledgeByDangerIds(Long[] dangerIds);
}
