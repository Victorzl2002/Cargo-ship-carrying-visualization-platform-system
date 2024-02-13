package com.ruoyi.web.controller.spacenote;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.spacenote.domain.BSpacenote;
import com.ruoyi.spacenote.service.IBSpacenoteService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 集装箱空间利用率Controller
 * 
 * @author victorzl
 * @date 2024-01-19
 */
@RestController
@RequestMapping("/spacenote/spacenote")
public class BSpacenoteController extends BaseController
{
    @Autowired
    private IBSpacenoteService bSpacenoteService;

    /**
     * 查询集装箱空间利用率列表
     */
    @PreAuthorize("@ss.hasPermi('spacenote:spacenote:list')")
    @GetMapping("/list")
    public TableDataInfo list(BSpacenote bSpacenote)
    {
        startPage();
        List<BSpacenote> list = bSpacenoteService.selectBSpacenoteList(bSpacenote);
        return getDataTable(list);
    }

    /**
     * 导出集装箱空间利用率列表
     */
    @PreAuthorize("@ss.hasPermi('spacenote:spacenote:export')")
    @Log(title = "集装箱空间利用率", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BSpacenote bSpacenote)
    {
        List<BSpacenote> list = bSpacenoteService.selectBSpacenoteList(bSpacenote);
        ExcelUtil<BSpacenote> util = new ExcelUtil<BSpacenote>(BSpacenote.class);
        util.exportExcel(response, list, "集装箱空间利用率数据");
    }

    /**
     * 获取集装箱空间利用率详细信息
     */
    @PreAuthorize("@ss.hasPermi('spacenote:spacenote:query')")
    @GetMapping(value = "/{spaceutilizationrateId}")
    public AjaxResult getInfo(@PathVariable("spaceutilizationrateId") Long spaceutilizationrateId)
    {
        return success(bSpacenoteService.selectBSpacenoteBySpaceutilizationrateId(spaceutilizationrateId));
    }

    /**
     * 新增集装箱空间利用率
     */
    @PreAuthorize("@ss.hasPermi('spacenote:spacenote:add')")
    @Log(title = "集装箱空间利用率", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BSpacenote bSpacenote)
    {
        return toAjax(bSpacenoteService.insertBSpacenote(bSpacenote));
    }

    /**
     * 修改集装箱空间利用率
     */
    @PreAuthorize("@ss.hasPermi('spacenote:spacenote:edit')")
    @Log(title = "集装箱空间利用率", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BSpacenote bSpacenote)
    {
        return toAjax(bSpacenoteService.updateBSpacenote(bSpacenote));
    }

    /**
     * 删除集装箱空间利用率
     */
    @PreAuthorize("@ss.hasPermi('spacenote:spacenote:remove')")
    @Log(title = "集装箱空间利用率", businessType = BusinessType.DELETE)
	@DeleteMapping("/{spaceutilizationrateIds}")
    public AjaxResult remove(@PathVariable Long[] spaceutilizationrateIds)
    {
        return toAjax(bSpacenoteService.deleteBSpacenoteBySpaceutilizationrateIds(spaceutilizationrateIds));
    }
}
