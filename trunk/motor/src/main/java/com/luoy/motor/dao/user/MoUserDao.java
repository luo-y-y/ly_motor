
package com.luoy.motor.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cat.common.bean.PageControlInfo;
import com.luoy.motor.bean.user.MoUser;

/**
 * 表名：mo_user
 * @author luoyang
 */
public interface MoUserDao {
   /**
    * 保存并返回主键
    * @param moUser 实体
    */
   Map<String, Object> save(@Param("entity") MoUser moUser);

   /**
    * 删除
    * @param moUser"""实体
    */
   Integer delete(@Param("id") Integer id);

   /**
    * 修改
    * @param moUser"""实体
    */
   Integer update(@Param("entity") MoUser moUser);
   
   /**
    * 根据主键获取实体
    * @param moUser"""实体
    * @return MoUser
    */
   MoUser load(@Param("entity") MoUser moUser);


   /**
    * 根据条件获取实体列表,当skip、max均等于null获取全部)
    * @param moUser"""实体
    * @param skip"""        当前页数
    * @param max"""        页记录数
    * @param params"""        其他参数
    * @return List<MoUser>
    */
   List<MoUser> findList(@Param("entity") MoUser moUser, @Param("skip") Integer start, @Param("max") Integer max, @Param("params") Map<String, Object> params);
   
   /**
    * 根据条件获取实体分页列表
    * @param moUser"""实体
    * @param skip"""        当前页数(当等于null时,返回null)
    * @param max"""        页记录数(默认为20)
    * @param params"""        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageList(@Param("entity") MoUser moUser, @Param("skip") Integer skip, @Param("max") Integer max, @Param("params") Map<String, Object> params);


}
