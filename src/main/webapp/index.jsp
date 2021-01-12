<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <a href="${pageContext.request.contextPath}/user/logout">退出</a>
    <h1>系统主页</h1>
    <ul>
        <shiro:hasRole name="admin">
            <li><a href="">用户管理</a></li>
        </shiro:hasRole>
        <shiro:hasAnyRoles name="user,cust">
            <li><a href="">商品管理</a></li>
        </shiro:hasAnyRoles>
        <shiro:hasPermission name="user:*:*">
            <li><a href="">订单管理</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="cust:*:01">
        <li><a href="">物流管理</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="cust:*:02">
            <li><a href="">其他管理</a></li>
        </shiro:hasPermission>
    </ul>
</body>
</html>