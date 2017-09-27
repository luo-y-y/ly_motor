
package com.luoy.motor.service.user.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.cat.common.lang.RDate;
import com.luoy.motor.bean.user.MoVersion;
import com.luoy.motor.dao.user.MoVersionDao;
import com.luoy.motor.service.user.MoVersionService;


/// ***********************import end*************************
@Service("moVersionService")
public class MoVersionServiceImpl  implements MoVersionService{
  
   private static Logger _logger = Logger.getLogger(MoVersionServiceImpl.class);
    
   @Autowired
   private MoVersionDao moVersionDao;


   @Override
   public Integer save(MoVersion moVersion){
      if(null == moVersion){
         _logger.info("save  moVersion is empty");
         return null;
      }
      moVersion.setCreateTime(RDate.getCurrentTime());
      Map<String, Object> map = moVersionDao.save(moVersion);
      flushVersion();
      Integer id = Integer.parseInt(map.get("id").toString());
      return id;
   }

   @Override
   public boolean delete(Integer id){
      if(null == id){
         _logger.info("delete id is empty");
         return false;
      }
      MoVersion entity = load(id);
      if(null == entity) {
         _logger.info("delete  record not exist. id="+id);
         return false;
      }
      Integer result = moVersionDao.delete(id);
      flushVersion();
      return result > 0;
   }

   @Override
   public RResult update(MoVersion oldEntity, MoVersion newEntity){
      if(null == newEntity){
         _logger.info("update  param is empty");
         return new RResult(RResult.MSG_FAIL, RResult.paramNull, "entity");
      }
      newEntity.setUpdateTime(RDate.getCurrentTime());
      Integer result = moVersionDao.update(newEntity);
       if(result > 0){
          flushVersion();
          return new RResult(RResult.MSG_SUCCESS);
         }
       return new RResult(RResult.MSG_FAIL);
   }

   @Override
   public MoVersion load(Integer id){
      if(null == id){
         _logger.info("load  id is empty");
         return null;
      }
      MoVersion moVersion = new MoVersion();
      moVersion.setId(id);
      return moVersionDao.load(moVersion);
   }

   @Override
   public MoVersion load(MoVersion moVersion){
      if(null == moVersion){
         _logger.info("load  moVersion is empty");
         return null;
      }
      return moVersionDao.load(moVersion);
   }


   @Override
   public List<MoVersion> findList(MoVersion moVersion, Integer skip, Integer max, Map<String, Object> params){
      return moVersionDao.findList(moVersion, skip, max, params);
   }

   @Override
   public PageControlInfo findPageList(MoVersion moVersion, Integer skip, Integer max, Map<String, Object> params){
      return moVersionDao.findPageList(moVersion, skip, max, params);
   }

   public  Map<String, MoVersion> getVersion(){
         if(null != versionInfo && atLastTime != 0L){
            //已有缓存的数据了 比对时间
            long nowTime =  new Date().getTime();
            if((nowTime - atLastTime) < atExpireTime){
               //未过期
               return versionInfo;
            }
         }
         return getVersionInfo();
   }

   
   private  Map<String, MoVersion> getVersionInfo(){
      MoVersion ios = moVersionDao.loadNewestByType(MoVersion.SysTypeIos);
      MoVersion android = moVersionDao.loadNewestByType(MoVersion.SysTypeAndroid);
      if(null == ios || null == android){
         atLastTime = 0L;
         return null;
      }
      versionInfo = new HashMap<String, MoVersion>();
      versionInfo.put(MoVersion.SysTypeIos, ios);
      versionInfo.put(MoVersion.SysTypeAndroid, android);
      atLastTime = RDate.getCurrentTime().getTime();
      return versionInfo;
   }
   
   private static Map<String, MoVersion> versionInfo = null;
   
   private static long atLastTime = 0L;
   
   //一个半小时过期时间
   private static long atExpireTime = 5400 * 1000;


   @Override
   public  MoVersion  getMoVersionByType(String type){
      Map<String, MoVersion> map =  getVersion();
      if(null == map){
         map = getVersionInfo();
      }
      
      if(null == map){
         return null;
      }
      
      return map.get(type);
   }

   @Override
   public void flushVersion(){
      // TODO Auto-generated method stub
      versionInfo = null;
      getVersionInfo();
   }
   
}
