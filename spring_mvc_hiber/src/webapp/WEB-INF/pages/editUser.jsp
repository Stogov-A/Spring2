<%--
  Created by IntelliJ IDEA.
  User: NoName
  Date: 27.12.2019
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <input type="hidden" value="${user.id}" name="id"/>
    <label>Edit User</label><br>
    <label>Name</label><br>
    <input name="name" value=${user.name}><br><br>
    <label>LastName</label><br>
    <input name="lastName" value=${user.lastName}><br><br>
    <label>Age</label><br>
    <input name="age" type="number" value=${user.age}><br><br>
    <label>Email</label><br>
    <input name="email" value=${user.email}><br><br>
    <label>Password</label><br>
    <input name="password" value=${user.password}><br><br>
    <input type="submit" value="Save"/><br>
</form>

</body>
</html>
