package com.cat.sy.service;
/// ***********************import begin***********************
import java.util.List;
import java.util.Map;

import com.cat.common.bean.PageControlInfo;
import com.cat.sy.bean.SyUser;
/// ***********************import end*************************
public interface SyUserService{

   /**
    * 保存返回主键
    * @param syUser 实体
    * @return Long
    */
   Integer save(SyUser syUser);

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
    * @param syUser	实体
    * @return boolean(true:成功, false:失败)
    */
   boolean update(SyUser syUser);
   
   /**
    * 根据主键获取实体
    * @param id	主键
    * @return syUser
    */
   SyUser load(Integer id);
   
   /**
    * 根据条件获取实体
    * @param syUser   实体
    * @return syUser
    */
   SyUser load(SyUser syUser);

   /**
    * 根据条件获取数量
    * @param syUser	实体
    * @return Integer
    */
   public Integer loadCount(SyUser syUser);

   /**
    * 根据条件检查是否存在
    * @param syUser	实体
    * @return boolean(true:存在; false:不存在)
    */
   boolean isExist(SyUser syUser);

   /**
    * 根据条件获取实体列表,当skip、max均等于null获取全部)
    * @param syUser	实体
    * @param skip	当前页数
    * @param max	页记录数
    * @param params	其他参数
    * @return List<syUser>
    */
   List<SyUser> findList(SyUser syUser, Integer skip, Integer max, Map<String, Object> params);
   
   /**
    * 根据条件获取实体分页列表
    * @param syUser	实体
    * @param skip	        当前页数(当等于null时,返回null)
    * @param max	        页记录数(默认为20)
    * @param params	        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageList(SyUser syUser, Integer skip, Integer max, Map<String, Object> params);
   /// ***********************method begin***********************
   /**
    * 获取用户所有角色
    * @param userId  用户编号
    * @return List<Long> 
    */
   List<Integer> findRolesByUserId(Integer userId);
   /**
    * 根据目录获取用户目录权限(格式:,"id1,id2,")
    * @param userId  用户编号
    * @return  String
    */
   String getCatalogPowerByUserId(Integer userId);
   /**
    * 根据登陆账号获取用户信息
    * @return syUser
    */
   SyUser loadByloginPassport(String loginPassport);
   
   /**
    * 重置密码
    * @param userId
    * @return
    */
   boolean resetPassword(Integer userId);
   
   /**
    * 重置密码
    * @param userId
    * @return
    */
   Integer updatePassword(Integer userId,String password,String  newPassword);
   /// ***********************method end*************************
}
