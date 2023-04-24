<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ignam
  Date: 24/04/2023
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<ClienteEntity> clienteEntityList = (List<ClienteEntity>) request.getAttribute("listaClientes");
%>

<html>
<head>
    <title>Listado de clientes y empresas</title>
</head>
<body>
    <h1>Listado de clientes y empresas</h1>

    <table>
        <tr>
            <th>ID</th>
            <th>Conversacion</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Telefono</th>
            <th>Fecha inicio</th>
            <th>ID Tipo Cliente</th>
            <th>ID Tipo Empresa</th>
        </tr>

        <%
            for(ClienteEntity clienteEntity : clienteEntityList) {
        %>
                <tr>
                    <td><%=clienteEntity.getIdcliente()%></td>
                    <td><%=clienteEntity.getConversacionsByIdcliente()%></td>
                    <td><%=clienteEntity.getNombre()%></td>
                    <td><%=clienteEntity.getEmail()%></td>
                    <td><%=clienteEntity.getTelefono()%></td>
                    <td><%=clienteEntity.getFechainicio()%></td>
                    <td><%=clienteEntity.getTipoclienteByIdtipocliente()%></td>
                    <td><%=clienteEntity.getEmpresaByIdempresa()%></td>
                </tr>
        <%
            }
        %>
    </table>
</body>
</html>
