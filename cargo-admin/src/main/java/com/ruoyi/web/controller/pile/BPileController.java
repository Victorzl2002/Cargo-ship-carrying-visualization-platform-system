package com.ruoyi.web.controller.pile;

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
import com.ruoyi.pile.domain.BPile;
import com.ruoyi.pile.service.IBPileService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 货堆管理Controller
 * 
 * @author victorzl
 * @date 2024-01-24
 */
@RestController
@RequestMapping("/pile/pile")
public class BPileController extends BaseController
{
    @Autowired
    private IBPileService bPileService;

    /**
     * 查询货堆管理列表
     */
    @PreAuthorize("@ss.hasPermi('pile:pile:list')")
    @GetMapping("/list")
    public TableDataInfo list(BPile bPile)
    {
        startPage();
        List<BPile> list = bPileService.selectBPileList(bPile);
        return getDataTable(list);
    }

    /**
     * 导出货堆管理列表
     */
    @PreAuthorize("@ss.hasPermi('pile:pile:export')")
    @Log(title = "货堆管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BPile bPile)
    {
        List<BPile> list = bPileService.selectBPileList(bPile);
        ExcelUtil<BPile> util = new ExcelUtil<BPile>(BPile.class);
        util.exportExcel(response, list, "货堆管理数据");
    }

    /**
     * 获取货堆管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('pile:pile:query')")
    @GetMapping(value = "/{pileId}")
    public AjaxResult getInfo(@PathVariable("pileId") Long pileId)
    {
        return success(bPileService.selectBPileByPileId(pileId));
    }

    /**
     * 新增货堆管理
     */
    @PreAuthorize("@ss.hasPermi('pile:pile:add')")
    @Log(title = "货堆管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BPile bPile)
    {
        return toAjax(bPileService.insertBPile(bPile));
    }

    /**
     * 修改货堆管理
     */
    @PreAuthorize("@ss.hasPermi('pile:pile:edit')")
    @Log(title = "货堆管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BPile bPile)
    {
        return toAjax(bPileService.updateBPile(bPile));
    }

    /**
     * 删除货堆管理
     */
    @PreAuthorize("@ss.hasPermi('pile:pile:remove')")
    @Log(title = "货堆管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pileIds}")
    public AjaxResult remove(@PathVariable Long[] pileIds)
    {
        return toAjax(bPileService.deleteBPileByPileIds(pileIds));
    }
}
