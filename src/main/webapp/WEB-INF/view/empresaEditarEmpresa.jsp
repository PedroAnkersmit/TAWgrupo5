<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: jesus
  Date: 25/04/2023
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style><%@include file="/css/styles.css"%></style>
    <title>Editar cliente</title>
</head>
<body>

<h1>Editar información de mi empresa</h1>
<form:form action="/empresa/editarempresa/guardar" method="post" modelAttribute="empresaAEditar">
  <form:hidden path="idempresa"/>
  Nombre <form:input path="nombre"/><br/>
  Fecha cierre <form:input type="date" path="fechacierre"/><br/>
  <form:button>Guardar cambios</form:button>
</form:form>
</body>
</html>
