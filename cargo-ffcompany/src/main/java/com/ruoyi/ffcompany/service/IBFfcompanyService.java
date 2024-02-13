package com.ruoyi.ffcompany.service;

import java.util.List;
import com.ruoyi.ffcompany.domain.BFfcompany;

/**
 * 货代公司管理Service接口
 * 
 * @author victorzl
 * @date 2024-01-15
 */
public interface IBFfcompanyService 
{
    /**
     * 查询货代公司管理
     * 
     * @param ffcompanyId 货代公司管理主键
     * @return 货代公司管理
     */
    public BFfcompany selectBFfcompanyByFfcompanyId(Long ffcompanyId);

    /**
     * 查询货代公司管理列表
     * 
     * @param bFfcompany 货代公司管理
     * @return 货代公司管理集合
     */
    public List<BFfcompany> selectBFfcompanyList(BFfcompany bFfcompany);

    /**
     * 新增货代公司管理
     * 
     * @param bFfcompany 货代公司管理
     * @return 结果
     */
    public int insertBFfcompany(BFfcompany bFfcompany);

    /**
     * 修改货代公司管理
     * 
     * @param bFfcompany 货代公司管理
     * @return 结果
     */
    public int updateBFfcompany(BFfcompany bFfcompany);

    /**
     * 批量删除货代公司管理
     * 
     * @param ffcompanyIds 需要删除的货代公司管理主键集合
     * @return 结果
     */
    public int deleteBFfcompanyByFfcompanyIds(Long[] ffcompanyIds);

    /**
     * 删除货代公司管理信息
     * 
     * @param ffcompanyId 货代公司管理主键
     * @return 结果
     */
    public int deleteBFfcompanyByFfcompanyId(Long ffcompanyId);
}
