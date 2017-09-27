
package com.luoy.motor.service.advert.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.cat.common.lang.RDate;
import com.luoy.motor.bean.advert.MoAdvert;
import com.luoy.motor.dao.advert.MoAdvertDao;
import com.luoy.motor.service.advert.MoAdvertService;


/// ***********************import end*************************
@Service("moAdvertService")
public class MoAdvertServiceImpl  implements MoAdvertService{
  
   private static Logger _logger = Logger.getLogger(MoAdvertServiceImpl.class);
    
   @Autowired
   private MoAdvertDao moAdvertDao;


   @Override
   public Integer save(MoAdvert moAdvert){
      if(null == moAdvert){
         _logger.info("save  moAdvert is empty");
         return null;
      }
      moAdvert.setCreateTime(RDate.getCurrentTime());
      Map<String, Object> map = moAdvertDao.save(moAdvert);
      Integer id = Integer.parseInt(map.get("id").toString());
      return id;
   }

   @Override
   public boolean delete(Integer id){
      if(null == id){
         _logger.info("delete id is empty");
         return false;
      }
      MoAdvert entity = load(id);
      if(null == entity) {
         _logger.info("delete  record not exist. id="+id);
         return false;
      }
      Integer result = moAdvertDao.delete(id);
      return result > 0;
   }

   @Override
   public RResult update(MoAdvert oldEntity, MoAdvert newEntity){
      if(null == newEntity){
         _logger.info("update  param is empty");
         return new RResult(RResult.MSG_FAIL, RResult.paramNull, "entity");
      }
      newEntity.setUpdateTime(RDate.getCurrentTime());
      Integer result = moAdvertDao.update(newEntity);
       if(result > 0){
          return new RResult(RResult.MSG_SUCCESS);
         }
       return new RResult(RResult.MSG_FAIL);
   }

   @Override
   public MoAdvert load(Integer id){
      if(null == id){
         _logger.info("load  id is empty");
         return null;
      }
      MoAdvert moAdvert = new MoAdvert();
      moAdvert.setId(id);
      return moAdvertDao.load(moAdvert);
   }

   @Override
   public MoAdvert load(MoAdvert moAdvert){
      if(null == moAdvert){
         _logger.info("load  moAdvert is empty");
         return null;
      }
      return moAdvertDao.load(moAdvert);
   }


   @Override
   public List<MoAdvert> findList(MoAdvert moAdvert, Integer skip, Integer max, Map<String, Object> params){
      return moAdvertDao.findList(moAdvert, skip, max, params);
   }

   @Override
   public PageControlInfo findPageList(MoAdvert moAdvert, Integer skip, Integer max, Map<String, Object> params){
      return moAdvertDao.findPageList(moAdvert, skip, max, params);
   }

   @Override
   public List<MoAdvert> findListForApp(){
      return moAdvertDao.findListForApp();
   }

   @Override
   public RResult updateForStatus(Integer id,
                                  String status){
      Integer result = moAdvertDao.updateForStatus(id, status, RDate.getCurrentTime());
      if(result > 0){
         return new RResult(RResult.MSG_SUCCESS);
        }
      return new RResult(RResult.MSG_FAIL);
   }

}
