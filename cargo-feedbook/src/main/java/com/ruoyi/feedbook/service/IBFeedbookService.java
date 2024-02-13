package com.ruoyi.feedbook.service;

import java.util.List;
import com.ruoyi.feedbook.domain.BFeedbook;

/**
 * 用户反馈Service接口
 * 
 * @author victorzl
 * @date 2024-01-09
 */
public interface IBFeedbookService 
{
    /**
     * 查询用户反馈
     * 
     * @param feedId 用户反馈主键
     * @return 用户反馈
     */
    public BFeedbook selectBFeedbookByFeedId(Long feedId);

    /**
     * 查询用户反馈列表
     * 
     * @param bFeedbook 用户反馈
     * @return 用户反馈集合
     */
    public List<BFeedbook> selectBFeedbookList(BFeedbook bFeedbook);

    /**
     * 新增用户反馈
     * 
     * @param bFeedbook 用户反馈
     * @return 结果
     */
    public int insertBFeedbook(BFeedbook bFeedbook);

    /**
     * 修改用户反馈
     * 
     * @param bFeedbook 用户反馈
     * @return 结果
     */
    public int updateBFeedbook(BFeedbook bFeedbook);

    /**
     * 批量删除用户反馈
     * 
     * @param feedIds 需要删除的用户反馈主键集合
     * @return 结果
     */
    public int deleteBFeedbookByFeedIds(Long[] feedIds);

    /**
     * 删除用户反馈信息
     * 
     * @param feedId 用户反馈主键
     * @return 结果
     */
    public int deleteBFeedbookByFeedId(Long feedId);
}
