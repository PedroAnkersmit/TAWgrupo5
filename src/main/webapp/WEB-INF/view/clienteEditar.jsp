<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--
  Created by IntelliJ IDEA.
  User: Pedro
  Date: 24/03/2023
  Time: 17:28
  To change this template use File | Settings | File Templates.

  Created by Pedro Ankersmit Carrión
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<h1>Mis Datos:</h1>
<form:form action="/clienteHome/guardar" modelAttribute="user" method="post">
    <form:hidden path="idCliente"/>
    <form:hidden path="idConversacion"/>
    Nombre: <form:input path="nombre" size="30px" maxlength="30"/> </br>
    Email: <form:input path="email" size="60px" maxlength="80"/> </br>
    Telefono: <form:input path="telefono" size="30px" maxlength="15"/> </br>
    <form:hidden path="tipocliente"/>
    <form:hidden path="empresa"/>
    <form:hidden path="fechainicio"/>
    <form:button>Guardar</form:button>
</form:form>

</body>
</html>