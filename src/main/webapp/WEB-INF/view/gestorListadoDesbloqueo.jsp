<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.ConversacionEntity" %><%--
  Created by IntelliJ IDEA.
  User: ignam
  Date: 07/05/2023
  Time: 3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ClienteEntity> listadoClientesDesbloqueo = (List<ClienteEntity>) request.getAttribute("listadoClientesDesbloqueo");
%>

<html>
<head>
    <title>Gestión de clientes - Desbloqueo</title>
</head>
<body>
<button><a href="/gestor/">Volver</a></button>
<h1>Solicitudes de desbloqueo</h1>
<h2>Clientes que han realizado una petición de desbloqueo de su cuenta</h2>

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
        for(ClienteEntity clienteEntity : listadoClientesDesbloqueo) {
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
            <a href="/gestor/desbloquearCuenta?id=<%=clienteEntity.getCuentasByIdcliente().get(0).getIdcuenta()%>">Desbloquear cuenta</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
