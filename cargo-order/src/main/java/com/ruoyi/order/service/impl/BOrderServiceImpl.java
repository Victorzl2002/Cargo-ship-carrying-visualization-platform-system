package com.ruoyi.order.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.BOrderMapper;
import com.ruoyi.order.domain.BOrder;
import com.ruoyi.order.service.IBOrderService;

/**
 * orderService业务层处理
 * 
 * @author victorzl
 * @date 2023-12-30
 */
@Service
public class BOrderServiceImpl implements IBOrderService 
{
    @Autowired
    private BOrderMapper bOrderMapper;

    /**
     * 查询order
     * 
     * @param orderId order主键
     * @return order
     */
    @Override
    public BOrder selectBOrderByOrderId(String orderId)
    {
        return bOrderMapper.selectBOrderByOrderId(orderId);
    }

    @Override
    public BOrder selectBOrderByCargoId(Long cargoId) {
        return bOrderMapper.selectBOrderByCargoId(cargoId);
    }


    /**
     * 查询order列表
     * 
     * @param bOrder order
     * @return order
     */
    @Override
    public List<BOrder> selectBOrderList(BOrder bOrder)
    {
        return bOrderMapper.selectBOrderList(bOrder);
    }

    /**
     * 新增order
     * 
     * @param bOrder order
     * @return 结果
     */
    @Override
    public int insertBOrder(BOrder bOrder)
    {
        bOrder.setCreateTime(DateUtils.getNowDate());
        return bOrderMapper.insertBOrder(bOrder);
    }

    /**
     * 修改order
     * 
     * @param bOrder order
     * @return 结果
     */
    @Override
    public int updateBOrder(BOrder bOrder)
    {
        return bOrderMapper.updateBOrder(bOrder);
    }

    /**
     * 批量删除order
     * 
     * @param orderIds 需要删除的order主键
     * @return 结果
     */
    @Override
    public int deleteBOrderByOrderIds(String[] orderIds)
    {
        return bOrderMapper.deleteBOrderByOrderIds(orderIds);
    }

    /**
     * 删除order信息
     * 
     * @param orderId order主键
     * @return 结果
     */
    @Override
    public int deleteBOrderByOrderId(String orderId)
    {
        return bOrderMapper.deleteBOrderByOrderId(orderId);
    }
}
