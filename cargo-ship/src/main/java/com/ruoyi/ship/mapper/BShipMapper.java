package com.ruoyi.ship.mapper;

import java.util.List;
import com.ruoyi.ship.domain.BShip;

/**
 * 船舶管理Mapper接口
 * 
 * @author victorzl
 * @date 2023-12-16
 */
public interface BShipMapper 
{
    /**
     * 查询船舶管理
     * 
     * @param shipId 船舶管理主键
     * @return 船舶管理
     */
    public BShip selectBShipByShipId(Long shipId);

    /**
     * 查询船舶管理列表
     * 
     * @param bShip 船舶管理
     * @return 船舶管理集合
     */
    public List<BShip> selectBShipList(BShip bShip);

    /**
     * 新增船舶管理
     * 
     * @param bShip 船舶管理
     * @return 结果
     */
    public int insertBShip(BShip bShip);

    /**
     * 修改船舶管理
     * 
     * @param bShip 船舶管理
     * @return 结果
     */
    public int updateBShip(BShip bShip);

    /**
     * 删除船舶管理
     * 
     * @param shipId 船舶管理主键
     * @return 结果
     */
    public int deleteBShipByShipId(Long shipId);

    /**
     * 批量删除船舶管理
     * 
     * @param shipIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBShipByShipIds(Long[] shipIds);
}
