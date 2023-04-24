<%@ page import="com.taw.grupo5.entity.ClienteEntity" %><%--
  Created by IntelliJ IDEA.
  User: ignam
  Date: 24/04/2023
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ClienteEntity clienteEntity = (ClienteEntity) request.getAttribute("cliente");
%>
<html>
<head>
    <title>Cliente</title>
</head>
<body>
    <h1>Datos del cliente: <%=clienteEntity.getNombre()%></h1>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Teléfono</th>
            <th>Fecha de inicio</th>
            <th>Tipo</th>
            <th>Empresa</th>
            <th>Conversación</th>
        </tr>

        <tr>
            <td><%=clienteEntity.getIdcliente()%></td>
            <td><%=clienteEntity.getNombre()%></td>
            <td><%=clienteEntity.getEmail()%></td>
            <td><%=clienteEntity.getTelefono()%></td>
            <td><%=clienteEntity.getFechainicio()%></td>
            <td><%=clienteEntity.getTipoclienteByIdtipocliente().getNombre()%></td>
            <td><%=clienteEntity.getEmpresaByIdempresa() == null ? "Sin empresa" : clienteEntity.getEmpresaByIdempresa().getNombre()%></td>
            <td><%=clienteEntity.getConversacionsByIdcliente()%></td>
        </tr>
    </table>
</body>
</html>
