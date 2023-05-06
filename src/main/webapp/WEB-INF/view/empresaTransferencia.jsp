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

<form:form action="/empresa/transferencia/enviar" method="post" modelAttribute="transferencia">
        <form:hidden path="idTransferencia"/>
        Cantidad <form:input path="cantidad"/><br/>
        Cuenta destino <form:input path="cuentaDestino"/><br/>
        Fecha de ejecución: <form:input type="date" path="fechaejecucion"/><br/>
        <form:hidden path="operacionByIdoperacion"/>
        <form:button>Guardar cambios</form:button>
</form:form>

</body>
</html>
