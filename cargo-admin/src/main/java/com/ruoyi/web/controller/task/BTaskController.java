package com.ruoyi.web.controller.task;

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
import com.ruoyi.task.domain.BTask;
import com.ruoyi.task.service.IBTaskService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 货车计划管理Controller
 * 
 * @author victorzl
 * @date 2024-01-24
 */
@RestController
@RequestMapping("/task/task")
public class BTaskController extends BaseController
{
    @Autowired
    private IBTaskService bTaskService;

    /**
     * 查询货车计划管理列表
     */
    @PreAuthorize("@ss.hasPermi('task:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(BTask bTask)
    {
        startPage();
        List<BTask> list = bTaskService.selectBTaskList(bTask);
        return getDataTable(list);
    }

    /**
     * 导出货车计划管理列表
     */
    @PreAuthorize("@ss.hasPermi('task:task:export')")
    @Log(title = "货车计划管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BTask bTask)
    {
        List<BTask> list = bTaskService.selectBTaskList(bTask);
        ExcelUtil<BTask> util = new ExcelUtil<BTask>(BTask.class);
        util.exportExcel(response, list, "货车计划管理数据");
    }

    /**
     * 获取货车计划管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:task:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return success(bTaskService.selectBTaskByTaskId(taskId));
    }

    /**
     * 新增货车计划管理
     */
    @PreAuthorize("@ss.hasPermi('task:task:add')")
    @Log(title = "货车计划管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BTask bTask)
    {
        return toAjax(bTaskService.insertBTask(bTask));
    }

    /**
     * 修改货车计划管理
     */
    @PreAuthorize("@ss.hasPermi('task:task:edit')")
    @Log(title = "货车计划管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BTask bTask)
    {
        return toAjax(bTaskService.updateBTask(bTask));
    }

    /**
     * 删除货车计划管理
     */
    @PreAuthorize("@ss.hasPermi('task:task:remove')")
    @Log(title = "货车计划管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(bTaskService.deleteBTaskByTaskIds(taskIds));
    }
}
