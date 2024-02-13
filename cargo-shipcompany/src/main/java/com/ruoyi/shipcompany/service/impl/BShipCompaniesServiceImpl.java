package com.ruoyi.shipcompany.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.shipcompany.mapper.BShipCompaniesMapper;
import com.ruoyi.shipcompany.domain.BShipCompanies;
import com.ruoyi.shipcompany.service.IBShipCompaniesService;

/**
 * shipcompanyService业务层处理
 * 
 * @author victorzl
 * @date 2023-12-08
 */
@Service
public class BShipCompaniesServiceImpl implements IBShipCompaniesService 
{
    @Autowired
    private BShipCompaniesMapper bShipCompaniesMapper;

    /**
     * 查询shipcompany
     * 
     * @param companyId shipcompany主键
     * @return shipcompany
     */
    @Override
    public BShipCompanies selectBShipCompaniesByCompanyId(Long companyId)
    {
        return bShipCompaniesMapper.selectBShipCompaniesByCompanyId(companyId);
    }

    /**
     * 查询shipcompany列表
     * 
     * @param bShipCompanies shipcompany
     * @return shipcompany
     */
    @Override
    public List<BShipCompanies> selectBShipCompaniesList(BShipCompanies bShipCompanies)
    {
        return bShipCompaniesMapper.selectBShipCompaniesList(bShipCompanies);
    }

    /**
     * 新增shipcompany
     * 
     * @param bShipCompanies shipcompany
     * @return 结果
     */
    @Override
    public int insertBShipCompanies(BShipCompanies bShipCompanies)
    {
        return bShipCompaniesMapper.insertBShipCompanies(bShipCompanies);
    }

    /**
     * 修改shipcompany
     * 
     * @param bShipCompanies shipcompany
     * @return 结果
     */
    @Override
    public int updateBShipCompanies(BShipCompanies bShipCompanies)
    {
        return bShipCompaniesMapper.updateBShipCompanies(bShipCompanies);
    }

    /**
     * 批量删除shipcompany
     * 
     * @param companyIds 需要删除的shipcompany主键
     * @return 结果
     */
    @Override
    public int deleteBShipCompaniesByCompanyIds(Long[] companyIds)
    {
        return bShipCompaniesMapper.deleteBShipCompaniesByCompanyIds(companyIds);
    }

    /**
     * 删除shipcompany信息
     * 
     * @param companyId shipcompany主键
     * @return 结果
     */
    @Override
    public int deleteBShipCompaniesByCompanyId(Long companyId)
    {
        return bShipCompaniesMapper.deleteBShipCompaniesByCompanyId(companyId);
    }
}
