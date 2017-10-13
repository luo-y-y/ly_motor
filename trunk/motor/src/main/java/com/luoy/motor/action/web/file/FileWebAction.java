package com.luoy.motor.action.web.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cat.common.bean.RResult;
import com.cat.common.exception.RequestException;
import com.cat.common.fastdfs.RFds;
import com.cat.common.img.ImageCompressionUtil;
import com.cat.common.img.ImgeUtil;
import com.cat.common.json.RJson;
import com.cat.common.lang.RString;
import com.cat.common.listener.RSystemConfig;
import com.cat.sy.action.base.WebBaseAction;

@RequestMapping("file")
@Controller
@Scope("prototype")
public class FileWebAction
      extends WebBaseAction{

   /**
    * 宽度 压缩标准
    */
   private static  int  CWIDTH = 700;
   
   /**
    * 高度 压缩标准
    */
   private static  int  CHEIGHT = 700;
   
   /**
    * 请求访问路径
    */
   private static final String requestPath = "file/";
   
   /**
    * kindedit页面
    */
   @RequestMapping("goKindEdit")
   public String goMain(){
      setAttributes();
      return requestPath+"kindEditorForm";
   }
   
   @RequestMapping("uploadImage")
   public void uploadImage() throws RequestException{
      try{
         MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
         MultipartFile mFile = mRequest.getFile("imageFile");
         if(null == mFile){
            printWriterJson(new RResult(RResult.paramNull));
            return;
         }
         String fileName = mFile.getOriginalFilename();
         _logger.info("uploadImagefile name ="+fileName);
         String extName = getFileExtName(fileName);
         if(RString.isBlank(extName)){
            extName = "png";
         }
         // 文件上传
         String  fileBasePath =  RSystemConfig.getValue("fileBasePath");
         String  fileSavePath =  RSystemConfig.getValue("fileSavePath");
         String fileUrl = ImgeUtil.saveFile(mFile.getBytes(), fileBasePath, fileSavePath, "."+extName);
        // String fileUrl = RFds.getInstance().uploadFile(mFile.getBytes(), fileName);
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("code", "0");
         map.put("success", true);
         map.put("fileUrl", fileUrl);
         map.put("fileRdfUrl", RSystemConfig.getValue("fastdfsUrl"));
         printWriterJson(map);
         _logger.info("uploadImage success uploadImage="+ fileUrl);
      }catch(Exception e){
         e.printStackTrace();
         _logger.info("uploadImage error", e);
         printWriterJson(new RResult(RResult.MSG_FAIL));
      }
   }

   @RequestMapping("uploadFile")
   public void uploadFile() throws RequestException{
      try{
         MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
         MultipartFile mFile = mRequest.getFile("fileName");
         if(null == mFile){
            printWriterJson(new RResult(RResult.paramNull));
            return;
         }
         String fileName = mFile.getOriginalFilename();
         _logger.info("uploadImagefile name ="+fileName);
         String extName = getFileExtName(fileName);
         // 文件上传
         String  fileBasePath =  RSystemConfig.getValue("fileBasePath");
         String  fileSavePath =  RSystemConfig.getValue("appFileSavePath");
         String fileUrl = ImgeUtil.saveFile(mFile.getBytes(), fileBasePath, fileSavePath, "."+extName);
        // String fileUrl = RFds.getInstance().uploadFile(mFile.getBytes(), fileName);
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("code", "0");
         map.put("success", true);
         map.put("fileUrl", fileUrl);
         map.put("fileRdfUrl", RSystemConfig.getValue("fastdfsUrl"));
         printWriterJson(map);
         _logger.info("uploadImage success uploadImage="+ fileUrl);
      }catch(Exception e){
         e.printStackTrace();
         _logger.info("uploadImage error", e);
         printWriterJson(new RResult(RResult.MSG_FAIL));
      }
   }
   
   /**
    * 获取文件扩展名
    * 
    * @param name
    *            文件名
    * @return
    */
   private String getFileExtName(String name) {
      String extName = null;
      if (name != null && name.contains(".")) {
         extName = name.substring(name.lastIndexOf(".") + 1);
      }
      return extName;
   }

   /**
    * 上传图片带完整地址  且压缩
    * @throws RequestException 
    */
   @RequestMapping("uploadImageForFullUrl")
   public void uploadImageForFullUrl() throws RequestException{
      try{
         
         MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
         MultipartFile mFile = mRequest.getFile("imageFile");
         if(null == mFile){
            printWriterJson(new RResult(RResult.paramNull));
            return;
         }
         String fileName = mFile.getOriginalFilename();
         _logger.info("uploadImagefile name ="+fileName);
         // 文件上传
         @SuppressWarnings("deprecation")
         String tempPath = request.getRealPath("/temp/temporary");
         byte[] cbyte = ImageCompressionUtil.compress(mFile.getInputStream(), tempPath + File.separator + fileName, CWIDTH, CHEIGHT);
         
         String fileUrl = RFds.getInstance().uploadFile(cbyte, fileName);
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("code", "0");
         map.put("fileRdfUrl", RSystemConfig.getValue("fastdfsUrl"));
         map.put("fileUrl", fileUrl);
         printWriterJson(map);
         _logger.info("uploadImage success uploadImage="+ fileUrl);
        
      }catch(Exception e){
         e.printStackTrace();
         _logger.info("uploadImage error", e);
         printWriterJson(new RResult(RResult.MSG_FAIL));
      }
   }

   @RequestMapping("kindEditorImageUpload")
   public void kindEditorImageUpload() throws RequestException{
      try{
         MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
         MultipartFile mFile = mRequest.getFile("imgFile");
         Map<String, Object> map = new HashMap<String, Object>();
         if(null == mFile){
            map.put("error", 1);
            map.put("message", "file not exist!");
            getPrintWriter().write(RJson.getJsonStr(map).toString());
            return;
         }
         String fileName = mFile.getOriginalFilename();
         _logger.info("uploadImage  file name = "+ fileName);
         // 文件服务器 映射的地址
         String fileRdfUrl = RSystemConfig.getValue("fastdfsUrl");
         String rfdsUrl = RFds.getInstance().uploadFile(mFile.getBytes(), fileName);
         String fileUrl = fileRdfUrl + rfdsUrl;
         map.put("error", 0);
         map.put("url", fileUrl);
         _logger.info("uploadImage succes uploadImage= "+fileUrl);
         printWriterJson(map);
      }catch(Exception e){
         e.printStackTrace();
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("error", 2);
         map.put("message", "some thing is wrong !please connect the admin!");
         _logger.info("uploadImage error", e);
         printWriterJson(map);
      }
   }

   /**
    * 下载模板
    * @throws RequestException 
   */
   @RequestMapping("downLoadFile")
   public void downLoadExcel() throws RequestException{
      String fileName = getRequestParameter("fileName");
      //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
      @SuppressWarnings("deprecation")
      String path = request.getRealPath("/temp/export");
      //得到要下载的文件
      File file = new File(path + File.separator + fileName);
      //如果文件不存在
      if(!file.exists()){
         //map.put("code", "300");
         Map<String, String> map = new HashMap<String, String>();
         map.put("note", "file not exist!");
         printWriterJson(map);
      }
      //处理文件名
      String realname = fileName.substring(fileName.indexOf("_") + 1);
      //设置响应头，控制浏览器下载该文件
      try{
         response.setContentType("application/octet-stream");
         response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
         //读取要下载的文件，保存到文件输入流
         FileInputStream in = new FileInputStream(path + File.separator + fileName);
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
