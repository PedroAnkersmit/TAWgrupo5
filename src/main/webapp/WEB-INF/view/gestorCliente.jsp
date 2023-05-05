<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.*" %><%--
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
            <td>
                <%
                    List<ConversacionEntity> listaConvers = clienteEntity.getConversacionsByIdcliente();
                    int i = 0;

                    for(ConversacionEntity conver : listaConvers) {
                        i++;
                %>
                        Conversación nº<%=i%> - Asunto: <%=conver.getAsunto()%><br/>
                <%
                    }
                %>
            </td>
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
                    <%if (!operacionEntity.getTransferenciasByIdoperacion().isEmpty()) {
                        for(TransferenciaEntity t : operacionEntity.getTransferenciasByIdoperacion()) {
                    %>
                            <p>Transferencia:</p>
                            Fecha de Ejecución: <%=t.getFechainstruccion()%></br>
                            Movimiento: <%=t.getCantidad()%></br>
                    <%
                        }
                    }

                    if (!operacionEntity.getSacardinerosByIdoperacion().isEmpty()) {
                        for(SacardineroEntity s : operacionEntity.getSacardinerosByIdoperacion()) {
                    %>
                            <p>Extracción:</p>
                            Cantidad: <%=s.getCantidad()%>
                    <%
                        }
                    }

                    if(!operacionEntity.getCambiodivisasByIdoperacion().isEmpty()) {
                        for(CambiodivisaEntity c: operacionEntity.getCambiodivisasByIdoperacion()) {
                    %>
                            <p>Cambio de Divisa</p>
                            Moneda
                            Comprada: <%=c.getCantidadcompra()%> <%=c.getMonedacompra()%> </br>
                            Moneda
                            Vendida: <%=c.getCantidadventa()%> <%=c.getMonedaventa()%> </br>
                            Comisión: <%=c.getComision()%>
                    <%
                        }
                    }
                    %>
                </td>
            </tr>
        <%}%>
    </table>
</body>
</html>