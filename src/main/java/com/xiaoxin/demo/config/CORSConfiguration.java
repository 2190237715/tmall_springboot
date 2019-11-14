package com.xiaoxin.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName CORSConfiguration--处理跨域问题
 * @createDate 2019/11/5 19:48
 */
@Configuration
public class CORSConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

}
