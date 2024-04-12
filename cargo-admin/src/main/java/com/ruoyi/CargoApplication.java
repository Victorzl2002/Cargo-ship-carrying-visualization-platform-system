package com.ruoyi;

import com.ruoyi.web.controller.terminal1.TerminalController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author Victorzl
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class CargoApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(CargoApplication.class, args);
        startTerminalController();
        System.out.println("                                  ___                                     ,--,    \n" +
                "       ,---.  ,--,              ,--.'|_                                 ,--.'|    \n" +
                "      /__./|,--.'|              |  | :,'   ,---.    __  ,-.       ,----,|  | :    \n" +
                " ,---.;  ; ||  |,               :  : ' :  '   ,'\\ ,' ,'/ /|     .'   .`|:  : '    \n" +
                "/___/ \\  | |`--'_       ,---. .;__,'  /  /   /   |'  | |' |  .'   .'  .'|  ' |    \n" +
                "\\   ;  \\ ' |,' ,'|     /     \\|  |   |  .   ; ,. :|  |   ,',---, '   ./ '  | |    \n" +
                " \\   \\  \\: |'  | |    /    / ':__,'| :  '   | |: :'  :  /  ;   | .'  /  |  | :    \n" +
                "  ;   \\  ' .|  | :   .    ' /   '  : |__'   | .; :|  | '   `---' /  ;--,'  : |__  \n" +
                "   \\   \\   ''  : |__ '   ; :__  |  | '.'|   :    |;  : |     /  /  / .`||  | '.'| \n" +
                "    \\   `  ;|  | '.'|'   | '.'| ;  :    ;\\   \\  / |  , ;   ./__;     .' ;  :    ; \n" +
                "     :   \\ |;  :    ;|   :    : |  ,   /  `----'   ---'    ;   |  .'    |  ,   /  \n" +
                "      '---\" |  ,   /  \\   \\  /   ---`-'                    `---'         ---`-'   \n" +
                "             ---`-'    `----'                                                     \n");
    }
    private static void startTerminalController() {
        TerminalController terminalController = new TerminalController();
        terminalController.init();
    }
}
