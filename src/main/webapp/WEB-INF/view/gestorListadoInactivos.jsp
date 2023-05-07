<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.ConversacionEntity" %><%--
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
    <title>Gestión de clientes - Inactivos</title>
</head>
<body>
    <button><a href="/gestor/">Volver</a></button>
    <h1>Listado de clientes inactivos</h1>
    <h2>Formado por aquellos clientes que no han realizado ninguna operación en los últimos 30 días</h2>

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
            <td>
                <a href="/gestor/desactivarCuenta?id=<%=clienteEntity.getCuentasByIdcliente().get(0).getIdcuenta()%>">Desactivar cuenta</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
