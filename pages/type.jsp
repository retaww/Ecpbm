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
	<table id="typeDg" class="easyui-datagrid"></table>
	<div id="typetb" style="padding:2px 5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" 
		iconCls="icon-add" plain="true" onclick="addType();">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" 
		iconCls="icon-edit" plain="true" onclick="editType();">修改</a>
	</div>
	
	<div id="dlg_type" class="easyui-dialog" title="添加商品类型" closed="true" style="width:500px">
		<div style="padding: 10px 60px 20px 60px">
			<form id="form_type" method="POST">
				<div style="text-align: center;padding: 3px">
					商品类型名&nbsp;<input id="name" name="name" type="text" class="easyui-textbox" style="width:100px" data-options="required:true">
					<br><br>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveType();">保存</a>
				</div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
		$(function() {
			$('#typeDg').datagrid({
				singleSelect:false ,//设置datagrid为单选项
				url: 'type/list',//设置数据源
				pagination: true,//启动分页
				pageSize: 10,//页大小
				pageList: [10,15,20],//设置可供选择的页大小
				rownumbers:true,//显示行号
				fit:true,//自适应
				toolbar:'#typetb',//为datagrid添加工具栏
				columns:[ [{
					title:'序号',
					field:'id',
					align:'center',
					checkbox:true
				},{
					field:'name',
					title:'商品类型名',
					width:200
				}
				] ]
			});
		});
		
		function addType(){
			$('#dlg_type').dialog('open').dialog('setTitle','添加商品类型');
			$('#dlg_type').form('clear');
			urls = 'type/addType';
		}
		
		function saveType(){
			$('#form_type').form("submit",{
				url:urls,
				success : function(result) {
					var result = eval('('+result+')');
					if(result.success == 'true'){
						$('#typeDg').datagrid('reload');
						$('#dlg_type').dialog('close');
					}
					$.messager.show({
						title: "提示信息",
						msg: result.message
					});
				}
			});
		}
		function editType(){
			var rows = $('#typeDg').datagrid('getSelections');
			if(rows.length > 0){
				var row = $('#typeDg').datagrid('getSelected');
				if(row){
					$('#dlg_type').dialog("open").dialog('setTitle',"修改商品类型信息");
					$('#form_type').form('load',{
						"id":row.id,
						"name" :row.name,
					});
					urls = "type/updateType?id="+row.id;
				}
			}else{
				$.messager.alert('提示','请选择要修改的商品类型','info');
			}
		}
	</script>
</body>
</html>