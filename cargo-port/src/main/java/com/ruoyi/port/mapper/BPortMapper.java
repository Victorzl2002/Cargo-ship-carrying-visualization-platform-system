package com.ruoyi.port.mapper;

import java.util.List;
import com.ruoyi.port.domain.BPort;

/**
 * portMapper接口
 * 
 * @author victorzl
 * @date 2023-12-06
 */
public interface BPortMapper 
{
    /**
     * 查询port
     * 
     * @param portId port主键
     * @return port
     */
    public BPort selectBPortByPortId(Long portId);

    /**
     * 查询port列表
     * 
     * @param bPort port
     * @return port集合
     */
    public List<BPort> selectBPortList(BPort bPort);

    /**
     * 新增port
     * 
     * @param bPort port
     * @return 结果
     */
    public int insertBPort(BPort bPort);

    /**
     * 修改port
     * 
     * @param bPort port
     * @return 结果
     */
    public int updateBPort(BPort bPort);

    /**
     * 删除port
     * 
     * @param portId port主键
     * @return 结果
     */
    public int deleteBPortByPortId(Long portId);

    /**
     * 批量删除port
     * 
     * @param portIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBPortByPortIds(Long[] portIds);
}
