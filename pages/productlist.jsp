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
	<div id="searchtb_productinfo" style="padding: 2px 5px">
			<form id="searchForm_productinfo" method="post">
				<div style="padding: 3px">
					商品编号&nbsp;&nbsp;<input class="easyui-textbox" name="productinfo_search_code" 
					id="productinfo_search_code" style="width:110px">
				</div>
				<div style="padding: 3px">
					商品名称&nbsp;&nbsp;<input class="easyui-textbox" name="productinfo_search_name" 
					id="productinfo_search_name" style="width:110px">
					&nbsp;&nbsp;商品类型&nbsp;&nbsp;<input class="easyui-combobox" name="productinfo_search_tid" 
					id="productinfo_search_tid" style="width:110px" data-options="valueField:'id',textField:'name',url:'type/getType/1'" value="0">
					&nbsp;&nbsp;商品品牌&nbsp;&nbsp;<input class="easyui-textbox" name="productinfo_search_brand" id="productinfo_search_brand" style="width:110px">
					&nbsp;&nbsp;价格&nbsp;&nbsp;<input class="easyui-numberbox" name="productinfo_search_priceFrom" id="productinfo_search_priceFrom" style="width:80px">
					~<input class="easyui-numberbox" name="productinfo_search_priceTo" id="productinfo_search_priceTo" style="width:80px">
					&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchProduct();">查找</a>
				</div>
			</form>
		</div>
		<div id="tb-productinfo" style="padding: 2px 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" 
			iconCls="icon-add" plain="true" onclick="addProduct();">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" 
			iconCls="icon-edit" plain="true" onclick="editProduct();">修改</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" 
			iconCls="icon-remove" plain="true" onclick="removeProduct();">下架</a>
		</div>
	<table id="dg_productinfo" class="easyui-datagrid"></table>
	
	<div id="dlg_productinfo" class="easyui-dialog" title="添加商品"
	closed="true" style="width:500px">
		<div style="padding: 10px 60px 20px 60px">
			<form id="ff_productinfo" method="POST" action="">
				<table cellpadding="5">
					<tr>
						<td>商品状态 :</td>
						<td><select id="status" class="easyui-combobox" name="status" style="width:150px">
							<option value="1">在售</option>
							<option value="0" >下架</option>
						</select></td>
					</tr> 
					<tr>
						<td>商品类型 :</td>
						<td><input style="width:150px" id="type.id" class="easyui-combobox" name="type.id"
						 data-options="valueField:'id',textField:'name',url:'type/getType/0'"/></td>
					</tr>
					<tr>
						<td>商品名称 :</td>
						<td><input id="name" type="text" class="easyui-textbox" name="name"
						 data-options="required:true"/></td>
					</tr>
					<tr>
						<td>商品编码 :</td>
						<td><input id="code" type="text" class="easyui-textbox" name="code"
						 data-options="required:true"/></td>
					</tr>
					<tr>
						<td>商品品牌 :</td>
						<td><input id="brand" type="text" class="easyui-textbox" name="brand"
						 data-options="required:true"/></td>
					</tr>
					<tr>
						<td>商品数量 :</td>
						<td><input id="num" type="text" class="easyui-textbox" name="num"
						 data-options="required:true"/></td>
					</tr>
					<tr>
						<td>商品价格 :</td>
						<td><input id="price" type="text" class="easyui-textbox" name="price"
						 data-options="required:true"/></td>
					</tr>
					<tr>
						<td>商品描述 :</td>
						<td><input id="intro" type="text" class="easyui-textbox" name="intro"
						 data-options="multiline:true" style="height:60px"/></td>
					</tr>
					<tr>
						<td>商品图片 :</td>
						<td><input id="file" class="easyui-filebox" name="file"
						 value="选择图片" style="width:200px"/></td>
					</tr>
				</table>
				<div style="text-align: center;padding: 5px">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveProduct();">保存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm();">清空</a>
				</div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function() {
			$('#dg_productinfo').datagrid({
				singleSelect:false ,//设置datagrid为单选项
				url: 'productinfo/list',//设置数据源
				pagination: true,//启动分页
				pageSize: 10,//页大小
				pageList: [10,15,20],//设置可供选择的页大小
				rownumbers:true,//显示行号
				fit:true,//自适应
				toolbar:'#tb-productinfo',//为datagrid添加工具栏
				header:'#searchtb_productinfo',//添加搜索栏
				columns:[ [{
					title:'序号',
					field:'id',
					align:'center',
					checkbox:true
				},{
					field:'name',
					title:'商品名称',
					width:200
				},{
					field:'type',
					title:'商品类型',
					formatter: function (value,row,index) {
						if(row.type){
							return row.type.name;
						}else{
							return value;
						}
					},width:60
				},{
					field:'status',
					title:'商品状态',
					formatter: function (value,row,index) {
						if(row.status==1){
							return "在售";
						}else{
							return "下架";
						}
					},width:60
				},{
					field:'code',
					title:'商品编号',
					width:100
				},{
					field:'brand',
					title:'品牌',
					width:120
				},{
					field:'price',
					title:'价格',
					width:50
				},{
					field:'num',
					title:"库存",
					width:50
				},{
					field:'intro',
					title:'商品描述',
					width:450
				}
				] ]
			});
		});
		function searchProduct(){
			var productinfo_search_code = $('#productinfo_search_code').textbox("getValue");
			var productinfo_search_name = $('#productinfo_search_name').textbox("getValue");
			var productinfo_search_tid = $('#productinfo_search_tid').combobox("getValue");
			var productinfo_search_brand = $('#productinfo_search_brand').textbox("getValue");
			var productinfo_search_priceFrom;
			if($("#productinfo_search_priceFrom").val()!=null&&$("#productinfo_search_priceFrom").val()!=""){
				productinfo_search_priceFrom = $("#productinfo_search_priceFrom").textbox("getValue");
			}else{
				productinfo_search_priceFrom = "0";
			}
			var productinfo_search_priceTo;
			if($("#productinfo_search_priceTo").val()!=null&&$("#productinfo_search_priceTo").val()!=""){
				productinfo_search_priceTo = $("#productinfo_search_priceTo").textbox("getValue");
			}else{
				productinfo_search_priceTo = "0";
			}
			
			$("#dg_productinfo").datagrid('load',{
				"code":productinfo_search_code,
				"name":productinfo_search_name,
				"type.id":productinfo_search_tid,
				"brand":productinfo_search_brand,
				"priceFrom":productinfo_search_priceFrom,
				"priceTo":productinfo_search_priceTo
			});
		}
		function addProduct(){
			$('#dlg_productinfo').dialog('open').dialog('setTitle','新增商品');
			$('#dlg_productinfo').form('clear');
			urls = 'productinfo/addProduct';
		}
		function saveProduct(){
			$('#ff_productinfo').form("submit",{
				url:urls,
				success : function(result) {
					var result = eval('('+result+')');
					if(result.success == 'true'){
						$('#dg_productinfo').datagrid('reload');
						$('#dlg_productinfo').dialog('close');
					}
					$.messager.show({
						title: "提示信息",
						msg: result.message
					});
				}
			});
		}
		//下架商品
		function removeProduct() {
			var rows = $('#dg_productinfo').datagrid('getSelections');
			if(rows.length > 0){
				$.messager.confirm('Confirm','确认要下架吗？',function(r){
					if(r){
						var ids = "";
						for(var i = 0;i<rows.length;i++){
							ids += rows[i].id + ",";
						}
						$.post('productinfo/deleteProduct',{
							id: ids,
							flag: 0
						},function(result){
							if(result.success == 'true'){
								$("#dg_productinfo").datagrid('reload');
								$.messager.show({
									title : "提示信息",
									msg : result.message
								});
							}else{
								$.messager.show({
									title : "提示信息",
									msg : result.message
								});
							}
						},'json');
					}
				});
			}else{
				$.messager.alert('提示','请选择要下架的商品','info');
			}
		}
		function editProduct(){
			var rows = $('#dg_productinfo').datagrid('getSelections');
			if(rows.length > 0){
				var row = $('#dg_productinfo').datagrid('getSelected');
				if(row){
					$('#dlg_productinfo').dialog("open").dialog('setTitle',"修改商品信息");
					$('#ff_productinfo').form('load',{
						"type.id":row.type.id,
						"name" :row.name,
						"code" :row.code,
						"brand":row.brand,
						"num"  :row.num,
						"price":row.price,
						"intro":row.intro,
						"status":row.status,
					});
					urls = "productinfo/updateProduct?id="+row.id;
				}
			}else{
				$.messager.alert('提示','请选择要修改的商品','info');
			}
		}
	</script>
</body>
</html>