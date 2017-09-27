package com.cat.interceptor;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.cat.common.lang.RDate;
import com.cat.common.lang.RString;

/**
 * 线程缓存帮助类
 * @author luoyang
 *
 */
public class ThreadLocalHelper{

   public  static Logger _logger = Logger.getLogger(ThreadLocalHelper.class);
   
   private static final String Request_Str = "requestStr";

   private static final String Seq = "seq";

   private static ThreadLocal<HashMap<String, Object>> local = new ThreadLocal<HashMap<String, Object>>(){

      @Override
      protected HashMap<String, Object> initialValue(){
         // TODO Auto-generated method stub
         return new HashMap<String, Object>();
      }
   };

   public static String getRequestStr(){
      HashMap<String, Object> map = local.get();
      if(null == map){
         return "";
      }
      return RString.toString(map.get(Request_Str));
   }

   public static void setRequestStr(String value){
      HashMap<String, Object> map = local.get();
      if(null == map){
         map = new HashMap<String, Object>();
      }
      map.put(Request_Str, value);
      local.set(map);
   }
   
   public static void setVale(String key,Object value){
	      HashMap<String, Object> map = local.get();
	      if(null == map){
	         map = new HashMap<String, Object>();
	      }
	      map.put(key, value);
	      local.set(map);
	   }
   
   public static Object getVale(String key){
	      HashMap<String, Object> map = local.get();
	      if(null == map){
	         map = new HashMap<String, Object>();
	      }
	      return map.get(key);
	   }

   /**
    * 获取当前线程的序列号
    */
   public static String setSeq(){
      HashMap<String, Object> map = local.get();
      if(null == map){
         map = new HashMap<String, Object>();
      }
      String seq = RDate.getTimeSeq();
      map.put(Seq, seq);
      local.set(map);
      return seq;
   }
   
   /**
    * 获取当前线程的序列号
    */
   public static String getSeq(){
      HashMap<String, Object> map = local.get();
      if(null == map){
         return "";
      }
      return RString.toString(map.get(Seq));
   }

   public static void remove(){
      local.remove();
   }
}
