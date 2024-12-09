
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Авторы</h1>
<ul>
  <c:forEach var="book" items="${requestScope.book}">
    <li>${book.id}</li>
  </c:forEach>
</ul>

</body>
</html>
