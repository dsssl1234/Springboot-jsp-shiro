<%@page contentType="text/html; UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>注册</h1>
<form action="${pageContext.request.contextPath}/user/register" method="post">
    <p>账号:</p><input type="text" name="userName">
    <p>密码:</p><input type="text" name="passWord">
    <p></p><button type="submit" value="提交">注册</button>
</form>
</body>
</html>