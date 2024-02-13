package com.ruoyi.web.controller.containerunloading;

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
import com.ruoyi.containerunloading.domain.BContainerunloading;
import com.ruoyi.containerunloading.service.IBContainerunloadingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 集装箱卸载管理Controller
 * 
 * @author victorzl
 * @date 2024-02-13
 */
@RestController
@RequestMapping("/containerunloading/containerunloading")
public class BContainerunloadingController extends BaseController
{
    @Autowired
    private IBContainerunloadingService bContainerunloadingService;

    /**
     * 查询集装箱卸载管理列表
     */
    @PreAuthorize("@ss.hasPermi('containerunloading:containerunloading:list')")
    @GetMapping("/list")
    public TableDataInfo list(BContainerunloading bContainerunloading)
    {
        startPage();
        List<BContainerunloading> list = bContainerunloadingService.selectBContainerunloadingList(bContainerunloading);
        return getDataTable(list);
    }

    /**
     * 导出集装箱卸载管理列表
     */
    @PreAuthorize("@ss.hasPermi('containerunloading:containerunloading:export')")
    @Log(title = "集装箱卸载管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BContainerunloading bContainerunloading)
    {
        List<BContainerunloading> list = bContainerunloadingService.selectBContainerunloadingList(bContainerunloading);
        ExcelUtil<BContainerunloading> util = new ExcelUtil<BContainerunloading>(BContainerunloading.class);
        util.exportExcel(response, list, "集装箱卸载管理数据");
    }

    /**
     * 获取集装箱卸载管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('containerunloading:containerunloading:query')")
    @GetMapping(value = "/{unloadingId}")
    public AjaxResult getInfo(@PathVariable("unloadingId") Long unloadingId)
    {
        return success(bContainerunloadingService.selectBContainerunloadingByUnloadingId(unloadingId));
    }

    /**
     * 新增集装箱卸载管理
     */
    @PreAuthorize("@ss.hasPermi('containerunloading:containerunloading:add')")
    @Log(title = "集装箱卸载管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BContainerunloading bContainerunloading)
    {
        return toAjax(bContainerunloadingService.insertBContainerunloading(bContainerunloading));
    }

    /**
     * 修改集装箱卸载管理
     */
    @PreAuthorize("@ss.hasPermi('containerunloading:containerunloading:edit')")
    @Log(title = "集装箱卸载管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BContainerunloading bContainerunloading)
    {
        return toAjax(bContainerunloadingService.updateBContainerunloading(bContainerunloading));
    }

    /**
     * 删除集装箱卸载管理
     */
    @PreAuthorize("@ss.hasPermi('containerunloading:containerunloading:remove')")
    @Log(title = "集装箱卸载管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{unloadingIds}")
    public AjaxResult remove(@PathVariable Long[] unloadingIds)
    {
        return toAjax(bContainerunloadingService.deleteBContainerunloadingByUnloadingIds(unloadingIds));
    }
}
