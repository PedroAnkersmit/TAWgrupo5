<%@ page import="com.taw.grupo5.entity.CuentaEntity" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Pedro
  Date: 26/04/2023
  Time: 11:48
  To change this template use File | Settings | File Templates.

  Created by Pedro Ankersmit Carrion
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Transferencia</h1>
<%
    List<CuentaEntity> listaCuentas = (List<CuentaEntity>) request.getAttribute("allAccounts");

%>

<form:form action="clienteHome/seleccionarRecibidorTransferencia" method="post" modelAttribute="transference">
    <form:hidden path="idoperacion"/>
    Cantidad a Transferir: <form:input path="cantidad" maxlength="5" size="20px"/>
    <form:button>Confirmar</form:button>
</form:form>

</body>
</html>
