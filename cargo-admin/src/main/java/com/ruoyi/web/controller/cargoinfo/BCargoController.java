package com.ruoyi.web.controller.cargoinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.order.domain.BOrder;
import com.ruoyi.order.service.IBOrderService;
import com.ruoyi.web.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.lettuce.core.ScriptOutputType;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.cargoinfo.domain.BCargo;
import com.ruoyi.cargoinfo.service.IBCargoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 货物管理Controller
 * 
 * @author victorzl
 * @date 2023-12-15
 */
@RestController
@RequestMapping("/cargoinfo/cargoinfo")
public class BCargoController extends BaseController
{
    @Autowired
    private IBCargoService bCargoService;

    @Autowired
    private IBOrderService ibOrderService;

    /**
     * 查询货物管理列表
     */
    @PreAuthorize("@ss.hasPermi('cargoinfo:cargoinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(BCargo bCargo)
    {
        startPage();
        List<BCargo> list = bCargoService.selectBCargoList(bCargo);
        return getDataTable(list);
    }

    @GetMapping( "/wx/getcargoInfoById")
    public AjaxResult getcargoInfoById(@RequestParam("cargoId") Integer cargoId )
    {
        System.out.println(cargoId);
        BCargo bCargo=bCargoService.selectBCargoByCargoId(Long.valueOf(cargoId));
        return AjaxResult.success(bCargo);
    }
    /**
     * 导出货物管理列表
     */
    @PreAuthorize("@ss.hasPermi('cargoinfo:cargoinfo:export')")
    @Log(title = "货物管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BCargo bCargo)
    {
        List<BCargo> list = bCargoService.selectBCargoList(bCargo);
        ExcelUtil<BCargo> util = new ExcelUtil<BCargo>(BCargo.class);
        util.exportExcel(response, list, "货物管理数据");
    }


    /**
     * 货物分配
     */
    @PostMapping("/allocate")
    public AjaxResult allocate(BCargo bCargo)
    {
        BOrder ordernull = new BOrder();
        ordernull.setOrderStatus("订单已创建");
        List<BOrder> orderList = ibOrderService.selectBOrderList(ordernull);
        System.out.println(orderList.size());
        if (orderList.size()>0)
        {
            // 使用Map按航运计划ID划分订单
            Map<String, List<BOrder>> ordersByShippingPlan = new HashMap<>();
            for (BOrder order : orderList) {
                String shippingPlanId = String.valueOf(order.getScheduleId());
                ordersByShippingPlan.computeIfAbsent(shippingPlanId, k -> new ArrayList<>()).add(order);
            }
            // 遍历划分的航运计划，对每个航运计划进行货物分配
            for (Map.Entry<String, List<BOrder>> entry : ordersByShippingPlan.entrySet()) {
                String shippingPlanId = entry.getKey();
                List<BOrder> ordersForShippingPlan = entry.getValue();
                // 在这里执行货物分配的逻辑，可以调用相应的方法
                bCargoService.allocateCargoForShippingPlan(shippingPlanId, ordersForShippingPlan);
            }
            return AjaxResult.success("success");
        }
        else {
            return AjaxResult.error("无需要货物分配的订单");
        }
    }
    /**
     * 获取货物管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('cargoinfo:cargoinfo:query')")
    @GetMapping(value = "/{cargoId}")
    public AjaxResult getInfo(@PathVariable("cargoId") Long cargoId)
    {
        return success(bCargoService.selectBCargoByCargoId(cargoId));
    }

    @PostMapping( "/wx/uploadcargo")
    public AjaxResult uploadcargo(@RequestBody BCargo cargo, @RequestHeader("token") String token)
    {
        cargo.setCargoStatus("未分配");
        Claims claims=JwtUtils.parseJWT(token);
        String openId=claims.get("openId",String.class);
        cargo.setUserId(openId);
        bCargoService.insertBCargo(cargo);
        return success(bCargoService.selectBCargoList(cargo));
    }

    @PostMapping( "/wx/getcargoinfo")
    public AjaxResult getcargoinfo(@RequestHeader("token") String token)
    {
        Claims claims=JwtUtils.parseJWT(token);
        String openId=claims.get("openId",String.class);
        BCargo cargo=new BCargo();
        cargo.setUserId(openId);
        List<BCargo> list= bCargoService.selectBCargoList(cargo);
        return success(list);
    }

    /**
     * 新增货物管理
     */
    @PreAuthorize("@ss.hasPermi('cargoinfo:cargoinfo:add')")
    @Log(title = "货物管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BCargo bCargo)
    {
        return toAjax(bCargoService.insertBCargo(bCargo));
    }

    /**
     * 修改货物管理
     */
    @PreAuthorize("@ss.hasPermi('cargoinfo:cargoinfo:edit')")
    @Log(title = "货物管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BCargo bCargo)
    {
        return toAjax(bCargoService.updateBCargo(bCargo));
    }

    /**
     * 删除货物管理
     */
    @PreAuthorize("@ss.hasPermi('cargoinfo:cargoinfo:remove')")
    @Log(title = "货物管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cargoIds}")
    public AjaxResult remove(@PathVariable Long[] cargoIds)
    {
        return toAjax(bCargoService.deleteBCargoByCargoIds(cargoIds));
    }
}
