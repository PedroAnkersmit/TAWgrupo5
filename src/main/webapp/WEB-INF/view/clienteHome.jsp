<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.CuentaEntity" %>
<%@ page import="com.taw.grupo5.entity.OperacionEntity" %>


<%--
  Created by IntelliJ IDEA.
  User: Pedro
  Date: 24/03/2023
  Time: 16:57
  To change this template use File | Settings | File Templates.

  Created by Pedro Ankersmit Carrión
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClienteEntity usuario = (ClienteEntity) request.getAttribute("user");
    List<CuentaEntity> cuentas = (List<CuentaEntity>) request.getAttribute("accounts");
    List<OperacionEntity> operaciones = (List<OperacionEntity>) request.getAttribute("operations");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Mis datos: </h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>ID_CONVERSACION</th>
        <th>NOMBRE</th>
        <th>EMAIL</th>
        <th>TELEFONO</th>
        <th>FECHA_INICIO</th>
    </tr>

    <tr>
        <td><%= usuario.getIdcliente()%>
        </td>
        <td><%= usuario.getIdconversacion()%>
        </td>
        <td><%= usuario.getNombre()%>
        </td>
        <td><%= usuario.getEmail()%>
        </td>
        <td><%= usuario.getTelefono()%>
        </td>
        <td><%= usuario.getFechainicio()%>
        </td>
        <td><a href="/clienteHome/editar?id=<%= usuario.getIdcliente()%>"> Editar datos</a></td>
    </tr>
</table>
<h1>Mis cuentas:</h1>
<table border="1">
    <tr>
        <th>NÚMERO DE CUENTA</th>
        <th>ESTADO</th>
        <th>SALDO</th>
        <th>FECHA DE APERTURA</th>
        <th>FECHA DE CIERRE</th>
    </tr>
    <%
        for (CuentaEntity c : cuentas) {
    %>
    <tr>
        <td><%=c.getNumerocuenta()%>
        </td>
        <td><%=c.getTipoestadoByIdestado().getNombre()%>
        </td>
        <td><%=c.getSaldo()%>
        </td>
        <td><%=c.getFechaapertura()%>
        </td>
        <td><%=c.getFechacierre()%>
        </td>
        <td><a href="/clienteHome/transfer?id=<%=c.getIdcuenta()%>">Hacer transferecia</a></td>
        <td><a href="/clienteHome/cambio?id=<%=c.getIdcuenta()%>">Cambiar divisa</a> </td>
        <% if (c.getTipoestadoByIdestado().getIdtipoestado() == 1) {
        %>
        <td><a href="/solicitarActivacion?id=<%=c.getIdcuenta()%>">Solicitar Activacion</a></td>
        <%
        } else if (c.getTipoestadoByIdestado().getIdtipoestado() == 4) {
        %>
        <td><a href="/solicitarDesbloqueo?id=<%=c.getIdcuenta()%>">Solicitar Desbloqueo</a></td>
        <%
        }%>
    </tr>
    <%
        }
    %>
</table>
<h1>Mis operaciones:</h1>
<form:form action="/clienteHome/filtrar" method="post" modelAttribute="filtro">
    Tipo de Operacion:</br>
    Transferencia <form:checkbox path="transferencia"/> Cambio de Divisa <form:checkbox path="cambioDivisa"/> Extraccion
    <form:checkbox path="sacarDinero"/> </br>
    Cantidad minima:
    <form:input path="cantidad" size="20px" maxlength="5"/></br>


    <form:button>Filtrar</form:button>
</form:form>

<table border="1">
    <tr>
        <th>IDENTIFICADOR</th>
        <th>FECHA DE INSTRUCCION</th>
        <th>TIPO</th>
    </tr>
    <%
        for (OperacionEntity o : operaciones) {
    %>
    <tr>
        <td><%=o.getIdoperacion()%>
        </td>
        <td><%=o.getFecha()%>
        </td>
        <td><%
            if (o.getCambiodivisaByIdoperacion() == null && o.getSacardineroByIdoperacion() == null) {
        %>
            <p>Transferencia:</p>
            Fecha de Ejecucion: <%=o.getTransferenciaByIdoperacion().getFechainstruccion()%></br>
            Movimiento: <%=o.getTransferenciaByIdoperacion().getCantidad()%></br>
            <%
            } else if (o.getCambiodivisaByIdoperacion() == null && o.getTransferenciaByIdoperacion() == null) {
            %>
            <p>Extracción:</p>
            Cantidad: <%=o.getSacardineroByIdoperacion().getCantidad()%>
            <%
            } else {
            %>
            <p>Cambio de Divisa</p>
            Moneda
            Comprada: <%=o.getCambiodivisaByIdoperacion().getCantidadcompra()%> <%=o.getCambiodivisaByIdoperacion().getMonedacompra()%> </br>
            Moneda
            Vendida: <%=o.getCambiodivisaByIdoperacion().getCantidadventa()%> <%=o.getCambiodivisaByIdoperacion().getMonedaventa()%> </br>
            Comision: <%=o.getCambiodivisaByIdoperacion().getComision()%>
            <%
                }
            %>
        </td>
    </tr>

    <%
        }
    %>
</table>


<h6> Accediendo con el siguiente sessionid: <%= session.getId() %>
</h6>

</body>
</html>