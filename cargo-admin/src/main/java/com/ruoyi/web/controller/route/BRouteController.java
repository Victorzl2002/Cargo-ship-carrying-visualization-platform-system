package com.ruoyi.web.controller.route;

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
import com.ruoyi.route.domain.BRoute;
import com.ruoyi.route.service.IBRouteService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * routeController
 * 
 * @author victorzl
 * @date 2023-12-08
 */
@RestController
@RequestMapping("/route/route")
public class BRouteController extends BaseController
{
    @Autowired
    private IBRouteService bRouteService;

    /**
     * 查询route列表
     */
    @PreAuthorize("@ss.hasPermi('route:route:list')")
    @GetMapping("/list")
    public TableDataInfo list(BRoute bRoute)
    {
        startPage();
        List<BRoute> list = bRouteService.selectBRouteList(bRoute);
        return getDataTable(list);
    }

    /**
     * 导出route列表
     */
    @PreAuthorize("@ss.hasPermi('route:route:export')")
    @Log(title = "route", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BRoute bRoute)
    {
        List<BRoute> list = bRouteService.selectBRouteList(bRoute);
        ExcelUtil<BRoute> util = new ExcelUtil<BRoute>(BRoute.class);
        util.exportExcel(response, list, "route数据");
    }

    /**
     * 获取route详细信息
     */
    @PreAuthorize("@ss.hasPermi('route:route:query')")
    @GetMapping(value = "/{routeId}")
    public AjaxResult getInfo(@PathVariable("routeId") Long routeId)
    {
        return success(bRouteService.selectBRouteByRouteId(routeId));
    }

    /**
     * 新增route
     */
    @PreAuthorize("@ss.hasPermi('route:route:add')")
    @Log(title = "route", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BRoute bRoute)
    {
        return toAjax(bRouteService.insertBRoute(bRoute));
    }

    /**
     * 修改route
     */
    @PreAuthorize("@ss.hasPermi('route:route:edit')")
    @Log(title = "route", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BRoute bRoute)
    {
        return toAjax(bRouteService.updateBRoute(bRoute));
    }

    /**
     * 删除route
     */
    @PreAuthorize("@ss.hasPermi('route:route:remove')")
    @Log(title = "route", businessType = BusinessType.DELETE)
	@DeleteMapping("/{routeIds}")
    public AjaxResult remove(@PathVariable Long[] routeIds)
    {
        return toAjax(bRouteService.deleteBRouteByRouteIds(routeIds));
    }
}
