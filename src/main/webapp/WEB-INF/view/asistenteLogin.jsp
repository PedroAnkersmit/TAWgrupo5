<%--
  Created by IntelliJ IDEA.
  User: diamo
  Date: 07/05/2023
  Time: 1:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Login Asistente</title>
</head>
<body>
    <h6> Accediendo con el siguiente sessionid: <%=session.getId()%> </h6>
    <form action="/asistente/login" method="post">
        Nombre: <input type="text" name="nombre"> <br>
        <button>Enviar</button>
    </form>
</body>
</html>
