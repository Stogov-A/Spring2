<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: NoName
  Date: 20.12.2019
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="5">
    <header>
        <c:if test="${param.locale == 'ru'}">
            МАШИНЫ
        </c:if>
        <c:if test="${param.locale == 'en'}">
            CARS
        </c:if>
    </header>
    <tr>
        <th>ID</th>
        <th>Brand</th>
        <th>Model</th>
        <th>Number</th>
    </tr>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>${car.id}</td>
            <td>${car.brand}</td>
            <td>${car.model}</td>
            <td>${car.number}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
