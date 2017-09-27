
function formatMoProjectStatus(value, row ,index) {
	if("normal" == value) {
		return '<font color="green">正常</font>';
	} else if("exception" == value) {
		return '<font color="red">异常</font>';;
	} else {
		return '未知';
	}
}

function formatInvoicedType(value, row ,index) {
	if("0" == value) {
		return '未开发票';
	} else if("1" == value) {
		return '<font color="red">已开发票</font>';;
	} else {
		return '未知';
	}
}


function formatMoUserStatus(value, row, index){
	if("open" == value){
		return '<font color="green">开启</font>';
	}else if("close" == value){
		return '<font color="red">关闭</font>';
	}else{
		return '未知';
	}
}
function formatType(value, row, index){
	if("web" == value){
		return '网页';
	}else if("interface" == value){
		return '接口';
	}else{
		return '未知';
	}
}

function formatMsg(value, row ,index) {
	if(null == value || "" == value){
		return "";
	}
	if( value.length > 10){
		return value.substring(0, 10)+"...";
	}else{
		return value;
	}
	
}

function formatOnOrOff(value, row ,index) {
	if("on" == value) {
		return '<font color="green">启用</font>';
	} else if("off" == value) {
		return '<font color="red">不启用</font>';;
	} else {
		return '未知';
	}
}


function formatPrizeType(value, row ,index) {
	if("M" == value) {
		return '<font color="green">流量</font>';
	} else if("Y" == value) {
		return '<font color="red">充值卡</font>';;
	}else if("I" == value) {
		return '<font color="red">IPAD</font>';;
	} else {
		return '未知';
	}
}


function formatPrize(value, row ,index) {
	if("start" == value) {
		return '<font color="green">未抽奖</font>';
	} else if("unprize" == value) {
		return '<font color="red">未中奖</font>';;
	} 
	else if("prize" == value) {
		return '<font color="red">已中奖</font>';;
	} 
	else if("cancelled" == value) {
		return '<font color="red">已作废</font>';;
	} else {
		return '未知';
	}
}


function formatIsWin(value, row ,index) {
	if("1" == value) {
		return '<font color="green">中奖</font>';
	} else if("0" == value) {
		return '未中奖';;
	} else {
		return '未知';
	}
}

function formatBdOrderType(value, row ,index) {
	if("1" == value) {
		return '钱包';
	} else if("2" == value) {
		return '本月月票充值 ';;
	} else if("3" == value) {
		return '本月月票复充 ';;
	}else if("4" == value) {
		return '下月月票充值';;
	}else if("5" == value) {
		return '下月月票复充';;
	}else {
		return '未知';
	}
}

function formatBdOrderStatus(value, row ,index) {
	if("0" == value) {
		return '未支付';
	} else if("100" == value) {
		return '支付成功';
	} else if("202" == value) {
		return '支付失败';
	}else if("410" == value) {
		return '退款中';
	}else if("511" == value) {
		return '退款成功';
	}else if("9" == value) {
		return '超时未支付';
	}else if("111" == value || "1" == value) {
		return '<font color="#F07B12">待补登</font>';
	}else if("3" == value) {
		return '<font color="green">已补登</font>';
	}else if("2" == value) {
		return '<font color="green">补登处理中</font>';
	}else if("4" == value) {
		return '<font color="green">已撤销</font>';
	}else if("5" == value) {
		return '<font color="green">补登失败</font>';
	}else if("6" == value) {
		return '<font color="green">补登状态未知</font>';
	}else {
		return '未知';
	}
}

function formatBdOrderActType(value, row ,index) {
	if("900" == value) {
		return '补登订单';
	} else if("901" == value) {
		return '红包订单';;
	} else {
		return '未知';
	}
}

function formatActPageStatus(value, row ,index) {
	if("ready" == value) {
		return '<font color="#F07B12">草稿</font>';
	}else if("on" == value) {
		return '<font color="green">上线</font>';
	}else if("down" == value) {
		return '<font color="red">下线</font>';
	}else {
		return '未知';
	}
}

function formatwinstatus(value, row ,index) {
	if("0" == value) {
		return '待确认';
	}else if("1" == value) {
		return '<font color="green">有效</font>';
	}else if("2" == value) {
		return '<font color="gray">无效</font>';
	}else {
		return '未知';
	}
}


function formatTime(value, row ,index) {
	if(value != null && value.length >= 14){
		return value.strToTime();
	}
}

String.prototype.strToTime = function(){
	return this.slice(0,4)+'-'+this.slice(4,6)+'-'+this.slice(6,8) + ' ' +this.slice(8,10) + ':'+this.slice(10,12) + ':'+this.slice(12,14);
}