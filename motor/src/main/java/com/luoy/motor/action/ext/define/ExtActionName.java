package com.luoy.motor.action.ext.define;

public class ExtActionName {

   public class File {
      /**
       * 文件上传
       */
      public static final String file_upload = "extFileuUpload";
      
   }
   
   public class Advert {
      /**
       * 文件上传
       */
      public static final String Advert_List = "extGetAdvert";
      
   }
   
	public class User {

	   /**
       * 用户手机号发送验证码
       */
      public static final String user_tel_send_code = "extTelSendCode";
      
		/**
		 * 用户手机号免密登录
		 */
		public static final String user_tel_msg_login = "extTelMsgLogin";

		/**
       * 用户用户名密码登录
       */
      public static final String user_tel_pass_login = "extTelPassLogin";
      
      /**
       * 完善个人信息
       */
      public static final String user_update_info = "extUpdateUserInfo";
      
	}

	public class Motor {
	   
      /**
       * 添加
       */
      public static final String Motor_add = "extMotorAdd";
      
      
      /**
       * 下架
       */
      public static final String Motor_off = "extMotorOff";
      
      /**
       * 已售出
       */
      public static final String Motor_sold = "extMotorSold";
      
      
      /**
       * 上架
       */
      public static final String Motor_on = "extMotorOn";
      
      /**
       * 修改
       */
      public static final String Motor_update = "extMotorUpdate";
      
      /**
       * 分页查询
       */
      public static final String Motor_findPage = "extMotorFindPage";
      
      
      /**
       * 查询单条
       */
      public static final String Motor_Load = "extMotorLoad";
      
      /**
       * 标记热门
       */
      public static final String Motor_Hot = "extMotorHot";
      
      
      /**
       * 取消热门
       */
      public static final String Motor_unHot = "extMotorUnHot";
      
   }
	
public class Evaluate {
      
      /**
       * 添加
       */
      public static final String Evaluate_Add = "extEvaluateAdd";
      
      
      /**
       * 删除
       */
      public static final String Evaluate_Delete = "extEvaluateDelete";
      
      /**
       * 由摩托查询评价
       */
      public static final String Evaluate_FindPageByMo = "extEvaluateFindPageByMo";
      
      
      /**
       * 查询个人砍价记录
       */
      public static final String Evaluate_FindPageByUser = "extEvaluateFindPageByUser";
   }


   public class Collect {
      
      /**
    * 添加
    */
   public static final String Collect_Add = "extCollectAdd";
   
   
   /**
    * 删除
    */
   public static final String Collect_Delete = "extCollectDelete";
   
   /**
    * 查询个人砍价记录
    */
   public static final String Collect_FindPageByUser = "extCollectFindPage";
   }
}
