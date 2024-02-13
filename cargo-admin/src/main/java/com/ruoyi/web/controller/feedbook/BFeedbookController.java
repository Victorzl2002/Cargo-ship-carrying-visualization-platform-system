package com.ruoyi.web.controller.feedbook;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.web.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.feedbook.domain.BFeedbook;
import com.ruoyi.feedbook.service.IBFeedbookService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户反馈Controller
 * 
 * @author victorzl
 * @date 2024-01-09
 */
@RestController
@RequestMapping("/feedbook/feedbook")
public class BFeedbookController extends BaseController
{
    @Autowired
    private IBFeedbookService bFeedbookService;

    /**
     * 查询用户反馈列表
     */
    @PreAuthorize("@ss.hasPermi('feedbook:feedbook:list')")
    @GetMapping("/list")
    public TableDataInfo list(BFeedbook bFeedbook)
    {
        startPage();
        List<BFeedbook> list = bFeedbookService.selectBFeedbookList(bFeedbook);
        return getDataTable(list);
    }

    /**
     * 导出用户反馈列表
     */
    @PreAuthorize("@ss.hasPermi('feedbook:feedbook:export')")
    @Log(title = "用户反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BFeedbook bFeedbook)
    {
        List<BFeedbook> list = bFeedbookService.selectBFeedbookList(bFeedbook);
        ExcelUtil<BFeedbook> util = new ExcelUtil<BFeedbook>(BFeedbook.class);
        util.exportExcel(response, list, "用户反馈数据");
    }

    /**
     * 获取用户反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('feedbook:feedbook:query')")
    @GetMapping(value = "/{feedId}")
    public AjaxResult getInfo(@PathVariable("feedId") Long feedId)
    {
        return success(bFeedbookService.selectBFeedbookByFeedId(feedId));
    }

    /**
     * 新增用户反馈
     */
    @PreAuthorize("@ss.hasPermi('feedbook:feedbook:add')")
    @Log(title = "用户反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BFeedbook bFeedbook)
    {
        return toAjax(bFeedbookService.insertBFeedbook(bFeedbook));
    }

    @PostMapping("/wx/feedback")
    public AjaxResult addwx(@RequestBody BFeedbook bFeedbook, @RequestHeader("token") String token)
    {
        System.out.println(bFeedbook);
        Claims claims= JwtUtils.parseJWT(token);
        String openId=claims.get("openId",String.class);
        bFeedbook.setFeedDate(new Date());
        bFeedbook.setUserId(openId);
        return toAjax(bFeedbookService.insertBFeedbook(bFeedbook));
    }

    /**
     * 修改用户反馈
     */
    @PreAuthorize("@ss.hasPermi('feedbook:feedbook:edit')")
    @Log(title = "用户反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BFeedbook bFeedbook)
    {
        return toAjax(bFeedbookService.updateBFeedbook(bFeedbook));
    }

    /**
     * 删除用户反馈
     */
    @PreAuthorize("@ss.hasPermi('feedbook:feedbook:remove')")
    @Log(title = "用户反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{feedIds}")
    public AjaxResult remove(@PathVariable Long[] feedIds)
    {
        return toAjax(bFeedbookService.deleteBFeedbookByFeedIds(feedIds));
    }
}
