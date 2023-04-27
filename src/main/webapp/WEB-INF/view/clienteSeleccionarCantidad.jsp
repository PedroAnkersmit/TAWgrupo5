<%@ page import="com.taw.grupo5.entity.CuentaEntity" %>
<%@ page import="com.taw.grupo5.entity.OperacionEntity" %>
<%@ page import="com.taw.grupo5.entity.TransferenciaEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Pedro
  Date: 27/04/2023
  Time: 12:35
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
    CuentaEntity cuentaReceptora = (CuentaEntity) request.getAttribute("sendAccount");
    Integer idCuentaEmisora = (Integer) request.getAttribute("idAccount");
    OperacionEntity operacion = (OperacionEntity) request.getAttribute("operation");
    TransferenciaEntity transferencia = (TransferenciaEntity) request.getAttribute("transference");
%>
<h1>Introduce la cantidad a Transferir</h1>
<form action="/executeTransfer" method="post">
    <input type="number" name="idAccount" value="<%=idCuentaEmisora%>" hidden>
    <input type="number" name="idOperation" value="<%=operacion.getIdoperacion()%>" hidden>
    <input type="number" name="idReceivingAccount" value="<%=cuentaReceptora.getIdcuenta()%>" hidden>
    <input type="number" name="cantidad" size="20px" maxlength="5">
    <input type="number" name="idTransferencia" value="<%=transferencia.getIdoperacion()%>">
</form>

</body>
</html>
