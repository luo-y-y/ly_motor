package com.cat.sy.service.impl;

/// ***********************import begin***********************
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.cat.common.bean.tree.easyUI.IconTree;
import com.cat.common.exception.RequestException;
import com.cat.common.json.RJson;
import com.cat.common.lang.RDate;
import com.cat.common.lang.RList;
import com.cat.common.lang.RString;
import com.cat.common.lang.RUuid;
import com.cat.sy.bean.SyCatalogNode;
import com.cat.sy.dao.SyCatalogNodeDao;
import com.cat.sy.service.SyCatalogNodeService;

/// ***********************import end*************************
@Service("syCatalogNodeService")
public class SyCatalogNodeServiceImpl
      implements
         SyCatalogNodeService{

   public Logger _logger = Logger.getLogger(this.getClass());

   private static String IconJson = "";

   @Autowired
   private SyCatalogNodeDao syCatalogNodeDao;

   /// ***********************define begin***********************
   /// ***********************define end*************************
   @Override
   public Integer save(SyCatalogNode syCatalogNode){
      if(null == syCatalogNode){
         _logger.info("save syCatalogNode  is empty");
         return null;
      }
      syCatalogNode.setUuid(RUuid.makeUuid());
      syCatalogNode.setCreateTime(RDate.getCurrentTime());
      Map<String, Object> map = syCatalogNodeDao.save(syCatalogNode);
      return Integer.parseInt(map.get("id").toString());
   }

   @Override
   public boolean delete(Integer id){
      if(null == id){
         _logger.info("delete id is empty");
         return false;
      }
      SyCatalogNode entity = load(id);
      if(null == entity){
         _logger.info("delete record not exist.");
         return false;
      }
      Integer result = syCatalogNodeDao.delete(id);
      return result > 0;
   }

   @Override
   @Transactional(rollbackFor = Exception.class)
   public boolean deleteByIds(String ids){
      if(RString.isBlank(ids)){
         _logger.info("deleteByIds ids is empty");
         return false;
      }
      String[] array = ids.split(",");
      List<Integer> list = new ArrayList<Integer>();
      for(String id : array){
         list.add(Integer.parseInt(id));
      }
      Integer result = syCatalogNodeDao.deleteByIds(list);
      return result > 0;
   }

   @Override
   public boolean update(SyCatalogNode syCatalogNode){
      if(null == syCatalogNode){
         _logger.info("update  is empty  syCatalogNode");
         return false;
      }
      Integer id = syCatalogNode.getId();
      if(null == id){
         _logger.info("update  is empty syCatalogNode.id");
         return false;
      }
      Integer result = syCatalogNodeDao.update(syCatalogNode);
      return result > 0;
   }

   @Override
   public RResult update(SyCatalogNode oldEntity,
                         SyCatalogNode newEntity){
      if(null == newEntity){
         _logger.info("update param is empty");
         return new RResult(RResult.MSG_FAIL, RResult.paramNull, "entity");
      }

      Integer result = syCatalogNodeDao.update(newEntity);
      if(result > 0){
         return new RResult(RResult.MSG_SUCCESS);
      }
      return new RResult(RResult.MSG_FAIL);
   }

   @Override
   public SyCatalogNode load(Integer id){
      if(null == id){
         _logger.info("load id is empty");
         return null;
      }
      SyCatalogNode syCatalogNode = new SyCatalogNode();
      syCatalogNode.setId(id);
      return syCatalogNodeDao.load(syCatalogNode);
   }

   @Override
   public SyCatalogNode load(SyCatalogNode syCatalogNode){
      if(null == syCatalogNode){
         _logger.info("load  is empty syCatalogNode");
         return null;
      }
      return syCatalogNodeDao.load(syCatalogNode);
   }

   @Override
   public Integer loadCount(SyCatalogNode syCatalogNode){
      return syCatalogNodeDao.loadCount(syCatalogNode);
   }

   @Override
   public boolean isExist(SyCatalogNode syCatalogNode){
      Integer count = loadCount(syCatalogNode);
      if(null == count){
         return false;
      }
      return count.intValue() > 0;
   }

   @Override
   public List<SyCatalogNode> findList(SyCatalogNode syCatalogNode,
                                       Integer skip,
                                       Integer max,
                                       Map<String, Object> params){
      return syCatalogNodeDao.findList(syCatalogNode, skip, max, params);
   }

   @Override
   public PageControlInfo findPageList(SyCatalogNode syCatalogNode,
                                       Integer skip,
                                       Integer max,
                                       Map<String, Object> params){
      return syCatalogNodeDao.findPageList(syCatalogNode, skip, max, params);
   }

   /// ***********************method begin***********************
   @Override
   public List<SyCatalogNode> findNode1(String linkCatalog){
      /*List<SyCatalogNode> list = syCatalogNodeDao.findNode(linkCatalog);
      if(RList.isBlank(list)){
         _logger.info( "findNode list is empty.");
         return null;
      }
      return list;*/
      return null;
   }

   @Override
   public List<SyCatalogNode> findChildrensNode(String catalogId){
      _logger.info("findChildrensNode params: [catalogId=" + catalogId + "]");
      List<SyCatalogNode> list = syCatalogNodeDao.findChildrensNode(catalogId);
      if(RList.isBlank(list)){
         _logger.info("findChildrensNode list is empty.");
         return null;
      }
      return list;
   }

   @Override
   public List<SyCatalogNode> findChildNodes(String parentId){

      // 只显示有效的
      List<SyCatalogNode> list = syCatalogNodeDao.findChildNode(parentId, "Y");
      if(RList.isBlank(list)){
         _logger.info("findChildNodes list is empty.");
         return null;
      }
      return list;
   }

   @Override
   public List<SyCatalogNode> findAllChildNodes(String parentId){
      // 全部显示
      List<SyCatalogNode> list = syCatalogNodeDao.findChildNode(parentId, null);
      if(RList.isBlank(list)){
         _logger.info("findChildNodes list is empty.");
         return null;
      }
      return list;
   }

   @Override
   public SyCatalogNode findNodeById(Integer id){
      // TODO Auto-generated method stub
      SyCatalogNode syCatalogNode = new SyCatalogNode();
      syCatalogNode.setId(id);
      return syCatalogNodeDao.load(syCatalogNode);
   }

   /**
    * 遍历子节点
    * @param path 根路径
    * @return
    */
   public List<IconTree> getFile(StringBuffer buff,
                                 String path){
      try{
         List<IconTree> list = new ArrayList<IconTree>();
         File file = new File(path);
         String name = file.getName();
         //获取所有的文件和文件夹
         File[] fs = file.listFiles();
         for(File f2 : fs){
            if(f2.isFile()){
               //是文件
               list.add(creatTree(buff, name, f2));
            }
         }
         return list;
      }catch(Exception e){
         // TODO: handle exception
      }
      return null;
   }

   private IconTree creatTree(StringBuffer buff,
                              String headName,
                              File file){
      IconTree t = new IconTree();
      String name = headName + "-" + file.getName();
      if(file.isDirectory()){
         t.setState("closed");
         name = file.getName();
      }else{
         name = headName + "-" + file.getName();
         name = RString.left(name, ".");
         t.setIconCls(name);
         if(null != buff){
            buff.append(".").append(name).append("{ background: url(\"../images/sys/").append(headName).append("/").append(file.getName()).append("\") no-repeat center center; }\r\n");
         }
      }
      t.setCode(name);
      t.setId(name);
      t.setText(name);
      return t;
   }

   /// ***********************method end*************************

   @Override
   public String getAllIconImgJson(String basePath,
                                   String cssFilePath){
      if(RString.isNotBlank(IconJson)){
         return IconJson;
      }
      try{
         IconJson = getIconJson(basePath, cssFilePath);
      }catch(RequestException e){
         e.printStackTrace();
      }
      return IconJson;
   }

   private String getIconJson(String basePath,
                              String cssFilePath)
         throws RequestException{
      File file = new File(basePath);
      if(file.isFile()){
         return "";
      }
      List<IconTree> tree = new ArrayList<IconTree>();
      StringBuffer cssBuffer = new StringBuffer();
      //获取所有子文件夹
      File[] rootFile = file.listFiles();
      for(File childFFile : rootFile){
         if(childFFile.isDirectory()){
            //是文件夹 创建节点
            IconTree pTree = creatTree(null, "", childFFile);
            //遍历子文件
            List<IconTree> list = getFile(cssBuffer, childFFile.getAbsolutePath());
            if(null != list){
               pTree.setChildren(list);
            }
            tree.add(pTree);
         }
      }
      try{
         createCssStyle(cssBuffer.toString(), cssFilePath);
      }catch(Exception e){
         // TODO: handle exception
      }
      //      return   RJson.getJsonArray(tree).toString();
      return RJson.getJsonStr(tree);
   }

   private void createCssStyle(String cssInfo,
                               String filePath)
         throws Exception{
      File file = new File(filePath);
      if(!file.exists()){
         file.getParentFile().mkdirs();// 创建父目录
         file.createNewFile();
      }
      FileWriter desWriter = new FileWriter(file);
      desWriter.write(cssInfo);
      desWriter.close();
   }
}
