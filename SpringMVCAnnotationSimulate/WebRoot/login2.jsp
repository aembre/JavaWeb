<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
 		<title>login2登陆页面</title>
 	</head> 
 
	<body>
		<p align="center">login2登陆页面</p>
		<fieldset>
			<legend>用户登录</legend>
			<form action="${pageContext.request.contextPath }/login/handle.do" method="post">
				用户名：<input type="text" value="${param.username }" name="username">
				<br/>
				密码：<input type="password" value="${param.pwd }" name="pwd">
				<br/>
				<input type="submit" value="登录">
			</form>
		</fieldset>
		<hr>
		<label style="color:red">${msg }</label>
	</body>
</html>