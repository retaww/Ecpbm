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
	<table id="userDg" class="easyui-datagrid"></table>
	<div id="userTb" style="padding:2px 5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="setIsEnableUser(1)" iconCls="icon-edit">启用客户</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="setIsEnableUser(0)" iconCls="icon-remove">禁用客户</a>
	</div>
	<div id="searchUserTb" style="padding:2px 5px">
		<form id="searchUserForm">
			<div style="padding:3px">
				客户名称&nbsp;<input  class="easyui-textbox" style="width:115px" id="search_userName" name="search_userName">&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="searchUserInfo()" iconCls="icon-search">查找</a>
			</div>
		</form>
	</div>
	
	
	<script type="text/javascript">
		$(function() {
			$('#userDg').datagrid({
				singleSelect:false ,//设置datagrid为单选项
				url: 'userinfo/list',//设置数据源
				pagination: true,//启动分页
				pageSize: 10,//页大小
				pageList: [10,15,20],//设置可供选择的页大小
				rownumbers:true,//显示行号
				fit:true,//自适应
				toolbar:'#userTb',//为datagrid添加工具栏
				header:'#searchUserTb',//添加搜索栏
				queryParams:{},
				columns:[[{
					title:'序号',
					field:'id',
					align:'center',
					checkbox:true
				},{
					field:'userName',
					title:'登录名',
					width:100
				},{
					field:'realName',
					title:'真实姓名',
					width:100
				},{
					field:'sex',
					title:'性别',
					width:100
				},{
					field:'address',
					title:'地址',
					width:200
				},{
					field:'email',
					title:'邮箱',
					width:200
				},{
					field:'regDate',
					title:'注册日期',
					width:200
				},{
					field:'status',
					title:'客户状态',
					width:100,
					formatter:function(value,row,index){
						if(row.status == 1){
							return "启用";
						}else{
							return "禁用";
						}
					}
				}
				
				]],
			});
		});
		
		function searchUserInfo(){
			var userName = $('#search_userName').textbox("getValue");
			$('#userDg').datagrid('load',{
				userName:userName
			});
		}
		
		function setIsEnableUser(flag){
			var rows = $('#userDg').datagrid('getSelections');
			if(rows.length>0){
				$.messager.confirm('Confirm','确定要设置吗？',function(r){
					if(r){
						var uids = "";
						for(var i=0;i<rows.length;i++){
							uids += rows[i].id + ",";
						}
						$.post('userinfo/setIsEnableUser',{
							uids:uids,
							flag:flag
						},function(result){
							if(result.success == 'true'){
								$('#userDg').datagrid('reload');
								$.messager.show({
									title: "提示消息",
									msg:result.message
								});
							}else{
								$.messager.show({
									title: "提示消息",
									msg:result.message
								});
							}
						},'json');
					}
				});
			}else{
				$.messager.alert('提示','请选择要启用或禁用的客户','info');
			}
		}
	</script>
</body>
</html>