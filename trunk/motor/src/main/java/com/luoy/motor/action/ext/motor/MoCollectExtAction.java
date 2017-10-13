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
import com.luoy.motor.bean.motor.MoCollect;
import com.luoy.motor.service.motor.MoCollectService;
import com.luoy.motor.service.motor.MoMotorService;

@Controller
@Scope("prototype")
public class MoCollectExtAction
      extends ExtBaseAction{

   @Autowired
   private MoCollectService moCollectService;
   
   @Autowired
   private MoMotorService moMotorService;

   @RequestMapping(value = ExtActionName.Collect.Collect_Add)
   @Anno("添加收藏")
   @ResponseBody
   public RMsgResponse extCollectAdd(){
      try{
         String pid = RString.toString(params.get("pid"));
       
         if(RString.isBlank(pid,userId+"")){
            return new RMsgResponse(EMsg.Param_NotFount.code(), EMsg.Param_NotFount.value());
         }
         
         MoCollect c = new MoCollect();
         c.setPid(Integer.parseInt(pid));
         c.setUserId(userId);
         
         MoCollect loadc = moCollectService.load(c);
         if(null != loadc){
            return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value()); 
         }
         c.setCreateTime(RDate.getCurrentTime());
        
         Integer t = moCollectService.save(c);
         if(t > 0){
            c.setId(t);
            return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value(), c);
         }
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }

   @RequestMapping(value = ExtActionName.Collect.Collect_Delete)
   @Anno("删除用户收藏")
   @ResponseBody
   public RMsgResponse extCollectDelete(){
      try{
         String pid = RString.toString(params.get("pid"));

         if(RString.isBlank(pid)){
            return new RMsgResponse(EMsg.Param_NotFount.code(), EMsg.Param_NotFount.value());
         }
         Integer i = Integer.parseInt(pid);
         boolean t = moCollectService.deleteByPid(i, userId);
         if(t){
            return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value());
         }
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }

   @RequestMapping(value = ExtActionName.Collect.Collect_FindPageByUser)
   @Anno("查询个人收藏")
   @ResponseBody
   public RMsgResponse extCollectFindPage(){
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
         PageControlInfo t = moMotorService.findPageListForCollect(userId, page, rows, m);
         return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value(),t);
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }

}
