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
    <title>Login</title>
</head>
<body>
    <h2>Iniciar sesion como cliente:</h2><br>
    <form action="/asistente/login" method="post">
        Correo: <input type="text" name="correo"> <br>
        <button type="submit">Iniciar sesión</button>
    </form>

    <h2>Iniciar sesion como asistente:</h2><br>
    <a href="/asistente/login"><button>Iniciar sesion como asistente id=2</button></a>
</body>
</html>
