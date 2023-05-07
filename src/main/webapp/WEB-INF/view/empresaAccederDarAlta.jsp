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
    EmpresaEntity empresaEntity = (EmpresaEntity) request.getAttribute("empresaDaAlta");
%>

<html>
<head>
    <style><%@include file="/css/formularios.css"%></style>
    <title>Dar de alta</title>
</head>
<body>
<h1>Dar de alta un socio o autorizado</h1>

<form:form method="post" action="/empresa/accederdaralta/ir" modelAttribute="empresaDaAlta">
Identificación empresa: <form:input path="idempresa"/>
    <form:button>Acceder</form:button>
</form:form>
</body>
</html>
