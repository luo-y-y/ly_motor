
package com.luoy.motor.service.motor.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.cat.common.lang.RDate;
import com.luoy.motor.bean.motor.MoMotor;
import com.luoy.motor.dao.motor.MoMotorDao;
import com.luoy.motor.service.motor.MoMotorService;


/// ***********************import end*************************
@Service("moMotorService")
public class MoMotorServiceImpl  implements MoMotorService{
  
   private static Logger _logger = Logger.getLogger(MoMotorServiceImpl.class);
    
   @Autowired
   private MoMotorDao moMotorDao;


   @Override
   public Integer save(MoMotor moMotor){
      if(null == moMotor){
         _logger.info("save  moMotor is empty");
         return null;
      }

      Map<String, Object> map = moMotorDao.save(moMotor);
      Integer id = Integer.parseInt(map.get("id").toString());
      return id;
   }

   @Override
   public boolean delete(Integer id){
      if(null == id){
         _logger.info("delete id is empty");
         return false;
      }
      MoMotor entity = load(id);
      if(null == entity) {
         _logger.info("delete  record not exist. id="+id);
         return false;
      }
      Integer result = moMotorDao.delete(id);
      return result > 0;
   }

   @Override
   public RResult update(MoMotor newEntity){
      if(null == newEntity){
         _logger.info("update  param is empty");
         return new RResult(RResult.MSG_FAIL, RResult.paramNull, "entity");
      }

      Integer result = moMotorDao.update(newEntity);
       if(result > 0){
          return new RResult(RResult.MSG_SUCCESS);
         }
       return new RResult(RResult.MSG_FAIL);
   }

   @Override
   public MoMotor load(Integer id){
      if(null == id){
         _logger.info("load  id is empty");
         return null;
      }
      MoMotor moMotor = new MoMotor();
      moMotor.setId(id);
      return moMotorDao.load(moMotor,null);
   }

   @Override
   public MoMotor load(MoMotor moMotor){
      if(null == moMotor){
         _logger.info("load  moMotor is empty");
         return null;
      }
      return moMotorDao.load(moMotor,null);
   }


   @Override
   public List<MoMotor> findList(MoMotor moMotor, Integer skip, Integer max, Map<String, Object> params){
      return moMotorDao.findList(moMotor, skip, max, params);
   }

   @Override
   public PageControlInfo findPageList(MoMotor moMotor, Integer skip, Integer max, Map<String, Object> params){
      return moMotorDao.findPageList(moMotor, skip, max, params);
   }



   @Override
   public boolean updateStatus(Integer id,
                               String status){
      Integer i =  moMotorDao.updateForStatus(id, status, RDate.getCurrentTime());
      if(i > 0){
         return true;
        }
      return false;
   }

   @Override
   public PageControlInfo findPageListForEva(Integer userId,
                                             Integer skip,
                                             Integer max,
                                             Map<String, Object> params){
      MoMotor moMotor = new MoMotor();
      moMotor.setUserId(userId);
      return moMotorDao.findPageListForEva(moMotor, skip, max, params);
   }

   @Override
   public PageControlInfo findPageListForCollect(Integer userId,
                                                 Integer skip,
                                                 Integer max,
                                                 Map<String, Object> params){
      MoMotor moMotor = new MoMotor();
      moMotor.setUserId(userId);
      return moMotorDao.findPageListForCollect(moMotor, skip, max, params);
   }

   @Override
   public MoMotor load(Integer id,
                       Integer userId){
      if(null == id){
         _logger.info("load  id is empty");
         return null;
      }
      MoMotor moMotor = new MoMotor();
      moMotor.setId(id);
      Map<String, Object> m =  new HashMap<String, Object>();
      m.put("collectUserId", userId);
      return moMotorDao.load(moMotor,m);
   }

   @Override
   public boolean updateIsHot(Integer id,
                              String isHot){
      Integer i =  moMotorDao.updateForHot(id, isHot, RDate.getCurrentTime());
      if(i > 0){
         return true;
        }
      return false;
   }

}
