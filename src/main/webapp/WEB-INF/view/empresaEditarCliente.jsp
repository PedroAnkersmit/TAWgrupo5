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
    <title>Editar cliente</title>
</head>
<body>

<h1>Editar mi información</h1>
<form:form action="/empresa/editarcliente/guardar" method="post" modelAttribute="clienteAEditar">
  <form:hidden path="idcliente"/>
  Nombre <form:input path="nombre"/><br/>
  Email <form:input path="email"/><br/>
  Telefono <form:input path="telefono"/><br/>
  <form:hidden path="tipoclienteByIdtipocliente.idtipocliente"/>
  <form:hidden path="empresaByIdempresa.idempresa"/>
  <form:button>Guardar cambios</form:button>
</form:form>
</body>
</html>
