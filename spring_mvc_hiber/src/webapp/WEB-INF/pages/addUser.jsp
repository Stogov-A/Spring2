<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: NoName
  Date: 28.12.2019
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <output>${message}</output><br><br>
    Name: <input type="text" name="name"/><br><br>
    LastName: <input type="text" name="lastName"/><br><br>
    Age: <input type="number" name="age" value="0"/><br><br>
    Email: <input type="text" name="email"/><br><br>
    Password: <input type="password" name="password"/><br><br>
    <p>
        <label>is user</label>
        <input type="checkbox" name="userRoles" id="user" value="ROLE_USER" checked /><br>
        <label>is admin</label>
        <input type="checkbox" name="userRoles" id="admin" value="ROLE_ADMIN"/><br>
    </p>
    <input type="submit" value="Add User"><br><br>
    <a href="<c:url value="/logout" />">Logout</a>
</form>
</body>
</html>
