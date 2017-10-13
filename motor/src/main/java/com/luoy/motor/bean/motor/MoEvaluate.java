
package com.luoy.motor.bean.motor;
import java.sql.Timestamp;
/**
 * 表名：[mo_evaluate]摩托评论
 * @author luoyang
 */
public class MoEvaluate
      implements 
          java.io.Serializable{

   private static final long serialVersionUID = 1L;

   /**
    * id
    */
   private Integer id;

   /**
    * 评论父ID
    */
   private Integer pid;

   /**
    * 评价人ID
    */
   private Integer userId;

   /**
    * 手机号码
    * 冗余的手机号码
    */
   private String tel;

   /**
    * 评论内容
    */
   private String content;

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

   public Integer getPid(){
      return pid;
   }

   public void setPid(Integer pid){
      this.pid = pid;
   }

   public Integer getUserId(){
      return userId;
   }

   public void setUserId(Integer userId){
      this.userId = userId;
   }

   public String getTel(){
      return tel;
   }

   public void setTel(String tel){
      this.tel = tel;
   }

   public String getContent(){
      return content;
   }

   public void setContent(String content){
      this.content = content;
   }

   public Timestamp getCreateTime(){
      return createTime;
   }

   public void setCreateTime(Timestamp createTime){
      this.createTime = createTime;
   }
 
}
