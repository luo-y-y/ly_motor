
package com.luoy.motor.bean.user;
import java.sql.Timestamp;
/**
 * 表名：[mo_version]版本
 * @author luoyang
 */
public class MoVersion
      implements 
          java.io.Serializable{

   private static final long serialVersionUID = 1L;

   /**
    * id
    */
   private Integer id;

   /**
    * 版本名称
    */
   private String versionName;

   /**
    * 版本code
    * 实际使用时间错 20170912183400
    */
   private String versionCode;

   /**
    *  是否强更
    * Y 强更  N 非强制更新
    */
   private String isForce;

   /**
    * 系统版本
    * ios android
    */
   public static final String SysTypeIos = "ios";
   
   public static final String SysTypeAndroid = "android";
   
   private String sysType;

   /**
    * 创建时间
    */
   private Timestamp createTime;

   /**
    * update_time
    */
   private Timestamp updateTime;



   public Integer getId(){
      return id;
   }

   public void setId(Integer id){
      this.id = id;
   }

   public String getVersionName(){
      return versionName;
   }

   public void setVersionName(String versionName){
      this.versionName = versionName;
   }

   public String getVersionCode(){
      return versionCode;
   }

   public void setVersionCode(String versionCode){
      this.versionCode = versionCode;
   }

   public String getIsForce(){
      return isForce;
   }

   public void setIsForce(String isForce){
      this.isForce = isForce;
   }

   public String getSysType(){
      return sysType;
   }

   public void setSysType(String sysType){
      this.sysType = sysType;
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
