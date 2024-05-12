package com.ruoyi.web.controller.transplan;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.MessageSender;
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
import com.ruoyi.transplan.domain.BTransplan;
import com.ruoyi.transplan.service.IBTransplanService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import static com.ruoyi.common.core.domain.AjaxResult.success;

/**
 * transplanController
 * 
 * @author victorzl
 * @date 2024-04-07
 */
@RestController
@RequestMapping("/transplan/transplan")
public class BTransplanController extends BaseController
{
    @Autowired
    private IBTransplanService bTransplanService;

    /**
     * 查询transplan列表
     */
    @PreAuthorize("@ss.hasPermi('transplan:transplan:list')")
    @GetMapping("/list")
    public TableDataInfo list(BTransplan bTransplan)
    {
        startPage();
        List<BTransplan> list = bTransplanService.selectBTransplanList(bTransplan);
        return getDataTable(list);
    }

    @GetMapping("/allocate/{planId}")
    public AjaxResult allocatetrans(@PathVariable("planId") Long planId) throws IOException {
        BTransplan bTransplan=new BTransplan();
        bTransplan.setPlanId(planId);
        List<BTransplan> list = bTransplanService.selectBTransplanList(bTransplan);
        System.out.println(list);
        String message = "Hello";
        System.out.println(message);
        return success();
    }


    /**
     * 导出transplan列表
     */
    @PreAuthorize("@ss.hasPermi('transplan:transplan:export')")
    @Log(title = "transplan", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BTransplan bTransplan)
    {
        List<BTransplan> list = bTransplanService.selectBTransplanList(bTransplan);
        ExcelUtil<BTransplan> util = new ExcelUtil<BTransplan>(BTransplan.class);
        util.exportExcel(response, list, "transplan数据");
    }

    /**
     * 获取transplan详细信息
     */
    @PreAuthorize("@ss.hasPermi('transplan:transplan:query')")
    @GetMapping(value = "/{planId}")
    public AjaxResult getInfo(@PathVariable("planId") Long planId)
    {
        return success(bTransplanService.selectBTransplanByPlanId(planId));
    }

    /**
     * 新增transplan
     */
    @PreAuthorize("@ss.hasPermi('transplan:transplan:add')")
    @Log(title = "transplan", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BTransplan bTransplan)
    {
        return toAjax(bTransplanService.insertBTransplan(bTransplan));
    }

    /**
     * 修改transplan
     */
    @PreAuthorize("@ss.hasPermi('transplan:transplan:edit')")
    @Log(title = "transplan", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BTransplan bTransplan)
    {
        return toAjax(bTransplanService.updateBTransplan(bTransplan));
    }

    /**
     * 删除transplan
     */
    @PreAuthorize("@ss.hasPermi('transplan:transplan:remove')")
    @Log(title = "transplan", businessType = BusinessType.DELETE)
	@DeleteMapping("/{planIds}")
    public AjaxResult remove(@PathVariable Long[] planIds)
    {
        return toAjax(bTransplanService.deleteBTransplanByPlanIds(planIds));
    }
}
