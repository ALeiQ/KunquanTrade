package com.sdut.trade.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类描述：拦截器注解的声明
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/26
 */
@Target({ElementType.METHOD})           // 可以用在方法上
@Retention(RetentionPolicy.RUNTIME)     // 注解再代码运行时起作用
public @interface LoginRequired {
}
