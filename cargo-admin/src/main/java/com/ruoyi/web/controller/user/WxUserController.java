package com.ruoyi.web.controller.user;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.user.domain.Code;
import com.ruoyi.web.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.user.domain.WxUser;
import com.ruoyi.user.service.IWxUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * wxuserController
 * 
 * @author victorzl
 * @date 2023-12-03
 */
@RestController
@RequestMapping("/user/user")
public class WxUserController extends BaseController
{
    @Autowired
    private IWxUserService wxUserService;

    /**
     * 查询wxuser列表
     */
    @PreAuthorize("@ss.hasPermi('user:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxUser wxUser)
    {
        startPage();
        List<WxUser> list = wxUserService.selectWxUserList(wxUser);
        return getDataTable(list);
    }

    /**
     * 导出wxuser列表
     */
    @PreAuthorize("@ss.hasPermi('user:user:export')")
    @Log(title = "wxuser", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxUser wxUser)
    {
        List<WxUser> list = wxUserService.selectWxUserList(wxUser);
        ExcelUtil<WxUser> util = new ExcelUtil<WxUser>(WxUser.class);
        util.exportExcel(response, list, "wxuser数据");
    }

    /**
     * 获取wxuser详细信息
     */
    @PreAuthorize("@ss.hasPermi('user:user:query')")
    @GetMapping(value = "/{openId}")
    public AjaxResult getInfo(@PathVariable("openId") String openId)
    {
        return success(wxUserService.selectWxUserByOpenId(openId));
    }

    /**
     * 新增wxuser
     */
    @PreAuthorize("@ss.hasPermi('user:user:add')")
    @Log(title = "wxuser", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxUser wxUser)
    {
        return toAjax(wxUserService.insertWxUser(wxUser));
    }

    /**
     * 修改wxuser
     */
    @PreAuthorize("@ss.hasPermi('user:user:edit')")
    @Log(title = "wxuser", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WxUser wxUser)
    {
        return toAjax(wxUserService.updateWxUser(wxUser));
    }

    /**
     * 删除wxuser
     */
    @PreAuthorize("@ss.hasPermi('user:user:remove')")
    @Log(title = "wxuser", businessType = BusinessType.DELETE)
	@DeleteMapping("/{openIds}")
    public AjaxResult remove(@PathVariable String[] openIds)
    {
        return toAjax(wxUserService.deleteWxUserByOpenIds(openIds));
    }

    //生成图片并传给服务器
    @PostMapping("/wx/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        try {
            // 保存文件到服务器，你可以根据需要更改保存路径
            String uploadDir = "C:\\Users\\16789\\Desktop\\总文件\\项目\\Cargo-ship-carrying-visualization-platform-system\\";
            File dest = new File(uploadDir + file.getOriginalFilename());
            file.transferTo(dest);

            // 在这里处理其他业务逻辑，比如将文件路径保存到数据库

            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("File upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//验证微信用户身份。生成令牌
    @PostMapping("/wx/getwxuser")
    public AjaxResult get_loginuser(@RequestBody Code code) throws Exception {
        System.out.println(code);
        if(code.getCode()==null||code.getCode().length()==0)
        {
            return AjaxResult.error();
        }
        List list=wxUserService.parseWxUser(code);
        String openId= (String) list.get(0);
        if (wxUserService.selectWxUserByOpenId(openId)==null)
        {
            WxUser myLoginUser=new WxUser();
            myLoginUser.setOpenId(openId);
            myLoginUser.setUserName("微信用户");
            myLoginUser.setWalletBalance(0L);
            wxUserService.insertWxUser(myLoginUser);
        }
        Map<String , Object> claims = new HashMap<>();
        claims.put("openId",openId);
        //使用JWT工具类，生成身份令牌
        String token = JwtUtils.generateJwt(claims);
        code.setToken(token);
        return AjaxResult.success(code);
    }

    //获取wx用户具体信息
    @PostMapping( "/wx/getwxinfo")
    public AjaxResult getWxInfo(@RequestHeader("token") String token)
    {
        try {
            Claims claims=JwtUtils.parseJWT(token);
            String openId=claims.get("openId",String.class);
            return success(wxUserService.selectWxUserByOpenId(openId));
        }catch (Exception e)
        {
            e.printStackTrace();
            return AjaxResult.error("令牌失效，需要重新授权");
        }
    }
}
