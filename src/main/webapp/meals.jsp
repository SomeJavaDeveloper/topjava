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
    <table>
        <thead>
        <tr>
            <th>Time</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<MealTo> mealToList = (List<MealTo>) request.getAttribute("mealToList");

            if (mealToList != null && !mealToList.isEmpty()) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                for (MealTo mealTo : mealToList) {
                    if(mealTo.isExcess()){
                        out.println("<tr style=\"background-color:#FF0000\">");
                    } else out.println("<tr style=\"background-color:#00FF00\">");
                        out.println("<th>" + mealTo.getDateTime().format(formatter) + "</th>");
                        out.println("<th>" + mealTo.getDescription() + "</th>");
                        out.println("<th>" + mealTo.getCalories() + "</th>");
                    out.println("</tr>");
                }
            }
        %>
        </tbody>
    </table>
    </body>
</html>
