<%@ page import="com.taw.grupo5.entity.CuentaEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 03/05/2023
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CuentaEntity cuenta = (CuentaEntity) request.getAttribute("cuenta");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" action="/cajero/cambioDivisa">
    <input name="idCuenta" hidden="true" value="<%=cuenta.getIdcuenta()%>">
    Cantidad: <input name="cantidad" type="number" min="1" max="<%=cuenta.getSaldo()%>">
    Cambio: <input readonly type="text" value="Euro a dólares">
    <button>Cambio</button>
</form>

</body>
</html>
