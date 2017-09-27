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
import com.cat.sy.bean.SyUser;

/**
 * 对Web请求的日志切面
 * @author luoyang
 *
 */
@Aspect
@Component
public class WebHandAspect{

   
   //@Pointcut("execution(* com..*.action..*Action.*(..))")
   @Pointcut("execution(* com..*.action.web..*Action.*(..)) && !execution(*  com..*.action.web..*BaseAction.*(..))")
   public void actionHander(){
   }

   @Around("actionHander()")
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
         map.put("reqData", dataMap);
         String datagrm = "";
         datagrm = RJson.getJsonStr(map);
         // 记录后台请求报文日志
         LogHelper.webRequestLog(datagrm);
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
         LogHelper.webExceptionResponseLog(RString.toString(t.getStackTrace()));
         throw t;
      }
      // 记录返回报文
      /*   String str ="";
         if(null != o){
            str = o.toString();
         }else{
            HttpServletResponse response =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            System.out.println(response.getBufferSize());
            System.out.println(response.getCharacterEncoding());
            System.out.println(response.getContentType());
         }
         System.out.println(str);
         ExtLogHelper.webResponseLog(seq,RDate.getCurrentTimeStr(), str.toString().length()>200?str.substring(0, 200):str);
       */// 返回被拦截的调用
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
