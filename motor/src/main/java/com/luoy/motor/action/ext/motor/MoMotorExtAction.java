package com.luoy.motor.action.ext.motor;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cat.aspect.Anno;
import com.cat.common.bean.EMsg;
import com.cat.common.bean.PageControlInfo;
import com.cat.common.bean.RResult;
import com.cat.common.lang.RDate;
import com.cat.common.lang.RString;
import com.cat.msg.RMsgResponse;
import com.luoy.motor.action.base.ExtBaseAction;
import com.luoy.motor.action.ext.define.ExtActionName;
import com.luoy.motor.bean.motor.MoMotor;
import com.luoy.motor.service.motor.MoMotorService;

@Controller
@Scope("prototype")
public class MoMotorExtAction
      extends ExtBaseAction{

   @Autowired
   private MoMotorService moMotorService;

   @RequestMapping(value = ExtActionName.Motor.Motor_add)
   @Anno("添加")
   @ResponseBody
   public RMsgResponse extMotorAdd(){
      try{
         String title = RString.toString(params.get("title"));
         String content = RString.toString(params.get("content"));
         String price = RString.toString(params.get("price"));
         String oriPrice = RString.toString(params.get("oriPrice"));
         String tel = RString.toString(params.get("tel"));
         String mileage = RString.toString(params.get("mileage"));
         String brand = RString.toString(params.get("brand"));
         String weight = RString.toString(params.get("weight"));
         String hasAbs = RString.toString(params.get("hasAbs"));
         String area = RString.toString(params.get("area"));
         String productDate = RString.toString(params.get("productDate"));
         String buyDate = RString.toString(params.get("buyDate"));
         String driLicense = RString.toString(params.get("driLicense"));
         String invoice = RString.toString(params.get("invoice"));
         String imgUrls = RString.toString(params.get("imgUrls"));

         if(RString.isBlank(title, content, tel, price,userId+"")){
            return new RMsgResponse(EMsg.Param_NotFount.code(), EMsg.Param_NotFount.value());
         }

         MoMotor mo = new MoMotor();
         mo.setUserId(userId);
         mo.setTitle(title);
         mo.setContent(content);

         mo.setPrice(getIntValue(price));
         mo.setOriPrice(getIntValue(oriPrice));
         mo.setTel(tel);
         mo.setMileage(getIntValue(mileage));
         mo.setBrand(brand);
         mo.setWeight(getIntValue(weight));
         mo.setHasAbs(hasAbs);
         mo.setArea(area);
         mo.setProductDate(getDateValue(productDate));
         mo.setBuyDate(getDateValue(buyDate));
         mo.setDriLicense(driLicense);
         mo.setInvoice(invoice);//发票
         mo.setStatus(MoMotor.Status_Edit);
         mo.setImgUrls(imgUrls);
         mo.setCreateTime(RDate.getCurrentTime());
         mo.setUpdateTime(RDate.getCurrentTime());

         Integer t = moMotorService.save(mo);
         if(t > 0){
            mo.setId(t);
            return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value(), mo);
         }
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }

   @RequestMapping(value = ExtActionName.Motor.Motor_off)
   @Anno("下架")
   @ResponseBody
   public RMsgResponse extMotorOff(){
      try{
         String id = RString.toString(params.get("id"));

         if(RString.isBlank(id)){
            return new RMsgResponse(EMsg.Param_NotFount.code(), EMsg.Param_NotFount.value());
         }
         Integer i = Integer.parseInt(id);
         boolean t = moMotorService.updateStatus(i, MoMotor.Status_Off);
         if(t){
            return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value());
         }
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }

   @RequestMapping(value = ExtActionName.Motor.Motor_sold)
   @Anno("已售出")
   @ResponseBody
   public RMsgResponse extMotorSold(){
      try{
         String id = RString.toString(params.get("id"));

         if(RString.isBlank(id)){
            return new RMsgResponse(EMsg.Param_NotFount.code(), EMsg.Param_NotFount.value());
         }
         Integer i = Integer.parseInt(id);
         boolean t = moMotorService.updateStatus(i, MoMotor.Status_Sold);
         if(t){
            return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value());
         }
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }
   
   @RequestMapping(value = ExtActionName.Motor.Motor_on)
   @Anno("上架架")
   @ResponseBody
   public RMsgResponse extMotorOn(){
      try{
         String id = RString.toString(params.get("id"));

         if(RString.isBlank(id)){
            return new RMsgResponse(EMsg.Param_NotFount.code(), EMsg.Param_NotFount.value());
         }
         Integer i = Integer.parseInt(id);
         boolean t = moMotorService.updateStatus(i, MoMotor.Status_Edit);
         if(t){
            return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value());
         }
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }

   @RequestMapping(value = ExtActionName.Motor.Motor_update)
   @Anno("修改")
   @ResponseBody
   public RMsgResponse extMotorUpdate(){
      try{
         String id = RString.toString(params.get("id"));
         String title = RString.toString(params.get("title"));
         String content = RString.toString(params.get("content"));
         String price = RString.toString(params.get("price"));
         String oriPrice = RString.toString(params.get("oriPrice"));
         String tel = RString.toString(params.get("tel"));
         String mileage = RString.toString(params.get("mileage"));
         String brand = RString.toString(params.get("brand"));
         String weight = RString.toString(params.get("weight"));
         String hasAbs = RString.toString(params.get("hasAbs"));
         String area = RString.toString(params.get("area"));
         String productDate = RString.toString(params.get("productDate"));
         String buyDate = RString.toString(params.get("buyDate"));
         String driLicense = RString.toString(params.get("driLicense"));
         String invoice = RString.toString(params.get("invoice"));
         String imgUrls = RString.toString(params.get("imgUrls"));

         if(RString.isBlank(title, content, tel, price,userId+"")){
            return new RMsgResponse(EMsg.Param_NotFount.code(), EMsg.Param_NotFount.value());
         }

         MoMotor mo = moMotorService.load(getIntValue(id));
         mo.setUserId(userId);
         mo.setTitle(title);
         mo.setContent(content);

         mo.setPrice(getIntValue(price));
         mo.setOriPrice(getIntValue(oriPrice));
         mo.setTel(tel);
         mo.setMileage(getIntValue(mileage));
         mo.setBrand(brand);
         mo.setWeight(getIntValue(weight));
         mo.setHasAbs(hasAbs);
         mo.setArea(area);
         mo.setProductDate(getDateValue(productDate));
         mo.setBuyDate(getDateValue(buyDate));
         mo.setDriLicense(driLicense);
         mo.setInvoice(invoice);//发票
         mo.setImgUrls(imgUrls);
         mo.setUpdateTime(RDate.getCurrentTime());

         RResult t = moMotorService.update(mo);
         if(t.isSuccess()){
            return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value(), mo);
         }
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }

   @RequestMapping(value = ExtActionName.Motor.Motor_findPage)
   @Anno("分页查询")
   @ResponseBody
   public RMsgResponse extMotorFindPage(){
      try{

         Map<String, Object> omap = new HashMap<String, Object>();
         String title = RString.toString(params.get("title"));
         String loaduserId = RString.toString(params.get("userId"));
         String status = RString.toString(params.get("status"));
         String brand = RString.toString(params.get("brand"));
         String area = RString.toString(params.get("area"));

         String minPrice = RString.toString(params.get("minPrice"));
         String maxPrice = RString.toString(params.get("maxPrice"));
         String minMileage = RString.toString(params.get("minMileage"));
         String maxMileage = RString.toString(params.get("maxMileage"));

         String minProductDate = RString.toString(params.get("minProductDate"));
         String maxProductDate = RString.toString(params.get("maxProductDate"));

         String orderKey = RString.toString(params.get("orderKey"));
         String isHot = RString.toString(params.get("isHot"));
         String rowsStr = RString.toString(params.get("rows"));
         String pageStr = RString.toString(params.get("page"));

         Integer rows = 20;
         Integer page = 1;
         try{
            rows = Integer.parseInt(rowsStr);
         }catch(Exception e){
         }
         try{
            page = Integer.parseInt(pageStr);
         }catch(Exception e){
         }
         MoMotor mo = new MoMotor();
         if(RString.isNotBlank(loaduserId)){
            mo.setUserId(getIntValue(loaduserId));
         }
         if(RString.isNotBlank(isHot)){
            mo.setIsHot(isHot);
         }
         if(RString.isNotBlank(title)){
            mo.setTitle(title);
         }
         if(RString.isNotBlank(status)){
            mo.setStatus(status);
         }else{
            omap.put("opposStatus", "off");
         }
         if(RString.isNotBlank(brand)){
            mo.setBrand(brand);
         }
         if(RString.isNotBlank(area)){
            mo.setArea(area);
         }
         if(RString.isNotBlank(minPrice)){
            omap.put("minPrice", minPrice);
         }
         if(RString.isNotBlank(maxPrice)){
            omap.put("maxPrice", maxPrice);
         }
         if(RString.isNotBlank(minMileage)){
            omap.put("minMileage", minMileage);
         }
         if(RString.isNotBlank(maxMileage)){
            omap.put("maxMileage", maxMileage);
         }
         if(RString.isNotBlank(minProductDate)){
            omap.put("minProductDate", minProductDate);
         }
         if(RString.isNotBlank(maxProductDate)){
            omap.put("maxProductDate", maxProductDate);
         }
         if(RString.isNotBlank(maxProductDate)){
            omap.put("maxProductDate", maxProductDate);
         }
         if("time".equalsIgnoreCase(orderKey)){
            omap.put("priceKey", "CREATE_TIME");
            omap.put("priceOrder", "DESC");
         }else if("priceup".equalsIgnoreCase(orderKey)){
            omap.put("priceKey", "PRICE");
            omap.put("priceOrder", "ASC");
         }else if("pricedown".equalsIgnoreCase(orderKey)){
            omap.put("priceKey", "PRICE");
            omap.put("priceOrder", "DESC");
         }
         
         if(RString.isNotBlank(userId+"")){
            omap.put("collectUserId", userId);
         }
         PageControlInfo t = moMotorService.findPageList(mo, page, rows, omap);
         return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value(), t ); 
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }

   @RequestMapping(value = ExtActionName.Motor.Motor_Load)
   @Anno("单条查询")
   @ResponseBody
   public RMsgResponse extMotorLoad(){
      try{
         String id = RString.toString(params.get("id"));
         
         MoMotor t = moMotorService.load(Integer.parseInt(id),userId);
         return new RMsgResponse(EMsg.Success.code(), EMsg.Success.value(), t ); 
      }catch(Exception e){
         e.printStackTrace();
      }
      return new RMsgResponse(EMsg.Fail.code(), EMsg.Fail.value());
   }
   
   private Integer getIntValue(String v){
      try{
         return Integer.parseInt(v);

      }catch(Exception e){
      }
      return null;
   }

   private java.sql.Date getDateValue(String v){
      try{
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         return new java.sql.Date(df.parse(v).getTime());
      }catch(Exception e){
      }
      return null;
   }
}
