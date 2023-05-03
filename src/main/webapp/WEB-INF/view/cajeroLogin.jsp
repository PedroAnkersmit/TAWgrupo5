<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 24/04/2023
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CAJERO</title>
</head>
<body>

    <form action="/cajero/login" method="post">

        Usuario: <input type="text" name="idCliente">
        <button>Iniciar sesión</button>

    </form>

</body>
</html>
