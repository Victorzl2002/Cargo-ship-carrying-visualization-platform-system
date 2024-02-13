package com.ruoyi.web.controller.order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.cargoinfo.domain.BCargo;
import com.ruoyi.routeschedule.domain.BRouteschedule;
import com.ruoyi.routeschedule.service.IBRoutescheduleService;
import com.ruoyi.ship.service.IBShipService;
import com.ruoyi.web.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.lettuce.core.ScriptOutputType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.order.domain.BOrder;
import com.ruoyi.order.service.IBOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * orderController
 * 
 * @author victorzl
 * @date 2023-12-30
 */
@RestController
@RequestMapping("/order/order")
public class BOrderController extends BaseController
{
    @Autowired
    private IBOrderService bOrderService;

    @Autowired
    private  IBRoutescheduleService ibRoutescheduleService;

    @Autowired
    private IBShipService ibShipService;

    /**
     * 查询order列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(BOrder bOrder)
    {
        startPage();
        List<BOrder> list = bOrderService.selectBOrderList(bOrder);
        return getDataTable(list);
    }

    @PostMapping( "/wx/getorderinfo")
    public AjaxResult getorderinfo(@RequestHeader("token") String token)
    {
        Claims claims=JwtUtils.parseJWT(token);
        String openId=claims.get("openId",String.class);
        BOrder order=new BOrder();
        order.setUserId(openId);
        List<BOrder> list= bOrderService.selectBOrderList(order);
        return success(list);
    }

    @GetMapping( "/wx/getorderinfoBynum")
    public AjaxResult getorderinfoBynum(@RequestParam("OrderNum") String orderNum )
    {
        BOrder order=bOrderService.selectBOrderByOrderId(orderNum);
        if (order!=null)
        {
            return AjaxResult.success(order);
        }
        else {
            return AjaxResult.error("暂无信息");
        }

    }





    @PreAuthorize("@ss.hasPermi('order:order:list')")
    @GetMapping("/list1")
    public TableDataInfo list1(BOrder bOrder)
    {
        startPage();
        List<BOrder> list = bOrderService.selectBOrderList(bOrder);
        List<BOrder> list1=new ArrayList<>();
        list.forEach(element->{
            if (ibRoutescheduleService.selectBRoutescheduleByRoutescheduleId(element.getScheduleId()).getEndingPort().equals("连云港")||
                    ibRoutescheduleService.selectBRoutescheduleByRoutescheduleId(element.getScheduleId()).getDeparturePort().equals("连云港"))
            {
                list1.add(element);
            }
        });
        return getDataTable(list1);
    }

    @PreAuthorize("@ss.hasPermi('order:order:list')")
    @GetMapping("/list2")
    public TableDataInfo list2(BOrder bOrder)
    {
        startPage();
        List<BOrder> list = bOrderService.selectBOrderList(bOrder);
        List<BOrder> list1=new ArrayList<>();
        list.forEach(element->{
            if (ibRoutescheduleService.selectBRoutescheduleByRoutescheduleId(element.getScheduleId()).getHdcompanyId()==1)
            {
                list1.add(element);
            }
        });
        return getDataTable(list1);
    }

    @PreAuthorize("@ss.hasPermi('order:order:list')")
    @GetMapping("/list3")
    public TableDataInfo list3(BOrder bOrder)
    {
        startPage();
        List<BOrder> list = bOrderService.selectBOrderList(bOrder);
        List<BOrder> list1=new ArrayList<>();
        list.forEach(element->{
            if (ibShipService.selectBShipByShipId(ibRoutescheduleService.selectBRoutescheduleByRoutescheduleId(element.getScheduleId()).getShipId()).getCompanyId()==1)
            {
                list1.add(element);
            }
        });
        return getDataTable(list1);
    }

    /**
     * 导出order列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:export')")
    @Log(title = "order", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BOrder bOrder)
    {
        List<BOrder> list = bOrderService.selectBOrderList(bOrder);
        ExcelUtil<BOrder> util = new ExcelUtil<BOrder>(BOrder.class);
        util.exportExcel(response, list, "order数据");
    }

    /**
     * 获取order详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:order:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") String orderId)
    {
        return success(bOrderService.selectBOrderByOrderId(orderId));
    }

    /**
     * 新增order
     */
    @PreAuthorize("@ss.hasPermi('order:order:add')")
    @Log(title = "order", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BOrder bOrder)
    {
        return toAjax(bOrderService.insertBOrder(bOrder));
    }

    @PostMapping("/wx/formorder")
    public AjaxResult formorder(@RequestBody BOrder order,@RequestHeader("token") String token)
    {
        Claims claims= JwtUtils.parseJWT(token);
        String openId=claims.get("openId",String.class);
        order.setUserId(openId);
        order.setCreateTime(new Date());
        String orderId=LocalDateTime.now()+openId;
        order.setOrderId(orderId);
        order.setOrderStatus("订单已创建");
        bOrderService.insertBOrder(order);
        return success(order);
    }
    /**
     * 修改order
     */
    @PreAuthorize("@ss.hasPermi('order:order:edit')")
    @Log(title = "order", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BOrder bOrder)
    {
        return toAjax(bOrderService.updateBOrder(bOrder));
    }

    /**
     * 删除order
     */
    @PreAuthorize("@ss.hasPermi('order:order:remove')")
    @Log(title = "order", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable String[] orderIds)
    {
        return toAjax(bOrderService.deleteBOrderByOrderIds(orderIds));
    }
}
