<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: jesus
  Date: 25/04/2023
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transferencia</title>
</head>
<body>
<h1>Realizar transferencia</h1>

<form:form action="/empresa/cambiodivisa/enviar" method="post" modelAttribute="cambiodivisa">
        <form:hidden path="idoperacion"/>
        <form:hidden path="monedaventa"/>
        <form:hidden path="monedacompra"/>
        <form:hidden path="cantidadcompra"/>
        Dólares <form:input path="cantidadventa"/><br/>
        <form:hidden path="comision"/>
        <form:hidden path="operacionByIdoperacion"/>
        <form:button>Guardar cambios</form:button>
</form:form>

</body>
</html>
