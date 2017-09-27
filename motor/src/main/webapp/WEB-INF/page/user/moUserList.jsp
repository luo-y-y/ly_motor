<%@page import="com.cat.common.listener.RSystemConfig"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
	<title>用户列表</title>
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
			url:'mo/user/findPageList.do'">
		<thead>
			<tr>
				<th data-options="field:'id',checkbox:true"></th>
				<th data-options="field:'tel',width:120">手机号</th>
				<!-- <th data-options="field:'userName',width:120"></th> -->
				<th data-options="field:'realName',width:120">真实姓名</th>
				<th data-options="field:'idCard',width:120">身份证</th>
				<th data-options="field:'headUrl',width:120">头像</th>
				<th data-options="field:'status',width:120,formatter:formatUserStatus">状态</th>
				<th data-options="field:'createTime',width:110">创建时间</th> 
				<th data-options="field:'updateTime',width:110">修改时间</th> 
			</tr>
		</thead>
	</table>
	</div>
<script type="text/javascript">

/* var addBtn = {text:'新增', iconCls:'icon-add', id:'addBtn'};
var updateBtn = {text:'修改', iconCls:'form-edit', id:'updateBtn'};
var deleteBtn = {text:'删除', iconCls:'form-delete', id:'deleteBtn'};
var viewBtn = {text:'查看', iconCls:'form-view', id:'viewBtn'}; */
var toolbar = [];

$(function(){
	tab_mask_hide();
	form_search($('#searchBtn'), 'searchForm', 'dg');
	form_clear($('#clearBtn'), 'searchForm');
/* 	form_add($("#addBtn"), 'mo/version/goAdd.do');
	form_update($("#updateBtn"), 'mo/version/goEdit.do', 'dg');
	form_delete($("#deleteBtn"), 'mo/version/doDelete.do', 'dg');
	form_view($("#viewBtn"), 'mo/version/goView.do', 'dg'); */

});
function bsMainLoad() {
	$("#dg").datagrid('reload');
	tab_mask_hide();
}

function formatUserStatus(value, row ,index) {
	if("on" == value) {
		return '<font color="green">激活</font>';
	} else if("unact" == value) {
		return '<font color="green">未激活</font>';;
	}else if("off" == value) {
		return '<font color="green">注销</font>';;
	}else if("lock" == value) {
		return '<font color="green">锁定</font>';;
	}else {
		return '未知';
	}
}

</script>
</body>
</html>