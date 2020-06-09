<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Meals</title>
    </head>
    <body>
    <h3><a href="index.html">Home</a> </h3>
    <hr>
    <h2>Meals</h2>
<%--    <table border=1>--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th>Id</th>--%>
<%--            <th>Time</th>--%>
<%--            <th>Description</th>--%>
<%--            <th>Calories</th>--%>
<%--            <th colspan=2>Action</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <%--%>
<%--            List<MealTo> mealToList = (List<MealTo>) request.getAttribute("mealToList");--%>

<%--            if (mealToList != null && !mealToList.isEmpty()) {--%>

<%--                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");--%>

<%--                for (MealTo mealTo : mealToList) {--%>
<%--                    if(mealTo.isExcess()){--%>
<%--                        out.println("<tr style=\"background-color:#FF0000\">");--%>
<%--                    } else out.println("<tr style=\"background-color:#00FF00\">");--%>
<%--                        out.println("<th>" + mealTo.getId() + "</th>");--%>
<%--                        out.println("<th>" + mealTo.getDateTime().format(formatter) + "</th>");--%>
<%--                        out.println("<th>" + mealTo.getDescription() + "</th>");--%>
<%--                        out.println("<th>" + mealTo.getCalories() + "</th>");--%>
<%--                        out.println("<th><a href=\"\"> Update </a></th>");--%>
<%--                        String mealId = String.valueOf(mealTo.getId());--%>
<%--                        String url = "<th><a href=\"topjava/meals?action=delete\">Delete</a></th>";--%>
<%--                        out.println(url);--%>
<%--                    out.println("</tr>");--%>
<%--                }--%>
<%--            }--%>
<%--        %>--%>
<%--        </tbody>--%>

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
            <c:forEach items="${mealToList}" var="meal">
                <tr>
<%--                    <% System.out.println(pageContext.findAttribute("mealToList")); %>--%>
                    <td><c:out value="${meal.id}"/></td>
                    <td><c:out value="${meal.dateTime}" /></td>
                    <td><c:out value="${meal.description}" /></td>
                    <td><c:out value="${meal.calories}" /></td>
                    <td><a href="meals?action=edit&userId=<c:out value="${meal}"/>">Update</a></td>
                    <td><a href="meals?action=delete&userId=<c:out value="${meal.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    <p><a href="">Add meal</a></p>
    </body>
</html>
