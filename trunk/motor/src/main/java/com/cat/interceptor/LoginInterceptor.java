package com.cat.interceptor;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Spring拦截器  后台管理拦截器
 * @author luoyang
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

   
   private static String[] urls = {"login.jsp","login.do"};
   
   //private static String extNoTokenLink = "Login";
  
   public boolean preHandle(HttpServletRequest request,
                            HttpServletResponse response,
                            Object handler)
         throws Exception{
     boolean bool = false;
     String url = request.getRequestURI();
     for(String s : urls){
       if(-1 != url.indexOf(s)){
         bool = true;
         return bool;
       }
     }
     
     
     if(!bool){
        bool = IsExistLoginUser(request);
     }
     
     if(!bool){
        redirectLogin(request, response);
     }
      return bool;
   }

   public boolean checkToken(HttpServletRequest request,HttpServletResponse response){
      
      //String token = request.getHeader("token");
     // String userId = request.getHeader("userId");
      
      try{
    	  /* PawnUser p = pawnUserService.load(RLong.toLong(userId));
         if(null == p){
          //数据库的为空
            JSONObject result = RJson.getJson(new FMsgResponse(Rmsg.USER_NOT_EXIST.getCode(), Rmsg.USER_NOT_EXIST.getValue()));
            response.getWriter().print(result);
            return false;
         }
         String dbToken = p.getToken();
         if(RString.isBlank(dbToken)){
            //数据库的为空
            JSONObject result = RJson.getJson(new FMsgResponse(Rmsg.SHOULD_BE_LOGIN.getCode(), Rmsg.SHOULD_BE_LOGIN.getValue()));
            response.getWriter().print(result);
            return false;
         }
         
         if(!dbToken.equalsIgnoreCase(token)){
            //和数据库不同
            JSONObject result = RJson.getJson(new FMsgResponse(Rmsg.USER_HAD_LOGIN.getCode(), Rmsg.USER_HAD_LOGIN.getValue()));
            response.getWriter().print(result);
            return false;
         }*/
         return true;
      }catch(Exception e){
         // TODO Auto-generated catch block
         e.printStackTrace();
         return false;
      }
     
   }
   private boolean IsExistLoginUser(HttpServletRequest request){
      HttpSession session = request.getSession();
      if(null == session){
         return false;
      }
      Object syUser = session.getAttribute("syUser");
      if(null == syUser){
         return false;
      }
      return true;
 }
   
   private void redirectLogin(HttpServletRequest request,
                              HttpServletResponse response)
         throws IOException{
      response.sendRedirect(request.getContextPath() + "/login.jsp");
   }
}
