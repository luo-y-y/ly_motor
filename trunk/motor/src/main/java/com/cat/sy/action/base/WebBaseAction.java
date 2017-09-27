package com.cat.sy.action.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.cat.common.bean.tree.Node;
import com.cat.common.bean.tree.easyUI.FEasyUITree;
import com.cat.common.bean.tree.easyUI.Tree;
import com.cat.common.exception.RequestException;
import com.cat.common.json.RJson;
import com.cat.common.lang.RList;
import com.cat.common.lang.RString;
import com.cat.common.listener.RSystemConfig;
import com.cat.sy.bean.SyUser;

/**
 * 基础action
 * @author ZZM
 *
 */
public class WebBaseAction{

   public Logger _logger = Logger.getLogger(this.getClass());
  /**
    * 请求
    */
   protected HttpServletRequest request;
   /**
    * 响应
    */
   protected HttpServletResponse response;
   /**
    * 会话
    */
   protected HttpSession session;
   //分页行数
   public static final String PAGE_ROWS = "rows";

   //分页总条数
   public static final String PAGE_TOTAL = "total";

   //其他查询条件
   public static final String OSEARCH = "osearch";
   //是否检查版本
   public static final String ISCHECKOVER = "isCheckOver";
   
   /**
    * 每页显示的记录数 
    */
   public int rows = 20;

   /**
    * 当前第几页 
    */
   public int page = 1;


   @ModelAttribute  
   public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
       this.request = request;  
       this.response = response;  
       this.session = request.getSession();  
   }
   
   /**
    * 设置分页参数
    * @param page    分页页数
    * @param rows    一页显示总记录数
    */
   @ModelAttribute
   public void setPageParams(String page,
                             String rows){
      if(RString.isNotBlank(page)){
         this.page = Integer.valueOf(page);
      }
      if(RString.isNotBlank(rows)){
         this.rows = Integer.valueOf(rows);
      }
   }

   /**
    * 页面起始条数
    * @return
    */
   public int getStart(){
      return (page - 1) * rows + 1;
   }

   /**
    * 分页条数
    * @return
    */
   public int getEnd(){
      return rows;
   }

   /**
    * 根据参数名称，获取参数值
    * @param name 参数名字
    * @return String
    */
   public String getRequestParameter(String name){
      String value = request.getParameter(name);
      return value;
   }

   /**
    * 获取请求的所有参数Map集合
    * @return Map
    */
   @SuppressWarnings("unchecked")
   public Map<String, Object> getRequestParameterMap(){
      Map<String, Object> map = request.getParameterMap();
      return map;
   }
   
   /**
    * 设置所有参数Map集合
    * @return Map
    */
   @SuppressWarnings("unchecked")
   public void setAttributes() {
      Map<String, Object> map = request.getParameterMap();
      for(Entry<String, Object> entry : map.entrySet()){
         String[] value = (String[]) entry.getValue();
         request.setAttribute(entry.getKey(), value[0]);
      }
      request.setAttribute("fastdfsUrl", RSystemConfig.getValue("fastdfs.url"));
   }
   
   /**
    * 写JSON数据
    * @param pageInfo
    * @throws JsonProcessingException 
    */
   public void printWriterJson(PageControlInfo pageInfo) throws RequestException {
      getPrintWriter().write(formatJosn(pageInfo));
   }
   
   public void printWriterJson(RResult rResult) throws RequestException {
	   getPrintWriter().write(RJson.getJsonStr(rResult));
   }
   
   public void printWriterJson(List<?> list) throws RequestException {
      if(RList.isBlank(list)) {
         getPrintWriter().write("{}");
         return;
      }
      getPrintWriter().write(RJson.getJsonStr(list));
   }
   
   
   
   public void printWriterJson(Object Object) throws RequestException {
      getPrintWriter().write(RJson.getJsonStr(Object));
   }
   
   /**
    * 获取写对象
    * @return  PrintWriter
    */
   public PrintWriter getPrintWriter(){
      PrintWriter out = null;
      try{
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html;charset=utf-8");
         out = response.getWriter();
      }catch(IOException e){
         _logger.error("out is exception", e);
      }
      return out;
   }
   
   /**
    * 将PageControlInfo对象格式成json分页对象
    * @param pageInfo   分页控制对象
    * @throws JsonProcessingException 
    */
   public String formatJosn(PageControlInfo pageInfo) throws RequestException{
      List<?> list = pageInfo.getSearchData();
      Map<String, Object> data = new HashMap<String, Object>();
      data.put(PAGE_ROWS, null == list ? "" : list);
      data.put(PAGE_TOTAL, pageInfo.getTotalNum());
      return RJson.getJsonStr(data);
   }
   
   /**
    * 实体中不存在的参数，加载到map中，查询用到
    * @return  Map<String, Object>
    */
   public Map<String, Object> loadOsearch(){
      Map<String, Object> map = getRequestParameterMap();
      Map<String, Object> osearch = new HashMap<String, Object>();
      for(Entry<String, Object> entry : map.entrySet()){
         if(RString.startsWith(entry.getKey(), OSEARCH)){
            String k = RString.subString(entry.getKey(), OSEARCH + ".", null);
            String[] value = (String[]) entry.getValue();
            osearch.put(k, value[0]==""?null:value[0]);
         }
      }
      //排序字段
      String sort = getRequestParameter("sort");
      String order = getRequestParameter("order");
      RString.isBlank(sort);
      if(RString.isNotBlank(sort,order)){
         String[] sorts = sort.split(",");
         String[] orders = order.split(",");
         for(int i = 0; i < sorts.length; i++){
            osearch.put(sorts[i]+"_order", orders[i]);
         }
      }
      return osearch;
   }
   
   public SyUser getSessionUser() {
	  return (SyUser) session.getAttribute("syUser");
   }
   
   public void getTreeJson(List<Node> list) throws RequestException{
	      FEasyUITree easyUI = new FEasyUITree();
	      List<Tree> l = easyUI.getTree(list);
	      getPrintWriter().write(RJson.getJsonStr(l));
	}
 
   public int getRows(){
      return rows;
   }

   public void setRows(int rows){
      this.rows = rows;
   }

   public int getPage(){
      return page;
   }

   public void setPage(int page){
      this.page = page;
   }
  
}
