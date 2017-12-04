<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/1
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
    <form action="/upload" method="post" enctype="multipart/form-data">
        单个文件上传：<br>
        <input type="file" name="file"><br>
        <input type="submit" value="文件上传">
    </form>
    <span>=================================</span>
    <form action="/uploads" method="post" enctype="multipart/form-data">
        多个文件上传：<br>
        文件1：<input type="file" name="file"><br>
        文件2：<input type="file" name="file"><br>
        文件3：<input type="file" name="file"><br>
        <input type="submit" value="多文件上传">
    </form>
</body>
</html>
