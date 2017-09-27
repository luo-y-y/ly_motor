package com.luoy.motor.action.ext.user;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cat.aspect.Anno;
import com.cat.common.bean.EMsg;
import com.cat.common.bean.RResult;
import com.cat.common.lang.RDate;
import com.cat.common.lang.RString;
import com.cat.common.safe.RDes;
import com.cat.common.safe.RMd5;
import com.cat.msg.RMsgResponse;
import com.luoy.motor.action.base.ExtBaseAction;
import com.luoy.motor.action.ext.define.ExtActionName;
import com.luoy.motor.bean.user.MoUser;
import com.luoy.motor.service.user.MoUserService;

@Controller
@Scope("prototype")
public class MoUserExtAction
      extends ExtBaseAction{

   @Autowired
   private MoUserService moUserService;

   @RequestMapping(value = ExtActionName.User.user_tel_send_code)
   @Anno("发送手机号验证码")
   @ResponseBody
   public RMsgResponse extTelSendCode(){
      try{
         String tel = RString.toString(params.get("tel"));
         
         if(RString.isBlank(tel)){
            return new RMsgResponse(EMsg.Param_NotFount.code(), EMsg.Param_NotFount.value());
         }
         //获取随机数
         String code = getTelCode();
         MoUser loadUser = moUserService.loadByTel(tel);
         boolean f = false;
         if(null == loadUser){
            //数据库没有该手机号 注册
            loadUser = new MoUser();
            loadUser.setTel(tel);
            loadUser.setStatus(MoUser.Status_Unact);
            loadUser.setMsgCode(code);
            loadUser.setMsgCodeTime(RDate.getCurrentTime());
            Integer id = moUserService.save(loadUser);
            if(id.intValue() > 0){
               f = true;
            }
         }else{
            //数据库已存在
            loadUser.setMsgCode(code);
            loadUser.setMsgCodeTime(RDate.getCurrentTime());
            RResult r = moUserService.update(loadUser);
            if(r.isSuccess()){
               f = true;
            }
         }

         if(f){
            //发送短信验证码
         }

         if(f){
            return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value());
         }else{
            return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
         }
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }

   @RequestMapping(value = ExtActionName.User.user_tel_msg_login)
   @Anno("用户手机号免密登录或注册带密码")
   @ResponseBody
   public RMsgResponse extTelMsgLogin(){
      try{
         String tel = RString.toString(params.get("tel"));
         String code = RString.toString(params.get("code"));
         String password = RString.toString(params.get("password"));
         
         if(RString.isBlank(tel,code)){
            return new RMsgResponse(EMsg.Param_NotFount.code(), EMsg.Param_NotFount.value());
         }

         MoUser loadUser = moUserService.loadByTel(tel);
         if(null == loadUser){
            return new RMsgResponse(EMsg.Fail.code(), "您还未发送验证码");
         }
         String dbCode = loadUser.getMsgCode();
         if(!isCodeTimeAct(loadUser.getMsgCodeTime())){
            return new RMsgResponse(EMsg.Fail.code(), "验证码已失效");
         }
         if(code.equalsIgnoreCase(dbCode) ){
            String status = loadUser.getStatus();
            if(MoUser.Status_Unact.equalsIgnoreCase(status)){
               //未激活的 需要激活
               loadUser.setStatus(MoUser.Status_On);
            }
            if(RString.isNotBlank(password)){
               loadUser.setPassword(RMd5.encode(password));
            }
            loadUser.setToken(getToken(loadUser.getId()));
            loadUser.setTokenTime(RDate.getCurrentTime());
            
            RResult r = moUserService.update(loadUser);
            if(!r.isSuccess()){
               return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
            }
            loadUser.setPassword(null);
            return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value(), loadUser);
         }else{
            return new RMsgResponse(EMsg.Fail.code(),"验证码不正确");
         }

      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }

   
   @RequestMapping(value = ExtActionName.User.user_tel_pass_login)
   @Anno("用户手机号密码登录")
   @ResponseBody
   public RMsgResponse extTelPassLogin(){
      try{
         String tel = RString.toString(params.get("tel"));
         String password = RString.toString(params.get("password"));
         
         if(RString.isBlank(tel,password)){
            return new RMsgResponse(EMsg.Param_NotFount.code(), EMsg.Param_NotFount.value());
         }
         
         MoUser loadUser = moUserService.loadByTel(tel);
         if(null == loadUser){
            return new RMsgResponse(EMsg.Fail.code(), "用户名不存在");
         }
         String status = loadUser.getStatus();
         if(MoUser.Status_lock.equalsIgnoreCase(status)){
            return new RMsgResponse(EMsg.Fail.code(), "用户已锁定，请使用短信登录！");
         }
         String dbpassword = loadUser.getPassword();
         if(RString.isBlank(dbpassword)){
            return new RMsgResponse(EMsg.Fail.code(), "您还未设置密码，请先设置密码！");
         }
         
         if(!RMd5.encode(password).equalsIgnoreCase(dbpassword)){
            return new RMsgResponse(EMsg.Fail.code(), "密码不正确！");
         }
         
         if(MoUser.Status_Unact.equalsIgnoreCase(status)){
            //未激活的 需要激活
            loadUser.setStatus(MoUser.Status_On);
         }
         loadUser.setToken(getToken(loadUser.getId()));
         loadUser.setTokenTime(RDate.getCurrentTime());
         
         RResult r = moUserService.update(loadUser);
         if(!r.isSuccess()){
            return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
         }
         loadUser.setPassword(null);
         return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value(), loadUser);

      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }
   
   
   @RequestMapping(value = ExtActionName.User.user_update_info)
   @Anno("完善个人信息")
   @ResponseBody
   public RMsgResponse extUpdateUserInfo(){
      try{
         String realName = RString.toString(params.get("realName"));
         String idCard = RString.toString(params.get("idCard"));
         String headUrl = RString.toString(params.get("headUrl"));
         
         if(RString.isAllBlank(realName,idCard,headUrl)){
            return new RMsgResponse(EMsg.Param_NotFount.code(), "参数不能为空");
         }
         
         MoUser loadUser = moUserService.load(userId);
         if(null == loadUser){
            return new RMsgResponse(EMsg.Fail.code(), "用户不存在");
         }
         String status = loadUser.getStatus();
         if(MoUser.Status_lock.equalsIgnoreCase(status)){
            return new RMsgResponse(EMsg.Fail.code(), "用户已锁定，请使用短信登录！");
         }
         
         if(RString.isNotBlank(headUrl)){
            loadUser.setHeadUrl(headUrl);
         }
         if(RString.isNotBlank(realName)){
            loadUser.setRealName(realName);
         }
         if(RString.isNotBlank(idCard)){
            loadUser.setIdCard(idCard);
         }
         loadUser.setUpdateTime(RDate.getCurrentTime());
         
         RResult r = moUserService.update(loadUser);
         if(!r.isSuccess()){
            return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
         }
         loadUser.setPassword(null);
         return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value(), loadUser);

      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }
   
   private String getTelCode(){
      //int a = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;
      //return a + "";
      return "1234";//没有短信接口，默认1234
   }

   private boolean isCodeTimeAct(Timestamp msgCodeTime){
      long t = new Date().getTime() - msgCodeTime.getTime();
      return t < 10 * 60 * 1000 ;//没有短信接口，默认1234
   }
   
   private  String getToken(Integer userId){
      String time = RDate.getCurrentTimeStr();
      String r = RString.toString((int)(Math.random()*10000));
      try{
         return RDes.encrypt("@"+time+"@"+userId+"@", "lluoyang20161228");
      }catch(Exception e){
         e.printStackTrace();
      }
      return r;
   }
   
}
