<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="com.taw.grupo5.entity.ConversacionEntity" %><%--
  Created by IntelliJ IDEA.
  User: ignam
  Date: 04/05/2023
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<ClienteEntity> listadoClientesAlta = (List<ClienteEntity>) request.getAttribute("listadoClientesAlta");
%>

<html>
<head>
    <style><%@include file="/css/styles.css"%></style>
    <title>Gestión de clientes - Dar visto bueno</title>
</head>
<body>
    <a href="/gestor/"><button>Volver</button></a>
    <h1>Solicitudes de alta en el sistema</h1>
    <h2>Clientes a la espera de recibir el visto bueno</h2>

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
            for(ClienteEntity clienteEntity : listadoClientesAlta) {
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
                <a href="/gestor/darVistoBuenoAlta?id=<%=clienteEntity.getCuentasByIdcliente().get(0).getIdcuenta()%>">Dar visto bueno</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
