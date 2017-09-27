package com.cat.sy.dao;
/// ***********************import begin***********************
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cat.common.bean.PageControlInfo;
import com.cat.sy.bean.SyRole;

/// ***********************import end*************************
/**
 * 表名：pm_role
 * @author Administrator
 */
@Repository("syRoleDao")
public interface SyRoleDao{
   /**
    * 保存并返回主键
    * @param SyRole 实体
    */
   Map<String, Object> save(@Param("entity") SyRole syRole);

   /**
    * 删除
    * @param SyRole	实体
    */
   Integer delete(@Param("id") Integer id);

   /**
    * 删除多条数据
    * @param idList	主键列表
    */
   Integer deleteByIds(@Param("idList") List<Integer> idList);

   /**
    * 修改
    * @param SyRole	实体
    */
   Integer update(@Param("entity") SyRole syRole);
   
   /**
    * 根据主键获取实体
    * @param SyRole	实体
    * @return SyRole
    */
   SyRole load(@Param("entity") SyRole syRole);

   /**
    * 根据主键获取实体
    * @param id	主键
    * @return SyRole
    */
   Integer loadCount(@Param("entity") SyRole syRole);

   /**
    * 根据条件获取实体列表,当skip、max均等于null获取全部)
    * @param SyRole	实体
    * @param skip	        当前页数
    * @param max	        页记录数
    * @param params	        其他参数
    * @return List<SyRole>
    */
   List<SyRole> findList(@Param("entity") SyRole syRole, @Param("skip") Integer start, @Param("max") Integer max, @Param("params") Map<String, Object> params);
   
   /**
    * 根据条件获取实体分页列表
    * @param SyRole	实体
    * @param skip	        当前页数(当等于null时,返回null)
    * @param max	        页记录数(默认为20)
    * @param params	        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageList(@Param("entity") SyRole syRole, @Param("skip") Integer skip, @Param("max") Integer max, @Param("params") Map<String, Object> params);
   /**
    * 保存操作历史
    * @param SyRole 实体
    */
   void insertHs(@Param("entity") SyRole syRole);
   /**
    * 根据条件获取实体历史操作分页列表
    * @param SyRole	实体
    * @param skip	        当前页数(当等于null时,返回null)
    * @param max	        页记录数(默认为20)
    * @param params	        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageHsList(@Param("entity") SyRole syRole, @Param("skip") Integer skip, @Param("max") Integer max, @Param("params") Map<String, Object> params);
   
   /// ***********************method begin***********************

   /// ***********************method end*************************
}
