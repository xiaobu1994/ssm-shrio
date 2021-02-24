package com.xiaobu.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 自定义的异常页面配置
 */
@Component
public class ErrorPagesConfig {

    /**
     * 自定义异常处理路径
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        //第二种写法：java8 lambda写法
        return (factory -> {
            factory.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401"));
            factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403"));
            factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
            factory.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error/405"));
            factory.addErrorPages(new ErrorPage(Throwable.class, "/error/500"));
            factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
        });

    }


}
