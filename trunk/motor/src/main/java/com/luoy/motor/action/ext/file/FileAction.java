package com.luoy.motor.action.ext.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cat.common.bean.EMsg;
import com.cat.common.exception.RequestException;
import com.cat.common.img.ImgeUtil;
import com.cat.common.json.RJson;
import com.cat.common.lang.RString;
import com.cat.common.listener.RSystemConfig;
import com.cat.msg.RMsgResponse;
import com.luoy.motor.action.base.ExtBaseAction;
import com.luoy.motor.action.ext.define.ExtActionName;

@Controller
@Scope("prototype")
public class FileAction extends ExtBaseAction {


	/**
    * 图片上传接口
    * 
    * @return
    */
   @RequestMapping(value = ExtActionName.File.file_upload)
   @ResponseBody
   public RMsgResponse extFileUpload() {
      try{
         String base64FileStr = RString.toString(params.get("base64FileStr"));
         String type = RString.toString(params.get("type"));
         if(RString.isBlank(base64FileStr)){
            return new RMsgResponse(EMsg.Param_NotFount.code(), EMsg.Param_NotFount.value());
         }
         if(RString.isBlank(type)){
            type = "jpeg";
         }
         String  fileBasePath =  RSystemConfig.getValue("fileBasePath");
         String  fileSavePath =  RSystemConfig.getValue("fileSavePath");
         String url = ImgeUtil.saveFile(base64FileStr, fileBasePath, fileSavePath, "."+type);
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("fileRdfUrl", RSystemConfig.getValue("fileHttpUrl"));
         map.put("fileUrl", url);
         return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value(),map);
      }catch(Exception e){
         _logger.error(e);
          return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
      }
   }



   /**
    * 下载模板
    * @throws RequestException 
   */
   @RequestMapping("downLoadExcel")
   public void downLoadExcel() throws RequestException{
      String fileName = "bsCoachExcel.xls";
      //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
      //request.getRealPath("/WEB-INF/upload");
      @SuppressWarnings("deprecation")
      String fileSaveRootPath = request.getRealPath("/temp/export");
      String a = this.getClass().getClassLoader().getResource(".").getPath();
      System.out.println(fileSaveRootPath);
      System.out.println(a);
      //通过文件名找出文件的所在目录
      String path = fileSaveRootPath;
      //得到要下载的文件
      File file = new File(path + File.separator + fileName);
      //如果文件不存在
      if(!file.exists()){
         //map.put("code", "300");
         Map<String, String> map = new HashMap<String, String>();
         map.put("note", "file not exist!");
         getPrintWriter().write(RJson.getJsonStr(map));
      }
      //处理文件名
      String realname = fileName.substring(fileName.indexOf("_") + 1);
      //设置响应头，控制浏览器下载该文件
      try{
         response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
         //读取要下载的文件，保存到文件输入流
         FileInputStream in = new FileInputStream(path + "\\" + fileName);
         //创建输出流
         OutputStream out = response.getOutputStream();
         //创建缓冲区
         byte buffer[] = new byte[1024];
         int len = 0;
         //循环将输入流中的内容读取到缓冲区当中
         while((len = in.read(buffer)) > 0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
         }
         //关闭文件输入流
         in.close();
         //关闭输出流
         out.close();
      }catch(Exception e){
         e.printStackTrace();
      }

   }

  
}
