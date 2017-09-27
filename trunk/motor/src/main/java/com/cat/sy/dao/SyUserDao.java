package com.cat.sy.dao;
/// ***********************import begin***********************
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cat.common.bean.PageControlInfo;
import com.cat.sy.bean.SyUser;

/// ***********************import end*************************
/**
 * 表名：pm_user
 * @author Administrator
 */

@Repository("syUserDao")
public interface SyUserDao{
   /**
    * 保存
    * @param syUser 实体
    */
   void insert(@Param("entity") SyUser syUser);

   /**
    * 保存并返回主键
    * @param syUser 实体
    */
   Map<String, Object> save(@Param("entity") SyUser syUser);

   /**
    * 删除
    * @param syUser	实体
    */
   Integer delete(@Param("id") Integer id);

   /**
    * 删除多条数据
    * @param idList	主键列表
    */
   Integer deleteByIds(@Param("idList") List<Long> idList);

   /**
    * 修改
    * @param syUser	实体
    */
   Integer update(@Param("entity") SyUser syUser);
   
   /**
    * 获取实体
    * @param syUser	       实体
    * @return syUser
    */
   SyUser load(@Param("entity") SyUser syUser);

   /**
    * 获取数量
    * @param syUser	           实体
    * @return syUser
    */
   Integer loadCount(@Param("entity") SyUser syUser);

   /**
    * 根据条件获取实体列表,当skip、max均等于null获取全部)
    * @param syUser       实体
    * @param skip	                当前页数
    * @param max	                页记录数
    * @param params	          其他参数
    * @return List<syUser>
    */
   List<SyUser> findList(@Param("entity") SyUser syUser, @Param("skip") Integer start, @Param("max") Integer max, @Param("params") Map<String, Object> params);
   
   /**
    * 根据条件获取实体分页列表
    * @param syUser	实体
    * @param skip	        当前页数(当等于null时,返回null)
    * @param max	        页记录数(默认为20)
    * @param params	        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageList(@Param("entity") SyUser syUser, @Param("skip") Integer skip, @Param("max") Integer max, @Param("params") Map<String, Object> params);
   /// ***********************method begin***********************

   /**
    * 重置密码
    * @param id
    * @param password
    * @return
    */
   Integer updateForResetPassword(@Param("id") Integer id, @Param("password") String password);
   
   /**
    * 修改密码
    * @param id
    * @param password
    * @return
    */
   Integer updatePassword(@Param("id") Integer id, @Param("password") String password, @Param("newPassword") String newPassword);
   /// ***********************method end*************************
}
