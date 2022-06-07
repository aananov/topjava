<%--
  Created by IntelliJ IDEA.
  User: хоз
  Date: 07.06.2022
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="ru">
<html>
<head>
    <title>
        <c:out value="${mealToUpdate==null ? 'Add Meal' : 'Update Meal'}"/>
    </title>
</head>

<body>
<h3>
    <a href="index.html">Home</a>
</h3>
<hr>
<h2>
    <c:out value="${mealToUpdate==null ? 'Add Meal' : 'Update Meal'}"/>
</h2>
<p></p>
<form method="post" action="meals" name="mealUpdate">
    <fieldset style="width: max-content">
        <legend>
            <c:out value="${mealToUpdate==null ? 'Add Meal' : 'Edit meal with ID: '}"/><c:out
                value="${mealToUpdate.getId()}"/>
        </legend>
        <p>
            <input type="hidden" name="mealID"
                   value="<c:out value="${mealToUpdate.getId()}" />"/>
        </p>
        <p>
            <label>DateTime: </label>
            <input type="datetime-local" name="dateTime" required
                   value="<c:out value="${mealToUpdate.getDateTime()}" />"/>
        </p>
        <p>
            <label>Description: </label>
            <input type="text" name="description" required
                   value="<c:out value="${mealToUpdate.getDescription()}" />"/>
        </p>
        <p>
            <label>Calories: </label>
            <input type="number" min="0" max="999999999" name="calories" required
                   value="<c:out value="${mealToUpdate.getCalories()}" />"/>
        </p>
    </fieldset>
    <p>
        <input type="reset" value="Clear">&nbsp
        <input type="submit" value="Save">&nbsp
        <button type="button" name="Cancel" onclick="location.href='meals'">Cancel</button>
    </p>
</form>
</body>
</html>
