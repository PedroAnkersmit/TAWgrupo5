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
    List<ClienteEntity> lista = (List<ClienteEntity>) request.getAttribute("clientes");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Listado de clientes</h1>

<form action="/clientes/filtrar" method="post">
    Buscar por: <input type="text" name="filtro">
    <button>Filtrar</button>
</form>
<table border="1">
    <tr>
        <th>ID</th>
        <th>ID_CONVERSACION</th>
        <th>NOMBRE</th>
        <th>EMAIL</th>
        <th>TELEFONO</th>
        <th>FECHA_INICIO</th>
    </tr>
    <%
        for (ClienteEntity cliente: lista){
    %>
    <tr>
        <td><%= cliente.getIdcliente()%></td>
        <td><%= cliente.getIdconversacion()%></td>
        <td><%= cliente.getNombre()%></td>
        <td><%= cliente.getEmail()%></td>
        <td><%= cliente.getTelefono()%></td>
        <td><%= cliente.getFechainicio()%></td>
        <td><a href="/clientes/editar?id=<%= cliente.getIdcliente()%>"> Editar</a></td>
        <td><a herf="/clientes/borrar?id=<%=cliente.getIdcliente()%>"> Borrar</a></td>
    </tr>
    <%
        }
    %>
</table border="1">
</body>
</html>