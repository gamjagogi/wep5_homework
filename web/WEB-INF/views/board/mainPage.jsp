<%@ page import="shop.mtcoding.mvcapp2.model.Board" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 8491389
  Date: 2023-02-24
  Time: 오후 6:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div>
 <form action="/board/login.do" method="post" enctype="application/x-www-form-urlencoded">
     <label for="userId">ID</label>
     <input type="text" id="userId" name="userId" placeholder="Enter id" required><br/>
     <label for="userPw">PASSWORD</label>
     <input type="text" id="userPw" name="userPw" placeholder="Enter password" required><br/>
     <%
         String a = (String) request.getAttribute("errorMessage");
     %>
     <span style="color: red"><%=a%></span>
     <br>
     <button type="submit">login</button>
 </form>
</div>
<form action='/board/admin.do' method="GET">
    <input type="button" value="회원가입" onclick="location.href='http://localhost:10000/board/admin.do'"/>
</form>
<h1>목록</h1>
<hr/>
<table border="1">
    <tread>
        <tr>
            <th>id</th>
            <th>만화</th>
            <th>주인공</th>
        </tr>
    </tread>
    <tbody>
    <%
        List<Board> boardList = (List<Board>) request.getAttribute("boardList");
        for(int i=0; i<boardList.size(); i++){
    %>
    <tr>
        <td><%=boardList.get(i).getId()%></td>
        <td><%=boardList.get(i).getTitle()%></td>
        <td><%=boardList.get(i).getContent()%></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

</body>
</html>
