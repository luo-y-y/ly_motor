
package com.luoy.motor.action.web.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cat.common.bean.EOperation;
import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.cat.common.exception.RequestException;
import com.cat.common.lang.RString;
import com.cat.common.reflect.RReflectUtils;
import com.cat.sy.action.base.WebBaseAction;
import com.cat.sy.bean.SyUser;
import com.luoy.motor.bean.user.MoVersion;
import com.luoy.motor.service.user.MoVersionService;

@RequestMapping("mo/version")
@Controller
@Scope("prototype")
public class MoVersionWebAction extends WebBaseAction{
   
   @Autowired
   private MoVersionService moVersionService;
   /**
    * 请求访问路径
    */
   private static final String requestPath = "version/";
   

   /**
    * 主页面
    */
   @RequestMapping("goMain")
   public String goMain(){
      setAttributes();
      return requestPath+"moVersionList";
   }
   
   /**
    * 新增页面
    */
   @RequestMapping("goAdd")
   public String goAdd(){
      setAttributes();
      request.setAttribute("operate", EOperation.Add.value());
      return requestPath+"moVersionForm";
   }

   /**
    * 查看页面
    */
   @RequestMapping("goView")
   public String goView(){
      setAttributes();
      request.setAttribute("operate", EOperation.View.value());
      return requestPath+"moVersionForm";
   }

   /**
    * 编辑页面
    */
   @RequestMapping("goEdit")
   public String goEdit(){
      setAttributes();
      request.setAttribute("operate", EOperation.Update.value());
      return requestPath+"moVersionForm";
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
            MoVersion entity = new MoVersion();
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
   private void save(MoVersion entity) throws RequestException{
      Integer id = moVersionService.save(entity);
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
      MoVersion oldEntity = moVersionService.load(Integer.parseInt(id));
      if(null == oldEntity) {
         _logger.info("update  fail record not exist, param[id="+id+"]");
         printWriterJson(new RResult(RResult.MSG_FAIL,RResult.paramNull));
         return;
      }
      MoVersion newEntity = new MoVersion();
      RReflectUtils.getObjectForObject(newEntity, oldEntity);
      Map<String, Object> map = getRequestParameterMap();
      RReflectUtils.getObjectForMap(newEntity, map);
     
      RResult info = moVersionService.update(oldEntity, newEntity);
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
          boolean bool = moVersionService.delete(Integer.parseInt(ids));
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
      MoVersion entity = moVersionService.load(Integer.parseInt(id));
      printWriterJson(entity);
   }
   
   /**
    * 列表查询
    */
   @RequestMapping("findPageList")
   public void findPageList(){
      try {
         Map<String,Object> map = getRequestParameterMap();
         MoVersion entity = new MoVersion();
         RReflectUtils.getObjectForMap(entity, map);
         PageControlInfo pageInfo = moVersionService.findPageList(entity, getPage(), getEnd(), loadOsearch());
         printWriterJson(pageInfo);
      } catch (Exception e) {
         _logger.error("findPageList", e);
      }
   }

}
