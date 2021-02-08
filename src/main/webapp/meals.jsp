<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Meals</title>
    <style>
        .txtGreen{color: green}
        .txtRed{color: red}
    </style>
</head>
<body>

<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table border="1" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th>
            Date
        </th>
        <th>
            Description
        </th>
        <th>
            Calories
        </th>
        <th>
            &nbsp;
        </th>
        <th>
            &nbsp;
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${mealsList}">
        <c:set var="mealId" value="${mealId+1}"/>
        <c:set var="clsName" value="txtGreen"/>
        <c:if test = "${meal.excess == true}">
            <c:set var="clsName" value="txtRed"/>
        </c:if>
        <tr class="<c:out value="${clsName}"/>">
            <td>
                <fmt:parseDate value="${ meal.dateTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" />
            </td>
            <td>
                <c:out value="${meal.description}" />
            </td>
            <td>
                <c:out value="${meal.calories}" />
            </td>
            <td>
                <a href="?act=update&id=<c:out value="${mealId}"/>">Update</a>
            </td>
            <td>
                <a href="?act=delete&id=<c:out value="${mealId}"/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
