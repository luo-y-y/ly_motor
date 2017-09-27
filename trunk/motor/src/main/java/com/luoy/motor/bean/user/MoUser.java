package com.luoy.motor.bean.user;

import java.sql.Date;
import java.sql.Timestamp;

import com.cat.common.listener.RSystemConfig;

/**
 * 表名：[mo_user]mo_user
 * @author luoyang
 */
public class MoUser
      implements
         java.io.Serializable{

   private static final long serialVersionUID = 1L;

   /**
    * id
    */
   private Integer id;

   /**
    * 手机号
    */
   private String tel;

   /**
    * 用户名
    */
   private String userName;

   /**
    * 密码
    * MD5 加密
    */
   private String password;

   /**
    * 真实姓名
    */
   private String realName;

   /**
    * 身份证
    */
   private String idCard;

   /**
    * 头像
    */
   private String headUrl;
   
   /**
    * 头像
    */
   private String  fileRdfUrl = RSystemConfig.getValue("fileHttpUrl");

   public static final String Status_Unact = "unact";

   public static final String Status_On = "on";

   public static final String Status_Off = "off";

   public static final String Status_lock = "lock";

   /**
    * 状态
    * on 启用 off 不启用 lock 锁定
    */
   private String status;

   /**
    * 密码错误次数
    * 错误次数10次 锁定
    */
   private Integer times;

   /**
    * 错误日期
    */
   private Date timesDate;

   /**
    * 类型
    * 人员类型
    */
   private String type;

   /**
    * 短信验证码
    */
   private String msgCode;
   
   /**
    * 短信验证码时间
    */
   private Timestamp msgCodeTime;

   /**
    * token
    */
   private String token;

   /**
    * token_time
    */
   private Timestamp tokenTime;

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

   public String getTel(){
      return tel;
   }

   public void setTel(String tel){
      this.tel = tel;
   }

   public String getUserName(){
      return userName;
   }

   public void setUserName(String userName){
      this.userName = userName;
   }

   public String getPassword(){
      return password;
   }

   public void setPassword(String password){
      this.password = password;
   }

   public String getRealName(){
      return realName;
   }

   public void setRealName(String realName){
      this.realName = realName;
   }

   public String getIdCard(){
      return idCard;
   }

   public void setIdCard(String idCard){
      this.idCard = idCard;
   }

   public String getHeadUrl(){
      return headUrl;
   }

   public void setHeadUrl(String headUrl){
      this.headUrl = headUrl;
   }

   public String getStatus(){
      return status;
   }

   public void setStatus(String status){
      this.status = status;
   }

   public Integer getTimes(){
      return times;
   }

   public void setTimes(Integer times){
      this.times = times;
   }

   public Date getTimesDate(){
      return timesDate;
   }

   public void setTimesDate(Date timesDate){
      this.timesDate = timesDate;
   }

   public String getType(){
      return type;
   }

   public void setType(String type){
      this.type = type;
   }

   public String getMsgCode(){
      return msgCode;
   }

   public void setMsgCode(String msgCode){
      this.msgCode = msgCode;
   }

   public Timestamp getMsgCodeTime(){
      return msgCodeTime;
   }

   
   public void setMsgCodeTime(Timestamp msgCodeTime){
      this.msgCodeTime = msgCodeTime;
   }

   public String getToken(){
      return token;
   }

   public void setToken(String token){
      this.token = token;
   }

   public Timestamp getTokenTime(){
      return tokenTime;
   }

   public void setTokenTime(Timestamp tokenTime){
      this.tokenTime = tokenTime;
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

   
   public String getFileRdfUrl(){
      return fileRdfUrl;
   }

   
   public void setFileRdfUrl(String fileRdfUrl){
      this.fileRdfUrl = fileRdfUrl;
   }

}
