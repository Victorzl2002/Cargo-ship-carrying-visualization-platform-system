package com.ruoyi.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.user.domain.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.user.mapper.WxUserMapper;
import com.ruoyi.user.domain.WxUser;
import com.ruoyi.user.service.IWxUserService;
import org.springframework.web.client.RestTemplate;

/**
 * wxuserService业务层处理
 * 
 * @author victorzl
 * @date 2023-12-03
 */
@Service
public class WxUserServiceImpl implements IWxUserService 
{
    @Autowired
    private WxUserMapper wxUserMapper;

    /**
     * 查询wxuser
     * 
     * @param openId wxuser主键
     * @return wxuser
     */
    @Override
    public WxUser selectWxUserByOpenId(String openId)
    {
        return wxUserMapper.selectWxUserByOpenId(openId);
    }

    /**
     * 查询wxuser列表
     * 
     * @param wxUser wxuser
     * @return wxuser
     */
    @Override
    public List<WxUser> selectWxUserList(WxUser wxUser)
    {
        return wxUserMapper.selectWxUserList(wxUser);
    }

    /**
     * 新增wxuser
     * 
     * @param wxUser wxuser
     * @return 结果
     */
    @Override
    public int insertWxUser(WxUser wxUser)
    {
        return wxUserMapper.insertWxUser(wxUser);
    }

    /**
     * 修改wxuser
     * 
     * @param wxUser wxuser
     * @return 结果
     */
    @Override
    public int updateWxUser(WxUser wxUser)
    {
        return wxUserMapper.updateWxUser(wxUser);
    }

    /**
     * 批量删除wxuser
     * 
     * @param openIds 需要删除的wxuser主键
     * @return 结果
     */
    @Override
    public int deleteWxUserByOpenIds(String[] openIds)
    {
        return wxUserMapper.deleteWxUserByOpenIds(openIds);
    }

    /**
     * 删除wxuser信息
     * 
     * @param openId wxuser主键
     * @return 结果
     */
    @Override
    public int deleteWxUserByOpenId(String openId)
    {
        return wxUserMapper.deleteWxUserByOpenId(openId);
    }

    @Override
    public List parseWxUser(Code code) throws Exception {
        String appID="wx1ef81a311cac7214";
        String appSecret="ed49994ce0c10d42ec15a6b7c2f0cfae";
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={code}&grant_type=authorization_code";
        Map<String, String> params = new HashMap<>();
        params.put("appid", appID);
        params.put("secret", appSecret);
        params.put("code", code.getCode());
        String response = restTemplate.getForObject(url, String.class, params);
// 解析响应结果，获取session_key和openid
        JSONObject jsonResponse = JSONObject.parseObject(response);
        String sessionKey = jsonResponse.getString("session_key");
        String openid = jsonResponse.getString("openid");
        List list=new ArrayList<>();
        list.add(openid);
        return list;
    }
}
