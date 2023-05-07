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
    <style><%@include file="/css/styles.css"%></style>
    <title>Gestor - Home</title>
</head>
<body>
    <h1>Listado de clientes y empresas</h1>
    <h2>Clientes</h2>
    <form action="/gestor/filtrarNombreCliente" method="post">
        <p>Buscar cliente:</p> <input type="text" name="filtroNombreCliente">
        <button>Filtrar</button>
    </form>

    <a href="/gestor/listadoDarVistoBuenoAlta"><button>Solicitudes de alta</button></a>
    <a href="/gestor/listadoInactivos"><button>Desactivar por inactividad</button></a>
    <a href="/gestor/listadoSospechosas"><button>Actividades sospechosas</button></a>
    <a href="/gestor/listadoDesbloqueo"><button>Solicitudes de desbloqueo</button></a>

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

    <h2>Empresas</h2>
    <form action="/gestor/filtrarNombreEmpresa" method="post">
        <p>Buscar empresa:</p> <input type="text" name="filtroNombreEmpresa">
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
