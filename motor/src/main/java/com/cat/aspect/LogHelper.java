package com.cat.aspect;

import java.util.Date;

import org.apache.log4j.Logger;

import com.cat.common.lang.RDate;
import com.cat.interceptor.ThreadLocalHelper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class LogHelper {
	
   private static final int limitInt = 3000;
   /**
    * 对外HTTP接口调用
    */
	private static Logger exthttplog = Logger.getLogger("datalog");
	
	/**
	 * 项目接口调用
	 */
	private static Logger interlog = Logger.getLogger("datalog");
	
	/**
	 * 网页操作日志
	 */
	private static Logger weblog = Logger.getLogger("datalog");
	
	  /**
    * 推送操作日志
    */
   private static Logger pushlog = Logger.getLogger("pushlog");
   
	
	
	public static void extHttpRequestLog(String datagram) {
	   ThreadLocalHelper.setVale("ext_requestTime", new Date().getTime());
		ObjectNode logContent = JsonNodeFactory.instance.objectNode();
		logContent.put("requestTime", RDate.getCurrentTimeSssStr());
		logContent.put("logType", "ext-http-request");
		logContent.put("seq", ThreadLocalHelper.getSeq());
		logContent.put("datagram", limitStr(datagram));
		exthttplog.info(logContent);
	}
	
	public static void extHttpResponseLog( String datagram){
	   long nowTime =  new Date().getTime();
	   long extTime = (long) ThreadLocalHelper.getVale("ext_requestTime");
		ObjectNode logContent = JsonNodeFactory.instance.objectNode();
		logContent.put("responseTime", RDate.getCurrentTimeSssStr());
		logContent.put("logType", "ext-http-response");
		logContent.put("seq", ThreadLocalHelper.getSeq());
		logContent.put("interval-time",getTimeLow(nowTime, extTime));
		logContent.put("datagram", limitStr(datagram));
		exthttplog.info(logContent);
	}
	
	public static void extHttpExceptionResponseLog(String datagram){
	   long nowTime =  new Date().getTime();
      long extTime = (long) ThreadLocalHelper.getVale("ext_requestTime");
      ObjectNode logContent = JsonNodeFactory.instance.objectNode();
      logContent.put("responseTime", RDate.getCurrentTimeSssStr());
      logContent.put("logType", "ext-http-e-response");
      logContent.put("seq", ThreadLocalHelper.getSeq());
      logContent.put("interval-time",getTimeLow(nowTime, extTime));
      logContent.put("datagram", datagram);
      exthttplog.info(logContent);
   }

	
	public static void webRequestLog(String datagram) {
	   ThreadLocalHelper.setVale("web_requestTime", new Date().getTime());
      ObjectNode logContent = JsonNodeFactory.instance.objectNode();
      logContent.put("requestTime", RDate.getCurrentTimeSssStr());
      logContent.put("logType", "web-request");
      logContent.put("seq", ThreadLocalHelper.setSeq());
      logContent.put("datagram", limitStr(datagram));
      weblog.info(logContent);
   }
   
   public static void webResponseLog( String datagram){
      long nowTime =  new Date().getTime();
      long webTime = (long) ThreadLocalHelper.getVale("web_requestTime");
      ObjectNode logContent = JsonNodeFactory.instance.objectNode();
      logContent.put("responseTime", RDate.getCurrentTimeSssStr());
      logContent.put("logType", "web-response");
      logContent.put("seq", ThreadLocalHelper.getSeq());
      logContent.put("interval-time",getTimeLow(nowTime, webTime));
      logContent.put("datagram", limitStr(datagram));
      weblog.info(logContent);
   }
   
   public static void webExceptionResponseLog( String datagram){
      long nowTime =  new Date().getTime();
      long webTime = (long) ThreadLocalHelper.getVale("web_requestTime");
      ObjectNode logContent = JsonNodeFactory.instance.objectNode();
      logContent.put("responseTime", RDate.getCurrentTimeSssStr());
      logContent.put("logType", "web-e-response");
      logContent.put("seq", ThreadLocalHelper.getSeq());
      logContent.put("interval-time",getTimeLow(nowTime, webTime));
      logContent.put("datagram", datagram);
      weblog.info(logContent);
   }
	
   
   public static void interRequestLog( String datagram) {
      ThreadLocalHelper.setVale("inter_requestTime", new Date().getTime());
      ObjectNode logContent = JsonNodeFactory.instance.objectNode();
      logContent.put("requestTime", RDate.getCurrentTimeSssStr());
      logContent.put("logType", "inter-request");
      //接口从前端第一次进入记录日志信息
      logContent.put("seq", ThreadLocalHelper.setSeq());
      logContent.put("datagram", limitStr(datagram));
      interlog.info(logContent);
   }
   
   public static void interResponseLog(String datagram){
      long nowTime =  new Date().getTime();
      long interTime = (long) ThreadLocalHelper.getVale("inter_requestTime");
      ObjectNode logContent = JsonNodeFactory.instance.objectNode();
      logContent.put("responseTime", RDate.getCurrentTimeSssStr());
      logContent.put("logType", "inter-response");
      logContent.put("seq", ThreadLocalHelper.getSeq());
      logContent.put("interval-time",getTimeLow(nowTime, interTime));
      logContent.put("datagram",limitStr(datagram));
      interlog.info(logContent);
   }
   
   public static void interExceptionResponseLog(String datagram){
      long nowTime =  new Date().getTime();
      long interTime = (long) ThreadLocalHelper.getVale("inter_requestTime");
      ObjectNode logContent = JsonNodeFactory.instance.objectNode();
      logContent.put("responseTime", RDate.getCurrentTimeSssStr());
      logContent.put("logType", "inter-e-response");
      logContent.put("seq", ThreadLocalHelper.getSeq());
      logContent.put("interval-time",getTimeLow(nowTime, interTime));
      logContent.put("datagram", datagram);//异常 不限制
      interlog.info(logContent);
   }
   
   
   public static void wsdlRequestLog( String datagram) {
      ThreadLocalHelper.setVale("wsdl_requestTime", new Date().getTime());
      ObjectNode logContent = JsonNodeFactory.instance.objectNode();
      logContent.put("requestTime", RDate.getCurrentTimeSssStr());
      logContent.put("logType", "wsdl-request");
      //接口从前端第一次进入记录日志信息
      logContent.put("seq", ThreadLocalHelper.getSeq());
      logContent.put("datagram", limitStr(datagram));
      interlog.info(logContent);
   }
   
   public static void wsdlResponseLog(String datagram){
      long nowTime =  new Date().getTime();
      long interTime = (long) ThreadLocalHelper.getVale("wsdl_requestTime");
      ObjectNode logContent = JsonNodeFactory.instance.objectNode();
      logContent.put("responseTime", RDate.getCurrentTimeSssStr());
      logContent.put("logType", "wsdl-response");
      logContent.put("seq", ThreadLocalHelper.getSeq());
      logContent.put("interval-time",getTimeLow(nowTime, interTime));
      logContent.put("datagram",limitStr(datagram));
      interlog.info(logContent);
   }
   
   public static void wsdlExceptionResponseLog(String datagram){
      long nowTime =  new Date().getTime();
      long interTime = (long) ThreadLocalHelper.getVale("wsdl_requestTime");
      ObjectNode logContent = JsonNodeFactory.instance.objectNode();
      logContent.put("responseTime", RDate.getCurrentTimeSssStr());
      logContent.put("logType", "wsdl-e-response");
      logContent.put("seq", ThreadLocalHelper.getSeq());
      logContent.put("interval-time",getTimeLow(nowTime, interTime));
      logContent.put("datagram", datagram);//异常 不限制
      interlog.info(logContent);
   }
   
   public static void pushLog(String type,String... datagram){
      try{
         ObjectNode logContent = JsonNodeFactory.instance.objectNode();
         logContent.put("logType", "push_log");
         logContent.put("nowTime", RDate.getCurrentTimeSssStr());
         logContent.put("pushType", type);
         logContent.put("seq", ThreadLocalHelper.getSeq());
         String d = "";
         for(String s : datagram){
            d += s+",";
         }
         logContent.put("datagram", d);//异常 不限制
         pushlog.info(logContent);
      }catch(Exception e){
      }
   }
   
   private static String limitStr(String datagram){
      return datagram.toString().length() > limitInt ? datagram.substring(0, limitInt)+"..." : datagram ;
   }
   
   private static String getTimeLow(long nowTime,long startTime){
      long temp = nowTime - startTime;    //相差毫秒数
      float d = (float)temp/1000;
      String result = new  java.text.DecimalFormat("0.000").format(d);//相差分钟数
      return result;
   }
  
}


