package com.ruoyi.web.controller.allocation;

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
import com.ruoyi.allocation.domain.BAllocation;
import com.ruoyi.allocation.service.IBAllocationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 货物分配Controller
 * 
 * @author victorzl
 * @date 2024-01-19
 */
@RestController
@RequestMapping("/allocation/allocation")
public class BAllocationController extends BaseController
{
    @Autowired
    private IBAllocationService bAllocationService;

    /**
     * 查询货物分配列表
     */
    @PreAuthorize("@ss.hasPermi('allocation:allocation:list')")
    @GetMapping("/list")
    public TableDataInfo list(BAllocation bAllocation)
    {
        startPage();
        List<BAllocation> list = bAllocationService.selectBAllocationList(bAllocation);
        return getDataTable(list);
    }

    /**
     * 导出货物分配列表
     */
    @PreAuthorize("@ss.hasPermi('allocation:allocation:export')")
    @Log(title = "货物分配", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BAllocation bAllocation)
    {
        List<BAllocation> list = bAllocationService.selectBAllocationList(bAllocation);
        ExcelUtil<BAllocation> util = new ExcelUtil<BAllocation>(BAllocation.class);
        util.exportExcel(response, list, "货物分配数据");
    }

    /**
     * 获取货物分配详细信息
     */
    @PreAuthorize("@ss.hasPermi('allocation:allocation:query')")
    @GetMapping(value = "/{allocationId}")
    public AjaxResult getInfo(@PathVariable("allocationId") Long allocationId)
    {
        return success(bAllocationService.selectBAllocationByAllocationId(allocationId));
    }

    /**
     * 新增货物分配
     */
    @PreAuthorize("@ss.hasPermi('allocation:allocation:add')")
    @Log(title = "货物分配", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BAllocation bAllocation)
    {
        return toAjax(bAllocationService.insertBAllocation(bAllocation));
    }

    /**
     * 修改货物分配
     */
    @PreAuthorize("@ss.hasPermi('allocation:allocation:edit')")
    @Log(title = "货物分配", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BAllocation bAllocation)
    {
        return toAjax(bAllocationService.updateBAllocation(bAllocation));
    }

    /**
     * 删除货物分配
     */
    @PreAuthorize("@ss.hasPermi('allocation:allocation:remove')")
    @Log(title = "货物分配", businessType = BusinessType.DELETE)
	@DeleteMapping("/{allocationIds}")
    public AjaxResult remove(@PathVariable Long[] allocationIds)
    {
        return toAjax(bAllocationService.deleteBAllocationByAllocationIds(allocationIds));
    }
}
