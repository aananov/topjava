<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<table>
    <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${mealTos}" var="mealTo">
            <tr style="${mealTo.isExcess() ? 'color:red' : 'color:green'}">
                <td><c:out value="${mealTo.getDateTime().toString().replace('T',' ')}"/></td>
                <td><c:out value="${mealTo.getDescription()}"/></td>
                <td><c:out value="${mealTo.getCalories()}"/></td>
            </tr>
        </c:forEach>
    </tbody>

</table>
</body>
</html>