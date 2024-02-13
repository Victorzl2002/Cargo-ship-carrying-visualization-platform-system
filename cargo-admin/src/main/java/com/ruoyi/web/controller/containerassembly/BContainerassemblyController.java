package com.ruoyi.web.controller.containerassembly;

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
import com.ruoyi.containerassembly.domain.BContainerassembly;
import com.ruoyi.containerassembly.service.IBContainerassemblyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 集装箱装配Controller
 * 
 * @author victorzl
 * @date 2024-02-12
 */
@RestController
@RequestMapping("/containerassembly/containerassembly")
public class BContainerassemblyController extends BaseController
{
    @Autowired
    private IBContainerassemblyService bContainerassemblyService;

    /**
     * 查询集装箱装配列表
     */
    @PreAuthorize("@ss.hasPermi('containerassembly:containerassembly:list')")
    @GetMapping("/list")
    public TableDataInfo list(BContainerassembly bContainerassembly)
    {
        startPage();
        List<BContainerassembly> list = bContainerassemblyService.selectBContainerassemblyList(bContainerassembly);
        return getDataTable(list);
    }

    /**
     * 导出集装箱装配列表
     */
    @PreAuthorize("@ss.hasPermi('containerassembly:containerassembly:export')")
    @Log(title = "集装箱装配", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BContainerassembly bContainerassembly)
    {
        List<BContainerassembly> list = bContainerassemblyService.selectBContainerassemblyList(bContainerassembly);
        ExcelUtil<BContainerassembly> util = new ExcelUtil<BContainerassembly>(BContainerassembly.class);
        util.exportExcel(response, list, "集装箱装配数据");
    }

    /**
     * 获取集装箱装配详细信息
     */
    @PreAuthorize("@ss.hasPermi('containerassembly:containerassembly:query')")
    @GetMapping(value = "/{assemblyId}")
    public AjaxResult getInfo(@PathVariable("assemblyId") Long assemblyId)
    {
        return success(bContainerassemblyService.selectBContainerassemblyByAssemblyId(assemblyId));
    }

    /**
     * 新增集装箱装配
     */
    @PreAuthorize("@ss.hasPermi('containerassembly:containerassembly:add')")
    @Log(title = "集装箱装配", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BContainerassembly bContainerassembly)
    {
        return toAjax(bContainerassemblyService.insertBContainerassembly(bContainerassembly));
    }

    /**
     * 修改集装箱装配
     */
    @PreAuthorize("@ss.hasPermi('containerassembly:containerassembly:edit')")
    @Log(title = "集装箱装配", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BContainerassembly bContainerassembly)
    {
        return toAjax(bContainerassemblyService.updateBContainerassembly(bContainerassembly));
    }

    /**
     * 删除集装箱装配
     */
    @PreAuthorize("@ss.hasPermi('containerassembly:containerassembly:remove')")
    @Log(title = "集装箱装配", businessType = BusinessType.DELETE)
	@DeleteMapping("/{assemblyIds}")
    public AjaxResult remove(@PathVariable Long[] assemblyIds)
    {
        return toAjax(bContainerassemblyService.deleteBContainerassemblyByAssemblyIds(assemblyIds));
    }
}
