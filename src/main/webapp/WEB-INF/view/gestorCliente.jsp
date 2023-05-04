<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="com.taw.grupo5.entity.OperacionEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ignam
  Date: 24/04/2023
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ClienteEntity clienteEntity = (ClienteEntity) request.getAttribute("cliente");
    List<OperacionEntity> operacionRepositoryList = (List<OperacionEntity>) request.getAttribute("listaOperaciones");
%>
<html>
<head>
    <title>Cliente</title>
</head>
<body>
    <h1>Datos del cliente: <%=clienteEntity.getNombre()%></h1>

    <h2>Información</h2>
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
    </table>

    <h2>Operaciones bancarias</h2>
    <table border="1">
        <tr>
            <td>ID</td>
            <td>FECHA INSTRUCCIÓN</td>
            <td>TIPO</td>
        </tr>

        <%for(OperacionEntity operacionEntity : operacionRepositoryList) {%>
            <tr>
                <td><%=operacionEntity.getIdoperacion()%></td>
                <td><%=operacionEntity.getFecha()%></td>
                <td>
                    <%if (operacionEntity.getCambiodivisaByIdoperacion() == null && operacionEntity.getSacardineroByIdoperacion() == null) {%>
                        <p>Transferencia:</p>
                        Fecha de ejecución: <%=operacionEntity.getTransferenciaByIdoperacion().getFechainstruccion()%> </br>
                        Movimiento: <%=operacionEntity.getTransferenciaByIdoperacion().getCantidad()%> </br>
                    <%} else if (operacionEntity.getCambiodivisaByIdoperacion() == null && operacionEntity.getTransferenciaByIdoperacion() == null) {%>
                        <p>Extracción:</p>
                        Cantidad: <%=operacionEntity.getSacardineroByIdoperacion().getCantidad()%>
                    <%} else {%>
                        <p>Cambio de Divisa</p>
                        Moneda compra: <%=operacionEntity.getCambiodivisaByIdoperacion().getCantidadcompra()%> <%=operacionEntity.getCambiodivisaByIdoperacion().getMonedacompra()%> </br>
                        Moneda venta: <%=operacionEntity.getCambiodivisaByIdoperacion().getCantidadventa()%> <%=operacionEntity.getCambiodivisaByIdoperacion().getMonedaventa()%> </br>
                        Comisión: <%=operacionEntity.getCambiodivisaByIdoperacion().getComision()%>
                    <%}%>
                </td>
            </tr>
        <%}%>

    </table>
</body>
</html>