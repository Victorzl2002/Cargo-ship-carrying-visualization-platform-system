package com.ruoyi.web.controller.terminalmanage;

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
import com.ruoyi.terminalmanage.domain.BTerminal;
import com.ruoyi.terminalmanage.service.IBTerminalService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * terminalmanageController
 * 
 * @author victorzl
 * @date 2024-04-07
 */
@RestController
@RequestMapping("/terminalmanage/terminalmanage")
public class BTerminalController extends BaseController
{
    @Autowired
    private IBTerminalService bTerminalService;

    /**
     * 查询terminalmanage列表
     */
    @PreAuthorize("@ss.hasPermi('terminalmanage:terminalmanage:list')")
    @GetMapping("/list")
    public TableDataInfo list(BTerminal bTerminal)
    {
        startPage();
        List<BTerminal> list = bTerminalService.selectBTerminalList(bTerminal);
        return getDataTable(list);
    }

    /**
     * 导出terminalmanage列表
     */
    @PreAuthorize("@ss.hasPermi('terminalmanage:terminalmanage:export')")
    @Log(title = "terminalmanage", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BTerminal bTerminal)
    {
        List<BTerminal> list = bTerminalService.selectBTerminalList(bTerminal);
        ExcelUtil<BTerminal> util = new ExcelUtil<BTerminal>(BTerminal.class);
        util.exportExcel(response, list, "terminalmanage数据");
    }

    /**
     * 获取terminalmanage详细信息
     */
    @PreAuthorize("@ss.hasPermi('terminalmanage:terminalmanage:query')")
    @GetMapping(value = "/{terminalId}")
    public AjaxResult getInfo(@PathVariable("terminalId") String terminalId)
    {
        return success(bTerminalService.selectBTerminalByTerminalId(terminalId));
    }

    /**
     * 新增terminalmanage
     */
    @PreAuthorize("@ss.hasPermi('terminalmanage:terminalmanage:add')")
    @Log(title = "terminalmanage", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BTerminal bTerminal)
    {
        System.out.println(bTerminal);
        return toAjax(bTerminalService.insertBTerminal1(bTerminal));
    }

    /**
     * 修改terminalmanage
     */
    @PreAuthorize("@ss.hasPermi('terminalmanage:terminalmanage:edit')")
    @Log(title = "terminalmanage", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BTerminal bTerminal)
    {
        System.out.println(bTerminal);
        return toAjax(bTerminalService.updateBTerminal(bTerminal));
    }

    /**
     * 删除terminalmanage
     */
    @PreAuthorize("@ss.hasPermi('terminalmanage:terminalmanage:remove')")
    @Log(title = "terminalmanage", businessType = BusinessType.DELETE)
	@DeleteMapping("/{terminalIds}")
    public AjaxResult remove(@PathVariable String[] terminalIds)
    {
        return toAjax(bTerminalService.deleteBTerminalByTerminalIds(terminalIds));
    }
}
