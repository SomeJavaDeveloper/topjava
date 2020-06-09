<%--
  Created by IntelliJ IDEA.
  User: friday58
  Date: 08.06.2020
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Java Enterprise (Topjava)</title>
</head>
<body>
<h3>Проект <a href="https://github.com/JavaWebinar/topjava" target="_blank">Java Enterprise (Topjava)</a></h3>
<hr>
<ul>
    <li><a href="users">Users</a></li>
    <li><a href="${pageContext.request.contextPath}/meals?action=listMeal">Meals</a></li>
</ul>
</body>
</html>
