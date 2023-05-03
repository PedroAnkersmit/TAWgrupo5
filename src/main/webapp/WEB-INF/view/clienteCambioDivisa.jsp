<%@ page import="com.taw.grupo5.entity.CuentaEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Pedro
  Date: 03/05/2023
  Time: 12:16
  To change this template use File | Settings | File Templates.

  Created by Pedro Ankersmit Carrion
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Integer idCuenta = (Integer) request.getAttribute("accountId");
    Integer idOp = (Integer) request.getAttribute("operationId");
%>
<h1>Introduzca la cantidad de dolares a cambiar</h1>
<form action="cambio" method="post">
    <input name="idCuenta" value="<%=idCuenta%>" hidden>
    <input name="idOperacion" value="<%=idOp%>" hidden>
    <input type="number" name="cantidad" size="20px" maxlength="5" min="1" max="1000"></br>
    <button>Enviar</button>
</form>

</body>
</html>
