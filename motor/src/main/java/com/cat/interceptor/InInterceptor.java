package com.cat.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cat.common.listener.RSystemConfig;

/**
 * Spring拦截器  后台管理拦截器
 * @author luoyang
 *
 */
public class InInterceptor
      extends HandlerInterceptorAdapter{

   public boolean preHandle(HttpServletRequest request,
                            HttpServletResponse response,
                            Object handler)
         throws Exception{
      RSystemConfig.setOrign(request, response);
      if(!RSystemConfig.isAllowMsg(request, response)){
         return false;
      }
      return checkToken(request, response);
   }

   private boolean checkToken(HttpServletRequest request,
                              HttpServletResponse response){
      return true;
   }

}
