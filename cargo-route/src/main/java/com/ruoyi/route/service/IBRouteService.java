package com.ruoyi.route.service;

import java.util.List;
import com.ruoyi.route.domain.BRoute;

/**
 * routeService接口
 * 
 * @author victorzl
 * @date 2023-12-08
 */
public interface IBRouteService 
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
     * 批量删除route
     * 
     * @param routeIds 需要删除的route主键集合
     * @return 结果
     */
    public int deleteBRouteByRouteIds(Long[] routeIds);

    /**
     * 删除route信息
     * 
     * @param routeId route主键
     * @return 结果
     */
    public int deleteBRouteByRouteId(Long routeId);
}
