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
    <title>Registro Empresa</title>
</head>
<body>
<h1>Registro empresa</h1>
<form:form action="/empresa/registro/guardar" method="post" modelAttribute="empresa">
    <form:hidden path="idempresa"/>
    Nombre <form:input path="nombre"/><br/>
    Fecha cierre <form:input type="date" path="fechacierre"/><br/>
    <form:button>Registrar</form:button>
</form:form>
</body>
</html>
