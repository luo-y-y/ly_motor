package com.cat.msg;

public enum RMsg{
   /*****************系统信息************************/
      Success(0, "成功"),
	   Fail(-1, "失败"),
	   Motor_Toekn_NUll(-1000, "您还未登录，请登录！"),
	   Motor_Toekn_Error(-1001, "您的账户在另外一处登录，请重新登录！"),
	   Motor_Toekn_TimeOut(-1002, "您的登录已过期，请重新登录！"),
	   
	   
	   End(-9000, "您还未登录，请登录！");
   private Integer _code;

   private String _value;

   private RMsg(Integer code,
                String value){
      _code = code;
      _value = value;
   }

   public String value(){
      return _value;
   }

   public Integer code(){
      return _code;
   }
  
}
