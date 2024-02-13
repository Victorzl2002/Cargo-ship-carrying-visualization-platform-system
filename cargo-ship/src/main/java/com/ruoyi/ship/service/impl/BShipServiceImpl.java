package com.ruoyi.ship.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ship.mapper.BShipMapper;
import com.ruoyi.ship.domain.BShip;
import com.ruoyi.ship.service.IBShipService;

/**
 * 船舶管理Service业务层处理
 * 
 * @author victorzl
 * @date 2023-12-16
 */
@Service
public class BShipServiceImpl implements IBShipService 
{
    @Autowired
    private BShipMapper bShipMapper;

    /**
     * 查询船舶管理
     * 
     * @param shipId 船舶管理主键
     * @return 船舶管理
     */
    @Override
    public BShip selectBShipByShipId(Long shipId)
    {
        return bShipMapper.selectBShipByShipId(shipId);
    }

    /**
     * 查询船舶管理列表
     * 
     * @param bShip 船舶管理
     * @return 船舶管理
     */
    @Override
    public List<BShip> selectBShipList(BShip bShip)
    {
        return bShipMapper.selectBShipList(bShip);
    }

    /**
     * 新增船舶管理
     * 
     * @param bShip 船舶管理
     * @return 结果
     */
    @Override
    public int insertBShip(BShip bShip)
    {
        return bShipMapper.insertBShip(bShip);
    }

    /**
     * 修改船舶管理
     * 
     * @param bShip 船舶管理
     * @return 结果
     */
    @Override
    public int updateBShip(BShip bShip)
    {
        return bShipMapper.updateBShip(bShip);
    }

    /**
     * 批量删除船舶管理
     * 
     * @param shipIds 需要删除的船舶管理主键
     * @return 结果
     */
    @Override
    public int deleteBShipByShipIds(Long[] shipIds)
    {
        return bShipMapper.deleteBShipByShipIds(shipIds);
    }

    /**
     * 删除船舶管理信息
     * 
     * @param shipId 船舶管理主键
     * @return 结果
     */
    @Override
    public int deleteBShipByShipId(Long shipId)
    {
        return bShipMapper.deleteBShipByShipId(shipId);
    }
}
