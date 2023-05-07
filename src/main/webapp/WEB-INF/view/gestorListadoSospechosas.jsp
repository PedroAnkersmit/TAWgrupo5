<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.CuentaEntity" %>
<%@ page import="com.taw.grupo5.entity.ConversacionEntity" %><%--
  Created by IntelliJ IDEA.
  User: Ignacio Martínez Gallardo
  Date: 06/05/2023
  Time: 3:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<ClienteEntity> listadoClientesSospechosos = (List<ClienteEntity>) request.getAttribute("listadoClientesSospechosos");
%>
<html>
<head>
    <style><%@include file="/css/styles.css"%></style>
    <title>Gestión de clientes - Sospechosos</title>
</head>
<body>
    <a href="/gestor/"><button>Volver</button></a>
    <h1>Registro de transferencias sospechosas</h1>
    <h2>Clientes que han realizado alguna transferencia a cuentas sospechosas</h2>

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
            <th>BLOQUEAR</th>
        </tr>

        <%
            for(ClienteEntity clienteEntity : listadoClientesSospechosos) {
               for(CuentaEntity cuenta : clienteEntity.getCuentasByIdcliente()) {
                   if(cuenta.getTipoestadoByIdestado().getIdtipoestado() != 4) {
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
                        if(cuenta.getTipoestadoByIdestado().getIdtipoestado() == 2) {
                    %>
                    <a href="/gestor/bloquearCuenta?id=<%=cuenta.getIdcuenta()%>">Cuenta nº<%=cuenta.getNumerocuenta()%></a>
                    <%
                        }
                    %>
                </td>
            </tr>
        <%          }
                }
            }
        %>
    </table>

</body>
</html>
