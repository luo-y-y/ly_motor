package com.cat.sy.dao;
/// ***********************import begin***********************
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cat.common.bean.PageControlInfo;
import com.cat.sy.bean.SyCatalogNode;


/// ***********************import end*************************
/**
 * 表名：sy_catalog_node
 * @author Administrator
 */
@Repository("syCatalogNodeDao")
public interface SyCatalogNodeDao{
   /**
    * 保存并返回主键
    * @param syCatalogNode 实体
    */
   Map<String, Object> save(@Param("entity") SyCatalogNode syCatalogNode);

   /**
    * 删除
    * @param syCatalogNode	实体
    */
   Integer delete(@Param("id") Integer id);

   /**
    * 删除多条数据
    * @param idList	主键列表
    */
   Integer deleteByIds(@Param("idList") List<Integer> idList);

   /**
    * 修改
    * @param syCatalogNode	实体
    */
   Integer update(@Param("entity") SyCatalogNode syCatalogNode);
   
   /**
    * 根据主键获取实体
    * @param syCatalogNode	实体
    * @return SyCatalogNode
    */
   SyCatalogNode load(@Param("entity") SyCatalogNode syCatalogNode);

   /**
    * 根据主键获取实体
    * @param id	主键
    * @return SyCatalogNode
    */
   Integer loadCount(@Param("entity") SyCatalogNode syCatalogNode);

   /**
    * 根据条件获取实体列表,当skip、max均等于null获取全部)
    * @param syCatalogNode	实体
    * @param skip	        当前页数
    * @param max	        页记录数
    * @param params	        其他参数
    * @return List<SyCatalogNode>
    */
   List<SyCatalogNode> findList(@Param("entity") SyCatalogNode syCatalogNode, @Param("skip") Integer start, @Param("max") Integer max, @Param("params") Map<String, Object> params);
   
   /**
    * 根据条件获取实体分页列表
    * @param syCatalogNode	实体
    * @param skip	        当前页数(当等于null时,返回null)
    * @param max	        页记录数(默认为20)
    * @param params	        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageList(@Param("entity") SyCatalogNode syCatalogNode, @Param("skip") Integer skip, @Param("max") Integer max, @Param("params") Map<String, Object> params);
   /// ***********************method begin***********************
   /**
    * 根据目录ID查询目录节点列表
    * @param catalogId 目录ID
    * @return List<SyCatalogNode>
    */
   List<SyCatalogNode> findChildrensNode(@Param("catalogId")String catalogId);
   /**
    * 根据父节点查询子目录列表
    * @param parentId 父目录ID
    * @param isValid 有效性(Y有效，N无效)
    * @return List<SyCatalogNode>
    */
   List<SyCatalogNode> findChildNode(@Param("parentId")String parentId, @Param("isValid")String isValid);
   /**
    * 根据关联节点查询目录节点  关联节点为空查询全部的目录节点
    * @param linkCatalog 关联节点
    * @return List<SyCatalogNode>
    */
  // List<SyCatalogNode> findNode(@Param("linkCatalog")String linkCatalog);

   /// ***********************method end*************************
}
