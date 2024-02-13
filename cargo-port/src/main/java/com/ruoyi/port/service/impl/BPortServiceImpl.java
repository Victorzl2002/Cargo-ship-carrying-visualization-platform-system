package com.ruoyi.port.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.port.mapper.BPortMapper;
import com.ruoyi.port.domain.BPort;
import com.ruoyi.port.service.IBPortService;

/**
 * portService业务层处理
 * 
 * @author victorzl
 * @date 2023-12-06
 */
@Service
public class BPortServiceImpl implements IBPortService 
{
    @Autowired
    private BPortMapper bPortMapper;

    /**
     * 查询port
     * 
     * @param portId port主键
     * @return port
     */
    @Override
    public BPort selectBPortByPortId(Long portId)
    {
        return bPortMapper.selectBPortByPortId(portId);
    }

    /**
     * 查询port列表
     * 
     * @param bPort port
     * @return port
     */
    @Override
    public List<BPort> selectBPortList(BPort bPort)
    {
        return bPortMapper.selectBPortList(bPort);
    }

    /**
     * 新增port
     * 
     * @param bPort port
     * @return 结果
     */
    @Override
    public int insertBPort(BPort bPort)
    {
        return bPortMapper.insertBPort(bPort);
    }

    /**
     * 修改port
     * 
     * @param bPort port
     * @return 结果
     */
    @Override
    public int updateBPort(BPort bPort)
    {
        return bPortMapper.updateBPort(bPort);
    }

    /**
     * 批量删除port
     * 
     * @param portIds 需要删除的port主键
     * @return 结果
     */
    @Override
    public int deleteBPortByPortIds(Long[] portIds)
    {
        return bPortMapper.deleteBPortByPortIds(portIds);
    }

    /**
     * 删除port信息
     * 
     * @param portId port主键
     * @return 结果
     */
    @Override
    public int deleteBPortByPortId(Long portId)
    {
        return bPortMapper.deleteBPortByPortId(portId);
    }
}
