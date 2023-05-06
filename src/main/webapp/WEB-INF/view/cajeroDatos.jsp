<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 02/05/2023
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form:form action="/cajero/datos/guardar" modelAttribute="cliente" method="post">
        <form:hidden path="idcliente"/>
        <form:hidden path="tipoclienteByIdtipocliente.idtipocliente"/>
        Nombre: <form:input path="nombre"/><br/>
        Email: <form:input path="email"/><br/>
        Teléfono: <form:input path="telefono"/><br/>
        <form:button>Guardar</form:button>
    </form:form>

</body>
</html>
