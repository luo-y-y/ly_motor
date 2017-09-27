<%@page import="com.cat.common.listener.RSystemConfig"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
	<title>广告列表</title>
	<%@include file="/head.jsp" %>
</head>
<body class="easyui-layout">
	<div class="easyui-panel" data-options="region:'north'">
		<form id="searchForm" method="post">
			<table class="form-search">
				<tr>
					<td>状态：</td>
					<td><select class="easyui-combobox" id="status" name="status" style="width: 226px;"
						data-options="editable:false,prompt:'请选择类型'">
							<option value="">请选择</option>
							<option value="on">上线</option>
							<option value="off">下线</option>
					</select></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- multiple: true, 多选-->
	<div data-options="region:'center'" style="border:0px;">
	<table id="dg" class="easyui-datagrid"
			data-options="rownumbers:true,
			fit:true,
			border:true,
			pagination:true,
			toolbar:toolbar,
			fitColumns: true,
			pageSize: 20,
			iconCls:'form-list',
			onClickRow: onClickRow,
			singleSelect:true,
			url:'mo/advert/findPageList.do'">
		<thead>
			<tr>
				<th data-options="field:'id',checkbox:true"></th>
				<th data-options="field:'name',width:120">名称</th>
				<th data-options="field:'imgUrl',width:120,formatter:imgFormatter">图片</th>
				<th data-options="field:'status',width:120,formatter:formatAdStatus">状态</th>
				<th data-options="field:'rank',width:120">排序</th>
				<th data-options="field:'createTime',width:110">创建时间</th> 
				<th data-options="field:'updateTime',width:110">创建时间</th> 
			</tr>
		</thead>
	</table>
	</div>
<script type="text/javascript">
var onBtn = {text:'上线', iconCls:'icon-edit', id:'onBtn'};
var offBtn = {text:'下线', iconCls:'icon-edit', id:'offBtn'};
var btn = new Array();
btn.push(onBtn,offBtn);
var toolbar = form_toolbar(btn);

$(function(){
	tab_mask_hide();
	form_search($('#searchBtn'), 'searchForm', 'dg');
	form_clear($('#clearBtn'), 'searchForm');
	form_add($("#addBtn"), 'mo/advert/goAdd.do');
	form_update($("#updateBtn"), 'mo/advert/goEdit.do', 'dg');
	form_delete($("#deleteBtn"), 'mo/advert/doDelete.do', 'dg');
	form_view($("#viewBtn"), 'mo/advert/goView.do', 'dg');
	
	form_updateStatus($("#onBtn"), 'mo/advert/updateStatus.do', 'on');
	form_updateStatus($("#offBtn"), 'mo/advert/updateStatus.do', 'off');
});
function bsMainLoad() {
	$("#dg").datagrid('reload');
	tab_mask_hide();
}

function form_updateStatus(o,url,status){
	o.click(function(){
		if(this.disabled == true) {return;}
		var row = grid_select_record("dg");
		if (row == undefined){
			$.messager.alert('提示信息', '请选择需要重置密码的记录');
			return false;
		} 
		var id=row.id;
		var thisStatus=row.status;
		if(thisStatus == status){
			if("on" == status){
				$.messager.alert('提示信息', '该记录已是上线状态');
			}else{
				$.messager.alert('提示信息', '该记录已是下线状态');
			}
			return false;
		}
		
		var showStatusStr = "";
		if("on" == status){
			showStatusStr =  '确认上线？';
		}else{
			showStatusStr =  '确认下线？';
		}
		if(window.confirm(showStatusStr)){
			$.ajax({
				type: "post",
				url: url,
				dataType:"json",
				data:{"id":id,"status":status},
				success: function(data){
						$.messager.alert('提示信息', data.note);
						bsMainLoad();
				   },error: function (message) {
						$.messager.alert('提示信息', '处理失败');
					}
				});
		}
	});
}

function formatAdStatus(value, row ,index) {
	if("on" == value) {
		return '<font color="green">上线</font>';
	} else if("off" == value) {
		return '<font color="grey">下线</font>';;
	} else {
		return '未知';
	}
}

function imgFormatter(value,row,index){
    if('' != value && null != value){
        var url = "<%= RSystemConfig.getValue("fileHttpUrl")%>";
        value = url + value;
        value = "<img onclick='window.open(\""+value+"\")' style='width:50px; height:50px' src='"+value+"' title='点击查看图片'/>";
    }
    return  value;
}
</script>
</body>
</html>