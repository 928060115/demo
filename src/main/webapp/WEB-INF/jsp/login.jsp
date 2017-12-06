<%@ page contentType="text/html; chartset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <form method="post" action="/user/login">
        用户名：<input type="text" name="username"><br>
        密码：<input type="password" name="password"><br>
        手机号：<input type="number" name="mobile"><br>
        flag：<input type="text" name="flag"><br>
        <input type="submit" value="登录">
    </form>
</body>
</html>