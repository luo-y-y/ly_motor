
package com.luoy.motor.bean.advert;
import java.sql.Timestamp;
/**
 * 表名：[mo_advert]广告
 * @author luoyang
 */
public class MoAdvert
      implements 
          java.io.Serializable{

   private static final long serialVersionUID = 1L;

   /**
    * id
    */
   private Integer id;

   /**
    * 名称
    */
   private String name;

   /**
    * 图片地址
    */
   private String imgUrl;

   /**
    * 优先级
    */
   private String rank;

   /**
    * 状态
    * on  上线   off  下线
    */
   private String status;

   /**
    * 创建时间
    */
   private Timestamp createTime;

   /**
    * 修改时间
    */
   private Timestamp updateTime;



   public Integer getId(){
      return id;
   }

   public void setId(Integer id){
      this.id = id;
   }

   public String getName(){
      return name;
   }

   public void setName(String name){
      this.name = name;
   }

   public String getImgUrl(){
      return imgUrl;
   }

   public void setImgUrl(String imgUrl){
      this.imgUrl = imgUrl;
   }

   public String getRank(){
      return rank;
   }

   public void setRank(String rank){
      this.rank = rank;
   }

   public String getStatus(){
      return status;
   }

   public void setStatus(String status){
      this.status = status;
   }

   public Timestamp getCreateTime(){
      return createTime;
   }

   public void setCreateTime(Timestamp createTime){
      this.createTime = createTime;
   }

   public Timestamp getUpdateTime(){
      return updateTime;
   }

   public void setUpdateTime(Timestamp updateTime){
      this.updateTime = updateTime;
   }
 
}
