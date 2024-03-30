package com.ruoyi.web.controller.routeschedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.ship.service.IBShipService;
import com.ruoyi.ship.service.impl.BShipServiceImpl;
import com.ruoyi.shipcompany.domain.BShipCompanies;
import com.ruoyi.shipcompany.service.IBShipCompaniesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.routeschedule.domain.BRouteschedule;
import com.ruoyi.routeschedule.service.IBRoutescheduleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 航运计划Controller
 * 
 * @author victorzl
 * @date 2023-12-16
 */
@RestController
@RequestMapping("/routeschedule/routeschedule")
public class BRoutescheduleController extends BaseController
{
    @Autowired
    private IBRoutescheduleService bRoutescheduleService;

    @Autowired
    private IBShipService ibShipService;

    @Autowired
    private IBShipCompaniesService shipCompaniesService;

    /**
     * 查询航运计划列表
     */
    @PreAuthorize("@ss.hasPermi('routeschedule:routeschedule:list')")
    @GetMapping("/list")
    public TableDataInfo list(BRouteschedule bRouteschedule)
    {
        startPage();
        List<BRouteschedule> list = bRoutescheduleService.selectBRoutescheduleList(bRouteschedule);
        return getDataTable(list);
    }
    @PreAuthorize("@ss.hasPermi('routeschedule:routeschedule:list')")
    @GetMapping("/list1")
    public TableDataInfo list1(BRouteschedule bRouteschedule)
    {
        startPage();
        List<BRouteschedule> list = bRoutescheduleService.selectBRoutescheduleList(bRouteschedule);
        List<BRouteschedule> list1=new ArrayList<>();
        list.forEach(element->{
            if (element.getDeparturePort().equals("连云港")||element.getEndingPort().equals("连云港"))
            {
                list1.add(element);
            }
        });
        return getDataTable(list1);
    }

    @PreAuthorize("@ss.hasPermi('routeschedule:routeschedule:list')")
    @GetMapping("/list2")
    public TableDataInfo list2(BRouteschedule bRouteschedule)
    {
        startPage();
        List<BRouteschedule> list = bRoutescheduleService.selectBRoutescheduleList(bRouteschedule);
        List<BRouteschedule> list1=new ArrayList<>();
        list.forEach(element->{
            if (element.getHdcompanyId()==1)
            {
                list1.add(element);
            }
        });
        return getDataTable(list1);
    }

    @GetMapping("/port/scheduleinfo")
    public List<BRouteschedule> getscheduleinfo(BRouteschedule bRouteschedule){
        System.out.println(bRouteschedule);
        List<BRouteschedule> schedulelist = bRoutescheduleService.selectBRoutescheduleListdate(bRouteschedule);
        System.out.println(schedulelist);
        return schedulelist;
    }

    @GetMapping("/port/hdinfo")
    public BShipCompanies gethdinfo(BRouteschedule bRouteschedule){
        System.out.println(bRouteschedule);
        BShipCompanies shipCompaniesList =shipCompaniesService.selectBShipCompaniesByCompanyId(bRouteschedule.getHdcompanyId());
        return shipCompaniesList;
    }
    /**
     * 导出航运计划列表
     */
    @PreAuthorize("@ss.hasPermi('routeschedule:routeschedule:export')")
    @Log(title = "航运计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BRouteschedule bRouteschedule)
    {
        List<BRouteschedule> list = bRoutescheduleService.selectBRoutescheduleList(bRouteschedule);
        ExcelUtil<BRouteschedule> util = new ExcelUtil<BRouteschedule>(BRouteschedule.class);
        util.exportExcel(response, list, "航运计划数据");
    }

    /**
     * 获取航运计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('routeschedule:routeschedule:query')")
    @GetMapping(value = "/{routescheduleId}")
    public AjaxResult getInfo(@PathVariable("routescheduleId") Long routescheduleId)
    {
        return success(bRoutescheduleService.selectBRoutescheduleByRoutescheduleId(routescheduleId));
    }

    /**
     * 新增航运计划
     */
    @PreAuthorize("@ss.hasPermi('routeschedule:routeschedule:add')")
    @Log(title = "航运计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BRouteschedule bRouteschedule)
    {
        if (ibShipService.selectBShipByShipId(bRouteschedule.getShipId())==null)
        {
            return AjaxResult.error("没有货轮信息");
        }
        else
        {
            return toAjax(bRoutescheduleService.insertBRouteschedule(bRouteschedule));
        }
    }

    @PreAuthorize("@ss.hasPermi('routeschedule:routeschedule:add')")
    @Log(title = "航运计划", businessType = BusinessType.INSERT)
    @PostMapping("/add1")
    public AjaxResult add1(@RequestBody BRouteschedule bRouteschedule)
    {
        if (ibShipService.selectBShipByShipId(bRouteschedule.getShipId())==null)
        {
             return AjaxResult.error("没有货轮信息");
        }
        else if (ibShipService.selectBShipByShipId(bRouteschedule.getShipId()).getCompanyId()==1)
        {
            return toAjax(bRoutescheduleService.insertBRouteschedule(bRouteschedule));
        }
        else
        {
            return AjaxResult.error("没有货轮信息");
        }
    }
    /**
     * 修改航运计划
     */
    @PreAuthorize("@ss.hasPermi('routeschedule:routeschedule:edit')")
    @Log(title = "航运计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BRouteschedule bRouteschedule)
    {
        return toAjax(bRoutescheduleService.updateBRouteschedule(bRouteschedule));
    }

    /**
     * 删除航运计划
     */
    @PreAuthorize("@ss.hasPermi('routeschedule:routeschedule:remove')")
    @Log(title = "航运计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{routescheduleIds}")
    public AjaxResult remove(@PathVariable Long[] routescheduleIds)
    {
        return toAjax(bRoutescheduleService.deleteBRoutescheduleByRoutescheduleIds(routescheduleIds));
    }
}
