<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<div id="editordertb" style="padding:2px 5px">
		<div id="editdivOrderInfo">
			<div style="padding: 3px">
				客户名称&nbsp;<input style="width: 115px" id="edit_uid" class="easyui-textbox" name="eait_uid"
				readonly="readonly" value="${requestScope.oi.ui.userName }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				订单金额&nbsp;<input style="width:115px" id="edit_orderprice" name="edit_orderprice" type="text" readonly="readonly"
				class="easyui-textbox" value="${requestScope.oi.orderprice }">&nbsp;&nbsp;
			</div>
			<div style="padding:3px ">
				订单日期&nbsp;<input style="width:115px" id="edit_ordertime" name="edit_ordertime" type="text" readonly="readonly"
				class="easyui-textbox" value="${requestScope.oi.ordertime }">&nbsp;&nbsp;&nbsp;&nbsp;
				订单状态&nbsp;<input style="width:115px" id="edit_status" name="edit_status" type="text" readonly="readonly"
				class="easyui-textbox" value="${requestScope.oi.status }">
			</div>
		</div>
	</div>
	<table id="editodbox"></table>
	<script type="text/javascript">
		var $editodbox = $('#editodbox');
		$(function(){
			$editodbox.datagrid({
				url:'orderinfo/getOrderDetail?oid=${requestScope.oi.id}',
				rownumbers:true,
				singleSelect:false,
				fit:true,
				toolbar:'#editordertb',
				columns:[[{
					field:'pid',
					title:'商品名称',
					width:300,
					formatter:function(value,row,index){
						if(row.pi){
							return row.pi.name;
						}else{
							return value;
						}
					}
				},{
					field:'price',
					title:'单价',
					width:80,
				},{
					field:'num',
					title:'数量',
					width:50,
				},{
					field:'totalprice',
					title:'小计',
					width:100,
				}
				]]
			});
		});
	</script>
	
	
</body>
</html>






