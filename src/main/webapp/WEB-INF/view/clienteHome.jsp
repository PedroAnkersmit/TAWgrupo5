<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.CuentaEntity" %>


<%--
  Created by IntelliJ IDEA.
  User: Pedro
  Date: 24/03/2023
  Time: 16:57
  To change this template use File | Settings | File Templates.

  Created by Pedro Ankersmit Carrión
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClienteEntity usuario = (ClienteEntity) request.getAttribute("user");
    List<CuentaEntity> cuentas = (List<CuentaEntity>) request.getAttribute("accounts");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Mis datos: </h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>ID_CONVERSACION</th>
        <th>NOMBRE</th>
        <th>EMAIL</th>
        <th>TELEFONO</th>
        <th>FECHA_INICIO</th>
    </tr>

    <tr>
        <td><%= usuario.getIdcliente()%>
        </td>
        <td><%= usuario.getIdconversacion()%>
        </td>
        <td><%= usuario.getNombre()%>
        </td>
        <td><%= usuario.getEmail()%>
        </td>
        <td><%= usuario.getTelefono()%>
        </td>
        <td><%= usuario.getFechainicio()%>
        </td>
        <td><a href="/clienteHome/editar?id=<%= usuario.getIdcliente()%>"> Editar datos</a></td>
    </tr>
</table>
<h1>Mis cuentas:</h1>
<table border="1">
    <tr>
        <th>NÚMERO DE CUENTA</th>
        <th>ESTADO</th>
        <th>SALDO</th>
        <th>FECHA DE APERTURA</th>
        <th>FECHA DE CIERRE</th>
    </tr>
    <%
        for (CuentaEntity c : cuentas) {
    %>
    <tr>
        <td><%=c.getNumerocuenta()%>
        </td>
        <td><%=c.getTipoestadoByIdestado().getNombre()%>
        </td>
        <td><%=c.getSaldo()%>
        </td>
        <td><%=c.getFechaapertura()%>
        </td>
        <td><%=c.getFechacierre()%>
        </td>
        <td><a href="/clienteHome/transfer?id=<%=c.getIdcuenta()%>">Hacer transferecia</a></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>