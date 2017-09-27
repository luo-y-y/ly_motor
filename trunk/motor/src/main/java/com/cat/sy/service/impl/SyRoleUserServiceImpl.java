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
import com.cat.common.lang.RList;
import com.cat.common.lang.RString;
import com.cat.common.lang.RUuid;
import com.cat.sy.bean.SyCatalogRole;
import com.cat.sy.bean.SyRoleUser;
import com.cat.sy.dao.SyRoleUserDao;
import com.cat.sy.service.SyCatalogRoleService;
import com.cat.sy.service.SyRoleUserService;

/// ***********************import end*************************
@Service("syRoleUserService")
public class SyRoleUserServiceImpl  implements SyRoleUserService{

	public Logger _logger = Logger.getLogger(this.getClass());

	
   @Autowired
   private SyRoleUserDao syRoleUserDao;

   @Autowired
   private SyCatalogRoleService syCatalogRoleService;

   
  
   /// ***********************define end*************************
   @Override
   public Integer save(SyRoleUser syRoleUser){
      if(null == syRoleUser){
         _logger.info("save, is empty ");
         return null;
      }
      syRoleUser.setUuid(RUuid.makeUuid());
      syRoleUser.setCreateTime(RDate.getCurrentTime());
      Map<String, Object> map = syRoleUserDao.save(syRoleUser);
      return Integer.parseInt(map.get("id").toString());
   }

   @Override
   public boolean delete(Integer id){
      if(null == id){
         _logger.info("delete, is empty");
         return false;
      }
      SyRoleUser entity = load(id);
      if(null == entity) {
         _logger.info("delete record not exist.  id");
         return false;
      }
      Integer result = syRoleUserDao.delete(id);
      return result > 0;
   }

   @Override
   public boolean deleteByIds(String ids){
      if(RString.isBlank(ids)){
         _logger.info( "deleteByIds  is empty ");
         return false;
      }
      String[] array = ids.split(",");
      List<Integer> list = new ArrayList<Integer>();
      for(String id : array){
         list.add(Integer.parseInt(id));
      }
      Integer result = syRoleUserDao.deleteByIds(list);
      return result > 0;
   }

   @Override
   public boolean update(SyRoleUser syRoleUser){
      if(null == syRoleUser){
         _logger.info( "update is empty ");
         return false;
      }
      Integer id = syRoleUser.getId();
      if(null == id) {
         _logger.info("update  is empty" );
         return false;
      }
      Integer result = syRoleUserDao.update(syRoleUser);
      return result > 0;
   }

   @Override
   public RResult update(SyRoleUser oldEntity, SyRoleUser newEntity){
      if(null == newEntity){
         _logger.info( "update param is empty");
         return new RResult(RResult.MSG_FAIL, RResult.paramNull, "newEntity");
      }
      Integer result = syRoleUserDao.update(newEntity);
      if(result > 0) {
         return new RResult(RResult.MSG_SUCCESS);	
      }
      return new RResult(RResult.MSG_FAIL);
   }
   
   @Override
   public SyRoleUser load(Integer id){
      if(null == id){
         _logger.info( "load  is empty id");
         return null;
      }
      SyRoleUser syRoleUser = new SyRoleUser();
      syRoleUser.setId(id);
      return syRoleUserDao.load(syRoleUser);
   }

   @Override
   public SyRoleUser load(SyRoleUser syRoleUser){
      if(null == syRoleUser){
         _logger.info("load  is empty syRoleUser");
         return null;
      }
      return syRoleUserDao.load(syRoleUser);
   }

   @Override
   public Integer loadCount(SyRoleUser syRoleUser) {
      return syRoleUserDao.loadCount(syRoleUser);
   }
   

   @Override
   public boolean isExist(SyRoleUser syRoleUser) {
      Integer count = loadCount(syRoleUser);
      if(null == count) {
         return false;
      }
      return count.intValue()>0;
   }

   @Override
   public List<SyRoleUser> findList(SyRoleUser syRoleUser, Integer skip, Integer max, Map<String, Object> params){
      return syRoleUserDao.findList(syRoleUser, skip, max, params);
   }

   @Override
   public PageControlInfo findPageList(SyRoleUser syRoleUser, Integer skip, Integer max, Map<String, Object> params){
      return syRoleUserDao.findPageList(syRoleUser, skip, max, params);
   }

