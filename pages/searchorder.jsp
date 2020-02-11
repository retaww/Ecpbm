<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link href="EasyUI/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
<link href="EasyUI/themes/icon.css" rel="stylesheet" type="text/css"/>
<link href="EasyUI/demo.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="EasyUI/jquery.min.js"></script>
<script type="text/javascript" src="EasyUI/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="EasyUI/jquery.easyui.min.js"></script>
</head>
<body>
	<div id="searchOrderTb" style="padding:2px 5px ">
		<form id="searchOrderForm">
			<div style="padding: 3px">
				订单编号&nbsp;<input class="easyui-textbox" id="search_oid" name="search_oid" style="width: 110px">
			</div>
			<div style="padding:3px">
				客户名称&nbsp;<input class="easyui-combobox" id="search_uid" name="search_uid" style="width:115px" 
				value="0" data-options="valueField:'id',textField:'userName',url:'userinfo/getValidUser'">&nbsp;&nbsp;&nbsp;
				订单状态&nbsp;<select class="easyui-combobox" id="search_status" name="search_status" style="width:115px">
								<option value="请选择" selected="selected">请选择</option>
								<option value="未付款">未付款</option>
								<option value="已付款">已付款</option>
								<option value="待发货">待发货</option>
								<option value="已发货">已发货</option>
								<option value="已完成">已完成</option>
							</select>&nbsp;&nbsp;&nbsp;
				订单时间&nbsp;<input class="easyui-datebox" name="orderTomeFrom" id="orderTimeFrom" style="width:115px">~
				<input class="easyui-datebox" name="orderTomeTo" id="orderTimeTo" style="width:115px">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchOrderInfo()">查找</a>
			</div>
		</form>
	</div>
	
	<table id="orderDg" class="easyui-datagrid"></table>
	<div id="orderTb" style="padding:2px 5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editOrder()">查看明细</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeOrder()">删除订单</a>
	</div>
	
	<script type="text/javascript">
		$(function(){
			$('#orderDg').datagrid({
				singleSelect:false,
				url:'orderinfo/list',
				queryParams:{},
				pagination:true,
				pageSize:5,
				pageList:[5,10,15],
				rownumbers:true,
				fit:true,
				toolbar:'#orderTb',
				header:'#searchOrderTb',
				columns:[[{
						title:'序号',
						field:'id',
						align:'center',
						checkbox:true
					},{
						field:'ui',
						title:'订单客户',
						formatter:function(value,row,index){
							if(row.ui){
								return row.ui.userName;
							}else{
								return value;
							}
						},
						width:100
					},{
						field:'status',
						title:'订单状态',
						width:80
					},{
						field:'ordertime',
						title:'订单时间',
						width:80
					},{
						field:'orderprice',
						title:'订单金额',
						width:100
					}
				]]
			});
		});
		
		function searchOrderInfo(){
			var oid = $('#search_oid').textbox("getValue");
		 	var status = $('#search_status').combobox("getValue");
		 	var uid = $('#search_uid').combobox("getValue");
		 	var orderTimeFrom =  $('#orderTimeFrom').datebox("getValue");
		 	var orderTimeTo =  $('#orderTimeTo').datebox("getValue");
		 	$('#orderDg').datagrid('load',{
		 		id:oid,
		 		status:status,
		 		uid:uid,
		 		orderTimeFrom:orderTimeFrom,
		 		orderTimeTo:orderTimeTo
		 	});
		}
		
		function removeOrder(){
			var rows = $('#orderDg').datagrid('getSelections');
			if(rows.length>0){
				$.messager.confirm('Confirm','确定要删除吗？',function(r){
					if(r){
						var ids = "";
						for(var i=0;i<rows.length;i++){
							ids += rows[i].id +",";
						}
						$.post('orderinfo/deleteOrder',{
							oids:ids
						},function(result){
							if(result.success=='true'){
								$("#orderDg").datagrid('reload');	
								$.messager.show({
									title:'提示信息',
									msg:result.message
								});
							}else{
								$.messager.show({
									title:'提示信息',
									msg:result.message
								});
							}
						},'json');
					}
				});
			}else{
				$.messager.alert('提示','请选择要删除的行','info');
			}
		}
		
		function editOrder(){
			var rows = $("#orderDg").datagrid('getSelections');
			if(rows.length>0){
				var row = $('#orderDg').datagrid("getSelected");
				if($('#tabs').tabs('exists','订单明细')){
					$('#tabs').tabs('close','订单明细');
				}
					$('#tabs').tabs('add',{
						title:"订单明细",
						href:'orderinfo/getOrderInfo?oid='+row.id,
						closable:true
					});
			}else{
				$.messager.alert('提示','请选择要修改的订单','info');
			}
		}
	</script>
	
</body>
</html>












