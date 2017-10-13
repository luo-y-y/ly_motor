
package com.luoy.motor.service.motor.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.luoy.motor.bean.motor.MoCollect;
import com.luoy.motor.dao.motor.MoCollectDao;
import com.luoy.motor.service.motor.MoCollectService;


/// ***********************import end*************************
@Service("moCollectService")
public class MoCollectServiceImpl  implements MoCollectService{
  
   private static Logger _logger = Logger.getLogger(MoCollectServiceImpl.class);
    
   @Autowired
   private MoCollectDao moCollectDao;


   @Override
   public Integer save(MoCollect moCollect){
      if(null == moCollect){
         _logger.info("save  moCollect is empty");
         return null;
      }

      Map<String, Object> map = moCollectDao.save(moCollect);
      Integer id = Integer.parseInt(map.get("id").toString());
      return id;
   }

   @Override
   public boolean delete(Integer id){
      if(null == id){
         _logger.info("delete id is empty");
         return false;
      }
      MoCollect entity = load(id);
      if(null == entity) {
         _logger.info("delete  record not exist. id="+id);
         return false;
      }
      Integer result = moCollectDao.delete(id);
      return result > 0;
   }

   @Override
   public RResult update(MoCollect oldEntity, MoCollect newEntity){
      if(null == newEntity){
         _logger.info("update  param is empty");
         return new RResult(RResult.MSG_FAIL, RResult.paramNull, "entity");
      }

      Integer result = moCollectDao.update(newEntity);
       if(result > 0){
          return new RResult(RResult.MSG_SUCCESS);
         }
       return new RResult(RResult.MSG_FAIL);
   }

   @Override
   public MoCollect load(Integer id){
      if(null == id){
         _logger.info("load  id is empty");
         return null;
      }
      MoCollect moCollect = new MoCollect();
      moCollect.setId(id);
      return moCollectDao.load(moCollect);
   }

   @Override
   public MoCollect load(MoCollect moCollect){
      if(null == moCollect){
         _logger.info("load  moCollect is empty");
         return null;
      }
      return moCollectDao.load(moCollect);
   }


   @Override
   public List<MoCollect> findList(MoCollect moCollect, Integer skip, Integer max, Map<String, Object> params){
      return moCollectDao.findList(moCollect, skip, max, params);
   }

   @Override
   public PageControlInfo findPageList(MoCollect moCollect, Integer skip, Integer max, Map<String, Object> params){
      return moCollectDao.findPageList(moCollect, skip, max, params);
   }

   @Override
   public boolean deleteByPid(Integer pid,Integer userId){
      Integer result = moCollectDao.deleteByPid(pid, userId);
      return result > 0;
   }

}
