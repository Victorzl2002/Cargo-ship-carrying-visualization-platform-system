package com.ruoyi.web.controller.ffcompany;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.ffcompany.domain.BFfcompany;
import com.ruoyi.ffcompany.service.IBFfcompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 货代公司管理Controller
 * 
 * @author victorzl
 * @date 2024-01-15
 */
@RestController
@RequestMapping("/ffcompany/ffcompany")
public class BFfcompanyController extends BaseController
{
    @Autowired
    private IBFfcompanyService bFfcompanyService;

    /**
     * 查询货代公司管理列表
     */
    @PreAuthorize("@ss.hasPermi('ffcompany:ffcompany:list')")
    @GetMapping("/list")
    public TableDataInfo list(BFfcompany bFfcompany)
    {
        startPage();
        List<BFfcompany> list = bFfcompanyService.selectBFfcompanyList(bFfcompany);
        return getDataTable(list);
    }

    /**
     * 导出货代公司管理列表
     */
    @PreAuthorize("@ss.hasPermi('ffcompany:ffcompany:export')")
    @Log(title = "货代公司管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BFfcompany bFfcompany)
    {
        List<BFfcompany> list = bFfcompanyService.selectBFfcompanyList(bFfcompany);
        ExcelUtil<BFfcompany> util = new ExcelUtil<BFfcompany>(BFfcompany.class);
        util.exportExcel(response, list, "货代公司管理数据");
    }

    /**
     * 获取货代公司管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('ffcompany:ffcompany:query')")
    @GetMapping(value = "/{ffcompanyId}")
    public AjaxResult getInfo(@PathVariable("ffcompanyId") Long ffcompanyId)
    {
        return success(bFfcompanyService.selectBFfcompanyByFfcompanyId(ffcompanyId));
    }

    /**
     * 新增货代公司管理
     */
    @PreAuthorize("@ss.hasPermi('ffcompany:ffcompany:add')")
    @Log(title = "货代公司管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BFfcompany bFfcompany)
    {
        return toAjax(bFfcompanyService.insertBFfcompany(bFfcompany));
    }

    /**
     * 修改货代公司管理
     */
    @PreAuthorize("@ss.hasPermi('ffcompany:ffcompany:edit')")
    @Log(title = "货代公司管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BFfcompany bFfcompany)
    {
        return toAjax(bFfcompanyService.updateBFfcompany(bFfcompany));
    }

    /**
     * 删除货代公司管理
     */
    @PreAuthorize("@ss.hasPermi('ffcompany:ffcompany:remove')")
    @Log(title = "货代公司管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ffcompanyIds}")
    public AjaxResult remove(@PathVariable Long[] ffcompanyIds)
    {
        return toAjax(bFfcompanyService.deleteBFfcompanyByFfcompanyIds(ffcompanyIds));
    }
}
