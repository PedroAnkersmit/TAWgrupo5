<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.ClienteEntity" %><%--
  Created by IntelliJ IDEA.
  User: ignam
  Date: 04/05/2023
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<ClienteEntity> listadoClientesAlta = (List<ClienteEntity>) request.getAttribute("listadoClientesAlta");
%>

<html>
<head>
    <title>Gestión de clientes - Dar de alta</title>
</head>
<body>
    <h1>Listado de clientes pendientes de tener el alta</h1>

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

        <%
            for(ClienteEntity clienteEntity : listadoClientesAlta) {
        %>
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
        <%
            }
        %>
    </table>
</body>
</html>
