package com.ruoyi.feedbook.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.feedbook.mapper.BFeedbookMapper;
import com.ruoyi.feedbook.domain.BFeedbook;
import com.ruoyi.feedbook.service.IBFeedbookService;

/**
 * 用户反馈Service业务层处理
 * 
 * @author victorzl
 * @date 2024-01-09
 */
@Service
public class BFeedbookServiceImpl implements IBFeedbookService 
{
    @Autowired
    private BFeedbookMapper bFeedbookMapper;

    /**
     * 查询用户反馈
     * 
     * @param feedId 用户反馈主键
     * @return 用户反馈
     */
    @Override
    public BFeedbook selectBFeedbookByFeedId(Long feedId)
    {
        return bFeedbookMapper.selectBFeedbookByFeedId(feedId);
    }

    /**
     * 查询用户反馈列表
     * 
     * @param bFeedbook 用户反馈
     * @return 用户反馈
     */
    @Override
    public List<BFeedbook> selectBFeedbookList(BFeedbook bFeedbook)
    {
        return bFeedbookMapper.selectBFeedbookList(bFeedbook);
    }

    /**
     * 新增用户反馈
     * 
     * @param bFeedbook 用户反馈
     * @return 结果
     */
    @Override
    public int insertBFeedbook(BFeedbook bFeedbook)
    {
        return bFeedbookMapper.insertBFeedbook(bFeedbook);
    }

    /**
     * 修改用户反馈
     * 
     * @param bFeedbook 用户反馈
     * @return 结果
     */
    @Override
    public int updateBFeedbook(BFeedbook bFeedbook)
    {
        return bFeedbookMapper.updateBFeedbook(bFeedbook);
    }

    /**
     * 批量删除用户反馈
     * 
     * @param feedIds 需要删除的用户反馈主键
     * @return 结果
     */
    @Override
    public int deleteBFeedbookByFeedIds(Long[] feedIds)
    {
        return bFeedbookMapper.deleteBFeedbookByFeedIds(feedIds);
    }

    /**
     * 删除用户反馈信息
     * 
     * @param feedId 用户反馈主键
     * @return 结果
     */
    @Override
    public int deleteBFeedbookByFeedId(Long feedId)
    {
        return bFeedbookMapper.deleteBFeedbookByFeedId(feedId);
    }
}
