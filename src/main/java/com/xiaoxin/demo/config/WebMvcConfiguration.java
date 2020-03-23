package com.xiaoxin.demo.config;

import com.xiaoxin.demo.interceptor.LoginInterceptor;
import com.xiaoxin.demo.interceptor.OtherInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author fuqiangxin
 * @Title: 全局拦截器
 * @Description: 控制全局
 * @date 2020/3/2115:48
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Bean
    public LoginInterceptor getLoginIntercepter() {
        return new LoginInterceptor();
    }

    @Bean
    public OtherInterceptor getOtherInterceptor() {
        return new OtherInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String staticStr[] = {"/", "/login", "/login.html", "/home",
                "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg",
                "/**/*.jpeg", "/**/*.gif", "/**/fonts/*", "/**/*.svg"};
        registry.addInterceptor(getLoginIntercepter())
                .addPathPatterns("/**").excludePathPatterns(staticStr);
        registry.addInterceptor(getOtherInterceptor())
                .addPathPatterns("/**").excludePathPatterns(staticStr);
    }
}
