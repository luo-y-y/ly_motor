package com.cat.sy.action;
/// ***********************import begin***********************
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cat.common.bean.EOperation;
import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.cat.common.exception.RequestException;
import com.cat.common.json.RJson;
import com.cat.common.lang.RString;
import com.cat.common.reflect.RReflectUtils;
import com.cat.sy.action.base.WebBaseAction;
import com.cat.sy.bean.SyCatalogRole;
import com.cat.sy.bean.SyUser;
import com.cat.sy.service.SyCatalogRoleService;
/// ***********************import end*************************
@RequestMapping("sy/CatalogRole")
@Controller
@Scope("prototype")
public class SyCatalogRoleAction
      extends WebBaseAction{
   
   @Autowired
   private SyCatalogRoleService syCatalogRoleService;
   /**
    * 请求访问路径
    */
   private static final String requestPath = "sy/catalog/";
   /// ***********************define begin***********************
   /**
    * 角色选择目录
    */
   @RequestMapping("goRoleForm")
   public String goRoleForm(){
      setAttributes();
      return requestPath+"roleForm";
   }
   /**
    * 角色绑定目录
    */
   @RequestMapping("goRoleCatalog")
   public String goRoleCatalog(){
      setAttributes();
      String roleId = RString.toString(this.getRequestParameter("roleId"));
      if(RString.isNotBlank(roleId)) {
         SyCatalogRole syCatalogRole = syCatalogRoleService.loadByRoleId(Integer.parseInt(roleId));
         if(null != syCatalogRole) {
            request.setAttribute("nodeIds", syCatalogRole.getNodeIds());
            request.setAttribute("id", syCatalogRole.getId());
         }
      }
      return requestPath+"roleCatalogForm";
   }
   /// ***********************define end*************************
   /**
    * 介绍页面
    */
   @RequestMapping("goIntroduce")
   public String goIntroduce(){
      setAttributes();
      return requestPath+"introduce";
   }

   /**
    * 主页面
    */
   @RequestMapping("goMain")
   public String goMain(){
      setAttributes();
      return requestPath+"syCatalogRoleList";
   }
   
   /**
    * 新增页面
    */
   @RequestMapping("goAdd")
   public String goAdd(){
      setAttributes();
      request.setAttribute("operate", EOperation.Add.value());
      return requestPath+"syCatalogRoleForm";
   }

   /**
    * 查看页面
    */
   @RequestMapping("goView")
   public String goView(){
      setAttributes();
      request.setAttribute("operate", EOperation.View.value());
      return requestPath+"syCatalogRoleForm";
   }

   /**
    * 编辑页面
    */
   @RequestMapping("goEdit")
   public String goEdit(){
      setAttributes();
      request.setAttribute("operate", EOperation.Update.value());
      return requestPath+"syCatalogRoleForm";
   }

   /**
    * 历史列表页面
    * @return
    */
   @RequestMapping("goViewHistory")
   public String goViewHistory(){
      setAttributes();
      return requestPath+"syCatalogRoleHsList";
   }
   
   /**
    * 历史页面
    * @return
    */
   @RequestMapping("goViewHistoryForm")
   public String goViewHistoryForm(){
      setAttributes();
      request.setAttribute("operate", EOperation.History.value());
      return requestPath+"syCatalogRoleForm";
   }
   
   /**
    * 保存/更新
 * @throws RequestException 
    */
   @RequestMapping("doSave")
   public void doSave() throws RequestException{
      try {
         SyUser pmUser = getSessionUser();
         String id = getRequestParameter("id");
         if(RString.isBlank(id) || id.equals("0")){
            Map<String, Object> map = getRequestParameterMap();
            SyCatalogRole entity = new SyCatalogRole();
            RReflectUtils.getObjectForMap(entity, map);
            entity.setCreateUserId(pmUser.getId());
            save(entity);
            return;
         }
         update(id, pmUser);
      } catch (Exception e) {
         printWriterJson(new RResult(RResult.MSG_FAIL));
      }
   }

   /**
    * 保存逻辑
 * @throws RequestException 
    */
   private void save(SyCatalogRole entity) throws RequestException{
      Integer id = syCatalogRoleService.save(entity);
      if(id >0){
    	  printWriterJson(new RResult(RResult.MSG_SUCCESS));
         return;
      }
      _logger.info( "save   save fail");
      printWriterJson(new RResult(RResult.MSG_FAIL));
   }

   /**
    * 修改逻辑
 * @throws RequestException 
    */
   private void update(String id, SyUser pmUser) throws RequestException{
      SyCatalogRole oldEntity = syCatalogRoleService.load(Integer.parseInt(id));
      if(null == oldEntity) {
         _logger.info("update fail record not exist, param[id={0}]"+ id);
         printWriterJson(new RResult(RResult.MSG_FAIL,RResult.paramNull));
         return;
      }
      SyCatalogRole newEntity = new SyCatalogRole();
      RReflectUtils.getObjectForObject(newEntity, oldEntity);
      Map<String, Object> map = getRequestParameterMap();
      RReflectUtils.getObjectForMap(newEntity, map);
      
      RResult info = syCatalogRoleService.update(oldEntity, newEntity);
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
	        return;
         }
         String[] values = RString.split(ids, ",");
         boolean bool = false;
         if(values.length > 1) {
            bool = syCatalogRoleService.deleteByIds(ids);
         } else {
            bool = syCatalogRoleService.delete(Integer.parseInt(ids));
         }
         if(bool){
        	 printWriterJson(new RResult(RResult.MSG_SUCCESS));
         }else{
        	 printWriterJson(new RResult(RResult.MSG_FAIL));
         }
      } catch (Exception e) {
         _logger.error(  e);
      }
   }

   /**
    * 根据ID加载记录
 * @throws RequestException 
    */
   @RequestMapping("load")
   public void load() throws RequestException{
      String id = getRequestParameter("id");
      _logger.info( "load id= "+id);
      if(RString.isBlank(id)){
    	  printWriterJson(new RResult(RResult.MSG_FAIL,RResult.paramNull));
         _logger.info( "load  is empty id");
         return;
      }
      SyCatalogRole entity = syCatalogRoleService.load(Integer.parseInt(id));
//      result = RJson.getJson(entity);
      getPrintWriter().write(RJson.getJsonStr(entity));
   }
   
   /**
    * 列表查询
    */
   @RequestMapping("findPageList")
   public void findPageList(){
      try {
         Map<String,Object> map = getRequestParameterMap();
         SyCatalogRole entity = new SyCatalogRole();
         RReflectUtils.getObjectForMap(entity, map);
         PageControlInfo pageInfo = syCatalogRoleService.findPageList(entity, getPage(), getEnd(), loadOsearch());
         printWriterJson(pageInfo);
      } catch (Exception e) {
         _logger.error(e);
      }
   }
   /// ***********************method begin***********************
   
   /// ***********************method end*************************

}
