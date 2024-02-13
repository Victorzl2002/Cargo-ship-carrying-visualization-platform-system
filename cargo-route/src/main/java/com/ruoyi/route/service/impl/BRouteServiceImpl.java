package com.ruoyi.route.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.route.mapper.BRouteMapper;
import com.ruoyi.route.domain.BRoute;
import com.ruoyi.route.service.IBRouteService;

/**
 * routeService业务层处理
 * 
 * @author victorzl
 * @date 2023-12-08
 */
@Service
public class BRouteServiceImpl implements IBRouteService 
{
    @Autowired
    private BRouteMapper bRouteMapper;

    /**
     * 查询route
     * 
     * @param routeId route主键
     * @return route
     */
    @Override
    public BRoute selectBRouteByRouteId(Long routeId)
    {
        return bRouteMapper.selectBRouteByRouteId(routeId);
    }

    /**
     * 查询route列表
     * 
     * @param bRoute route
     * @return route
     */
    @Override
    public List<BRoute> selectBRouteList(BRoute bRoute)
    {
        return bRouteMapper.selectBRouteList(bRoute);
    }

    /**
     * 新增route
     * 
     * @param bRoute route
     * @return 结果
     */
    @Override
    public int insertBRoute(BRoute bRoute)
    {
        return bRouteMapper.insertBRoute(bRoute);
    }

    /**
     * 修改route
     * 
     * @param bRoute route
     * @return 结果
     */
    @Override
    public int updateBRoute(BRoute bRoute)
    {
        return bRouteMapper.updateBRoute(bRoute);
    }

    /**
     * 批量删除route
     * 
     * @param routeIds 需要删除的route主键
     * @return 结果
     */
    @Override
    public int deleteBRouteByRouteIds(Long[] routeIds)
    {
        return bRouteMapper.deleteBRouteByRouteIds(routeIds);
    }

    /**
     * 删除route信息
     * 
     * @param routeId route主键
     * @return 结果
     */
    @Override
    public int deleteBRouteByRouteId(Long routeId)
    {
        return bRouteMapper.deleteBRouteByRouteId(routeId);
    }
}
