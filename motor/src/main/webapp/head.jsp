<%@page import="com.cat.sy.bean.SyUser"%>
<%@page import = "java.util.Date"%>
<%@page import = "com.cat.common.lang.RString" %>
<%@page contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+path+"/"; 
Long data = new Date().getTime();
SyUser syUser = (SyUser)request.getSession().getAttribute("syUser");
String userName  = "";
if(null != syUser){
	userName = syUser.getUserName();
}
%>
<base href="<%=basePath%>" > 
<title>杭州市民卡活动管理平台</title>
<link rel="shortcut icon" href="images/login/title.png" />
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="js/easyui/themes/metro-blue/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/my_icon.css" />
<script type="text/javascript" src="js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/easyui/extension/portal/jquery.portal.js"></script>
<script type="text/javascript" src="js/plugins/jquery.common.js"></script>
<script type="text/javascript" src="js/plugins/ajaxfileupload.js"></script>
<script type="text/javascript" src="js/pages/main.js"></script>
<script type="text/javascript" src="js/pages/form.js"></script>