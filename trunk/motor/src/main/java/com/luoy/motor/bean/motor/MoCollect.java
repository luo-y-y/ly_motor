
package com.luoy.motor.bean.motor;
import java.sql.Timestamp;
/**
 * 表名：[mo_collect]摩托收藏
 * @author luoyang
 */
public class MoCollect
      implements 
          java.io.Serializable{

   private static final long serialVersionUID = 1L;

   /**
    * 主键
    */
   private Integer id;

   /**
    * 用户ID
    */
   private Integer userId;

   /**
    * 收藏的ID
    */
   private Integer pid;

   /**
    * 创建时间
    */
   private Timestamp createTime;



   public Integer getId(){
      return id;
   }

   public void setId(Integer id){
      this.id = id;
   }

   public Integer getUserId(){
      return userId;
   }

   public void setUserId(Integer userId){
      this.userId = userId;
   }


   
   public Integer getPid(){
      return pid;
   }

   
   public void setPid(Integer pid){
      this.pid = pid;
   }

   public Timestamp getCreateTime(){
      return createTime;
   }

   public void setCreateTime(Timestamp createTime){
      this.createTime = createTime;
   }
 
}
