package com.ruoyi.terminalmanage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.terminalmanage.mapper.BTerminalMapper;
import com.ruoyi.terminalmanage.domain.BTerminal;
import com.ruoyi.terminalmanage.service.IBTerminalService;

/**
 * terminalmanageService业务层处理
 * 
 * @author victorzl
 * @date 2024-04-07
 */
@Service
public class BTerminalServiceImpl implements IBTerminalService 
{
    @Autowired
    private BTerminalMapper bTerminalMapper;

    /**
     * 查询terminalmanage
     * 
     * @param terminalId terminalmanage主键
     * @return terminalmanage
     */
    @Override
    public BTerminal selectBTerminalByTerminalId(String terminalId)
    {
        return bTerminalMapper.selectBTerminalByTerminalId(terminalId);
    }

    /**
     * 查询terminalmanage列表
     * 
     * @param bTerminal terminalmanage
     * @return terminalmanage
     */
    @Override
    public List<BTerminal> selectBTerminalList(BTerminal bTerminal)
    {
        return bTerminalMapper.selectBTerminalList(bTerminal);
    }

    /**
     * 新增terminalmanage
     * 
     * @param bTerminal terminalmanage
     * @return 结果
     */
    @Override
    public int insertBTerminal1(BTerminal bTerminal)
    {
        return bTerminalMapper.insertBTerminal1(bTerminal);
    }

    /**
     * 修改terminalmanage
     * 
     * @param bTerminal terminalmanage
     * @return 结果
     */
    @Override
    public int updateBTerminal(BTerminal bTerminal)
    {
        return bTerminalMapper.updateBTerminal(bTerminal);
    }

    /**
     * 批量删除terminalmanage
     * 
     * @param terminalIds 需要删除的terminalmanage主键
     * @return 结果
     */
    @Override
    public int deleteBTerminalByTerminalIds(String[] terminalIds)
    {
        return bTerminalMapper.deleteBTerminalByTerminalIds(terminalIds);
    }

    /**
     * 删除terminalmanage信息
     * 
     * @param terminalId terminalmanage主键
     * @return 结果
     */
    @Override
    public int deleteBTerminalByTerminalId(String terminalId)
    {
        return bTerminalMapper.deleteBTerminalByTerminalId(terminalId);
    }
}
