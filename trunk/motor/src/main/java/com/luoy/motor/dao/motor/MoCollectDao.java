package com.luoy.motor.dao.motor;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cat.common.bean.PageControlInfo;
import com.luoy.motor.bean.motor.MoCollect;

/**
 * 表名：mo_collect
 * @author luoyang
 */
public interface MoCollectDao {
   /**
    * 保存并返回主键
    * @param moCollect 实体
    */
   Map<String, Object> save(@Param("entity") MoCollect moCollect);

   /**
    * 删除
    * @param moCollect"""实体
    */
   Integer delete(@Param("id") Integer id);

   /**
    * 删除
    * @param moCollect"""实体
    */
   Integer deleteByPid(@Param("pid") Integer pid,@Param("userId") Integer userId);
   
   /**
    * 修改
    * @param moCollect"""实体
    */
   Integer update(@Param("entity") MoCollect moCollect);
   
   /**
    * 根据主键获取实体
    * @param moCollect"""实体
    * @return MoCollect
    */
   MoCollect load(@Param("entity") MoCollect moCollect);


   /**
    * 根据条件获取实体列表,当skip、max均等于null获取全部)
    * @param moCollect"""实体
    * @param skip"""        当前页数
    * @param max"""        页记录数
    * @param params"""        其他参数
    * @return List<MoCollect>
    */
   List<MoCollect> findList(@Param("entity") MoCollect moCollect, @Param("skip") Integer start, @Param("max") Integer max, @Param("params") Map<String, Object> params);
   
   /**
    * 根据条件获取实体分页列表
    * @param moCollect"""实体
    * @param skip"""        当前页数(当等于null时,返回null)
    * @param max"""        页记录数(默认为20)
    * @param params"""        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageList(@Param("entity") MoCollect moCollect, @Param("skip") Integer skip, @Param("max") Integer max, @Param("params") Map<String, Object> params);


}
