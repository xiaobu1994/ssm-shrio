package com.xiaobu.base.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author xiaobu
 * @date 2018/12/19 11:37
 * @descprition  用浏览器打开默认网站页面
 * @version 1.0
 */
@Component
@Slf4j
public class MyCommandRunner implements CommandLineRunner {
    @Value("${spring.web.loginurl}")
    private String loginUrl;

    @Value("${spring.web.googleexcute}")
    private String googleExcutePath;

    @Value("${spring.auto.openurl}")
    private boolean isOpen;

    @Override
    public void run(String... args) throws Exception {
        if(isOpen){
            String cmd = googleExcutePath +" "+ loginUrl;
            Runtime run = Runtime.getRuntime();
            try{
                run.exec(cmd);
                log.debug("启动浏览器打开项目成功");
            }catch (Exception e){
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
    }
}
