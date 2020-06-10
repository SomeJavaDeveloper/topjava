<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Meals</title>
    </head>
    <body>
    <h3><a href="index.jsp">Home</a> </h3>
    <hr>
    <h2>Meals</h2>
        <table border=1>
            <thead>
            <tr>
                <th>Id</th>
                <th>Time</th>
                <th>Description</th>
                <th>Calories</th>
                <th colspan=2>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${mealToList}" var="mealTo">
<%--                Refractor--%>
                    <c:if test="${mealTo.excess}">
                        <tr bgcolor="#FF0000">
                            <td><c:out value="${mealTo.id}"/></td>
                            <td><javatime:format pattern="yyyy-MM-dd HH:mm" value="${mealTo.dateTime}"/></td>
                            <td><c:out value="${mealTo.description}" /></td>
                            <td><c:out value="${mealTo.calories}" /></td>
                            <td><a href="meals?action=update&mealId=<c:out value="${mealTo.id}"/>">Update</a></td>
                            <td><a href="meals?action=delete&mealId=<c:out value="${mealTo.id}"/>">Delete</a></td>
                        </tr>
                    </c:if>
                    <c:if test="${!mealTo.excess}">
                        <tr bgcolor="#00FF00">
                            <td><c:out value="${mealTo.id}"/></td>
                            <td><javatime:format pattern="yyyy-MM-dd HH:mm" value="${mealTo.dateTime}"/></td>
                            <td><c:out value="${mealTo.description}" /></td>
                            <td><c:out value="${mealTo.calories}" /></td>
                            <td><a href="meals?action=update&mealId=<c:out value="${mealTo.id}"/>">Update</a></td>
                            <td><a href="meals?action=delete&mealId=<c:out value="${mealTo.id}"/>">Delete</a></td>
                        </tr>
                    </c:if>
            </c:forEach>
            </tbody>
        </table>
    <p><a href="meals?action=add">Add meal</a></p>
    </body>
</html>
