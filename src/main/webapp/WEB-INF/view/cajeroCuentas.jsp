<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="com.taw.grupo5.entity.CuentaEntity" %>
<%@ page import="com.taw.grupo5.dto.ClienteDTO" %>
<%@ page import="com.taw.grupo5.dto.CuentaDTO" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 24/04/2023
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
%>
<html>
<head>
    <style><%@include file="/css/styles.css"%></style>
    <title>Title</title>
</head>
<body>

    <h1>BIENVENIDO/A <%=cliente.getNombre()%></h1>
    <h2>Estas son tus cuentas</h2>

  <table border="1">

      <tr>
          <th>NUMERO DE CUENTA</th>
          <th>SALDO</th>
          <th>ESTADO</th>
      </tr>

      <% for(CuentaDTO c : cliente.getCuentasByIdCliente()){ %>
      <tr>
          <td><%= c.getNumerocuenta() %></td>
          <td><%= c.getSaldo() %></td>
          <td><%= c.getTipoEstado().getNombre()%></td>

          <% if(c.getTipoEstado().getNombre().equals("operativa")){ %> <td><a href="/cajero/datos/operaciones?idCuenta=<%= c.getIdcuenta() %>">Ver operaciones</a></td>
          <% } else if(c.getTipoEstado().getNombre().equals("bloqueada")){ %> <td><a href="/cajero/solicitudDesbloqueo?idCuenta=<%= c.getIdcuenta() %>">Solicitar desbloqueo</a></td>
          <% } %>
        <%}%>
      </tr>

  </table>

  <a href="/cajero/datos/editar?idCliente=<%=cliente.getIdCliente()%>">Modificar datos</a>


</body>
</html>
