package com.ruoyi.web.controller.port;

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
import com.ruoyi.port.domain.BPort;
import com.ruoyi.port.service.IBPortService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * portController
 * 
 * @author victorzl
 * @date 2023-12-06
 */
@RestController
@RequestMapping("/port/port")
public class BPortController extends BaseController
{
    @Autowired
    private IBPortService bPortService;

    /**
     * 查询port列表
     */
    @PreAuthorize("@ss.hasPermi('port:port:list')")
    @GetMapping("/list")
    public TableDataInfo list(BPort bPort)
    {
        startPage();
        List<BPort> list = bPortService.selectBPortList(bPort);
        return getDataTable(list);
    }

    /**
     * 导出port列表
     */
    @PreAuthorize("@ss.hasPermi('port:port:export')")
    @Log(title = "port", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BPort bPort)
    {
        List<BPort> list = bPortService.selectBPortList(bPort);
        ExcelUtil<BPort> util = new ExcelUtil<BPort>(BPort.class);
        util.exportExcel(response, list, "port数据");
    }

    /**
     * 获取port详细信息
     */
    @PreAuthorize("@ss.hasPermi('port:port:query')")
    @GetMapping(value = "/{portId}")
    public AjaxResult getInfo(@PathVariable("portId") Long portId)
    {
        return success(bPortService.selectBPortByPortId(portId));
    }

    /**
     * 新增port
     */
    @PreAuthorize("@ss.hasPermi('port:port:add')")
    @Log(title = "port", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BPort bPort)
    {
        return toAjax(bPortService.insertBPort(bPort));
    }

    /**
     * 修改port
     */
    @PreAuthorize("@ss.hasPermi('port:port:edit')")
    @Log(title = "port", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BPort bPort)
    {
        return toAjax(bPortService.updateBPort(bPort));
    }

    /**
     * 删除port
     */
    @PreAuthorize("@ss.hasPermi('port:port:remove')")
    @Log(title = "port", businessType = BusinessType.DELETE)
	@DeleteMapping("/{portIds}")
    public AjaxResult remove(@PathVariable Long[] portIds)
    {
        return toAjax(bPortService.deleteBPortByPortIds(portIds));
    }
}
