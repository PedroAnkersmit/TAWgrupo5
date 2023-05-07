<%--
  Created by IntelliJ IDEA.
  User: Hilaria Romero Bouyahia
  Date: 07/05/2023
  Time: 1:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <style><%@include file="/css/styles.css"%></style>
    <title>Login Asistente</title>
</head>
<body>
    <h6> Accediendo con el siguiente sessionid: <%=session.getId()%> </h6>

    <h1>Iniciar sesión:</h1>
    <form action="/asistente/login" method="post">
        Nombre: <input type="text" name="nombre"> <br>
        <button>Enviar</button>
    </form>
    <a href="/"><button>Volver al inicio</button></a>
</body>
</html>
