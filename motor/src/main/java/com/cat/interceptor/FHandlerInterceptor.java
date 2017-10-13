package com.cat.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cat.common.bean.EMsg;
import com.cat.common.bean.RConfig;
import com.cat.common.exception.RequestException;
import com.cat.common.json.RJson;
import com.cat.common.lang.RString;
import com.cat.common.listener.RSystemConfig;
import com.cat.common.safe.RAESOperator;
import com.cat.msg.RMsg;
import com.cat.msg.RMsgResponse;
import com.luoy.motor.bean.user.MoUser;
import com.luoy.motor.bean.user.MoVersion;
import com.luoy.motor.service.user.MoUserService;
import com.luoy.motor.service.user.MoVersionService;

/**
 * Spring拦截器
 */
public class FHandlerInterceptor
      extends HandlerInterceptorAdapter{

   private Logger _logger = Logger.getLogger(this.getClass());

   private static final List<String> noLoginMethod = Arrays.asList(new String[]{"extTelSendCode","extTelMsgLogin","extTelPassLogin","extFileuUpload",
         "extMotorFindPage","extMotorLoad"});

   @Autowired
   private MoUserService moUserService;
   
   @Autowired
   private MoVersionService moVersionService;
   
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
     try{
         String appId = request.getHeader("appId");
         String userId = request.getHeader("userId");
         String token = request.getHeader("token");
         String url = request.getRequestURI();
         String method = RString.right(url, "/");
         if(RString.endsWith(method, ".ext")){
            method = RString.removeString(method, ".ext");
         }
         
         // APPID 不能为空
         if(RString.isBlank(appId)){
            printWriterJson(response,new RMsgResponse(EMsg.App_Id_NUll.code(), EMsg.App_Id_NUll.value()));
            return false;
         }
         ThreadLocalHelper.setVale("appId", appId);
         String sysType = RString.right(appId, ".");
         MoVersion version = moVersionService.getMoVersionByType(sysType);
         if(null != version){
            ThreadLocalHelper.setVale("version", version.getVersionName());
            ThreadLocalHelper.setVale("versionCode", version.getVersionCode());
            ThreadLocalHelper.setVale("isForce", version.getIsForce());
            ThreadLocalHelper.setVale("dowloadUrl", version.getUrl());
         }
         
         /**
          * 加密密钥
          */
         String aecKey = RSystemConfig.getValue(appId);
         if(RString.isBlank(aecKey)){
            printWriterJson(response,new RMsgResponse(EMsg.App_Id_Wrong.code(), EMsg.App_Id_Wrong.value()));
            return false;
         }
         
         String requestData = RString.toString(request.getParameter(RConfig.KEY_REQUEST));
         if(RString.isBlank(requestData)) {
            return true;
         }

         //需要加密的密钥
         String aesOpenAppIds = RSystemConfig.getValue(RConfig.KEY_AESADDID);
         if(RString.contains(aesOpenAppIds, appId)) {
            requestData = RAESOperator.getInstance().Decrypt(requestData, aecKey);
         }
//         //将解密后的请求放入线程缓存中。
         ThreadLocalHelper.setRequestStr(requestData);
         if(RString.isBlank(requestData)){
            // 密文不为空，解密后还是未空，
            printWriterJson(response,new RMsgResponse(EMsg.Aec_Wrong.code(), EMsg.Aec_Wrong.value()));
            return false;
         }
         
         if(noLoginMethod.contains(method)){
            //不需要登录的方法  不校验token
            return true;
         }
         if(RString.isBlank(userId,token)){
            //用户没有登录
            printWriterJson(response,new RMsgResponse(RMsg.Motor_Toekn_NUll.code(), RMsg.Motor_Toekn_NUll.value()));
            return false;
         }
         int userIdInt = 0;
         try{
             userIdInt = Integer.parseInt(userId);
         }catch(Exception e){
            printWriterJson(response,new RMsgResponse(EMsg.Fail.code(), "用户编号格式不正确"));
            return false;
         }
         MoUser loadUser = moUserService.load(userIdInt);
         if(null == loadUser){
            printWriterJson(response,new RMsgResponse(EMsg.Fail.code(), "用户编号不存在"));
            return false;
         }
         String dbToken = loadUser.getToken();
         if(RString.isBlank(dbToken)){
            //登录过期
            printWriterJson(response,new RMsgResponse(RMsg.Motor_Toekn_TimeOut.code(), RMsg.Motor_Toekn_TimeOut.value()));
            return false;
         }
         
         if(!dbToken.equalsIgnoreCase(token)){
            //登录被T下线
            printWriterJson(response,new RMsgResponse(RMsg.Motor_Toekn_Error.code(), RMsg.Motor_Toekn_Error.value()));
            return false;
         }
         return true;
      }catch(Exception e){
         _logger.error("parseToken", e);
      }
     
      return true;
   }

   
   public void printWriterJson(HttpServletResponse response,RMsgResponse msg) throws RequestException {
      if(null == msg){
         getPrintWriter(response).write("{}");
         return;
      }
      getPrintWriter(response).write(RJson.getJsonStr(msg));
   }
   
  /**
    * 写出
    * @param response
    * @return
    */
   private PrintWriter getPrintWriter(HttpServletResponse response){
      PrintWriter out = null;
      try{
         response.setHeader("Content-Type", "application/json;charset=UTF-8");
         out = response.getWriter();
      }catch(IOException e){
         _logger.error(e);
      }
      return out;
   }
   
}
