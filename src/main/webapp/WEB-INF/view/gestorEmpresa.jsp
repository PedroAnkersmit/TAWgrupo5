<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.*" %><%--
  Created by IntelliJ IDEA.
  User: ignam
  Date: 24/04/2023
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  EmpresaEntity empresaEntity = (EmpresaEntity) request.getAttribute("empresa");
  List<ClienteEntity> clienteEntityList = (List<ClienteEntity>) request.getAttribute("listadoClientes");
  List<OperacionEntity> operacionRepositoryList = (List<OperacionEntity>) request.getAttribute("listaOperaciones");
%>

<html>
<head>
    <style><%@include file="/css/styles.css"%></style>
    <title>Empresa</title>
</head>
<body>
    <button><a href="/gestor/">Volver</a></button>
    <h1>Información de la empresa: <%=empresaEntity.getNombre()%></h1>

    <h2>Datos</h2>
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

    <h2>Clientes</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>NOMBRE</th>
            <th>EMAIL</th>
            <th>TELÉFONO</th>
            <th>FECHA DE INICIO</th>
            <th>TIPO</th>
            <th>EMPRESA</th>
            <th>CONVERSACIÓN</th>
        </tr>

        <%
            for(ClienteEntity clienteEntity : clienteEntityList) {
        %>
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
                    List<ConversacionEntity> listadoConversaciones = clienteEntity.getConversacionsByIdcliente();

                    if(!listadoConversaciones.isEmpty()) {
                        for(ConversacionEntity conversacion : listadoConversaciones) {
                %>
                Conversación <%=conversacion.getIdconversacion()%> - Asunto: <%=conversacion.getAsunto()%><br/>
                <%
                    }
                } else {
                %>
                Sin conversaciones
                <%
                    }
                %>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <h2>Operaciones bancarias</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>FECHA INSTRUCCIÓN</th>
            <th>TIPO</th>
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
