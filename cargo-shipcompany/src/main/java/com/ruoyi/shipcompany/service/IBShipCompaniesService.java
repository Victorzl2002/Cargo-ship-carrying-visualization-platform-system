package com.ruoyi.shipcompany.service;

import java.util.List;
import com.ruoyi.shipcompany.domain.BShipCompanies;

/**
 * shipcompanyService接口
 * 
 * @author victorzl
 * @date 2023-12-08
 */
public interface IBShipCompaniesService 
{
    /**
     * 查询shipcompany
     * 
     * @param companyId shipcompany主键
     * @return shipcompany
     */
    public BShipCompanies selectBShipCompaniesByCompanyId(Long companyId);

    /**
     * 查询shipcompany列表
     * 
     * @param bShipCompanies shipcompany
     * @return shipcompany集合
     */
    public List<BShipCompanies> selectBShipCompaniesList(BShipCompanies bShipCompanies);

    /**
     * 新增shipcompany
     * 
     * @param bShipCompanies shipcompany
     * @return 结果
     */
    public int insertBShipCompanies(BShipCompanies bShipCompanies);

    /**
     * 修改shipcompany
     * 
     * @param bShipCompanies shipcompany
     * @return 结果
     */
    public int updateBShipCompanies(BShipCompanies bShipCompanies);

    /**
     * 批量删除shipcompany
     * 
     * @param companyIds 需要删除的shipcompany主键集合
     * @return 结果
     */
    public int deleteBShipCompaniesByCompanyIds(Long[] companyIds);

    /**
     * 删除shipcompany信息
     * 
     * @param companyId shipcompany主键
     * @return 结果
     */
    public int deleteBShipCompaniesByCompanyId(Long companyId);
}
