package com.ruoyi.web.controller.containerslot;

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
import com.ruoyi.containerslot.domain.BContainerslot;
import com.ruoyi.containerslot.service.IBContainerslotService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 箱位管理Controller
 * 
 * @author victorzl
 * @date 2024-02-12
 */
@RestController
@RequestMapping("/containerslot/containerslot")
public class BContainerslotController extends BaseController
{
    @Autowired
    private IBContainerslotService bContainerslotService;

    /**
     * 查询箱位管理列表
     */
    @PreAuthorize("@ss.hasPermi('containerslot:containerslot:list')")
    @GetMapping("/list")
    public TableDataInfo list(BContainerslot bContainerslot)
    {
        startPage();
        List<BContainerslot> list = bContainerslotService.selectBContainerslotList(bContainerslot);
        return getDataTable(list);
    }

    /**
     * 导出箱位管理列表
     */
    @PreAuthorize("@ss.hasPermi('containerslot:containerslot:export')")
    @Log(title = "箱位管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BContainerslot bContainerslot)
    {
        List<BContainerslot> list = bContainerslotService.selectBContainerslotList(bContainerslot);
        ExcelUtil<BContainerslot> util = new ExcelUtil<BContainerslot>(BContainerslot.class);
        util.exportExcel(response, list, "箱位管理数据");
    }

    /**
     * 获取箱位管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('containerslot:containerslot:query')")
    @GetMapping(value = "/{slotId}")
    public AjaxResult getInfo(@PathVariable("slotId") Long slotId)
    {
        return success(bContainerslotService.selectBContainerslotBySlotId(slotId));
    }

    /**
     * 新增箱位管理
     */
    @PreAuthorize("@ss.hasPermi('containerslot:containerslot:add')")
    @Log(title = "箱位管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BContainerslot bContainerslot)
    {
        return toAjax(bContainerslotService.insertBContainerslot(bContainerslot));
    }

    /**
     * 修改箱位管理
     */
    @PreAuthorize("@ss.hasPermi('containerslot:containerslot:edit')")
    @Log(title = "箱位管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BContainerslot bContainerslot)
    {
        return toAjax(bContainerslotService.updateBContainerslot(bContainerslot));
    }

    /**
     * 删除箱位管理
     */
    @PreAuthorize("@ss.hasPermi('containerslot:containerslot:remove')")
    @Log(title = "箱位管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{slotIds}")
    public AjaxResult remove(@PathVariable Long[] slotIds)
    {
        return toAjax(bContainerslotService.deleteBContainerslotBySlotIds(slotIds));
    }
}
