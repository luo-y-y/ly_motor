<%@page import="com.cat.common.listener.RSystemConfig"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
	<title>版本列表</title>
	<%@include file="/head.jsp" %>
</head>
<body class="easyui-layout">
	<div class="easyui-panel" data-options="region:'north'">
		<form id="searchForm" method="post">
			<table class="form-search">
				<tr>
					<td>更新类型：</td>
					<td><select class="easyui-combobox" id="status" name="isForce" style="width: 226px;"
						data-options="editable:false,prompt:'请选择类型'">
							<option value="">请选择</option>
							<option value="y">强制更新</option>
							<option value="n">非强制更新</option>
					</select></td>
					<td></td>
					<td><a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-search',toggle:true" id="searchBtn">查询</a>
						<a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="iconCls:'icon-cancel',toggle:true" id="clearBtn">清除</a>
					</td>
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
			url:'mo/version/findPageList.do'">
		<thead>
			<tr>
				<th data-options="field:'id',checkbox:true"></th>
				<th data-options="field:'versionName',width:120">版本名称</th>
				<th data-options="field:'versionCode',width:120">版本编码</th>
				<th data-options="field:'isForce',width:120,formatter:formatForeStatus">状态</th>
				<th data-options="field:'url',width:120">下载地址</th>
				<th data-options="field:'sysType',width:120">系统类型</th>
				<th data-options="field:'createTime',width:110">创建时间</th> 
				<th data-options="field:'updateTime',width:110">修改时间</th> 
			</tr>
		</thead>
	</table>
	</div>
<script type="text/javascript">
var btn = new Array();
var toolbar = form_toolbar(btn);

$(function(){
	tab_mask_hide();
	form_search($('#searchBtn'), 'searchForm', 'dg');
	form_clear($('#clearBtn'), 'searchForm');
	form_add($("#addBtn"), 'mo/version/goAdd.do');
	form_update($("#updateBtn"), 'mo/version/goEdit.do', 'dg');
	form_delete($("#deleteBtn"), 'mo/version/doDelete.do', 'dg');
	form_view($("#viewBtn"), 'mo/version/goView.do', 'dg');

});
function bsMainLoad() {
	$("#dg").datagrid('reload');
	tab_mask_hide();
}

function formatForeStatus(value, row ,index) {
	if("y" == value) {
		return '<font color="red">强制更新</font>';
	} else if("n" == value) {
		return '<font color="green">非强制更新</font>';;
	} else {
		return '未知';
	}
}

</script>
</body>
</html>