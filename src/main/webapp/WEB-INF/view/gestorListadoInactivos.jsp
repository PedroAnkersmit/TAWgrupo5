<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.ConversacionEntity" %>
<%@ page import="com.taw.grupo5.entity.CuentaEntity" %><%--
  Created by IntelliJ IDEA.
  User: ignam
  Date: 06/05/2023
  Time: 1:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<ClienteEntity> listadoClientesInactivos = (List<ClienteEntity>) request.getAttribute("listadoClientesInactivos");
%>

<html>
<head>
    <style><%@include file="/css/styles.css"%></style>
    <title>Gestión de clientes - Inactivos</title>
</head>
<body>
<a href="/gestor/"><button>Volver</button></a>
    <h1>Registro de actividad</h1>
    <h2>Clientes que no han realizado ninguna operación en los últimos 30 días</h2>

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
            <th></th>
        </tr>

        <%
            for(ClienteEntity clienteEntity : listadoClientesInactivos) {
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
            <td>
                <%
                    for(CuentaEntity cuenta : clienteEntity.getCuentasByIdcliente()) {
                        if(cuenta.getTipoestadoByIdestado().getIdtipoestado() == 2) {
                %>
                        <a href="/gestor/desactivarCuenta?id=<%=cuenta.getIdcuenta()%>">Desactivar cuenta nº<%=cuenta.getNumerocuenta()%></a> <br/>
                <%
                        }
                    }
                %>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
