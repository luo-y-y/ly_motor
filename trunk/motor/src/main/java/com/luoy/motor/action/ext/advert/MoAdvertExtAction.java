package com.luoy.motor.action.ext.advert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cat.aspect.Anno;
import com.cat.common.bean.EMsg;
import com.cat.msg.RMsgResponse;
import com.luoy.motor.action.base.ExtBaseAction;
import com.luoy.motor.action.ext.define.ExtActionName;
import com.luoy.motor.bean.advert.MoAdvert;
import com.luoy.motor.service.advert.MoAdvertService;

@Controller
@Scope("prototype")
public class MoAdvertExtAction
      extends ExtBaseAction{

   @Autowired
   private MoAdvertService moAdvertService;

   @RequestMapping(value = ExtActionName.Advert.Advert_List)
   @Anno("获取广告页")
   @ResponseBody
   public RMsgResponse extTelSendCode(){
      try{
         List<MoAdvert> list = moAdvertService.findListForApp();
         return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value(), list);
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }
}
