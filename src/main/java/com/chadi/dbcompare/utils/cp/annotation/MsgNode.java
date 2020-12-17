package com.chadi.dbcompare.utils.cp.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @ClassName MsgNode
 * @Description TODO
 * @Author XuDong
 * @Date 2020/12/17 10:04
 * @Version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
public @interface MsgNode {
    String name() default "";
}
