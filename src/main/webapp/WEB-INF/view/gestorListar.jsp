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
    <h1>Listado de clientes y empresas</h1>
    <h2>Clientes</h2>
    <p>Añadir filtro por nombre y por tipo</p>
    <form action="/gestor/clienteFiltrar" method="post">
        Buscar por: <input type="text" name="filtroCliente">
        <button>Filtrar</button>
    </form>
    <!-- Clientes -->
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Tipo</th>
        </tr>

        <%
            for(ClienteEntity clienteEntity : clienteEntityList) {
        %>
                <tr>
                    <td><%=clienteEntity.getIdcliente()%></td>
                    <td><a href="/gestor/cliente?id=<%=clienteEntity.getIdcliente()%>"><%=clienteEntity.getNombre()%></a></td>
                    <td><%=clienteEntity.getTipoclienteByIdtipocliente().getNombre()%></td>
                </tr>
        <%
            }
        %>
    </table>

    <h3>Gestión de clientes</h3>
    <button><a href="/gestor/listadoDarVistoBuenoAlta">Dar visto bueno al alta</a></button>
    <button><a href="/gestor/listadoInactivos">Bloquear inactivos</a></button>
    <button>Sospechosos</button>

    <h2>Empresas</h2>
    <p>Añadir filtro por nombre</p>
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
