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

<form:form action="/empresa/portal/filtrar" method="post" modelAttribute="filtro">
    Buscar por: <br/>
    <form:select multiple="true" path="" size="6" >
        <form:option value="" label="------" />
        <form:options items="${supermercados}" itemLabel="zipCode" itemValue="zipCode" />
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
                List<ConversacionEntity> listaConvers = c.getConversacionsByIdcliente();
                int i = 0;

                for(ConversacionEntity conver : listaConvers)
                {
                    i++;
            %>
            Conversación nº<%=i%> - Asunto: <%=conver.getAsunto()%><br/>
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
