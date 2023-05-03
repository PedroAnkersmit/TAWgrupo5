<%@ page import="com.taw.grupo5.entity.CuentaEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 03/05/2023
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  CuentaEntity cuenta = (CuentaEntity) request.getAttribute("cuenta");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" action="/cajero/transferencia">
  <input name="idCuenta" hidden="true" value="<%=cuenta.getIdcuenta()%>">
  <input name="cantidad" type="number" min="1" max="<%=cuenta.getSaldo()%>">
  <input name="idDestino" type="number">
  <button>Enviar dinero</button>
</form>

</body>
</html>
