<%@page import="com.cat.common.listener.RSystemConfig"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
	<title>摩托列表</title>
	<%@include file="/head.jsp" %>
</head>
<body class="easyui-layout">
	<div class="easyui-panel" data-options="region:'north'">
		<form id="searchForm" method="post">
			<table class="form-search">
				<tr>
				    <td>标题：</td>
					<td><input class="easyui-textbox" type="text"
						name="title"></input></td>
					<td>状态：</td>
					<td><select class="easyui-combobox" id="status" name="status" style="width: 226px;"
						data-options="editable:false,prompt:'请选择类型'">
							<option value="">全部</option>
							<option value="edit">认证中</option>
							<option value="pass">认证通过</option>
							<option value="unpass">认证失败</option>
							<option value="off">已下架</option>
							<option value="sold">已售出</option>
					</select></td>
					<td>是否热门：</td>
					<td><select class="easyui-combobox" id="isHot" name="isHot" style="width: 226px;"
						data-options="editable:false,prompt:'请选择类型'">
							<option value="">全部</option>
							<option value="y">热门</option>
							<option value="n">非热门</option>
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
			url:'mo/motor/findPageList.do'">
		<thead>
			<tr>
				<th data-options="field:'id',checkbox:true"></th>
				<th data-options="field:'tel',width:120">手机号</th>
				<th data-options="field:'title',width:120">标题</th>
				<th data-options="field:'status',width:120,formatter:formatMotorStatus">状态</th>
				<th data-options="field:'isHot',width:120,formatter:formatIsHot">是否热门</th>
				<th data-options="field:'brand',width:120">品牌</th>
				<th data-options="field:'price',width:120,formatter:formatPrice">价格</th>
				<th data-options="field:'mileage',width:120">里程</th>
				<th data-options="field:'createTime',width:110">创建时间</th> 
			</tr>
		</thead>
	</table>
	</div>
<script type="text/javascript">

/* var addBtn = {text:'新增', iconCls:'icon-add', id:'addBtn'};
var updateBtn = {text:'修改', iconCls:'form-edit', id:'updateBtn'};
var deleteBtn = {text:'删除', iconCls:'form-delete', id:'deleteBtn'};
var viewBtn = {text:'查看', iconCls:'form-view', id:'viewBtn'}; */
var onBtn = {text:'热门', iconCls:'icon-edit', id:'onBtn'};
var offBtn = {text:'非热门', iconCls:'icon-edit', id:'offBtn'};
var statusPassBtn = {text:'认证通过', iconCls:'icon-edit', id:'statusPassBtn'};
var statusUnpassBtn = {text:'认证失败', iconCls:'icon-edit', id:'statusUnpassBtn'};
var statusOffBtn = {text:'下架', iconCls:'icon-edit', id:'statusOffBtn'};
var deleteBtn = {text:'删除', iconCls:'form-delete', id:'deleteBtn'};
var toolbar = [onBtn,offBtn,statusPassBtn,statusUnpassBtn,statusOffBtn,deleteBtn];

$(function(){
	tab_mask_hide();
	form_search($('#searchBtn'), 'searchForm', 'dg');
	form_clear($('#clearBtn'), 'searchForm');
/* 	form_add($("#addBtn"), 'mo/version/goAdd.do');
	form_update($("#updateBtn"), 'mo/version/goEdit.do', 'dg');
	form_delete($("#deleteBtn"), 'mo/version/doDelete.do', 'dg');
	form_view($("#viewBtn"), 'mo/version/goView.do', 'dg'); */
	form_delete($("#deleteBtn"), 'mo/version/doDelete.do', 'dg');
	form_updateHot($("#onBtn"), 'mo/motor/updateIsHot.do', 'y');
	form_updateHot($("#offBtn"), 'mo/motor/updateIsHot.do', 'n');
	form_updateStatus($("#statusPassBtn"), 'mo/motor/updateStatus.do', 'pass');
	form_updateStatus($("#statusUnpassBtn"), 'mo/motor/updateStatus.do', 'unpass');
	form_updateStatus($("#statusOffBtn"), 'mo/motor/updateStatus.do', 'off');
});
function bsMainLoad() {
	$("#dg").datagrid('reload');
	tab_mask_hide();
}

function formatMotorStatus(value, row ,index) {
	if("edit" == value) {
		return '<font color="green">认证中</font>';
	} else if("pass" == value) {
		return '<font color="black">认证通过</font>';;
	}else if("unpass" == value) {
		return '<font color="red">认证失败</font>';;
	}else if("off" == value) {
		return '<font color="gray">已下架</font>';;
	}else if("sold" == value) {
		return '<font color="gray">已出售</font>';;
	}else {
		return '未知';
	}
}


function formatPrice(v, row ,index) {
	var value = v+"";
	if(value == null  || value == ""){
		return  "0.00";
	}
	if(value.length == 1){
		return "0.0"+value;
	}
	if(value.length == 2){
		return "0."+value;
	}
	return value.substring(0,value.length-2)+"."+value.substring(value.length-2);
}

function formatIsHot(value, row ,index) {
	if("y" == value) {
		return '<font color="green">热门</font>';
	} else if("n" == value) {
		return '<font color="black">非热门</font>';;
	}else {
		return '未知';
	}
}

function form_updateHot(o,url,isHot){
	o.click(function(){
		if(this.disabled == true) {return;}
		var row = grid_select_record("dg");
		if (row == undefined){
			$.messager.alert('提示信息', '请选择需要重置密码的记录');
			return false;
		} 
		var id=row.id;
		var thisIsHot=row.isHot;
		if(thisIsHot == isHot){
			if("y" == isHot){
				$.messager.alert('提示信息', '该记录已是热门');
			}else{
				$.messager.alert('提示信息', '该记录已是非热门');
			}
			return false;
		}
		
		var showStatusStr = "";
		if("y" == isHot){
			showStatusStr =  '确认标记为热门？';
		}else{
			showStatusStr =  '确认标记为非热门？';
		}
		if(window.confirm(showStatusStr)){
			$.ajax({
				type: "post",
				url: url,
				dataType:"json",
				data:{"id":id,"isHot":isHot},
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
		if("sold" == thisStatus){
			$.messager.alert('提示信息', '已售出不能修改状态');
			return false;
		}
		
		if(thisStatus == status){
			if("pass" == status){
				$.messager.alert('提示信息', '该记录已是认证通过');
			}else if("unpass" == status){
				$.messager.alert('提示信息', '该记录已是认证失败');
			}else{
				$.messager.alert('提示信息', '该记录已是下架');
			}
			return false;
		}
		
		var showStatusStr = "";
		if("pass" == status){
			showStatusStr =  '确认认证通过？';
		}else if("unpass" == status){
			showStatusStr =  '确认认证失败？';
		}else{
			showStatusStr =  '确认下架？';
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
</script>
</body>
</html>