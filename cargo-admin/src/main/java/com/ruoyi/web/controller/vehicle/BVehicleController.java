package com.ruoyi.web.controller.vehicle;

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
import com.ruoyi.vehicle.domain.BVehicle;
import com.ruoyi.vehicle.service.IBVehicleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 货车管理Controller
 * 
 * @author victorzl
 * @date 2024-01-24
 */
@RestController
@RequestMapping("/vehicle/vehicle")
public class BVehicleController extends BaseController
{
    @Autowired
    private IBVehicleService bVehicleService;

    /**
     * 查询货车管理列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:vehicle:list')")
    @GetMapping("/list")
    public TableDataInfo list(BVehicle bVehicle)
    {
        startPage();
        List<BVehicle> list = bVehicleService.selectBVehicleList(bVehicle);
        return getDataTable(list);
    }

    /**
     * 导出货车管理列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:vehicle:export')")
    @Log(title = "货车管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BVehicle bVehicle)
    {
        List<BVehicle> list = bVehicleService.selectBVehicleList(bVehicle);
        ExcelUtil<BVehicle> util = new ExcelUtil<BVehicle>(BVehicle.class);
        util.exportExcel(response, list, "货车管理数据");
    }

    /**
     * 获取货车管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('vehicle:vehicle:query')")
    @GetMapping(value = "/{vehicleId}")
    public AjaxResult getInfo(@PathVariable("vehicleId") String vehicleId)
    {
        return success(bVehicleService.selectBVehicleByVehicleId(vehicleId));
    }

    /**
     * 新增货车管理
     */
    @PreAuthorize("@ss.hasPermi('vehicle:vehicle:add')")
    @Log(title = "货车管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BVehicle bVehicle)
    {
        return toAjax(bVehicleService.insertBVehicle(bVehicle));
    }

    /**
     * 修改货车管理
     */
    @PreAuthorize("@ss.hasPermi('vehicle:vehicle:edit')")
    @Log(title = "货车管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BVehicle bVehicle)
    {
        return toAjax(bVehicleService.updateBVehicle(bVehicle));
    }

    /**
     * 删除货车管理
     */
    @PreAuthorize("@ss.hasPermi('vehicle:vehicle:remove')")
    @Log(title = "货车管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{vehicleIds}")
    public AjaxResult remove(@PathVariable String[] vehicleIds)
    {
        return toAjax(bVehicleService.deleteBVehicleByVehicleIds(vehicleIds));
    }
}
