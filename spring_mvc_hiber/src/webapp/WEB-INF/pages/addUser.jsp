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
    <output>${message}</output>
    <br>
    ID: <input type="number" name="id"/><br><br>
    Name: <input type="text" name="name"/><br><br>
    LastName: <input type="text" name="lastName"/><br><br>
    Age: <input type="number" name="age"/><br><br>
    Email: <input type="text" name="email"/><br><br>
    Password: <input type="password" name="password"/><br><br>
    <input type="submit" value="Add User">
</form>
</body>
</html>
