<%@ page import="shop.mtcoding.mvcapp2.model.Board" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 8491389
  Date: 2023-02-25
  Time: 오후 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
<h1>게시글 쓰기</h1>
<form action="/board/save.do" method="post" enctype="application/x-www-form-urlencoded">
    <input type="text" name="title" placeholder="Enter title" /><br/>
    <input type="text" name="content" placeholder="Enter content" /><br/>
    <button type="submit">생성</button>
</form>
</div>
</body>
</html>
