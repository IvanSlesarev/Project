<%--
  Created by IntelliJ IDEA.
  User: ivanslesarev
  Date: 8.12.24
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="header.jsp"%>
    <div>
        <span>Content.Русский</span>
        <p>Size: ${requestScope.books.size()}</p>
        <p>Id: ${requestScope.books[1].id}</p>
        <p>Empty list: ${not empty books}</p>
    </div>
    <%@include file="footer.jsp"%>
</body>
</html>
