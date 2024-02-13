package com.ruoyi.route.mapper;

import java.util.List;
import com.ruoyi.route.domain.BRoute;

/**
 * routeMapper接口
 * 
 * @author victorzl
 * @date 2023-12-08
 */
public interface BRouteMapper 
{
    /**
     * 查询route
     * 
     * @param routeId route主键
     * @return route
     */
    public BRoute selectBRouteByRouteId(Long routeId);

    /**
     * 查询route列表
     * 
     * @param bRoute route
     * @return route集合
     */
    public List<BRoute> selectBRouteList(BRoute bRoute);

    /**
     * 新增route
     * 
     * @param bRoute route
     * @return 结果
     */
    public int insertBRoute(BRoute bRoute);

    /**
     * 修改route
     * 
     * @param bRoute route
     * @return 结果
     */
    public int updateBRoute(BRoute bRoute);

    /**
     * 删除route
     * 
     * @param routeId route主键
     * @return 结果
     */
    public int deleteBRouteByRouteId(Long routeId);

    /**
     * 批量删除route
     * 
     * @param routeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBRouteByRouteIds(Long[] routeIds);
}
