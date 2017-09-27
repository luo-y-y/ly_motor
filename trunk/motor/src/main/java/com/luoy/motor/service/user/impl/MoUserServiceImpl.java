
package com.luoy.motor.service.user.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.luoy.motor.bean.user.MoUser;
import com.luoy.motor.dao.user.MoUserDao;
import com.luoy.motor.service.user.MoUserService;


/// ***********************import end*************************
@Service("moUserService")
public class MoUserServiceImpl  implements MoUserService{
  
   private static Logger _logger = Logger.getLogger(MoUserServiceImpl.class);
    
   @Autowired
   private MoUserDao moUserDao;


   @Override
   public Integer save(MoUser moUser){
      if(null == moUser){
         _logger.info("save  moUser is empty");
         return null;
      }

      Map<String, Object> map = moUserDao.save(moUser);
      Integer id = Integer.parseInt(map.get("id").toString());
      return id;
   }

   @Override
   public boolean delete(Integer id){
      if(null == id){
         _logger.info("delete id is empty");
         return false;
      }
      MoUser entity = load(id);
      if(null == entity) {
         _logger.info("delete  record not exist. id="+id);
         return false;
      }
      Integer result = moUserDao.delete(id);
      return result > 0;
   }

   @Override
   public RResult update(MoUser newEntity){
      if(null == newEntity){
         _logger.info("update  param is empty");
         return new RResult(RResult.MSG_FAIL, RResult.paramNull, "entity");
      }

      Integer result = moUserDao.update(newEntity);
       if(result > 0){
          return new RResult(RResult.MSG_SUCCESS);
         }
       return new RResult(RResult.MSG_FAIL);
   }

   @Override
   public MoUser load(Integer id){
      if(null == id){
         _logger.info("load  id is empty");
         return null;
      }
      MoUser moUser = new MoUser();
      moUser.setId(id);
      return moUserDao.load(moUser);
   }

   @Override
   public MoUser load(MoUser moUser){
      if(null == moUser){
         _logger.info("load  moUser is empty");
         return null;
      }
      return moUserDao.load(moUser);
   }


   @Override
   public List<MoUser> findList(MoUser moUser, Integer skip, Integer max, Map<String, Object> params){
      return moUserDao.findList(moUser, skip, max, params);
   }

   @Override
   public PageControlInfo findPageList(MoUser moUser, Integer skip, Integer max, Map<String, Object> params){
      return moUserDao.findPageList(moUser, skip, max, params);
   }

   @Override
   public MoUser loadByTel(String tel){
      MoUser moUser = new MoUser();
      moUser.setTel(tel);
      return moUserDao.load(moUser);
   }

   @Override
   public MoUser loadByTelAndPassword(String tel,
                                      String md5Password){
      MoUser moUser = new MoUser();
      moUser.setTel(tel);
      moUser.setPassword(md5Password);
      return moUserDao.load(moUser);
   }

}
