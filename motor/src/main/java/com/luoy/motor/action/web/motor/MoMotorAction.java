package com.luoy.motor.action.web.motor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cat.aspect.Anno;
import com.cat.common.bean.EOperation;
import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.cat.common.exception.RequestException;
import com.cat.common.lang.RString;
import com.cat.common.reflect.RReflectUtils;
import com.cat.sy.action.base.WebBaseAction;
import com.cat.sy.bean.SyUser;
import com.luoy.motor.bean.motor.MoMotor;
import com.luoy.motor.service.motor.MoMotorService;

@RequestMapping("mo/motor")
@Controller
@Scope("prototype")
public class MoMotorAction extends WebBaseAction{
   
   @Autowired
   private MoMotorService moMotorService;
   /**
    * 请求访问路径
    */
   private static final String requestPath = "motor/";
   

   /**
    * 主页面
    */
   @RequestMapping("goMain")
   public String goMain(){
      setAttributes();
      return requestPath+"moMotorList";
   }
   
   /**
    * 新增页面
    */
   @RequestMapping("goAdd")
   public String goAdd(){
      setAttributes();
      request.setAttribute("operate", EOperation.Add.value());
      return requestPath+"moMotorForm";
   }

   /**
    * 查看页面
    */
   @RequestMapping("goView")
   public String goView(){
      setAttributes();
      request.setAttribute("operate", EOperation.View.value());
      return requestPath+"moMotorForm";
   }

   /**
    * 编辑页面
    */
   @RequestMapping("goEdit")
   public String goEdit(){
      setAttributes();
      request.setAttribute("operate", EOperation.Update.value());
      return requestPath+"moMotorForm";
   }

   
   /**
    * 保存/更新
    */
   @RequestMapping("doSave")
   public void doSave() throws RequestException{
      try {
         SyUser syUser = getSessionUser();
         String id = getRequestParameter("id");
         if(RString.isBlank(id) || id.equals("0")){
            Map<String, Object> map = getRequestParameterMap();
            MoMotor entity = new MoMotor();
            RReflectUtils.getObjectForMap(entity, map);
            save(entity);
            return;
         }
         update(id, syUser);
      } catch (Exception e) {
         _logger.error("doSave", e);
         printWriterJson(new RResult(RResult.MSG_FAIL));
      }
   }

   /**
    * 保存逻辑
    */
   private void save(MoMotor entity) throws RequestException{
      Integer id = moMotorService.save(entity);
      if(id >0){
          printWriterJson(new RResult(RResult.MSG_SUCCESS));
         return;
      }
      _logger.info("save save fail");
       printWriterJson(new RResult(RResult.MSG_FAIL));
   }

   /**
    * 修改逻辑
    */
   private void update(String id, SyUser syUser) throws RequestException{
      MoMotor oldEntity = moMotorService.load(Integer.parseInt(id));
      if(null == oldEntity) {
         _logger.info("update  fail record not exist, param[id="+id+"]");
         printWriterJson(new RResult(RResult.MSG_FAIL,RResult.paramNull));
         return;
      }
      MoMotor newEntity = new MoMotor();
      RReflectUtils.getObjectForObject(newEntity, oldEntity);
      Map<String, Object> map = getRequestParameterMap();
      RReflectUtils.getObjectForMap(newEntity, map);
     
      RResult info = moMotorService.update(newEntity);
      printWriterJson(info);
   }

   /**
    * 删除
    */
   @RequestMapping("doDelete")
   public void doDelete(){
      try {
         String ids = getRequestParameter("ids");
         if(RString.isBlank(ids)){
            printWriterJson(new RResult(RResult.MSG_FAIL,RResult.paramNull));
            _logger.info("doDelete  ids is empty");
            return;
         }
          boolean bool = moMotorService.delete(Integer.parseInt(ids));
          if(bool){
            printWriterJson(new RResult(RResult.MSG_SUCCESS));
         }else{
            printWriterJson(new RResult(RResult.MSG_FAIL));
         }
      } catch (Exception e) {
         _logger.error("doDelete", e);
      }
   }

   /**
    * 根据ID加载记录
    */
   @RequestMapping("load")
   public void load() throws RequestException{
      String id = getRequestParameter("id");
      _logger.info("load  id="+id);
      if(RString.isBlank(id)){
          printWriterJson(new RResult(RResult.MSG_FAIL,RResult.paramNull));
         _logger.info( "load  is empty id");
         return;
      }
      MoMotor entity = moMotorService.load(Integer.parseInt(id));
      printWriterJson(entity);
   }
   
   /**
    * 列表查询
    */
   @RequestMapping("findPageList")
   public void findPageList(){
      try {
         Map<String,Object> map = getRequestParameterMap();
         MoMotor entity = new MoMotor();
         RReflectUtils.getObjectForMap(entity, map);
         PageControlInfo pageInfo = moMotorService.findPageList(entity, getPage(), getEnd(), loadOsearch());
         printWriterJson(pageInfo);
      } catch (Exception e) {
         _logger.error("findPageList", e);
      }
   }

   
   @RequestMapping("updateIsHot")
   @Anno("取消热门")
   @ResponseBody
   public void updateIsHot(){
      try{
         String id = getRequestParameter("id");
         String isHot = getRequestParameter("isHot");
         if(RString.isBlank(id,isHot)){
            printWriterJson(new RResult(RResult.MSG_FAIL,RResult.paramNull));
            return;
         }
         Integer i = Integer.parseInt(id);
         boolean t = moMotorService.updateIsHot(i, isHot);
         if(t){
            printWriterJson(new RResult(RResult.MSG_SUCCESS));
         }else{
            printWriterJson(new RResult(RResult.MSG_FAIL));
         }
      }catch(Exception e){
         e.printStackTrace();
      }
   }
   

   @RequestMapping("updateStatus")
   @Anno("修改状态")
   @ResponseBody
   public void updateStatus(){
      try{
         String id = getRequestParameter("id");
         String status = getRequestParameter("status");
         if(RString.isBlank(id,status)){
            printWriterJson(new RResult(RResult.MSG_FAIL,RResult.paramNull));
            return;
         }
         Integer i = Integer.parseInt(id);
         boolean t = moMotorService.updateStatus(i, status);
         if(t){
            printWriterJson(new RResult(RResult.MSG_SUCCESS));
         }else{
            printWriterJson(new RResult(RResult.MSG_FAIL));
         }
      }catch(Exception e){
         e.printStackTrace();
      }
   }
}
