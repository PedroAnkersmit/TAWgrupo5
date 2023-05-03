<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="com.taw.grupo5.entity.CuentaEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 24/04/2023
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

  <table border="1">

      <tr>
          <th>NUMERO DE CUENTA</th>
          <th>SALDO</th>
          <th>ESTADO</th>
      </tr>

      <% for(CuentaEntity c : cliente.getCuentasByIdcliente()){ %>
      <tr>
          <td><%= c.getNumerocuenta() %></td>
          <td><%= c.getSaldo() %></td>
          <td><%= c.getTipoestadoByIdestado().getNombre()%></td>
          <td><a href="/cajero/datos/operaciones?idCuenta=<%= c.getIdcuenta() %>">Ver operaciones</a></td>

        <%}%>
      </tr>

  </table>

  <a href="/cajero/datos/editar?idCliente=<%=cliente.getIdcliente()%>">Modificar datos</a>


</body>
</html>
