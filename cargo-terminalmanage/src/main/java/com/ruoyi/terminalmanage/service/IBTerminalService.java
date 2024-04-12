package com.ruoyi.terminalmanage.service;

import java.util.List;
import com.ruoyi.terminalmanage.domain.BTerminal;

/**
 * terminalmanageService接口
 * 
 * @author victorzl
 * @date 2024-04-07
 */
public interface IBTerminalService 
{
    /**
     * 查询terminalmanage
     * 
     * @param terminalId terminalmanage主键
     * @return terminalmanage
     */
    public BTerminal selectBTerminalByTerminalId(String terminalId);

    /**
     * 查询terminalmanage列表
     * 
     * @param bTerminal terminalmanage
     * @return terminalmanage集合
     */
    public List<BTerminal> selectBTerminalList(BTerminal bTerminal);

    /**
     * 新增terminalmanage
     * 
     * @param bTerminal terminalmanage
     * @return 结果
     */
    public int insertBTerminal1(BTerminal bTerminal);

    /**
     * 修改terminalmanage
     * 
     * @param bTerminal terminalmanage
     * @return 结果
     */
    public int updateBTerminal(BTerminal bTerminal);

    /**
     * 批量删除terminalmanage
     * 
     * @param terminalIds 需要删除的terminalmanage主键集合
     * @return 结果
     */
    public int deleteBTerminalByTerminalIds(String[] terminalIds);

    /**
     * 删除terminalmanage信息
     * 
     * @param terminalId terminalmanage主键
     * @return 结果
     */
    public int deleteBTerminalByTerminalId(String terminalId);
}
