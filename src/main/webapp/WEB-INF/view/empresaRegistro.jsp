<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: jesus
  Date: 24/04/2023
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <style><%@include file="/css/formularios.css"%></style>
    <title>Registro Empresa</title>
</head>
<body>
<h1>Registro empresa</h1>
<form:form action="/empresa/registro/guardar" method="post" modelAttribute="empresaregistro">
    <h2>Empresa:</h2>
    <form:hidden path="empresa.idempresa"/>
    Nombre <form:input path="empresa.nombre"/><br/>
    Fecha cierre <form:input type="date" path="empresa.fechacierre"/><br/>

    <h2>Socio empresa:</h2>
    <form:hidden path="cliente.idcliente"/>
    Nombre <form:input path="cliente.nombre"/><br/>
    Email <form:input path="cliente.email"/><br/>
    Telefono <form:input path="cliente.telefono"/><br/>
    <form:hidden path="cliente.tipoclienteByIdtipocliente.idtipocliente"/>
    <form:hidden path="cliente.empresaByIdempresa.idempresa"/>

    <h2>Cuenta del socio:</h2>
    <form:hidden path="cuenta.idcuenta"/>
    <form:hidden path="cuenta.clienteByIdcliente.idcliente"/>
    <form:hidden path="cuenta.numerocuenta"/>
    <form:hidden path="cuenta.tipoestadoByIdestado.idtipoestado"/>
    <form:hidden path="cuenta.saldo"/>
    Fecha cierre: <form:input type="date" path="cuenta.fechacierre"/><br/>
    <form:button>Registrar</form:button>
</form:form>
</body>
</html>