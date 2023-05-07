<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>Gestor - Home</title>
</head>
<body>
    <h1>Listado de clientes y empresas</h1>
    <h2>Clientes</h2>
    <form action="/gestor/filtrarNombreCliente" method="post">
        Buscar cliente: <input type="text" name="filtroNombreCliente">
        <button>Filtrar</button>
    </form>

    <!-- Clientes -->
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Tipo</th>
            <th></th>
        </tr>

        <%
            for(ClienteEntity clienteEntity : clienteEntityList) {
        %>
                <tr>
                    <td><%=clienteEntity.getIdcliente()%></td>
                    <td><%=clienteEntity.getNombre()%></td>
                    <td><%=clienteEntity.getTipoclienteByIdtipocliente().getNombre()%></td>
                    <td><a href="/gestor/cliente?id=<%=clienteEntity.getIdcliente()%>">Más información</a></td>
                </tr>
        <%
            }
        %>
    </table>

    <h3>Gestión de clientes</h3>
    <button><a href="/gestor/listadoDarVistoBuenoAlta">Solicitudes de alta</a></button>
    <button><a href="/gestor/listadoInactivos">Bloqueo por inactividad</a></button>
    <button><a href="/gestor/listadoSospechosas">Actividades sospechosas</a></button>
    <button><a href="/gestor/listadoDesbloqueo">Solicitudes de desbloqueo</a></button>

    <h2>Empresas</h2>
    <form action="/gestor/filtrarNombreEmpresa" method="post">
        Buscar empresa: <input type="text" name="filtroNombreEmpresa">
        <button>Filtrar</button>
    </form>
    <!-- Empresas -->
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th></th>
        </tr>

        <%
            for(EmpresaEntity empresaEntity : empresaEntityList) {
        %>
        <tr>
            <td><%=empresaEntity.getIdempresa()%></td>
            <td><%=empresaEntity.getNombre()%></td>
            <td><a href="/gestor/empresa?id=<%=empresaEntity.getIdempresa()%>">Más información</a></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
