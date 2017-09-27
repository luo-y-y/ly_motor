package com.cat.sy.service.impl;
/// ***********************import begin***********************
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.cat.common.lang.RDate;
import com.cat.common.lang.RString;
import com.cat.sy.bean.SyCatalogRole;
import com.cat.sy.dao.SyCatalogRoleDao;
import com.cat.sy.service.SyCatalogRoleService;

/// ***********************import end*************************
@Service("syCatalogRoleService")
public class SyCatalogRoleServiceImpl implements  SyCatalogRoleService{

	 public Logger _logger = Logger.getLogger(this.getClass());

	 
   @Autowired
   private SyCatalogRoleDao syCatalogRoleDao;

   /// ***********************define begin***********************
   /// ***********************define end*************************
   @Override
   public Integer save(SyCatalogRole syCatalogRole){
      if(null == syCatalogRole){
         _logger.info("save  is empty syCatalogRole");
         return null;
      }
      this.saveBefore(syCatalogRole);
      syCatalogRole.setCreateTime(RDate.getCurrentTime());
      Map<String, Object> map = syCatalogRoleDao.save(syCatalogRole);
      return Integer.parseInt(map.get("id").toString());
   }

   @Override
   public boolean delete(Integer id){
      if(null == id){
         _logger.info( "delete  is empty ");
         return false;
      }
      SyCatalogRole entity = load(id);
      this.deleteBefore(entity);
      if(null == entity) {
         _logger.info( "delete record not exist. id="+id);
         return false;
      }
      Integer result = syCatalogRoleDao.delete(id);
      this.deleteAfter(entity);
      return result > 0;
   }

   @Override
   public boolean deleteByIds(String ids){
      if(RString.isBlank(ids)){
         _logger.info( "deleteByIds ids is empty ");
         return false;
      }
      String[] array = ids.split(",");
      List<Integer> list = new ArrayList<Integer>();
      for(String id : array){
         list.add(Integer.parseInt(id));
      }
      Integer result = syCatalogRoleDao.deleteByIds(list);
      return result > 0;
   }

   @Override
   public boolean update(SyCatalogRole syCatalogRole){
      if(null == syCatalogRole){
         _logger.info("update  is empty syCatalogRole");
         return false;
      }
      Integer id = syCatalogRole.getId();
      if(null == id) {
         _logger.info( "update syCatalogRole.id is empty");
         return false;
      }
      Integer result = syCatalogRoleDao.update(syCatalogRole);
      return result > 0;
   }

   @Override
   public RResult update(SyCatalogRole oldEntity, SyCatalogRole newEntity){
      if(null == newEntity){
         _logger.info("update param is empty");
         return new RResult(RResult.MSG_FAIL, RResult.paramNull, "entity");
      }
      Integer result = syCatalogRoleDao.update(newEntity);
      if(result > 0) {
         return new RResult(RResult.MSG_SUCCESS);	
      }
      
      return new RResult(RResult.MSG_FAIL);
   }

   @Override
   public SyCatalogRole load(Integer id){
      if(null == id){
         _logger.info( "load {0} is empty id");
         return null;
      }
      SyCatalogRole syCatalogRole = new SyCatalogRole();
      syCatalogRole.setId(id);
      return syCatalogRoleDao.load(syCatalogRole);
   }

   @Override
   public SyCatalogRole load(SyCatalogRole syCatalogRole){
      if(null == syCatalogRole){
         _logger.info( "load syCatalogRole is empty ");
         return null;
      }
      return syCatalogRoleDao.load(syCatalogRole);
   }

   @Override
   public Integer loadCount(SyCatalogRole syCatalogRole) {
      return syCatalogRoleDao.loadCount(syCatalogRole);
   }
   
   @Override
   public boolean isExist(SyCatalogRole syCatalogRole) {
      Integer count = loadCount(syCatalogRole);
      if(null == count) {
         return false;
      }
      return count.intValue()>0;
   }

   @Override
   public List<SyCatalogRole> findList(SyCatalogRole syCatalogRole, Integer skip, Integer max, Map<String, Object> params){
      return syCatalogRoleDao.findList(syCatalogRole, skip, max, params);
   }

   @Override
   public PageControlInfo findPageList(SyCatalogRole syCatalogRole, Integer skip, Integer max, Map<String, Object> params){
      return syCatalogRoleDao.findPageList(syCatalogRole, skip, max, params);
   }

   /// ***********************method begin***********************
   /**
    * 保存之前处理
    * @param syCatalogRole 实体
    */
   private void saveBefore(SyCatalogRole syCatalogRole){
   
   }
  
 
   /**
    * 删除之前处理
    * @param syCatalogRole 实体
    */
   private void deleteBefore(SyCatalogRole syCatalogRole){
   		
   }
   /**
    * 删除之后处理
    * @param syCatalogRole 实体
    */
   private void deleteAfter(SyCatalogRole syCatalogRole){
   
   }
   
   @Override
   public SyCatalogRole loadByRoleId(Integer roleId) {
     
      SyCatalogRole syCatalogRole = new SyCatalogRole();
      syCatalogRole.setRoleId(roleId);
      syCatalogRole.setIsValid("Y");
      List<SyCatalogRole> list =  findList(syCatalogRole, null, null, null);
      if(null != list && list.size() > 0) {
     	 //一个角色只有一个权限
     	 return list.get(0);
      }
      return null;
   }

   /// ***********************method end*************************

}
