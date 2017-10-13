package com.luoy.motor.action.ext.motor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cat.aspect.Anno;
import com.cat.common.bean.EMsg;
import com.cat.common.bean.PageControlInfo;
import com.cat.common.lang.RDate;
import com.cat.common.lang.RString;
import com.cat.msg.RMsgResponse;
import com.luoy.motor.action.base.ExtBaseAction;
import com.luoy.motor.action.ext.define.ExtActionName;
import com.luoy.motor.bean.motor.MoEvaluate;
import com.luoy.motor.service.motor.MoEvaluateService;
import com.luoy.motor.service.motor.MoMotorService;

@Controller
@Scope("prototype")
public class MoEvaluateExtAction
      extends ExtBaseAction{

   @Autowired
   private MoEvaluateService moEvaluateService;
   
   @Autowired
   private MoMotorService moMotorService;

   @RequestMapping(value = ExtActionName.Evaluate.Evaluate_Add)
   @Anno("添加评价")
   @ResponseBody
   public RMsgResponse extEvaluateAdd(){
      try{
         String pid = RString.toString(params.get("pid"));
         String content = RString.toString(params.get("content"));
         String tel = RString.toString(params.get("tel"));
       
         if(RString.isBlank(pid, content,tel,userId+"")){
            return new RMsgResponse(EMsg.Param_NotFount.code(), EMsg.Param_NotFount.value());
         }

         MoEvaluate eva = new MoEvaluate();
         eva.setPid(Integer.parseInt(pid));
         eva.setTel(tel);
         eva.setUserId(userId);
         eva.setContent(content);
         eva.setCreateTime(RDate.getCurrentTime());
        
         Integer t = moEvaluateService.save(eva);
         if(t > 0){
            eva.setId(t);
            return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value(), eva);
         }
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }

   @RequestMapping(value = ExtActionName.Evaluate.Evaluate_Delete)
   @Anno("删除用户评价")
   @ResponseBody
   public RMsgResponse extMotorOff(){
      try{
         String id = RString.toString(params.get("id"));

         if(RString.isBlank(id)){
            return new RMsgResponse(EMsg.Param_NotFount.code(), EMsg.Param_NotFount.value());
         }
         Integer i = Integer.parseInt(id);
         boolean t = moEvaluateService.delete(i);
         if(t){
            return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value());
         }
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }

   @RequestMapping(value = ExtActionName.Evaluate.Evaluate_FindPageByMo)
   @Anno("查询摩托的评价")
   @ResponseBody
   public RMsgResponse extEvaluateFindPageByMo(){
      try{
         String pid = RString.toString(params.get("pid"));
         String rowsStr = RString.toString(params.get("rows"));
         String pageStr = RString.toString(params.get("page"));
         if(RString.isBlank(pid)){
            return new RMsgResponse(EMsg.Param_NotFount.code(), EMsg.Param_NotFount.value());
         }
         Integer rows = 20;
         Integer page = 1;
         try{
            rows = Integer.parseInt(rowsStr);
         }catch(Exception e){
         }
         try{
            page = Integer.parseInt(pageStr);
         }catch(Exception e){
         }
         
         MoEvaluate load = new MoEvaluate();
         load.setPid(Integer.parseInt(pid));
         PageControlInfo t = moEvaluateService.findPageList(load, page, rows, null);
         return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value(),t);
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }

   @RequestMapping(value = ExtActionName.Evaluate.Evaluate_FindPageByUser)
   @Anno("查询用戶的的评价")
   @ResponseBody
   public RMsgResponse extMotorOn(){
      try{
         String rowsStr = RString.toString(params.get("rows"));
         String pageStr = RString.toString(params.get("page"));
        
         if(RString.isBlank(userId+"")){
            return new RMsgResponse(EMsg.Param_NotFount.code(), EMsg.Param_NotFount.value());
         }
         
         Integer rows = 20;
         Integer page = 1;
         try{
            rows = Integer.parseInt(rowsStr);
         }catch(Exception e){
         }
         try{
            page = Integer.parseInt(pageStr);
         }catch(Exception e){
         }
         Map<String, Object> m = new HashMap<String, Object>();
         m.put("collectUserId", userId);
         PageControlInfo t = moMotorService.findPageListForEva(userId, page, rows, m);
         return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value(),t);
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }
}
