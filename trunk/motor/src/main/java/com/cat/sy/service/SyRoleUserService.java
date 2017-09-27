package com.cat.sy.service;
/// ***********************import begin***********************
import java.util.List;
import java.util.Map;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.cat.sy.bean.SyRoleUser;

/// ***********************import end*************************

public interface SyRoleUserService{

   /**
    * 保存返回主键
    * @param SyRoleUser 实体
    * @return Long
    */
	Integer save(SyRoleUser syRoleUser);

   /**
    * 删除
    * @param id 主键
    * @return boolean(true:成功, false:失败)
    */
   boolean delete(Integer id);

   /**
    * 删除多条数据
    * @param idList	主键列表
    * @return boolean(true:成功, false:失败)
    */
   boolean deleteByIds(String ids);

   /**
    * 修改
    * @param SyRoleUser	实体
    * @return boolean(true:成功, false:失败)
    */
   boolean update(SyRoleUser syRoleUser);
   
   /**
    * 修改
    * @param oldEntity     旧实体
    * @param newEntity     新实体
    * @param isCheckOver   是否检查版本(Y是，N否)
    * @return FResult
    */
   RResult update(SyRoleUser oldEntity, SyRoleUser newEntity);
   
   /**
    * 根据主键获取实体
    * @param id	主键
    * @return SyRoleUser
    */
   SyRoleUser load(Integer id);

   /**
    * 根据条件获取实体
    * @param SyRoleUser   实体
    * @return SyRoleUser
    */
   SyRoleUser load(SyRoleUser syRoleUser);
   
   /**
    * 根据条件获取数量
    * @param SyRoleUser	实体
    * @return Integer
    */
   Integer loadCount(SyRoleUser syRoleUser);

   /**
    * 根据条件检查是否存在
    * @param SyRoleUser	实体
    * @return boolean(true:存在; false:不存在)
    */
   boolean isExist(SyRoleUser syRoleUser);

   /**
    * 根据条件获取实体列表,当skip、max均等于null获取全部)
    * @param SyRoleUser	实体
    * @param skip	当前页数
    * @param max	页记录数
    * @param params	其他参数
    * @return List<SyRoleUser>
    */
   List<SyRoleUser> findList(SyRoleUser syRoleUser, Integer skip, Integer max, Map<String, Object> params);
   
   /**
    * 根据条件获取实体分页列表
    * @param SyRoleUser	实体
    * @param skip	        当前页数(当等于null时,返回null)
    * @param max	        页记录数(默认为20)
    * @param params	        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageList(SyRoleUser syRoleUser, Integer skip, Integer max, Map<String, Object> params);
   /// ***********************method begin***********************
   /**
    * 根据条件检查条件是否存在
    * @param roleId  角色编号
    * @param userId  用户编号
    * @return  boolean
    */
   boolean isExist(Integer roleId, Integer userId);
   
   /**
    * 批量保存
    * @param entity  角色用户实体
    * @param userIds 多个用户
    * @return  FResult
    */
   RResult saveRoleUsers(SyRoleUser entity, String userIds);
   /**
    * 批量更新
    * @param entity  角色用户实体
    * @param ids     多条记录
    * @return  FResult
    */
   RResult updateRoleUsers(SyRoleUser entity, String ids);
   
  
   /**
    * 获取用户角色列表(联合查询)
    * @param userId  用户编号
    * @return  List<Long>
    */
   List<Integer> findUserRoles(Integer userId);
   
   /**
    * 获取角色用户表中的所有用户角色
    * <p>状态为空获取所有,也可根据状态过滤</p>
    * @param userId     用户编号
    * @param statusCd   状态(B绑定,R解除)
    * @return  List<SyRoleUser>
    */
   List<SyRoleUser> findUserRole(Integer userId, String statusCd);
   
   
   Integer saveRoleUser(Integer userId, Integer roleId, Integer operateUserId);
   
   String getCatalogPowerByUserId(Integer userId);
   /// ***********************method end*************************

}
