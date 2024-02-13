package com.ruoyi.web.controller.dangerinfo;

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
import com.ruoyi.dangerinfo.domain.BDanger;
import com.ruoyi.dangerinfo.service.IBDangerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 危险品识别信息管理Controller
 * 
 * @author victorzl
 * @date 2024-01-07
 */
@RestController
@RequestMapping("/dangerinfo/dangerinfo")
public class BDangerController extends BaseController
{
    @Autowired
    private IBDangerService bDangerService;

    /**
     * 查询危险品识别信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('dangerinfo:dangerinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(BDanger bDanger)
    {
        startPage();
        List<BDanger> list = bDangerService.selectBDangerList(bDanger);
        return getDataTable(list);
    }

    /**
     * 导出危险品识别信息管理列表
     */
    @PreAuthorize("@ss.hasPermi('dangerinfo:dangerinfo:export')")
    @Log(title = "危险品识别信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BDanger bDanger)
    {
        List<BDanger> list = bDangerService.selectBDangerList(bDanger);
        ExcelUtil<BDanger> util = new ExcelUtil<BDanger>(BDanger.class);
        util.exportExcel(response, list, "危险品识别信息管理数据");
    }

    /**
     * 获取危险品识别信息管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('dangerinfo:dangerinfo:query')")
    @GetMapping(value = "/{dangerId}")
    public AjaxResult getInfo(@PathVariable("dangerId") Long dangerId)
    {
        return success(bDangerService.selectBDangerByDangerId(dangerId));
    }

    /**
     * 新增危险品识别信息管理
     */
    @PreAuthorize("@ss.hasPermi('dangerinfo:dangerinfo:add')")
    @Log(title = "危险品识别信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BDanger bDanger)
    {
        return toAjax(bDangerService.insertBDanger(bDanger));
    }

    /**
     * 修改危险品识别信息管理
     */
    @PreAuthorize("@ss.hasPermi('dangerinfo:dangerinfo:edit')")
    @Log(title = "危险品识别信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BDanger bDanger)
    {
        return toAjax(bDangerService.updateBDanger(bDanger));
    }

    /**
     * 删除危险品识别信息管理
     */
    @PreAuthorize("@ss.hasPermi('dangerinfo:dangerinfo:remove')")
    @Log(title = "危险品识别信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dangerIds}")
    public AjaxResult remove(@PathVariable Long[] dangerIds)
    {
        return toAjax(bDangerService.deleteBDangerByDangerIds(dangerIds));
    }
}
