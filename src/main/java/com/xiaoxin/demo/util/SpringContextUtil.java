package com.xiaoxin.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author fuqiangxin
 * @Title:
 * @Description: 绕过aop 。重新走缓存
 * @date 2020/3/2516:03
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private SpringContextUtil() {
    }

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> tclass) {
        return applicationContext.getBean(tclass);
    }
}
