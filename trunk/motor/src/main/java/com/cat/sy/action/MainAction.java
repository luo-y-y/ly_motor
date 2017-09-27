package com.cat.sy.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cat.common.bean.tree.Node;
import com.cat.common.exception.RequestException;
import com.cat.common.lang.RList;
import com.cat.common.lang.RString;
import com.cat.common.reflect.RReflectUtils;
import com.cat.sy.action.base.WebBaseAction;
import com.cat.sy.bean.SyCatalogNode;
import com.cat.sy.bean.SyUser;
import com.cat.sy.service.SyCatalogNodeService;
import com.cat.sy.service.SyRoleUserService;

@RequestMapping("sy/main")
@Controller
@Scope("prototype")
public class MainAction extends WebBaseAction{

   @Autowired
   private SyCatalogNodeService syCatalogNodeService;
   
   @Autowired
   private SyRoleUserService syRoleUserService;
   
  // @Autowired
   //private SyUserService pmUserService;
   
   @RequestMapping("goBaiduMap")
   public String goBaiduMap(){
      setAttributes();
      return "sy/main/baiduMap";
   }
   
   @RequestMapping("center")
   public String center(){
      String id = getRequestParameter("id");
      String introduceUrl = getRequestParameter("introduceUrl");
      request.setAttribute("id", id);
      request.setAttribute("introduceUrl", introduceUrl);
      return "sy/main/center";
   }

   /**
    * 介绍页面
    * @return
    */
   @RequestMapping("goIntroduce")
   public String goIntroduce(){
      return "sy/main/introduce";
   }

   /**
    * 加载主菜单
 * @throws JsonProcessingException 
    */
   @RequestMapping(value="loadMenu")
   public void loadMenu() throws RequestException{
      String id = getRequestParameter("id");
      List<SyCatalogNode> nodes = syCatalogNodeService.findChildNodes(id);
      if(RList.isBlank(nodes)){
         return;
      }
      List<Node> list = new ArrayList<Node>();
      SyUser syUser = getSessionUser();
      String catalogPower = syRoleUserService.getCatalogPowerByUserId(syUser.getId());
      for(SyCatalogNode node : nodes){
         if(RString.contains(catalogPower, RString.toString(node.getId()))) {
            Node n = new Node();
            RReflectUtils.getObjectForObject(n, node);
            list.add(n);
         }
      }
      if(RList.isBlank(list)){
    	  return;
      }
      getTreeJson(list);
   }

   /**
    * 异步树
 * @throws RequestException 
    */
   @RequestMapping(value="loadSubMenu")
   public void loadSubMenu() throws RequestException{
      String parentId = getRequestParameter("parentId");
      String id = getRequestParameter("id");
      _logger.info("loadSubMenu param: [parentId="+parentId+", id= "+id+"]");
      if(StringUtils.isBlank(parentId)){
         return;
      }
      List<SyCatalogNode> nodes = null;
      if(null == id){
         nodes = syCatalogNodeService.findChildNodes(parentId);
      }else{
         nodes = syCatalogNodeService.findChildNodes(id);
      }
      if(RList.isBlank(nodes)){
         return;
      }
      SyUser pmUser = getSessionUser();
      String catalogPower = syRoleUserService.getCatalogPowerByUserId(pmUser.getId());
      List<Node> list = new ArrayList<Node>();
      for(SyCatalogNode node : nodes){
         if(RString.contains(catalogPower, RString.toString(node.getId()))) {
            Node n = new Node();
            RReflectUtils.getObjectForObject(n, node);
            list.add(n);
         }
      }
      getTreeJson(list);
   }

   /**
    * 加载后台管理菜单
 * @throws RequestException 
    */
   @RequestMapping("loadTabs")
   public void loadTabs() throws RequestException{
    
      //根目录的ID 为0
      List<SyCatalogNode> nodes = syCatalogNodeService.findChildNodes("0");
      if(RList.isBlank(nodes)){
         return;
      }
      List<Node> data = new ArrayList<Node>();
      SyUser pmUser = getSessionUser();
      String catalogPower = syRoleUserService.getCatalogPowerByUserId(pmUser.getId());
      for(SyCatalogNode node : nodes){
         if(RString.contains(catalogPower, RString.toString(node.getId()))) {
            Node n = new Node();
            RReflectUtils.getObjectForObject(n, node);
            data.add(n);
         }
      }
      getTreeJson(data);
   }
   
   /**
    * 待处理页面
    */
   @RequestMapping("doPending")
   public String doPending(){
      String typeCd = getRequestParameter("typeCd");
      request.setAttribute("typeCd", typeCd);
      return "sy/main/pending";
   }
}
