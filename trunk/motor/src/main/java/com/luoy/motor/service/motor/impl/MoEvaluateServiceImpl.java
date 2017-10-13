
package com.luoy.motor.service.motor.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.luoy.motor.bean.motor.MoEvaluate;
import com.luoy.motor.dao.motor.MoEvaluateDao;
import com.luoy.motor.service.motor.MoEvaluateService;


/// ***********************import end*************************
@Service("moEvaluateService")
public class MoEvaluateServiceImpl  implements MoEvaluateService{
  
   private static Logger _logger = Logger.getLogger(MoEvaluateServiceImpl.class);
    
   @Autowired
   private MoEvaluateDao moEvaluateDao;


   @Override
   public Integer save(MoEvaluate moEvaluate){
      if(null == moEvaluate){
         _logger.info("save  moEvaluate is empty");
         return null;
      }

      Map<String, Object> map = moEvaluateDao.save(moEvaluate);
      Integer id = Integer.parseInt(map.get("id").toString());
      return id;
   }

   @Override
   public boolean delete(Integer id){
      if(null == id){
         _logger.info("delete id is empty");
         return false;
      }
      MoEvaluate entity = load(id);
      if(null == entity) {
         _logger.info("delete  record not exist. id="+id);
         return false;
      }
      Integer result = moEvaluateDao.delete(id);
      return result > 0;
   }

   @Override
   public RResult update(MoEvaluate oldEntity, MoEvaluate newEntity){
      if(null == newEntity){
         _logger.info("update  param is empty");
         return new RResult(RResult.MSG_FAIL, RResult.paramNull, "entity");
      }

      Integer result = moEvaluateDao.update(newEntity);
       if(result > 0){
          return new RResult(RResult.MSG_SUCCESS);
         }
       return new RResult(RResult.MSG_FAIL);
   }

   @Override
   public MoEvaluate load(Integer id){
      if(null == id){
         _logger.info("load  id is empty");
         return null;
      }
      MoEvaluate moEvaluate = new MoEvaluate();
      moEvaluate.setId(id);
      return moEvaluateDao.load(moEvaluate);
   }

   @Override
   public MoEvaluate load(MoEvaluate moEvaluate){
      if(null == moEvaluate){
         _logger.info("load  moEvaluate is empty");
         return null;
      }
      return moEvaluateDao.load(moEvaluate);
   }


   @Override
   public List<MoEvaluate> findList(MoEvaluate moEvaluate, Integer skip, Integer max, Map<String, Object> params){
      return moEvaluateDao.findList(moEvaluate, skip, max, params);
   }

   @Override
   public PageControlInfo findPageList(MoEvaluate moEvaluate, Integer skip, Integer max, Map<String, Object> params){
      return moEvaluateDao.findPageList(moEvaluate, skip, max, params);
   }

}
