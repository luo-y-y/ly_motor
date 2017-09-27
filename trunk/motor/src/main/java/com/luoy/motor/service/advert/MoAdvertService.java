
package com.luoy.motor.service.advert;
import java.util.List;
import java.util.Map;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.luoy.motor.bean.advert.MoAdvert;


public interface MoAdvertService{

   /**
    * 保存返回主键
    * @param moAdvert 实体
    * @return Long
    */
   Integer save(MoAdvert moAdvert);

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
   RResult update(MoAdvert oldEntity, MoAdvert newEntity);
   
   /**
    * 根据主键获取实体
    * @param id"""主键
    * @return MoAdvert
    */
   MoAdvert load(Integer id);

   /**
    * 根据条件获取实体
    * @param moAdvert   实体
    * @return MoAdvert
    */
   MoAdvert load(MoAdvert moAdvert);
   

   /**
    * 根据条件获取实体列表,当skip、max均等于null获取全部
    * @param moAdvert"""实体
    * @param skip"""当前页数
    * @param max"""页记录数
    * @param params"""其他参数
    * @return List<MoAdvert>
    */
   List<MoAdvert> findList(MoAdvert moAdvert, Integer skip, Integer max, Map<String, Object> params);
   
   /**
    * 根据条件获取实体分页列表
    * @param moAdvert"""实体
    * @param skip"""        当前页数(当等于null时,返回null)
    * @param max"""        页记录数(默认为20)
    * @param params"""        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageList(MoAdvert moAdvert, Integer skip, Integer max, Map<String, Object> params);


   /**
    * app 查询广告页
    * @return
    */
   List<MoAdvert> findListForApp();

   /**
    * 修改
    * @param oldEntity     旧实体
    * @param newEntity     新实体
    * @return FResult
    */
   RResult updateForStatus(Integer id,String status);
   
 }
