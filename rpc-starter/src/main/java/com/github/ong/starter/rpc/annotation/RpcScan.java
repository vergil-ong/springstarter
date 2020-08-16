package com.github.ong.starter.rpc.annotation;


import com.github.ong.starter.rpc.registar.RpcBeanDefinitionRegistar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(RpcBeanDefinitionRegistar.class)
public @interface RpcScan {

    String basePackage() default "";
}