   /// ***********************method begin***********************
   @Override
   public boolean isExist(Integer roleId, Integer userId) {
      SyRoleUser syRoleUser = new SyRoleUser();
      syRoleUser.setRoleId(roleId);
      syRoleUser.setUserId(userId);
      Integer count = loadCount(syRoleUser);
      if(null == count) {
         return false;
      }
      return count.intValue()>0;
   }
   
   @Override
   public RResult saveRoleUsers(SyRoleUser entity, String userIds) {
      Integer roleId = entity.getRoleId();
      if(RString.isBlank(userIds) || null == roleId){
         _logger.info("saveRoleUsers  param error.");
         return new RResult(RResult.MSG_FAIL, RResult.paramNull, " ");
      }
      String[] uIds = RString.split(userIds, ",");
      for(int i=0; i<uIds.length; i++){
         Integer userId = Integer.parseInt(uIds[i]);
         if(!isExist(entity.getRoleId(), userId)) {
            entity.setId(null);
            entity.setUserId(userId);
            save(entity);
         }
      }
      return new RResult(RResult.MSG_SUCCESS);
   }
   
   @Override
   public RResult updateRoleUsers(SyRoleUser entity, String ids){
      String[] idArray = RString.split(ids, ",");
      for(int i=0; i<idArray.length; i++){
         Integer id = Integer.parseInt(idArray[i]);
         SyRoleUser syRoleUser = load(id);
         if(null != syRoleUser) {
            syRoleUser.setStatus(entity.getStatus());
            update(syRoleUser);
         }
      }
      return new RResult(RResult.MSG_SUCCESS);
   }
   
  
  @Override
   public List<Integer> findUserRoles(Integer userId) {
      if(null==userId) {
         _logger.info("findUserOrgRole param error. userId is null");
         return null;
      }
      return syRoleUserDao.findUserRoles(userId);
   }
   
   
   @Override
   public List<SyRoleUser> findUserRole(Integer userId, String status) {
      _logger.info("findUserRole param[userId="+userId+"], status="+status+"]"  );
      if(null==userId) {
         _logger.info("findUserRole param error. userId is null");
         return null;
      }
      SyRoleUser syRoleUser = new SyRoleUser();
      syRoleUser.setUserId(userId);
      syRoleUser.setStatus(status);
      return findList(syRoleUser, null, null, null);
   }
   
   private List<Integer> findRolesByUserId(Integer userId) {
	      if(null == userId){
	         _logger.info( "findRolesByUserId userId  is empty");
	         return null;
	      }
	      
	      List<Integer>  roleList = new ArrayList<Integer>();
	      // 用户-绑定及解除的角色
	      List<SyRoleUser> syRoleUserList = findUserRole(userId, null);
	      if(RList.isNotBlank(syRoleUserList)) {
	         for(int i=0; i<syRoleUserList.size(); i++) {
	            SyRoleUser syRoleUser = syRoleUserList.get(i);
	            // 用户-绑定角色
	            roleList.add(syRoleUser.getRoleId());
	         }
	      }
	      
	      return roleList;
	}
   
   @Override
   public Integer saveRoleUser(Integer userId, Integer roleId, Integer operateUserId){
	   if(null==userId || null==roleId || null==operateUserId) {
		   return null;
	   }
	   if(isExist(roleId, userId)){
	      return null;
	   }
	   SyRoleUser syRoleUser = new SyRoleUser();
	   syRoleUser.setCreateUserId(operateUserId);
	   syRoleUser.setUserId(userId);
	   syRoleUser.setRoleId(roleId);
	   syRoleUser.setStatus("B");
	   return save(syRoleUser);
   }
   
   @Override
   public String getCatalogPowerByUserId(Integer userId) {
	   
	      List<Integer> roleIdList = findRolesByUserId(userId);
	      // 用户目录权限
	      String catalogPower = ",";
	      if(RList.isBlank(roleIdList)) {
	         return catalogPower;
	      }
	      for(int i=0; i<roleIdList.size(); i++) {
	    	 SyCatalogRole syCatalogRole = syCatalogRoleService.loadByRoleId(roleIdList.get(i));
	         if(null != syCatalogRole) {
	            catalogPower +=syCatalogRole.getPowerNodeIds()+",";
	         }
	      }
	      return catalogPower;
	   }
   /// ***********************method end*************************

}
