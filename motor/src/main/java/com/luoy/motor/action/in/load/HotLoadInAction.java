package com.luoy.motor.action.in.load;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cat.aspect.Anno;
import com.cat.common.bean.EMsg;
import com.cat.common.bean.FMsgResponse;
import com.cat.common.lang.RString;
import com.cat.common.listener.RSystemConfig;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.luoy.motor.action.in.define.InActionName;

@Controller
@Scope("prototype")
public class HotLoadInAction{

   public Logger _logger = Logger.getLogger(this.getClass());

   /**
    * 请求
    */
   protected HttpServletRequest request;

   /**
    * 响应
    */
   protected HttpServletResponse response;

   @ModelAttribute
   public void setReqAndRes(HttpServletRequest request,
                            HttpServletResponse response)
         throws JsonParseException, JsonMappingException, IOException{
      this.request = request;
      this.response = response;
   }

   @RequestMapping(value = InActionName.Load.In_Hot_Load_Conf)
   @Anno("热加载env.xml配置文件")
   @ResponseBody
   public FMsgResponse selectMsg(){
      String  password = request.getParameter("password");
      String hotPassword = RSystemConfig.getValue("hotUpdateEnvPass");
      String sysName = RSystemConfig.SysUserName;
      String sysIp = RSystemConfig.SysIp;
      String note = "IP:"+sysIp+",sysName:"+sysName;
      if(RString.isBlank(password)){
         return new FMsgResponse(EMsg.Fail.code(), "热加载密码不能为空,"+note, null);
      }
      if(RString.isBlank(hotPassword)){
         return new FMsgResponse(EMsg.Fail.code(), "不允许热更新,"+note, null);
      }
      if(!hotPassword.equalsIgnoreCase(password)){
         return new FMsgResponse(EMsg.Fail.code(), "热更新密码错误1,"+note, null);
      }
      Map<String, String> f =  RSystemConfig.reLoadEnv();
      if(f != null){
         return new FMsgResponse(EMsg.Success.code(), "热更新env.xml成功,"+note, f);
      }else{
         return new FMsgResponse(EMsg.Fail.code(), "热更新env.xml失败,"+note, null);
      }
   }

}
