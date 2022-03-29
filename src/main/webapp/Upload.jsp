<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/3/28
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form action="fileUpload"   method="post" enctype="multipart/form-data">
    文件1：<input type="file" name="file1"><br>
    文件2：<input type="file" name="file2"><br>
    <input type="submit" value="上传文件">
</form>

<h2>下载文件</h2>
<a href="downLoadServlet?filename=a.png">a.png</a>
<a href="downLoadServlet?filename=哈哈.png">哈哈.png</a>
</body>
</html>
