<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/13
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        你是超级管理员可以看的信息
    </sec:authorize>
    <br>
    ==================================================
    <br>
    <sec:authorize access="hasRole('ROLE_CUSTOM')">
        你是普通用户可以看的信息
    </sec:authorize>
</body>
</html>
