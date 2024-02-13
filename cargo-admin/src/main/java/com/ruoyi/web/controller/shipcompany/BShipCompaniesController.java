package com.ruoyi.web.controller.shipcompany;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.shipcompany.domain.BShipCompanies;
import com.ruoyi.shipcompany.service.IBShipCompaniesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;

import com.ruoyi.common.core.page.TableDataInfo;

/**
 * shipcompanyController
 * 
 * @author victorzl
 * @date 2023-12-08
 */
@RestController
@RequestMapping("/shipcompany/shipcompany")
public class BShipCompaniesController extends BaseController
{
    @Autowired
    private IBShipCompaniesService bShipCompaniesService;

    /**
     * 查询shipcompany列表
     */
    @PreAuthorize("@ss.hasPermi('shipcompany:shipcompany:list')")
    @GetMapping("/list")
    public TableDataInfo list(BShipCompanies bShipCompanies)
    {
        startPage();
        List<BShipCompanies> list = bShipCompaniesService.selectBShipCompaniesList(bShipCompanies);
        return getDataTable(list);
    }

    /**
     * 导出shipcompany列表
     */
    @PreAuthorize("@ss.hasPermi('shipcompany:shipcompany:export')")
    @Log(title = "shipcompany", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BShipCompanies bShipCompanies)
    {
        List<BShipCompanies> list = bShipCompaniesService.selectBShipCompaniesList(bShipCompanies);
        ExcelUtil<BShipCompanies> util = new ExcelUtil<BShipCompanies>(BShipCompanies.class);
        util.exportExcel(response, list, "shipcompany数据");
    }

    /**
     * 获取shipcompany详细信息
     */
    @PreAuthorize("@ss.hasPermi('shipcompany:shipcompany:query')")
    @GetMapping(value = "/{companyId}")
    public AjaxResult getInfo(@PathVariable("companyId") Long companyId)
    {
        return success(bShipCompaniesService.selectBShipCompaniesByCompanyId(companyId));
    }

    /**
     * 新增shipcompany
     */
    @PreAuthorize("@ss.hasPermi('shipcompany:shipcompany:add')")
    @Log(title = "shipcompany", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BShipCompanies bShipCompanies)
    {
        return toAjax(bShipCompaniesService.insertBShipCompanies(bShipCompanies));
    }

    /**
     * 修改shipcompany
     */
    @PreAuthorize("@ss.hasPermi('shipcompany:shipcompany:edit')")
    @Log(title = "shipcompany", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BShipCompanies bShipCompanies)
    {
        return toAjax(bShipCompaniesService.updateBShipCompanies(bShipCompanies));
    }

    /**
     * 删除shipcompany
     */
    @PreAuthorize("@ss.hasPermi('shipcompany:shipcompany:remove')")
    @Log(title = "shipcompany", businessType = BusinessType.DELETE)
	@DeleteMapping("/{companyIds}")
    public AjaxResult remove(@PathVariable Long[] companyIds)
    {
        return toAjax(bShipCompaniesService.deleteBShipCompaniesByCompanyIds(companyIds));
    }
}
