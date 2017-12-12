<%@ page contentType="text/html; chartset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form method="post" action="/login">
        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">--%>
        用户名：<input type="text" name="username" value="admin"><br>
        密码：<input type="password" name="password" value="12345678"><br>
        <%--手机号：<input type="number" name="mobile" value="185000000017"><br>
        flag：<input type="text" name="flag" value="1"><br>--%>
        <input type="submit" value="登录">
    </form>
</body>
</html>