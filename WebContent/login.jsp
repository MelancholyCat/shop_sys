<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/login"  method="post">
		账  号：<input id="phone" type="text" name="phone"  placeholder="请输入账号"/> <br>
		密  码：<input id="password" type="password" name="password" placeholder="请输入手机号码"> <br>
		<input type="submit" id="submit" value="登录"/>
	</form>
</body>
</html>