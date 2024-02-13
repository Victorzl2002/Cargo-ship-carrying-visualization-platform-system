package com.ruoyi.user.service;

import java.util.List;

import com.ruoyi.user.domain.Code;
import com.ruoyi.user.domain.WxUser;

/**
 * wxuserService接口
 * 
 * @author victorzl
 * @date 2023-12-03
 */
public interface IWxUserService 
{
    /**
     * 查询wxuser
     * 
     * @param openId wxuser主键
     * @return wxuser
     */
    public WxUser selectWxUserByOpenId(String openId);

    /**
     * 查询wxuser列表
     * 
     * @param wxUser wxuser
     * @return wxuser集合
     */
    public List<WxUser> selectWxUserList(WxUser wxUser);

    /**
     * 新增wxuser
     * 
     * @param wxUser wxuser
     * @return 结果
     */
    public int insertWxUser(WxUser wxUser);

    /**
     * 修改wxuser
     * 
     * @param wxUser wxuser
     * @return 结果
     */
    public int updateWxUser(WxUser wxUser);

    /**
     * 批量删除wxuser
     * 
     * @param openIds 需要删除的wxuser主键集合
     * @return 结果
     */
    public int deleteWxUserByOpenIds(String[] openIds);

    /**
     * 删除wxuser信息
     * 
     * @param openId wxuser主键
     * @return 结果
     */
    public int deleteWxUserByOpenId(String openId);

    public List parseWxUser(Code code) throws Exception;
}
