package com.ruoyi.web.controller.driver;

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
import com.ruoyi.driver.domain.BDriver;
import com.ruoyi.driver.service.IBDriverService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * driverController
 * 
 * @author victorzl
 * @date 2024-04-07
 */
@RestController
@RequestMapping("/driver/driver")
public class BDriverController extends BaseController
{
    @Autowired
    private IBDriverService bDriverService;

    /**
     * 查询driver列表
     */
    @PreAuthorize("@ss.hasPermi('driver:driver:list')")
    @GetMapping("/list")
    public TableDataInfo list(BDriver bDriver)
    {
        startPage();
        List<BDriver> list = bDriverService.selectBDriverList(bDriver);
        return getDataTable(list);
    }

    /**
     * 导出driver列表
     */
    @PreAuthorize("@ss.hasPermi('driver:driver:export')")
    @Log(title = "driver", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BDriver bDriver)
    {
        List<BDriver> list = bDriverService.selectBDriverList(bDriver);
        ExcelUtil<BDriver> util = new ExcelUtil<BDriver>(BDriver.class);
        util.exportExcel(response, list, "driver数据");
    }

    /**
     * 获取driver详细信息
     */
    @PreAuthorize("@ss.hasPermi('driver:driver:query')")
    @GetMapping(value = "/{driverId}")
    public AjaxResult getInfo(@PathVariable("driverId") Long driverId)
    {
        return success(bDriverService.selectBDriverByDriverId(driverId));
    }

    /**
     * 新增driver
     */
    @PreAuthorize("@ss.hasPermi('driver:driver:add')")
    @Log(title = "driver", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BDriver bDriver)
    {
        return toAjax(bDriverService.insertBDriver(bDriver));
    }

    /**
     * 修改driver
     */
    @PreAuthorize("@ss.hasPermi('driver:driver:edit')")
    @Log(title = "driver", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BDriver bDriver)
    {
        return toAjax(bDriverService.updateBDriver(bDriver));
    }

    /**
     * 删除driver
     */
    @PreAuthorize("@ss.hasPermi('driver:driver:remove')")
    @Log(title = "driver", businessType = BusinessType.DELETE)
	@DeleteMapping("/{driverIds}")
    public AjaxResult remove(@PathVariable Long[] driverIds)
    {
        return toAjax(bDriverService.deleteBDriverByDriverIds(driverIds));
    }
}
