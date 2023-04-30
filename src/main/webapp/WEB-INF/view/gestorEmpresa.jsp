<%@ page import="com.taw.grupo5.entity.EmpresaEntity" %>
<%@ page import="com.taw.grupo5.entity.OperacionEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ignam
  Date: 24/04/2023
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  EmpresaEntity empresaEntity = (EmpresaEntity) request.getAttribute("empresa");
  List<OperacionEntity> operacionRepositoryList = (List<OperacionEntity>) request.getAttribute("listaOperaciones");
%>

<html>
<head>
    <title>Empresa</title>
</head>
<body>
    <h1>Datos del cliente: <%=empresaEntity.getNombre()%></h1>

    <h2>Información</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Fecha de cierre</th>
        </tr>

        <tr>
            <td><%=empresaEntity.getIdempresa()%></td>
            <td><%=empresaEntity.getNombre()%></td>
            <td><%=empresaEntity.getFechacierre()%></td>
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
