<%--
  Created by IntelliJ IDEA.
  User: friday58
  Date: 10.06.2020
  Time: 0:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<form method="POST" action='meals' name="frmAddMeal">
    Meal Id : <input
        type="hidden" name="mealId"
        value="<c:out value="${meal.id}" />" /> <br />

    Date Time : <input
        type="datetime-local" name="dateTime"
        value="<c:out value="${meal.dateTime}" />" /> <br />

    Description : <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />" /> <br />
    Calories : <input
        type="text" name="calories"
        value="<c:out value="${meal.calories}" />" /> <br />
    <input type="submit" value="Submit" />
</form>
</body>
</html>
