<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jesus
  Date: 25/04/2023
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("clientePortal");
    List<ClienteEntity> listaClientesDeLaEmpresa = (List<ClienteEntity>) request.getAttribute("listaClientes");
%>

<html>
<head>
    <title>Portal empleado</title>
</head>
<body>
<h1>Bienvenido <%=cliente.getNombre()%></h1>
<h2>Modificar datos</h2>
<a href="./editarcliente?id=<%=cliente.getIdcliente()%>"><button>Modificar mis datos</button></a>
<a href="./editarempresa?id=<%=cliente.getEmpresaByIdempresa().getIdempresa()%>"><button>Modificar datos de mi empresa</button></a>
<%
    if(cliente.getTipoclienteByIdtipocliente().getIdtipocliente() == 2)
    {
%>
<h2>Listado de compañeros de empresa</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>CONVERSACIÓN</th>
        <th>NOMBRE</th>
        <th>EMAIL</th>
        <th>TELEFONO</th>
        <th>FECHA INICIO</th>
        <th>TIPO CLIENTE</th>
        <th>EMPRESA</th>
        <th>BLOQUEAR</th>
    </tr>
<%
  for(ClienteEntity c : listaClientesDeLaEmpresa)
  {
%>
    <tr>
        <td><%= c.getIdcliente() %></td>
        <td><%= c.getConversacionsByIdcliente() %></td>
        <td><%= c.getNombre() %></td>
        <td><%= c.getEmail() %></td>
        <td><%= c.getTelefono() %></td>
        <td><%= c.getFechainicio() %></td>
        <td><%= c.getTipoclienteByIdtipocliente().getIdtipocliente() %></td>
        <td><%= c.getEmpresaByIdempresa().getIdempresa() %></td>
        <td><a href="/empresa/bloquear?id=<%=c.getIdcliente() %>">Bloquear</a></td>
    </tr>
<%
        }
    }
%>
</table border="1">

<h2>Movimientos bancarios</h2>
<button>Hacer transferencia</button>
<button>Hacer cambio de divisas</button><br/>
<h2>Listado de operaciones de compañeros de empresa</h2>

<%
    if(cliente.getCuentasByIdcliente().get(0).getTipoestadoByIdestado().getIdtipoestado() == 4)
    {
%>
<h2>Reactivar/desbloquear cuenta</h2>
<a href="/empresa/solicitarActivacion?id=<%=cliente.getIdcliente()%>"><button>Solicitar activación/desbloqueo cuenta</button></a>
<%
    } else if (cliente.getCuentasByIdcliente().get(0).getTipoestadoByIdestado().getIdtipoestado() == 1)
    {
%>

<h2>Su cuenta esta pendiente de activación/desbloqueo</h2>

<%
    }
%>

</body>
</html>
