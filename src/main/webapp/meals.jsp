<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="ru">
<style>
    table{
        border: black solid 1px;
        border-collapse: collapse;
    }
    td,th{
        border: black solid 1px;
        padding-left: 20px;
        padding-right: 20px;
    }
</style>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<p><a href="meals?action=create">Add Meal</a> </p>
<table>
    <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${mealTos}" var="mealTo">
            <tr style="${mealTo.isExcess() ? 'color:red' : 'color:green'}">
                <td><c:out value="${mealTo.getDateTime().toString().replace('T',' ')}"/></td>
                <td><c:out value="${mealTo.getDescription()}"/></td>
                <td><c:out value="${mealTo.getCalories()}"/></td>
                <td><a href="meals?action=update&id=<c:out value="${mealTo.getId()}"/>">Update</a> </td>
                <td><a href="meals?action=delete&id=<c:out value="${mealTo.getId()}"/>">Delete</a> </td>
            </tr>
        </c:forEach>
    </tbody>

</table>
</body>
</html>