<%@ page import="com.taw.grupo5.entity.CuentaEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 03/05/2023
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    double cantidad = (double) request.getAttribute("cantidad");
    String moneda = (String) request.getAttribute("moneda");
    CuentaEntity cuenta = (CuentaEntity) request.getAttribute("cuenta");
%>
<html>
<head>
    <style><%@include file="/css/styles.css"%></style>
    <title>Title</title>
</head>
<body>

<h1>Aquí tiene su(s) <%=cantidad%> <%=moneda%></h1>
<h2>Saldo restante de la cuenta: <%=cuenta.getSaldo()%></h2>
<form action="/cajero/datos/">
    <input hidden="true" name="idCliente" value="<%=cuenta.getClienteByIdcliente().getIdcliente()%>">
    <button>Proceder</button>
</form>

</body>
</html>
