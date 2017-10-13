<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.cat.common.listener.RSystemConfig"%>
<!DOCTYPE html>
<html>
<head>
	<title>广告表单</title>
	<%@include file="/head.jsp" %>
		<style type="text/css">
.uploadImgDiv{
   	margin:10px 10px 10px 10px;
   	cursor: pointer;
   	width: 216px;
   	height:110px;
   	position: relative;
   	border: 1px solid #cccccc;
   	width: 220px;
   	text-align: left;
}
.uploadImg{
   	width: 200px;
   	height: 112px;
   	background-color:#c3c3c3;
}
.uploadDelete{
	position: absolute;
	float: right;
	width: 14px;
	height: 14px;
	cursor: pointer;
	top: 0px;
	right: 0px;
}

.ly-mask {
	font-size: 1px;
	height: 100%;
	left: 0;
	opacity: 0.4;
	overflow: hidden;
	position: absolute;
	top: 0;
	width: 100%;
	background: #ccc none repeat scroll 0 0;
}
</style>
</head>
<body class="easyui-layout">
	<div class="easyui-panel form-tool" data-options="region:'north'">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'" id="submitButton">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="plain:true,iconCls:'icon-cancel'" id="closeButton">关闭</a>
	</div>
	
	<div class="easyui-panel" data-options="region:'center',border:true,fit:false">
	<form id="verform" method="post">
		<input name="id" type="hidden"/>
		<table class="form-table">
			<tr>
			   <td class="none_right">版本名称：</td>
				<td><input class="easyui-validatebox" type="text" name="versionName" data-options="required:true" /></td>
			</tr>
			<tr>
			   <td class="none_right">版本编码：</td>
				<td><input class="easyui-validatebox" type="text" name="versionCode" data-options="required:true" /></td>
			</tr>
			<tr>
				<td class="none_right">下载地址：</td>
				<td>
					<input id="url" class="easyui-validatebox" type="text" name="url" style="width:225px;" />
					<input type="button" id="bannerUrl" value="上传" />
				</td>
			</tr>
			<tr>
			<td class="none_right">系统类型：</td>
				<td><select class="easyui-combobox" name="sysType"  style="width:225px;">
										<option value="ios">IOS</option>
										<option value="android">安卓</option>
									</select>
				</td>
			</tr>
			<tr>
			<td class="none_right">更新类型：</td>
				<td><select class="easyui-combobox" name="isForce"  style="width:225px;">
										<option value="n">非强制更新</option>
										<option value="y">强制更新</option>
									</select>
				</td>
			</tr>
			
			<tr style="display: none;" id="hideCreateLine">
			<td class="none_right">创建时间：</td>
				<td><input class="easyui-validatebox" type="text" name="createTime" disabled /></td>
			</tr>
			
		</table>
	</form>
	</div>
<script type="text/javascript">
$(function(){
	var operate = '${operate}';
	if('U' == operate || 'V' == operate || 'H' == operate) {
		$('#hideCreateLine').show();
		$('#hideUpdateLine').show();
		$('#password').attr("disabled","disabled");
	}
	if('V' == operate || 'H' == operate) {
		$('#password').attr("disabled","disabled");
		var permissionObj = ['submitButton'];
		form_toolbar_control(permissionObj, 'hide');
	}
	
	//对象{'url':访问地址,'gridId':列表对象ID,'pKey':记录主键字段, 'toTabIndex':转向至tabs索引数的页面（从0开始)}
	var params = {'url':"mo/version/load.do", 'gridId':'dg'};
	form_load('verform', operate, params);
	tab_mask_hide();
	form_submit($("#submitButton"), 'verform', 'mo/version/doSave.do');
	form_close_tab($('#closeButton'));
	form_fileUpload($('#bannerUrl'),'file/uploadFile.do',urlSetVal)
});


function urlSetVal(data){
$('#url').val(data.fileUrl);
}


</script>
</body>
</html>