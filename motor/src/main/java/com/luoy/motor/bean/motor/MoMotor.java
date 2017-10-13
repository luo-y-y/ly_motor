package com.luoy.motor.bean.motor;
import java.sql.Date;
import java.sql.Timestamp;

import com.cat.common.listener.RSystemConfig;
/**
 * 表名：[mo_motor]发布的车辆
 * @author luoyang
 */
public class MoMotor
      implements 
          java.io.Serializable{

   private static final long serialVersionUID = 1L;

   /**
    * id
    */
   private Integer id;

   /**
    * user_id
    */
   private Integer userId;

   /**
    * 标题
    */
   private String title;

   /**
    * 介绍
    */
   private String content;

   public static final String Status_Edit = "edit";
   
   public static final String Status_Pass = "pass";
   
   public static final String Status_Unpass = "unpass";
   
   public static final String Status_Off = "off";
   
   public static final String Status_Sold = "sold";
   
   /**
    * 状态
    * edit 认证中   认证通过pass  不通过unpass  下架 off  已售出 sold
    */
   private String status;
   
   /**
    * 价格
    * 单位分
    */
   private Integer price;

   /**
    * 原价
    * 单位分
    */
   private Integer oriPrice;

   /**
    * 联系方式
    */
   private String tel;

   /**
    * 里程
    * 里
    */
   private Integer mileage;

   /**
    * 品牌
    */
   private String brand;

   /**
    * 重量
    * 公斤
    */
   private Integer weight;

   /**
    * abs系统
    * true  false
    */
   private String hasAbs;

   /**
    * 区域
    * 区域编码
    */
   private String area;

   /**
    * 出厂时间
    */
   private Date productDate;

   /**
    * 上牌卖的时间
    */
   private Date buyDate;

   /**
    * 描述图片
    * 多个图片之间使用英文逗号分隔
    */
   private String imgUrls;

   /**
    * 行驶证
    */
   private String driLicense;

   /**
    * 发票图片
    */
   private String invoice;

   /**
    * create_time
    */
   private Timestamp createTime;

   /**
    * 修改时间
    */
   private Timestamp updateTime;

   /**
    * 是否收藏
    */
   private Integer isCollect;

   
   public static final String Hot_Yes = "y";
   
   public static final String Hot_No = "n";
   
   /**
    * 是否热门
    */
   private String isHot;
   
   /**
    * 地址
    */
   private String  fileRdfUrl = RSystemConfig.getValue("fileHttpUrl");

   public Integer getId(){
      return id;
   }

   public void setId(Integer id){
      this.id = id;
   }

   public Integer getUserId(){
      return userId;
   }

   public void setUserId(Integer userId){
      this.userId = userId;
   }

   public String getTitle(){
      return title;
   }

   public void setTitle(String title){
      this.title = title;
   }

   public String getContent(){
      return content;
   }

   public void setContent(String content){
      this.content = content;
   }

   public String getStatus(){
      return status;
   }

   public void setStatus(String status){
      this.status = status;
   }

   public Integer getPrice(){
      return price;
   }

   public void setPrice(Integer price){
      this.price = price;
   }

   public Integer getOriPrice(){
      return oriPrice;
   }

   public void setOriPrice(Integer oriPrice){
      this.oriPrice = oriPrice;
   }

   public String getTel(){
      return tel;
   }

   public void setTel(String tel){
      this.tel = tel;
   }

   public Integer getMileage(){
      return mileage;
   }

   public void setMileage(Integer mileage){
      this.mileage = mileage;
   }

   public String getBrand(){
      return brand;
   }

   public void setBrand(String brand){
      this.brand = brand;
   }

   public Integer getWeight(){
      return weight;
   }

   public void setWeight(Integer weight){
      this.weight = weight;
   }

   public String getHasAbs(){
      return hasAbs;
   }

   public void setHasAbs(String hasAbs){
      this.hasAbs = hasAbs;
   }

   public String getArea(){
      return area;
   }

   public void setArea(String area){
      this.area = area;
   }

   public Date getProductDate(){
      return productDate;
   }

   public void setProductDate(Date productDate){
      this.productDate = productDate;
   }

   public Date getBuyDate(){
      return buyDate;
   }

   public void setBuyDate(Date buyDate){
      this.buyDate = buyDate;
   }

   public String getImgUrls(){
      return imgUrls;
   }

   public void setImgUrls(String imgUrls){
      this.imgUrls = imgUrls;
   }

   public String getDriLicense(){
      return driLicense;
   }

   public void setDriLicense(String driLicense){
      this.driLicense = driLicense;
   }

   public String getInvoice(){
      return invoice;
   }

   public void setInvoice(String invoice){
      this.invoice = invoice;
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

   
   public Integer getIsCollect(){
      return isCollect;
   }

   
   public void setIsCollect(Integer isCollect){
      this.isCollect = isCollect;
   }

   
   public String getIsHot(){
      return isHot;
   }

   
   public void setIsHot(String isHot){
      this.isHot = isHot;
   }
 
}
