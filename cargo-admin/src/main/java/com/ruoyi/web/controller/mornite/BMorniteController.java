package com.ruoyi.web.controller.mornite;

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
import com.ruoyi.envirinfo.domain.BMornite;
import com.ruoyi.envirinfo.service.IBMorniteService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 监测信息反馈Controller
 * 
 * @author victorzl
 * @date 2024-05-11
 */
@RestController
@RequestMapping("/envirinfo/mornite")
public class BMorniteController extends BaseController
{
    @Autowired
    private IBMorniteService bMorniteService;

    /**
     * 查询监测信息反馈列表
     */
    @PreAuthorize("@ss.hasPermi('envirinfo:mornite:list')")
    @GetMapping("/list")
    public TableDataInfo list(BMornite bMornite)
    {
        startPage();
        List<BMornite> list = bMorniteService.selectBMorniteList(bMornite);
        return getDataTable(list);
    }

    /**
     * 导出监测信息反馈列表
     */
    @PreAuthorize("@ss.hasPermi('envirinfo:mornite:export')")
    @Log(title = "监测信息反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BMornite bMornite)
    {
        List<BMornite> list = bMorniteService.selectBMorniteList(bMornite);
        ExcelUtil<BMornite> util = new ExcelUtil<BMornite>(BMornite.class);
        util.exportExcel(response, list, "监测信息反馈数据");
    }

    /**
     * 获取监测信息反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('envirinfo:mornite:query')")
    @GetMapping(value = "/{morniteId}")
    public AjaxResult getInfo(@PathVariable("morniteId") Long morniteId)
    {
        return success(bMorniteService.selectBMorniteByMorniteId(morniteId));
    }

    /**
     * 新增监测信息反馈
     */
    @PreAuthorize("@ss.hasPermi('envirinfo:mornite:add')")
    @Log(title = "监测信息反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BMornite bMornite)
    {
        return toAjax(bMorniteService.insertBMornite(bMornite));
    }

    /**
     * 修改监测信息反馈
     */
    @PreAuthorize("@ss.hasPermi('envirinfo:mornite:edit')")
    @Log(title = "监测信息反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BMornite bMornite)
    {
        return toAjax(bMorniteService.updateBMornite(bMornite));
    }

    /**
     * 删除监测信息反馈
     */
    @PreAuthorize("@ss.hasPermi('envirinfo:mornite:remove')")
    @Log(title = "监测信息反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{morniteIds}")
    public AjaxResult remove(@PathVariable Long[] morniteIds)
    {
        return toAjax(bMorniteService.deleteBMorniteByMorniteIds(morniteIds));
    }
}
