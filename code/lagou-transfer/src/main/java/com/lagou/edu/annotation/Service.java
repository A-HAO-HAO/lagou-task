package com.lagou.edu.annotation;

import java.lang.annotation.*;

@Target(value={ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {

    String name() default "";
}
