<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>电子商品平台--后台登录页</title>
<link href="EasyUI/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
<link href="EasyUI/themes/icon.css" rel="stylesheet" type="text/css"/>
<link href="EasyUI/demo.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="EasyUI/jquery.min.js"></script>
<script type="text/javascript" src="EasyUI/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="EasyUI/jquery.easyui.min.js"></script>
</head>
<body>

	<script type="text/javascript">
		function clearForm() {
			$('#adminLoginForm').form('clear');
		}
		function chectAdminLogin() {
			$('#adminLoginForm').form("submit",{
				//向控制器发送请求
				url : 'admininfo/login',
				success : function (result) {
					var result = eval('('+result+')');
					if(result.success == 'true'){
						window.location.href = 'admin.jsp';
						$("#adminLoginDlg").dialog("close");
					}else{
						$.messager.show({
							title : "提示信息",
							msg : result.message
						})
					}
				}
			})
		}
	</script>
	<div id="adminLoginDlg" class="easyui-dialog" style="left:550px;top:200px;width:300px;height: 200px"
		data-options="title:'后台登录',buttons:'#bb',modal:true">
		<form id="adminLoginForm" method="post">
			<table style="margin: 20px;font-size: 13;">
				<tr>
					<th>用户名</th>
					<td><input class="easyui-textbox" type="text" id="name" name="name" data-options="required:true" value="罗煜炜"/></td>
				</tr>
				<tr>
					<th>密码</th>
					<td><input class="easyui-textbox" type="text" id="pwd" name="pwd" data-options="required:true" value="123456"/></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="bb">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="chectAdminLogin();">登录</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm();">重置</a>
	</div>














</body>
</html>