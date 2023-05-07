<%@ page import="com.taw.grupo5.entity.CuentaEntity" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 02/05/2023
  Time: 19:40
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

  <form action="/cajero/sacarDinero" method="post">
    Introducir cantidad: <input type="number" name="cantidad" min="1" max="<%=cuenta.getSaldo()%>"><br/>
    <input type="number" hidden="true" value="<%=cuenta.getIdcuenta()%>" name="idCuenta">
    <button>Sacar dinero</button>
  </form>

</body>
</html>
