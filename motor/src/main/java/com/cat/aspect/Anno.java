package com.cat.aspect;  
  
import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  

/**
 * 做日志切面需要用到的注解类
 * 在需要些备注的地方加入注解
 * @author luoyang
 *
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)  
public @interface Anno {  
    public String value();  
}