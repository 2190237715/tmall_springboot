package com.xiaoxin.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName GloabalExceptionHandler--父类异常处理
 * @createDate 2019/11/5 19:53
 */
@RestController
@ControllerAdvice
public class GloabalExceptionHandler {
    public String defaultErrorHandler(HttpServletRequest request, Exception e) throws ClassNotFoundException {
        e.printStackTrace();
        Class constrain = Class.forName("org.hibernate.exception.ConstraintViolationException");
        if (null != e.getCause() && constrain == e.getCause().getClass()) {
            return "违反了约束，多半是外键约束";
        }
        return e.getMessage();
    }
}
