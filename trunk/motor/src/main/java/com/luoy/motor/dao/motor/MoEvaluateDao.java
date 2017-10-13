
package com.luoy.motor.dao.motor;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cat.common.bean.PageControlInfo;
import com.luoy.motor.bean.motor.MoEvaluate;

/**
 * 表名：mo_evaluate
 * @author luoyang
 */
public interface MoEvaluateDao {
   /**
    * 保存并返回主键
    * @param moEvaluate 实体
    */
   Map<String, Object> save(@Param("entity") MoEvaluate moEvaluate);

   /**
    * 删除
    * @param moEvaluate"""实体
    */
   Integer delete(@Param("id") Integer id);

   /**
    * 修改
    * @param moEvaluate"""实体
    */
   Integer update(@Param("entity") MoEvaluate moEvaluate);
   
   /**
    * 根据主键获取实体
    * @param moEvaluate"""实体
    * @return MoEvaluate
    */
   MoEvaluate load(@Param("entity") MoEvaluate moEvaluate);


   /**
    * 根据条件获取实体列表,当skip、max均等于null获取全部)
    * @param moEvaluate"""实体
    * @param skip"""        当前页数
    * @param max"""        页记录数
    * @param params"""        其他参数
    * @return List<MoEvaluate>
    */
   List<MoEvaluate> findList(@Param("entity") MoEvaluate moEvaluate, @Param("skip") Integer start, @Param("max") Integer max, @Param("params") Map<String, Object> params);
   
   /**
    * 根据条件获取实体分页列表
    * @param moEvaluate"""实体
    * @param skip"""        当前页数(当等于null时,返回null)
    * @param max"""        页记录数(默认为20)
    * @param params"""        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageList(@Param("entity") MoEvaluate moEvaluate, @Param("skip") Integer skip, @Param("max") Integer max, @Param("params") Map<String, Object> params);


}
