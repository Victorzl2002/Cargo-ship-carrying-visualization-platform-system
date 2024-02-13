package com.ruoyi.web.controller.container;

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
import com.ruoyi.container.domain.BContainer;
import com.ruoyi.container.service.IBContainerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 集装箱管理Controller
 * 
 * @author victorzl
 * @date 2024-01-18
 */
@RestController
@RequestMapping("/container/container")
public class BContainerController extends BaseController
{
    @Autowired
    private IBContainerService bContainerService;

    /**
     * 查询集装箱管理列表
     */
    @PreAuthorize("@ss.hasPermi('container:container:list')")
    @GetMapping("/list")
    public TableDataInfo list(BContainer bContainer)
    {
        startPage();
        List<BContainer> list = bContainerService.selectBContainerList(bContainer);
        System.out.println(list);
        return getDataTable(list);
    }

    /**
     * 查询集装箱管理列表
     */
    @PreAuthorize("@ss.hasPermi('container:container:list')")
    @GetMapping("/list1")
    public TableDataInfo list1(BContainer bContainer)
    {
        startPage();
        bContainer.setContainerLocation("连云港");
        List<BContainer> list = bContainerService.selectBContainerList(bContainer);
        return getDataTable(list);
    }

    /**
     * 查询集装箱管理列表
     */
    @PreAuthorize("@ss.hasPermi('container:container:list')")
    @GetMapping("/list2")
    public TableDataInfo list2(BContainer bContainer)
    {
        startPage();
        bContainer.setContainerLocation("在船");
        List<BContainer> list = bContainerService.selectBContainerList(bContainer);
        return getDataTable(list);
    }

    /**
     * 导出集装箱管理列表
     */
    @PreAuthorize("@ss.hasPermi('container:container:export')")
    @Log(title = "集装箱管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BContainer bContainer)
    {
        List<BContainer> list = bContainerService.selectBContainerList(bContainer);
        ExcelUtil<BContainer> util = new ExcelUtil<BContainer>(BContainer.class);
        util.exportExcel(response, list, "集装箱管理数据");
    }

    /**
     * 获取集装箱管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('container:container:query')")
    @GetMapping(value = "/{containerId}")
    public AjaxResult getInfo(@PathVariable("containerId") Long containerId)
    {
        return success(bContainerService.selectBContainerByContainerId(containerId));
    }

    /**
     * 新增集装箱管理
     */
    @PreAuthorize("@ss.hasPermi('container:container:add')")
    @Log(title = "集装箱管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BContainer bContainer)
    {
        return toAjax(bContainerService.insertBContainer(bContainer));
    }


    /**
     * 新增集装箱管理
     */
    @PreAuthorize("@ss.hasPermi('container:container:add')")
    @Log(title = "集装箱管理", businessType = BusinessType.INSERT)
    @PostMapping("port")
    public AjaxResult add1(@RequestBody BContainer bContainer)
    {
        bContainer.setContainerLocation("在港");
        return toAjax(bContainerService.insertBContainer(bContainer));
    }

    /**
     * 新增集装箱管理
     */
    @PreAuthorize("@ss.hasPermi('container:container:add')")
    @Log(title = "集装箱管理", businessType = BusinessType.INSERT)
    @PostMapping("ship")
    public AjaxResult add2(@RequestBody BContainer bContainer)
    {
        bContainer.setContainerLocation("在船");
        return toAjax(bContainerService.insertBContainer(bContainer));
    }

    /**
     * 修改集装箱管理
     */
    @PreAuthorize("@ss.hasPermi('container:container:edit')")
    @Log(title = "集装箱管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BContainer bContainer)
    {
        return toAjax(bContainerService.updateBContainer(bContainer));
    }

    /**
     * 删除集装箱管理
     */
    @PreAuthorize("@ss.hasPermi('container:container:remove')")
    @Log(title = "集装箱管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{containerIds}")
    public AjaxResult remove(@PathVariable Long[] containerIds)
    {
        return toAjax(bContainerService.deleteBContainerByContainerIds(containerIds));
    }
}
