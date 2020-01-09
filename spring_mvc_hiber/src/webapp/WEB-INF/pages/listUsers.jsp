<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: NoName
  Date: 26.12.2019
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get" action=/addUser style="display:inline;">
    <input type="submit" value="Add new user">
    <br><br>
</form>
<table border="5">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Last Name</th>
        <th>Age</th>
        <th>Email</th>
        <th>Roles</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.lastName}</td>
        <td>${user.age}</td>
        <td>${user.email}</td>
        <td>${user.roles}</td>
        <td><a href=/edit?id=${user.id}>Edit</a></td>
        <td><a href="/delete?id=${user.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>
