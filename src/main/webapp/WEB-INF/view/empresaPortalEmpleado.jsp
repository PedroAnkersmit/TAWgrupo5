<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.OperacionEntity" %>
<%@ page import="com.taw.grupo5.dao.OperacionRepository" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.taw.grupo5.entity.CuentaEntity" %>
<%@ page import="com.taw.grupo5.entity.ConversacionEntity" %><%--
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
    List<OperacionEntity> listaOperaciones = (List<OperacionEntity>) request.getAttribute("listaOperaciones");
%>

<style>
    body {
        margin: 0;
        font-family: Arial, sans-serif;
        text-align: center;
    }

    h1, h2 {
        text-align: center;
        color: #333;
        font-weight: bold;
        margin-bottom: 20px;
    }

    button {
        padding: 10px 20px;
        border-radius: 5px;
        border: none;
        background-color: #007bff;
        color: #fff;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    button:hover {
        background-color: #0062cc;
    }

    table {
        border-collapse: collapse;
        margin: 20px auto;
    }

    th, td {
        padding: 10px;
        border: 1px solid #ddd;
        text-align: center;
    }

    th {
        background-color: #f5f5f5;
    }

    form {
        display: flex;
        justify-content: center;
        margin-bottom: 20px;
    }

    label, input, select {
        margin: 5px;
    }

    input, select {
        padding: 5px;
        border-radius: 5px;
        border: 1px solid #ddd;
    }

    button[type="submit"] {
        background-color: #28a745;
        color: #fff;
        border: none;
        border-radius: 5px;
        padding: 10px 20px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    button[type="submit"]:hover {
        background-color: #218838;
    }
</style>
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

<form:form action="/empresa/portal/filtrarClientes" method="post" modelAttribute="filtroClientes">
    Buscar por: <br/>
    <form:hidden path="idClienteDelPortal"/>
    <form:input type="date" path="fechaMinima"/>
    <form:input type="date" path="fechaMaxima"/>
    <form:select path="tipoCliente">
        <form:option value="" label="------" />
        <form:option value="socio" label="Socios" />
        <form:option value="autorizado" label="Autorizados" />
    </form:select>
    <button>Filtrar</button>
</form:form>

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
    List<CuentaEntity> cuentasCliente = new ArrayList<>();

    for(ClienteEntity c : listaClientesDeLaEmpresa)
    {
%>
    <tr>
        <td><%= c.getIdcliente() %></td>
        <td>
            <%
                int i;

                List<ConversacionEntity> listaConvers = c.getConversacionsByIdcliente();

                for(ConversacionEntity conver : listaConvers)
                {
            %>
            Conversación nº<%=conver.getIdconversacion()%> - Asunto: <%=conver.getAsunto()%><br/>
            <%
                }
            %>
        </td>
        <td><%= c.getNombre() %></td>
        <td><%= c.getEmail() %></td>
        <td><%= c.getTelefono() %></td>
        <td><%= c.getFechainicio() %></td>
        <td><%= c.getTipoclienteByIdtipocliente().getIdtipocliente() %></td>
        <td><%= c.getEmpresaByIdempresa().getIdempresa() %></td>
        <td>
            <%
                cuentasCliente = c.getCuentasByIdcliente();
                i = 0;
                for(CuentaEntity cuenta : cuentasCliente)
                {
                    i++;
            %>
            <a href="/empresa/bloquear?id=<%=cuenta.getIdcuenta()%>">Bloquear cuenta nº<%=i%></a><br/>
            <%
                }
            %>
        </td>
    </tr>
<%
        }
    }
%>
</table border="1">

<h2>Movimientos bancarios</h2>
<%
    List<CuentaEntity> cuentasCliente = cliente.getCuentasByIdcliente();
    int i = 0;
    for(CuentaEntity cuenta : cuentasCliente)
    {
        i++;
%>
<a href="/empresa/transferencia?id=<%=cuenta.getIdcuenta()%>"><button>Hacer transferencia con cuenta nº<%=i%></button></a>
<a href="/empresa/cambiodivisa?id=<%=cuenta.getIdcuenta()%>"><button>Hacer cambio de divisas con cuenta nº<%=i%></button></a><br/>
<%
    }
%>
<h2>Listado de operaciones de compañeros de empresa</h2>
<form:form action="/empresa/portal/filtrarOperaciones" method="post" modelAttribute="filtroOperaciones">
    Buscar por: <br/><br/>
    <form:hidden path="idClienteDelPortal"/>
    <form:input type="date" path="fechaMinima"/>
    <form:input type="date" path="fechaMaxima"/>
    <form:select path="tipoCliente">
        <form:option value="" label="------" />
        <form:option value="socio" label="Socios" />
        <form:option value="autorizado" label="Autorizados" />
    </form:select>
    <button>Filtrar</button>
</form:form>
<table border="1">
    <tr>
        <th>OPERACIÓN</th>
        <th>CUENTA</th>
        <th>FECHA</th>
        <th>CLIENTE</th>
    </tr>
    <%
        for(OperacionEntity o : listaOperaciones)
        {
    %>
    <tr>
        <td><%= o.getIdoperacion() %></td>
        <td><%= o.getCuentaByIdcuenta().getIdcuenta() %></td>
        <td><%= o.getFecha() %></td>
        <td><%= o.getIdcliente() %></td>
    </tr>
    <%
        }
    %>
</table border="1">
<h2>Reactivar/desbloquear cuenta</h2>
<%
    cuentasCliente = cliente.getCuentasByIdcliente();
    i = 0;
    for(CuentaEntity cuenta : cuentasCliente)
    {
        i++;
    if(cuenta.getTipoestadoByIdestado().getIdtipoestado() == 4)
    {
%>
<a href="/empresa/solicitarActivacion?id=<%=cuenta.getIdcuenta()%>"><button>Solicitar activación/desbloqueo cuenta nº<%=i%></button></a><br/>
<%
    } else if (cuenta.getTipoestadoByIdestado().getIdtipoestado() == 1)
    {
%>

<h2>Su cuenta nº<%=i%> esta pendiente de activación/desbloqueo</h2>

<%
    }
    }
%>

</body>
</html>