<%--
  Created by IntelliJ IDEA.
  User: NoName
  Date: 28.12.2019
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <LABEL>Delete User</LABEL><br><br>
    <label>Name</label>
    ${user.name}<br><br>

    <label>LastName</label>
    ${user.lastName}<br><br>

    <label>Age</label>
    ${user.age}<br><br>

    <label>Email</label>
    ${user.email}<br><br>

    <label>Enter password for delete user</label>
    <input name="password" type="password"><br>
    <input type="submit" value="Delete"/><br>
</form>
</body>
</html>
