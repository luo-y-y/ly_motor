package com.cat.sy.service;
/// ***********************import begin***********************
import java.util.List;
import java.util.Map;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.cat.sy.bean.SyCatalogNode;


/// ***********************import end*************************

public interface SyCatalogNodeService{

   /**
    * 保存返回主键
    * @param syCatalogNode 实体
    * @return Long
    */
   Integer save(SyCatalogNode syCatalogNode);

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
    * @param syCatalogNode	实体
    * @return boolean(true:成功, false:失败)
    */
   boolean update(SyCatalogNode syCatalogNode);
   
   /**
    * 修改
    * @param oldEntity     旧实体
    * @param newEntity     新实体
    * @param isCheckOver   是否检查版本(Y是，N否)
    * @return FResult
    */
   RResult update(SyCatalogNode oldEntity, SyCatalogNode newEntity);
   
   /**
    * 根据主键获取实体
    * @param id	主键
    * @return SyCatalogNode
    */
   SyCatalogNode load(Integer id);

   /**
    * 根据条件获取实体
    * @param syCatalogNode   实体
    * @return SyCatalogNode
    */
   SyCatalogNode load(SyCatalogNode syCatalogNode);
   
   /**
    * 根据条件获取数量
    * @param syCatalogNode	实体
    * @return Integer
    */
   Integer loadCount(SyCatalogNode syCatalogNode);

   /**
    * 根据条件检查是否存在
    * @param syCatalogNode	实体
    * @return boolean(true:存在; false:不存在)
    */
   boolean isExist(SyCatalogNode syCatalogNode);

   /**
    * 根据条件获取实体列表,当skip、max均等于null获取全部)
    * @param syCatalogNode	实体
    * @param skip	当前页数
    * @param max	页记录数
    * @param params	其他参数
    * @return List<SyCatalogNode>
    */
   List<SyCatalogNode> findList(SyCatalogNode syCatalogNode, Integer skip, Integer max, Map<String, Object> params);
   
   /**
    * 根据条件获取实体分页列表
    * @param syCatalogNode	实体
    * @param skip	        当前页数(当等于null时,返回null)
    * @param max	        页记录数(默认为20)
    * @param params	        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageList(SyCatalogNode syCatalogNode, Integer skip, Integer max, Map<String, Object> params);
   /// ***********************method begin***********************
   /**
    * 根据关联节点查询目录节点  关联节点为空查询全部的目录节点
    * @param linkCatalog 关联节点
    * @return List<SyCatalogNode>
    */
   List<SyCatalogNode> findNode1(String linkCatalog);
   /**
    * 根据目录ID查询目录节点列表
    * @param catalogId 目录ID
    * @return List<SyCatalogNode>
    */
   List<SyCatalogNode> findChildrensNode(String catalogId);
   /**
    * 根据父节点查询子节点列表，只显示有效的
    * @param parentId 父目录ID
    * @return List<SyCatalogNode>
    */
   List<SyCatalogNode> findChildNodes(String parentId);
   /**
    * 根据父节点查询子节点列表,显示全部
    * @param parentId 父目录ID
    * @return List<SyCatalogNode>
    */
   List<SyCatalogNode> findAllChildNodes(String parentId);
   
   /**
    * 根据父节点查询子节点列表，只显示有效的
    * @param parentId 父目录ID
    * @return List<SyCatalogNode>
    */
   SyCatalogNode findNodeById(Integer id);
   
   
   /**
    * 查找easyUi显示的图标
    * @return
    */
   String getAllIconImgJson(String basePath,String cssPath);
   /// ***********************method end*************************
}
