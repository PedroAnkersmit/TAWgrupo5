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
    <style><%@include file="/css/styles.css"%></style>
    <title>Title</title>
</head>
<body>
<%
    CuentaEntity cuentaReceptora = (CuentaEntity) request.getAttribute("receiveAccount");
    CuentaEntity cuentaEmisora = (CuentaEntity) request.getAttribute("sendAccount");
    Integer idOperacion = (Integer) request.getAttribute("idOperacion");
    //TransferenciaEntity transferencia = (TransferenciaEntity) request.getAttribute("transference");
    //<input type="number" name="idTransferencia" value="transferencia.getIdoperacion()" hidden>

%>
<h1>Introduce la cantidad a Transferir</h1>
<form action="executeTransfer" method="post">
    <input name="idAccount" value="<%=cuentaEmisora.getIdcuenta()%>" hidden>
    <input name="idReceivingAccount" value="<%=cuentaReceptora.getIdcuenta()%>" hidden>
    <input name="idOperation" value="<%=idOperacion%>" hidden>
    <input type="number" name="cantidad" size="20px" maxlength="5" min="1" max="<%=cuentaEmisora.getSaldo()%>">
        <button>Ejecutar</button>
</form>

</body>
</html>
