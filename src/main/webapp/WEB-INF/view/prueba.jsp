<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: ignam
  Date: 22/04/2023
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form>
    <form action="/asistente/login" method="post">
        Usuario: <input type="text" name="user"></input> <br>
        <button type="submit">Iniciar sesión</button>
    </form>
</form>
</body>
</html>
