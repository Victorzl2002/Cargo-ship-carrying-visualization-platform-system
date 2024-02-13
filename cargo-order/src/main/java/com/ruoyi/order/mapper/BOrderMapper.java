package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.BOrder;

/**
 * orderMapper接口
 * 
 * @author victorzl
 * @date 2023-12-30
 */
public interface BOrderMapper 
{
    /**
     * 查询order
     * 
     * @param orderId order主键
     * @return order
     */
    public BOrder selectBOrderByOrderId(String orderId);

    public BOrder selectBOrderByCargoId(Long cargoId);

    /**
     * 查询order列表
     * 
     * @param bOrder order
     * @return order集合
     */
    public List<BOrder> selectBOrderList(BOrder bOrder);

    /**
     * 新增order
     * 
     * @param bOrder order
     * @return 结果
     */
    public int insertBOrder(BOrder bOrder);

    /**
     * 修改order
     * 
     * @param bOrder order
     * @return 结果
     */
    public int updateBOrder(BOrder bOrder);

    /**
     * 删除order
     * 
     * @param orderId order主键
     * @return 结果
     */
    public int deleteBOrderByOrderId(String orderId);

    /**
     * 批量删除order
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBOrderByOrderIds(String[] orderIds);
}
