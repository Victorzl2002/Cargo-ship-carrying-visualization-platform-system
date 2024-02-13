package com.ruoyi.web.controller.dangerknowledge;

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
import com.ruoyi.dangerknowledge.domain.BDangerknowledge;
import com.ruoyi.dangerknowledge.service.IBDangerknowledgeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 危险品知识库Controller
 * 
 * @author victorzl
 * @date 2024-01-22
 */
@RestController
@RequestMapping("/dangerknowledge/dangerknowledge")
public class BDangerknowledgeController extends BaseController
{
    @Autowired
    private IBDangerknowledgeService bDangerknowledgeService;

    /**
     * 查询危险品知识库列表
     */
    @PreAuthorize("@ss.hasPermi('dangerknowledge:dangerknowledge:list')")
    @GetMapping("/list")
    public TableDataInfo list(BDangerknowledge bDangerknowledge)
    {
        startPage();
        List<BDangerknowledge> list = bDangerknowledgeService.selectBDangerknowledgeList(bDangerknowledge);
        return getDataTable(list);
    }

    /**
     * 导出危险品知识库列表
     */
    @PreAuthorize("@ss.hasPermi('dangerknowledge:dangerknowledge:export')")
    @Log(title = "危险品知识库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BDangerknowledge bDangerknowledge)
    {
        List<BDangerknowledge> list = bDangerknowledgeService.selectBDangerknowledgeList(bDangerknowledge);
        ExcelUtil<BDangerknowledge> util = new ExcelUtil<BDangerknowledge>(BDangerknowledge.class);
        util.exportExcel(response, list, "危险品知识库数据");
    }

    /**
     * 获取危险品知识库详细信息
     */
    @PreAuthorize("@ss.hasPermi('dangerknowledge:dangerknowledge:query')")
    @GetMapping(value = "/{dangerId}")
    public AjaxResult getInfo(@PathVariable("dangerId") Long dangerId)
    {
        return success(bDangerknowledgeService.selectBDangerknowledgeByDangerId(dangerId));
    }

    /**
     * 新增危险品知识库
     */
    @PreAuthorize("@ss.hasPermi('dangerknowledge:dangerknowledge:add')")
    @Log(title = "危险品知识库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BDangerknowledge bDangerknowledge)
    {
        return toAjax(bDangerknowledgeService.insertBDangerknowledge(bDangerknowledge));
    }

    /**
     * 修改危险品知识库
     */
    @PreAuthorize("@ss.hasPermi('dangerknowledge:dangerknowledge:edit')")
    @Log(title = "危险品知识库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BDangerknowledge bDangerknowledge)
    {
        return toAjax(bDangerknowledgeService.updateBDangerknowledge(bDangerknowledge));
    }

    /**
     * 删除危险品知识库
     */
    @PreAuthorize("@ss.hasPermi('dangerknowledge:dangerknowledge:remove')")
    @Log(title = "危险品知识库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dangerIds}")
    public AjaxResult remove(@PathVariable Long[] dangerIds)
    {
        return toAjax(bDangerknowledgeService.deleteBDangerknowledgeByDangerIds(dangerIds));
    }
}
