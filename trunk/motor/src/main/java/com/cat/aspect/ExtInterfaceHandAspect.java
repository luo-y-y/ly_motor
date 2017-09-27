package com.cat.aspect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cat.common.json.RJson;
import com.cat.common.lang.RString;
import com.cat.interceptor.ThreadLocalHelper;
import com.cat.sy.bean.SyUser;

/**
 * 对外第三方的接口请求的日志切面
 * @author luoyang
 *
 */
@Aspect
@Component
public class ExtInterfaceHandAspect{

   //@Pointcut("execution(* com..*.action..*Action.*(..)) && !execution(*  com..*.action.ext..*Action.*(..))")
   //@Pointcut("execution(*  com..*.action.ext..*Action.*(..))")
   @Pointcut("execution(*  com..*.action.ext..*Action.*(..)) && !execution(*  com..*.action.ext..*BaseAction.*(..))")
   public void extInterHander(){
   }

   @Around("extInterHander()")
   public Object doActionClient(ProceedingJoinPoint pjp)
         throws Throwable{
      try{
         //做好异常处理，避免记录日志造成请求不正确
         HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
         String ip = request.getRemoteAddr();
         String userName = "";
         HttpSession session = request.getSession();
         if(null != session){
            SyUser sy = (SyUser) session.getAttribute("syUser");//登录用户
            if(null != sy){
               userName = sy.getUserName();
            }
         }
         
         @SuppressWarnings("unchecked")
         Map<String, Object> dataMap = request.getParameterMap();
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("userName", userName);
         map.put("methon", pjp.getSignature().getName());
         map.put("methonName", getControllerMethodDescription(pjp));
         map.put("className", pjp.getSignature().getDeclaringTypeName());
         map.put("ip", ip);
         String reqestStr = ThreadLocalHelper.getRequestStr();
         if(RString.isNotBlank(reqestStr)){
            //解密后的数据不为空
            map.put("afterEncrpt",reqestStr );
         }else{
            //不同时日志记录 加密和解密的数据，以免日志过大
            map.put("beforEncrpt", dataMap);
         }
         String datagrm = RJson.getJsonStr(map);
         // 记录后台请求报文日志
         LogHelper.interRequestLog(datagrm);
      }catch(Exception e){
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      // 异常处理
      Object o = null;
      try{
         o = pjp.proceed();
      }catch(Throwable t){
    	  t.printStackTrace();
         // 记录返回报文
         LogHelper.interExceptionResponseLog(t.getMessage());
         throw t;
      }
      
      // 记录返回报文
      try{
         String str = "";
         if(null != o){
            str = RJson.getJsonStr(o);
         }
         LogHelper.interResponseLog(str);

      }catch(Exception e){
         e.printStackTrace();
      }
      //线程池会复用，所以清理掉
      ThreadLocalHelper.remove();
      return o;

   }

   /**  
    * 获取注解中对方法的描述信息 用于Controller层注解  
    *  
    * @param joinPoint 切点  
    * @return 方法描述  
    * @throws Exception  
    */
   @SuppressWarnings("rawtypes")
   public static String getControllerMethodDescription(JoinPoint joinPoint)
         throws Exception{
      String targetName = joinPoint.getTarget().getClass().getName();
      String methodName = joinPoint.getSignature().getName();
      Object[] arguments = joinPoint.getArgs();
      Class<?> targetClass = Class.forName(targetName);
      Method[] methods = targetClass.getMethods();
      String description = "";
      for(Method method : methods){
         if(method.getName().equals(methodName)){
            Class[] clazzs = method.getParameterTypes();
            if(clazzs.length == arguments.length){
               Anno n = method.getAnnotation(Anno.class);
               if(null != n){
                  description = n.value();
               }
               break;
            }
         }
      }
      return description;
   }

}
