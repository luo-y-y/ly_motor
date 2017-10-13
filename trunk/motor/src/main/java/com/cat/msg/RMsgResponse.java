package com.cat.msg;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cat.interceptor.ThreadLocalHelper;


/**
 * 业务请求返回消息基类
 */
public class RMsgResponse implements java.io.Serializable{

   private static final long serialVersionUID = 1L;

   /**
    * 操作结果返回码，0表示成功，小于0表示失败
    */
   private int code = 0;

   /**
    * 认证出错信息，如果result<0，会有相应的错误信息提示，返回数据全部用户UTF-8编码
    */
   private String msg;
   
   /**
    * 响应数据内容
    */
   private Object response;
   
   /**
    * 系统日期
    */
   private String systemDate;

   /**
    * 版本名称
    */
   private String version =  (String) ThreadLocalHelper.getVale("version"); 
   
   /**
    * 版本日期
    */
   private String versionCode = (String) ThreadLocalHelper.getVale("versionCode"); ;
   
   /**
    * 是否强更
    */
   private String isForce = (String) ThreadLocalHelper.getVale("isForce"); ;
   
   /**
    * 下载地址
    */
   private String dowloadUrl = (String) ThreadLocalHelper.getVale("dowloadUrl"); ;
   
   RMsgResponse(){
   }
   
   public RMsgResponse(int code, String msg) {
      this.setCode(code);
      this.setMsg(msg);
   }
   
   public RMsgResponse(int code, String msg, Object response) {
      this.setCode(code);
      this.setMsg(msg);
      this.setResponse(response);
   }

   public int getCode(){
      return code;
   }

   public void setCode(int code){
      this.code = code;
   }

   public String getMsg(){
      return msg;
   }

   public void setMsg(String msg){
      this.msg = msg;
   }

   public Object getResponse(){
      return response;
   }
   
   public void setResponse(Object response){
      this.response = response;
   }
   
   public String getSystemDate(){
      SimpleDateFormat _format10 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      systemDate = _format10.format(new Date());
      return systemDate;
   }

   public void setSystemDate(String systemDate){
      this.systemDate = systemDate;
   }

   
   public String getVersion(){
      return version;
   }

   
   public void setVersion(String version){
      this.version = version;
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

   
   public String getDowloadUrl(){
      return dowloadUrl;
   }

   
   public void setDowloadUrl(String dowloadUrl){
      this.dowloadUrl = dowloadUrl;
   }
   
}
