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
	<form id="adform" method="post">
		<input name="id" type="hidden"/>
		
		<table class="form-table">
			<tr>
			   <td class="none_right">名称：</td>
				<td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
			</tr>
			<tr>
			<td class="none_right">状态：</td>
				<td><select class="easyui-combobox" name="status"  style="width:225px;">
										<option value="on">启用</option>
										<option value="off">禁用</option>
									</select>
				</td>
			</tr>
			<tr>
				<td class="none_right">排序：</td>
				<td><input class="easyui-validatebox" type="text" name="rank"  value="100"/> 数字越小优先级越高</td>
			</tr>
			<tr>
			<td class="none_right">图片：</td>
				<td>
					<input id="picUrl" class="easyui-validatebox" type="text" name="imgUrl" style="width:225px;" 
						readonly data-options="required:true"/>
					<input type="button" id="bannerPic" value="上传" />
					<input type="button" id="showPic" value="查看" />
					
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
	var params = {'url':"mo/advert/load.do", 'gridId':'dg'};
	form_load('adform', operate, params);
	tab_mask_hide();
	form_submit($("#submitButton"), 'adform', 'mo/advert/doSave.do');
	form_close_tab($('#closeButton'));
	
	form_imgUpload($('#bannerPic'),'file/uploadImage.do',bannerPicMethod)
	form_imgShow($('#showPic'),$('#picUrl'));
});

function bannerPicMethod(data){
	$('#picUrl').val(data.fileUrl);
}

function bannerPicMethod(data){
	$('#picUrl').val(data.fileUrl);
}


function form_imgShow(o,imgObj){
	   var  ts = new Date().getTime();
	   var dialogHtml = '<div id="imgShow_'+ts+'"  style="width:90%;padding:10px;"  >'+
                    '<img id="imgShowUrl_'+ts+'" src=""  />'+
                        '</div>'+
                    ' </div>';
		$("body").append(dialogHtml);
		$("#imgShow_"+ts).dialog({
		    title: '查看图片',
		    width: 520,
		    height: 320,
		    closed: true,
		    cache: false,
		    modal: true
		});
		
		//打开对话框事件
		o.bind('click', function(){
			  var  imgUrl = imgObj.val();
			   if(null ==imgUrl || ""==imgUrl){
				   alert("您还未上传图片！");
				   return false;
			   }
			   imgUrl = "<%=RSystemConfig.getValue("fileHttpUrl")%>"+"/"+imgUrl;
			   $("#imgShowUrl_"+ts).attr("src",imgUrl);
			  $("#imgShow_"+ts).dialog("open");
		    });

}


</script>
</body>
</html>