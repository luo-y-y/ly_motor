
package com.luoy.motor.service.user;
import java.util.List;
import java.util.Map;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.luoy.motor.bean.user.MoVersion;


public interface MoVersionService{

   /**
    * 保存返回主键
    * @param moVersion 实体
    * @return Long
    */
   Integer save(MoVersion moVersion);

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
   RResult update(MoVersion oldEntity, MoVersion newEntity);
   
   /**
    * 根据主键获取实体
    * @param id"""主键
    * @return MoVersion
    */
   MoVersion load(Integer id);

   /**
    * 根据条件获取实体
    * @param moVersion   实体
    * @return MoVersion
    */
   MoVersion load(MoVersion moVersion);
   

   /**
    * 根据条件获取实体列表,当skip、max均等于null获取全部
    * @param moVersion"""实体
    * @param skip"""当前页数
    * @param max"""页记录数
    * @param params"""其他参数
    * @return List<MoVersion>
    */
   List<MoVersion> findList(MoVersion moVersion, Integer skip, Integer max, Map<String, Object> params);
   
   /**
    * 根据条件获取实体分页列表
    * @param moVersion"""实体
    * @param skip"""        当前页数(当等于null时,返回null)
    * @param max"""        页记录数(默认为20)
    * @param params"""        其他参数
    * @return PageControlInfo
    */
   PageControlInfo findPageList(MoVersion moVersion, Integer skip, Integer max, Map<String, Object> params);

   
   MoVersion getMoVersionByType(String type);
   
   /**
    * 刷新版本缓存
    * @return
    */
   void flushVersion();
 }
