package com.ruoyi;

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
}