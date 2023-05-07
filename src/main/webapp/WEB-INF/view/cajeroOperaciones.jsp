<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.taw.grupo5.entity.CuentaEntity" %>
<%@ page import="com.taw.grupo5.entity.OperacionEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 02/05/2023
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  CuentaEntity cuenta = (CuentaEntity) request.getAttribute("cuenta");
  List<OperacionEntity> listaOperaciones = (List<OperacionEntity>) request.getAttribute("listaOperaciones");
%>
<html>
<head>
    <style><%@include file="/css/styles.css"%></style>
    <title>Title</title>
</head>
<body>

<form:form action="/cajero/datos/operaciones" method="post" modelAttribute="filtro">
  <form:input type="date" path="fechaMinima"/>
  <form:input type="date" path="fechaMaxima"/>
  <form:hidden path="idCuenta"/>
  <form:select path="tipoOperacion">
    <form:option value="" label="Sin Filtro"/>
    <form:option value="sacarDinero" label="Extracciones"/>
    <form:option value="transferencia" label="Transferencias"/>
    <form:option value="cambioDivisa" label="Cambios de divisa"/>
  </form:select>
  <form:button>Filtrar</form:button>
</form:form>

<h1>NÚMERO DE CUENTA: <%=cuenta.getNumerocuenta()%></h1>
<h2>SALDO DE LA CUENTA: <%=cuenta.getSaldo()%></h2>

  <table border="1">

    <tr>
      <th>OPERACIÓN</th>
      <th>CANTIDAD</th>
      <th>FECHA</th>
    </tr>

    <% for(OperacionEntity o : listaOperaciones){ %>

      <tr>
        <% if(!o.getSacardinerosByIdoperacion().isEmpty()){ %>

        <td>EXTRACCIÓN</td>
        <td><%=o.getSacardinerosByIdoperacion().get(0).getCantidad()%>€</td>

        <%} else if(!o.getTransferenciasByIdoperacion().isEmpty()){ %>

        <td>TRANSFERENCIA</td>
        <td><%=o.getTransferenciasByIdoperacion().get(0).getCantidad()%>€ (a <%=o.getTransferenciasByIdoperacion().get(0).getIdcuentadestino()%>)</td>

        <%} else if(!o.getCambiodivisasByIdoperacion().isEmpty()){ %>

        <td>CAMBIO DE DIVISA</td>
        <td><%=o.getCambiodivisasByIdoperacion().get(0).getCantidadventa()%>€</td>

        <%}%>

        <td><%=o.getFecha()%></td>
      </tr>

    <%}%>

  </table>

  <a href="/cajero/sacarDinero?idCuenta=<%=cuenta.getIdcuenta()%>">Sacar dinero</a><br/>
  <a href="/cajero/transferencia?idCuenta=<%=cuenta.getIdcuenta()%>">Transferencia</a><br/>
  <a href="/cajero/cambioDivisa?idCuenta=<%=cuenta.getIdcuenta()%>">Cambio divisa</a><br/>


</body>
</html>
