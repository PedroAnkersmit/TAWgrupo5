<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.taw.grupo5.entity.EmpresaEntity" %><%--
  Created by IntelliJ IDEA.
  User: jesus
  Date: 25/04/2023
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    EmpresaEntity empresaEntity = (EmpresaEntity) request.getAttribute("empresaAlta");
%>

<html>
<head>
    <style><%@include file="/css/styles.css"%></style>
    <title>Dar de alta</title>
</head>
<body>
<h1>Dar de alta un socio o autorizado en <%=empresaEntity.getNombre()%> </h1>

<form:form method="post" action="/empresa/dardealta/guardar" modelAttribute="personaDeAlta">
<form:hidden path="cliente.idcliente"/>
    Nombre <form:input path="cliente.nombre"/><br/>
    Email <form:input path="cliente.email"/><br/>
    Telefono <form:input path="cliente.telefono"/><br/>
    Tipo cliente: <form:select path="cliente.tipoclienteByIdtipocliente.idtipocliente" items="${tiposcliente}" itemValue="idtipocliente" itemLabel="nombre"/><br/>
    <form:hidden value="<%=empresaEntity.getIdempresa()%>" path="cliente.empresaByIdempresa.idempresa"/>
    Fecha cierre de la cuenta: <form:input type="date" path="cuenta.fechacierre"/><br/>
    <form:button>Dar de alta</form:button>
</form:form>
</body>
</html>
