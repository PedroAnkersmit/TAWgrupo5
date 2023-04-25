<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>



<%--
  Created by IntelliJ IDEA.
  User: Pedro
  Date: 24/03/2023
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClienteEntity usuario = (ClienteEntity) request.getAttribute("user");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Tus datos: </h1>

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
        <td><%= usuario.getIdcliente()%></td>
        <td><%= usuario.getIdconversacion()%></td>
        <td><%= usuario.getNombre()%></td>
        <td><%= usuario.getEmail()%></td>
        <td><%= usuario.getTelefono()%></td>
        <td><%= usuario.getFechainicio()%></td>
        <td><a href="/clienteHome/editar?id=<%= usuario.getIdcliente()%>"> Editar</a></td>
        <td><a herf="/clientes/borrar?id=<%=usuario.getIdcliente()%>"> Borrar</a></td>
    </tr>

</table border="1">
</body>
</html>