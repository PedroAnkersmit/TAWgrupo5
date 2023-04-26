<%@ page import="com.taw.grupo5.entity.EmpleadoEntity" %>
<%@ page import="com.taw.grupo5.entity.ConversacionEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Hilaria
  Date: 26/04/2023
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  EmpleadoEntity empleado = (EmpleadoEntity) request.getAttribute("empleado");
    List<ConversacionEntity> lista = (List<ConversacionEntity>) request.getAttribute("lista"); %>
<html>
<head>
    <title>Conversaciones</title>
</head>
<body>
    <h1>Conversaciones</h1>
    <p>Filtros:</p>

    <table border="1">
        <tr>
            <th>Asunto</th>
            <th>Nombre del cliente</th>
        </tr>
        <% for (ConversacionEntity conversacion : lista){
            if(conversacion.getEmpleadoByIdempleado().equals(empleado)){ //o get id%>
            <tr>
                <td><a href="/asistente/conversacion?id=<%=conversacion.getIdconversacion()%>">
                    <%=conversacion.getAsunto()%></a></td>
                <td><%=conversacion.getClienteByIdcliente().getNombre()%></td>
                <% if(conversacion.getAbierto()>0){%>   <td>Abrir chat</td>
                <% }else{%>                             <td>Conversación cerrada</td>  <%}%>
            </tr>
            <%}
        }%>
    </table>
</body>
</html>
