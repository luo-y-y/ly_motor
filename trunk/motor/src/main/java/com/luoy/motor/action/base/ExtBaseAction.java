package com.luoy.motor.action.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cat.common.exception.RequestException;
import com.cat.common.json.RJson;
import com.cat.common.lang.RString;
import com.cat.interceptor.ThreadLocalHelper;

/**
 * 基础action
 */
public class ExtBaseAction{

   public Logger _logger = Logger.getLogger(this.getClass());

   /**
    * 请求
    */
   protected HttpServletRequest request;

   /**
    * 响应
    */
   protected HttpServletResponse response;

   protected Map<String, Object> params;

   public String appId;

   public String version;

   /**
    * 项目路径
    */
   public String basePath;

   public Integer userId;


   @ModelAttribute
   public void setReqAndRes(HttpServletRequest request,
                            HttpServletResponse response) throws  RequestException{
      this.request = request;
      this.response = response;
      this.basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
      this.appId = request.getHeader("appId");
      this.version = request.getHeader("version");
      String userIdStr = request.getHeader("userId");
      if(RString.isNotBlank(userIdStr)){
          this.userId = Integer.parseInt(request.getHeader("userId"));
      }
      
      params = getRequestMap();
   }


   /**
    * 接收发送过来的数据流
    * @return
    * @throws RequestException 
 * @throws IOException 
 * @throws JsonMappingException 
 * @throws JsonParseException 
    */
   public Map<String, Object> getRecieveData() throws RequestException {
      String inputLine = null;
      // 接收到的数据
      StringBuffer recieveData = new StringBuffer();
      BufferedReader in = null;
      try{
         in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
         while((inputLine = in.readLine()) != null){
            recieveData.append(inputLine);
         }
      }catch(IOException e){
      }finally{
         try{
            if(null != in){
               in.close();
            }
         }catch(IOException e){
         }
      }
      String requestData = recieveData.toString();
      return RJson.parseJson2Map(requestData);
   }

   public Map<String, Object> getRequestMap() throws RequestException {
      //从线程缓存中获取解密好的数据
      String requestData = ThreadLocalHelper.getRequestStr();
      if(RString.isBlank(requestData)) {
         return new HashMap<String, Object>();
      }
      return RJson.parseJson2Map(requestData);
   }


   /**
    * 获取写对象
    * @return  PrintWriter
    */
   public PrintWriter getPrintWriter(){
      PrintWriter out = null;
      try{
         out = response.getWriter();
      }catch(IOException e){

      }
      return out;
   }

   public String getVersion(){
      return version;
   }

   public void setVersion(String version){
      this.version = version;
   }

   public String getAppId(){
      return appId;
   }

   public void setAppId(String appId){
      this.appId = appId;
   }

}
