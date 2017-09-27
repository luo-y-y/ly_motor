<%@ page import="java.net.URLDecoder" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%
  	/* response.setHeader("Pragma","No-cache");      
	response.setHeader("Cache-Control","no-cache");      
	response.setDateHeader("Expires", -10); */
	Cookie[] cookies = request.getCookies();       
	String Name="";
	String Password="";     
	if(cookies!=null)
	{
	    for(int x=0; x<cookies.length; x++){
	    	if("username".equals(cookies[x].getName())){
	    		String value = URLDecoder.decode(cookies[x].getValue(),"UTF-8");//解码，避免中文错误
	    		String [] values = value.split(",");
	    		
	    		if(values[0] != null && !"".equals(values[0])){
	    			Name = values[0];
	    			if(values[1] != null && !values[1].equals("null")){
	    					Password = values[1]; 
	    				}
	    		} 
	    	}
    	 request.setAttribute("name",Name);
         request.setAttribute("passward",Password);
//         System.out.println(name+password);
	    }
	}
%>

<head>
	<title>杭州市民卡活动管理平台</title>
	<link rel="shortcut icon" href="images/login/title.png" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/login.css" />
    <link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="css/swiper.css"/>
    <script type="text/javascript" src="js/swiper/swiper.js"></script>
	<script type="text/javascript" src="js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
  	<script type='text/javascript' src='js/particleground/jquery.particleground.js'></script>
</head>



<body scoll="no">
	<div id="particles"  style='position: absolute;width: 100%;height: 100%;    z-index: -1;'>
	  <div id="intro">
	   </div>
	</div>
	<div id="topHeader" style='height: 75px;'>
	  <div class='logoImage' style='padding-top: 20px;padding-left: 75px'>
	  <img src="images/login/logo_1.png" height="35"  alt="" />
	  </div>
	</div>
		<div class="body-border">
		    <div class="content">
		    	<form id="form1" method="post" action="sy/user/login.do">
		        <div class="login-box-warp" style="right:240px;float: right;width: 300px;">
		            <div class="name-password">
		                <div class="field pwd-field">
		                    <span id="J_StandardPwd">用户名：</span>
		                    <input name="userName" id="userName" value="<%= Name %>" class="login-text" maxlength="28" placeholder='请输入用户名' tabindex="2" type="text" />
		                </div>
		                <div class="field pwd-field">
		                    <span id="J_StandardPwd">密<span style="margin-left:16px;"></span>码：</span>
		                    <input name="password" id="password" value="<%= Password %>" class="login-text" maxlength="28" tabindex="2" placeholder='请输入密码'  type="password" />
		                </div>
		                <div class="field pwd-field" style=" display:block;">                  
		                    <span id="J_StandardPwd" style="vertical-align:middle;display: block;">验证码：</span>
		                    <input id="checkCode" name="checkCode" class="login-text login-code"  maxlength="28" tabindex="2" placeholder='请输入验证码'  type="text" />
		                    <a href="javascript:changecheckcode();">
		                    <img id="createCheckCode" align="middle" src="PictureCheckCode" style="width: 60px;height: 32px;margin-top: -2px;" />
		                    <span style="font-size: 16px;vertical-align: middle;">换一张</span>
		                    </a>
		                </div>
		                <div class="field pwd-field">
		                    <span id="J_StandardPwd" style="float:left; padding-top:5px;">记住我</span>
		                    <input name="checkboxOneInput" type="checkbox" value="" checked="checked" id="checkboxOneInput" />
		                    <label for="checkboxOneInput"></label>
		                    <!--<span style="float:right; padding-top:5px;"><a href="##" class="register" target="_blank">忘记登录密码？</a></span>-->
		                </div>
		               <div class="submit">
		               		<a href="javascript:void(0)" onclick="check();">登 录</a>
		                </div>
		            </div>
		        </div>
		        </form>
		    </div>
		    
		<p class="foot">&copy; 杭州市民卡</p>
		</div>

<div class="line"></div>
<script type="text/javascript">
$(document).ready(function() {
  $('#particles').particleground({
    dotColor: '#2C95A4',
    lineColor: '#2C95A4'
  });
  $('.intro').css({
    'margin-top': -($('.intro').height() / 2)
  });
});
getMessage();
function getMessage(){
	var message = "${message}";
	if(message == "nameOrpasswordWrong"){
		$.messager.alert('提示:','用户名或密码不正确!');
		rest();
	}
	if(message == "codeWrong"){
		$.messager.alert('提示:','验证码不正确!');
		rest();
	}
}

function check(){
   	var userName = $("#userName").val();
   	var password = $("#password").val();
   	var checkCode = $("#checkCode").val();
/*    	var checkboxOneInput = $("#checkboxOneInput").val(); */
   	
   	if(userName == ""){
	   $.messager.alert('提示:','用户名不能为空!');
		return;
	}
	if(password == ""){
		$.messager.alert('提示:','密码不能为空!');
		return;
	}
	if (checkCode == "") {
		$.messager.alert('提示:','验证码不能为空!');
		return;
	}
	$("#form1").submit();
}
  
//换验证码
function changecheckcode(){
    document.getElementById("createCheckCode").src="PictureCheckCode"+'?date=' + new Date();
}
    
 //重置 
function rest(){
	var form = document.getElementById("form1");
	form.userName.value = "";
	form.password.value = "";
	form.checkCode.value = "";
}
    
if(document.addEventListener){//如果是Firefox   
	document.addEventListener("keypress",handler, true);
}else{   
	//IE
	document.attachEvent("onkeypress",handler);
} 
	
function handler(evt){ 
	if(evt.keyCode == 13){
		check();
	}
}
//web容器重启之后 点击左侧列表跳转登录首页 
if (window != top) {
	top.location.href = location.href; 
}
</script>
</body>
</html>