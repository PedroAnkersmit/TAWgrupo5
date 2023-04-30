<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.EmpresaEntity" %><%--
  Created by IntelliJ IDEA.
  User: ignam
  Date: 24/04/2023
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<ClienteEntity> clienteEntityList = (List<ClienteEntity>) request.getAttribute("listaClientes");
    List<EmpresaEntity> empresaEntityList = (List<EmpresaEntity>) request.getAttribute("listaEmpresas");
%>

<html>
<head>
    <title>Listado de clientes y empresas</title>
</head>
<body>
    <h1>Listado de clientes</h1>
    <!-- Clientes -->
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
        </tr>

        <%
            for(ClienteEntity clienteEntity : clienteEntityList) {
        %>
                <tr>
                    <td><%=clienteEntity.getIdcliente()%></td>
                    <td><a href="/gestor/cliente?id=<%=clienteEntity.getIdcliente()%>"><%=clienteEntity.getNombre()%></a></td>
                </tr>
        <%
            }
        %>
    </table>

    <h1>Listado de empresas</h1>
    <!-- Empresas -->
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
        </tr>

        <%
            for(EmpresaEntity empresaEntity : empresaEntityList) {
        %>
        <tr>
            <td><%=empresaEntity.getIdempresa()%></td>
            <td><a href="/gestor/empresa?id=<%=empresaEntity.getIdempresa()%>"><%=empresaEntity.getNombre()%></a></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
