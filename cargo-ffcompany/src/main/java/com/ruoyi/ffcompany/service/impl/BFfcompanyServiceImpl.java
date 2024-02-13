package com.ruoyi.ffcompany.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ffcompany.mapper.BFfcompanyMapper;
import com.ruoyi.ffcompany.domain.BFfcompany;
import com.ruoyi.ffcompany.service.IBFfcompanyService;

/**
 * 货代公司管理Service业务层处理
 * 
 * @author victorzl
 * @date 2024-01-15
 */
@Service
public class BFfcompanyServiceImpl implements IBFfcompanyService 
{
    @Autowired
    private BFfcompanyMapper bFfcompanyMapper;

    /**
     * 查询货代公司管理
     * 
     * @param ffcompanyId 货代公司管理主键
     * @return 货代公司管理
     */
    @Override
    public BFfcompany selectBFfcompanyByFfcompanyId(Long ffcompanyId)
    {
        return bFfcompanyMapper.selectBFfcompanyByFfcompanyId(ffcompanyId);
    }

    /**
     * 查询货代公司管理列表
     * 
     * @param bFfcompany 货代公司管理
     * @return 货代公司管理
     */
    @Override
    public List<BFfcompany> selectBFfcompanyList(BFfcompany bFfcompany)
    {
        return bFfcompanyMapper.selectBFfcompanyList(bFfcompany);
    }

    /**
     * 新增货代公司管理
     * 
     * @param bFfcompany 货代公司管理
     * @return 结果
     */
    @Override
    public int insertBFfcompany(BFfcompany bFfcompany)
    {
        return bFfcompanyMapper.insertBFfcompany(bFfcompany);
    }

    /**
     * 修改货代公司管理
     * 
     * @param bFfcompany 货代公司管理
     * @return 结果
     */
    @Override
    public int updateBFfcompany(BFfcompany bFfcompany)
    {
        return bFfcompanyMapper.updateBFfcompany(bFfcompany);
    }

    /**
     * 批量删除货代公司管理
     * 
     * @param ffcompanyIds 需要删除的货代公司管理主键
     * @return 结果
     */
    @Override
    public int deleteBFfcompanyByFfcompanyIds(Long[] ffcompanyIds)
    {
        return bFfcompanyMapper.deleteBFfcompanyByFfcompanyIds(ffcompanyIds);
    }

    /**
     * 删除货代公司管理信息
     * 
     * @param ffcompanyId 货代公司管理主键
     * @return 结果
     */
    @Override
    public int deleteBFfcompanyByFfcompanyId(Long ffcompanyId)
    {
        return bFfcompanyMapper.deleteBFfcompanyByFfcompanyId(ffcompanyId);
    }
}
