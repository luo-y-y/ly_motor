package com.cat.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.cat.common.json.RJson;
import com.cat.common.lang.RString;

/**
 * 对外部webservice请求的日志切面
 * @author luoyang
 *
 */
@Aspect
@Component
public class WsdlAspect{

   @Pointcut("execution(* com.cat.common.wsdl.WsdlClient.*(..))")
   public void service(){
   }

   @Around("service()")
   public Object doServiceClient(ProceedingJoinPoint pjp)
         throws Throwable{

      try{
         Object[] args = pjp.getArgs();
         String datagrm = "";
         if(null != args){
            datagrm = RJson.getJsonStr(args);
         }
         // 记录后台请求报文日志
         LogHelper.wsdlRequestLog(datagrm);
      }catch(Exception e){
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      // 异常处理
      Object o = null;
      try{
         o = pjp.proceed();
      }catch(Throwable t){
         // 记录返回报文
         LogHelper.wsdlExceptionResponseLog(RString.toString(t.getStackTrace()));
         throw t;
      }
      try{
         // 记录返回报文
         String str = o==null?"":o.toString();
         LogHelper.wsdlResponseLog(str);
      }catch(Exception e){
         e.printStackTrace();
      }
      // 返回被拦截的调用
      return o;

   }

}
