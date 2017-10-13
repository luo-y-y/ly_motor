package com.luoy.motor.dao.motor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cat.common.bean.PageControlInfo;
import com.luoy.motor.bean.motor.MoMotor;

/**
 * 表名：mo_motor
 * @author luoyang
 */
public interface MoMotorDao {
   /**
    * 保存并返回主键
    * @param moMotor 实体
    */
   Map<String, Object> save(@Param("entity") MoMotor moMotor);

   /**
    * 删除
    * @param moMotor"""实体
    */
   Integer delete(@Param("id") Integer id);

   /**
    * 修改
    * @param moMotor"""实体
    */
   Integer update(@Param("entity") MoMotor moMotor);
   
   /**
    * 根据主键获取实体
    * @param moMotor"""实体
    * @return MoMotor
    */
   MoMotor load(@Param("entity") MoMotor moMotor,@Param("params") Map<String, Object> params);


   /**
    * 根据条件获取实体列表,当skip、max均等于null获取全部)
    * @param moMotor"""实体
    * @param skip"""        当前页数
    * @param max"""        页记录数
    * @param params"""        其他参数
    * @return List<MoMotor>
    */
   List<MoMotor> findList(@Param("entity") MoMotor moMotor, @Param("skip") Integer start, @Param("max") Integer max, @Param("params") Map<String, Object> params);
   
   /**
    * 根据条件获取实体分页列表
    * @param moMotor"""实体
    * @param skip"""        当前页数(当等于null时,返回null)
    * @param max"""        页记录数(默认为20)
    * @param params"""        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageList(@Param("entity") MoMotor moMotor, @Param("skip") Integer skip, @Param("max") Integer max, @Param("params") Map<String, Object> params);


   /**
    * 修改
    * @param moMotor"""实体
    */
   Integer updateForStatus(@Param("id") Integer id,@Param("status") String status,@Param("updateTime") Timestamp updateTime);

   /**
    * 查询用户评价的摩托
    * @param moMotor
    * @param skip
    * @param max
    * @param params
    * @return
    */
   PageControlInfo findPageListForEva(@Param("entity") MoMotor moMotor, @Param("skip") Integer skip, @Param("max") Integer max, @Param("params") Map<String, Object> params);

   /**
    * 查询用户收藏的摩托
    * @param moMotor
    * @param skip
    * @param max
    * @param params
    * @return
    */
   PageControlInfo findPageListForCollect(@Param("entity") MoMotor moMotor, @Param("skip") Integer skip, @Param("max") Integer max, @Param("params") Map<String, Object> params);

   /**
    * 修改
    * @param moMotor"""实体
    */
   Integer updateForHot(@Param("id") Integer id,@Param("isHot") String isHot,@Param("updateTime") Timestamp updateTime);
   
}
