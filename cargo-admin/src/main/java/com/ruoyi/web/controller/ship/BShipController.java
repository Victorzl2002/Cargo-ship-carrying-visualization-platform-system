package com.ruoyi.web.controller.ship;

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
import com.ruoyi.ship.domain.BShip;
import com.ruoyi.ship.service.IBShipService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 船舶管理Controller
 * 
 * @author victorzl
 * @date 2023-12-16
 */
@RestController
@RequestMapping("/ship/ship")
public class BShipController extends BaseController
{
    @Autowired
    private IBShipService bShipService;

    /**
     * 查询船舶管理列表
     */
    @PreAuthorize("@ss.hasPermi('ship:ship:list')")
    @GetMapping("/list")
    public TableDataInfo list(BShip bShip)
    {
        startPage();
        List<BShip> list = bShipService.selectBShipList(bShip);
        return getDataTable(list);
    }


    @PreAuthorize("@ss.hasPermi('ship:ship:list')")
    @GetMapping("/list1")
    public TableDataInfo list1(BShip bShip)
    {
        startPage();
        bShip.setCompanyId(Long.valueOf(1));
        List<BShip> list = bShipService.selectBShipList(bShip);
        return getDataTable(list);
    }

    /**
     * 导出船舶管理列表
     */
    @PreAuthorize("@ss.hasPermi('ship:ship:export')")
    @Log(title = "船舶管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BShip bShip)
    {
        List<BShip> list = bShipService.selectBShipList(bShip);
        ExcelUtil<BShip> util = new ExcelUtil<BShip>(BShip.class);
        util.exportExcel(response, list, "船舶管理数据");
    }

    /**
     * 获取船舶管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('ship:ship:query')")
    @GetMapping(value = "/{shipId}")
    public AjaxResult getInfo(@PathVariable("shipId") Long shipId)
    {
        return success(bShipService.selectBShipByShipId(shipId));
    }

    /**
     * 新增船舶管理
     */
    @PreAuthorize("@ss.hasPermi('ship:ship:add')")
    @Log(title = "船舶管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BShip bShip)
    {
        System.out.println(bShip);
        return toAjax(bShipService.insertBShip(bShip));
    }

    /**
     * 修改船舶管理
     */
    @PreAuthorize("@ss.hasPermi('ship:ship:edit')")
    @Log(title = "船舶管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BShip bShip)
    {
        return toAjax(bShipService.updateBShip(bShip));
    }

    /**
     * 删除船舶管理
     */
    @PreAuthorize("@ss.hasPermi('ship:ship:remove')")
    @Log(title = "船舶管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{shipIds}")
    public AjaxResult remove(@PathVariable Long[] shipIds)
    {
        return toAjax(bShipService.deleteBShipByShipIds(shipIds));
    }
}
