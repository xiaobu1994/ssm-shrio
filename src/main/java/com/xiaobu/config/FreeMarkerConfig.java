package com.xiaobu.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import com.xiaobu.tag.CustomTagDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * freemarker配置类
 */
@Configuration
public class FreeMarkerConfig {

    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    protected CustomTagDirective customTagDirective;

    /**
     * 添加自定义标签  setSharedVariablet会去寻找ftl的标签hwTag去获取相对应的模板
     */
    @PostConstruct
    public void setSharedVariable() {
        configuration.setSharedVariable("thwTag", customTagDirective);
        //shiro标签
        configuration.setSharedVariable("shiro", new ShiroTags());
    }
}
