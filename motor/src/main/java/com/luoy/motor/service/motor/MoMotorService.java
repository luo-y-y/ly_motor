
package com.luoy.motor.service.motor;
import java.util.List;
import java.util.Map;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.luoy.motor.bean.motor.MoMotor;


public interface MoMotorService{

   /**
    * 保存返回主键
    * @param moMotor 实体
    * @return Long
    */
   Integer save(MoMotor moMotor);

   /**
    * 删除
    * @param id 主键
    * @return boolean(true:成功, false:失败)
    */
   boolean delete(Integer id);

   /**
    * 修改
    * @param oldEntity     旧实体
    * @param newEntity     新实体
    * @return FResult
    */
   RResult update(MoMotor newEntity);
   
   /**
    * 根据主键获取实体
    * @param id"""主键
    * @return MoMotor
    */
   MoMotor load(Integer id);

   /**
    * 根据主键获取实体
    * @param id"""主键
    * @return MoMotor
    */
   MoMotor load(Integer id,Integer userId);
   
   /**
    * 根据条件获取实体
    * @param moMotor   实体
    * @return MoMotor
    */
   MoMotor load(MoMotor moMotor);
   

   /**
    * 根据条件获取实体列表,当skip、max均等于null获取全部
    * @param moMotor"""实体
    * @param skip"""当前页数
    * @param max"""页记录数
    * @param params"""其他参数
    * @return List<MoMotor>
    */
   List<MoMotor> findList(MoMotor moMotor, Integer skip, Integer max, Map<String, Object> params);
   
   /**
    * 根据条件获取实体分页列表
    * @param moMotor"""实体
    * @param skip"""        当前页数(当等于null时,返回null)
    * @param max"""        页记录数(默认为20)
    * @param params"""        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageList(MoMotor moMotor, Integer skip, Integer max, Map<String, Object> params);


   /**
    * 
    * @param id
    * @param status
    * @return
    */
   boolean updateStatus(Integer id,String status);
   
 /**
  * 用户评价的摩托
  * @param userId
  * @param skip
  * @param max
  * @param params
  * @return
  */
   PageControlInfo findPageListForEva(Integer userId, Integer skip, Integer max, Map<String, Object> params);

   
   /**
    * 用户收藏的摩托
    * @param userId
    * @param skip
    * @param max
    * @param params
    * @return
    */
   PageControlInfo findPageListForCollect(Integer userId, Integer skip, Integer max, Map<String, Object> params);

   /**
    * 
    * @param id
    * @param status
    * @return
    */
   boolean updateIsHot(Integer id,String status);
   
 }
