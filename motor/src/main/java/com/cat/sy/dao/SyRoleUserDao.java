package com.cat.sy.dao;
/// ***********************import begin***********************
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cat.common.bean.PageControlInfo;
import com.cat.sy.bean.SyRoleUser;

/// ***********************import end*************************
/**
 * 表名：syRoleUser
 * @author Administrator
 */
@Repository("syRoleUserDao")
public interface SyRoleUserDao{
   /**
    * 保存并返回主键
    * @param SyRoleUser 实体
    */
   Map<String, Object> save(@Param("entity") SyRoleUser syRoleUser);

   /**
    * 删除
    * @param SyRoleUser	实体
    */
   Integer delete(@Param("id") Integer id);

   /**
    * 删除多条数据
    * @param idList	主键列表
    */
   Integer deleteByIds(@Param("idList") List<Integer> idList);

   /**
    * 修改
    * @param SyRoleUser	实体
    */
   Integer update(@Param("entity") SyRoleUser syRoleUser);
   
   /**
    * 根据主键获取实体
    * @param SyRoleUser	实体
    * @return SyRoleUser
    */
   SyRoleUser load(@Param("entity") SyRoleUser syRoleUser);

   /**
    * 根据主键获取实体
    * @param id	主键
    * @return SyRoleUser
    */
   Integer loadCount(@Param("entity") SyRoleUser syRoleUser);
   
   /**
    * 根据条件获取实体列表,当skip、max均等于null获取全部)
    * @param SyRoleUser	实体
    * @param skip	        当前页数
    * @param max	        页记录数
    * @param params	        其他参数
    * @return List<SyRoleUser>
    */
   List<SyRoleUser> findList(@Param("entity") SyRoleUser syRoleUser, @Param("skip") Integer start, @Param("max") Integer max, @Param("params") Map<String, Object> params);
   
   /**
    * 根据条件获取实体分页列表
    * @param SyRoleUser	实体
    * @param skip	        当前页数(当等于null时,返回null)
    * @param max	        页记录数(默认为20)
    * @param params	        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageList(@Param("entity") SyRoleUser syRoleUser, @Param("skip") Integer skip, @Param("max") Integer max, @Param("params") Map<String, Object> params);
   /// ***********************method begin***********************
   /**
    * 获取用户所属组织的角色列表
    * @param userId  用户编号
    * @return  List<Long>
    */
   List<Integer> findUserRoles(@Param("userId") Integer userId);
   /// ***********************method end*************************
}
