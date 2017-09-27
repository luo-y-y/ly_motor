
package com.luoy.motor.dao.advert;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cat.common.bean.PageControlInfo;
import com.luoy.motor.bean.advert.MoAdvert;

/**
 * 表名：mo_advert
 * @author luoyang
 */
public interface MoAdvertDao {
   /**
    * 保存并返回主键
    * @param moAdvert 实体
    */
   Map<String, Object> save(@Param("entity") MoAdvert moAdvert);

   /**
    * 删除
    * @param moAdvert"""实体
    */
   Integer delete(@Param("id") Integer id);

   /**
    * 修改
    * @param moAdvert"""实体
    */
   Integer update(@Param("entity") MoAdvert moAdvert);
   
   /**
    * 根据主键获取实体
    * @param moAdvert"""实体
    * @return MoAdvert
    */
   MoAdvert load(@Param("entity") MoAdvert moAdvert);


   /**
    * 根据条件获取实体列表,当skip、max均等于null获取全部)
    * @param moAdvert"""实体
    * @param skip"""        当前页数
    * @param max"""        页记录数
    * @param params"""        其他参数
    * @return List<MoAdvert>
    */
   List<MoAdvert> findList(@Param("entity") MoAdvert moAdvert, @Param("skip") Integer start, @Param("max") Integer max, @Param("params") Map<String, Object> params);
   
   /**
    * 根据条件获取实体分页列表
    * @param moAdvert"""实体
    * @param skip"""        当前页数(当等于null时,返回null)
    * @param max"""        页记录数(默认为20)
    * @param params"""        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageList(@Param("entity") MoAdvert moAdvert, @Param("skip") Integer skip, @Param("max") Integer max, @Param("params") Map<String, Object> params);

   /**
    * 查询所有上线广告
    * @return
    */
   List<MoAdvert> findListForApp();

   /**
    * 修改
    * @param moAdvert"""实体
    */
   Integer updateForStatus(@Param("id") Integer id,@Param("status")String status,@Param("updateTime")Timestamp updateTime);
}
