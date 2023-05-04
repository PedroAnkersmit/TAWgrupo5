<%@ page import="com.taw.grupo5.entity.EmpleadoEntity" %>
<%@ page import="com.taw.grupo5.entity.ConversacionEntity" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Hilaria
  Date: 26/04/2023
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  //EmpleadoEntity empleado = (EmpleadoEntity) request.getAttribute("empleado");
    EmpleadoEntity empleado = (EmpleadoEntity) pageContext.getSession().getAttribute("usuario");
    List<ConversacionEntity> lista = (List<ConversacionEntity>) request.getAttribute("lista"); %>
<html>
<head>
    <title>Conversaciones</title>
</head>
<body>
    <h1>Conversaciones</h1>

    <a href="/asistente/cerrarSesion"><button>Cerrar sesion</button></a>
    <%if(!empleado.getTipoempleadoByIdtipoempleado().getPuesto().equalsIgnoreCase("asistente")){ // NO es gestor%>
    <p>No tienes conversaciones porque no eres asistente.</p>
    <%}else{%>
    <a href="/asistente/nuevoChat"><button>Crear nueva conversación</button></a>
    <p>Filtros:</p>

    <table border="1">
        <tr>
            <th>Asunto</th>
            <th>Nombre del cliente</th>
            <th>Conversación</th>
        </tr>
        <% for (ConversacionEntity conversacion : lista){
            if(conversacion.getEmpleadoByIdempleado().equals(empleado)){%>
            <tr>
                <td><%=conversacion.getAsunto()%></td>
                <td><%=conversacion.getClienteByIdcliente().getNombre()%></td>
                <% if(conversacion.getAbierto()>0){%>   <td>Abierta</td>
                <% }else{%>                             <td style="color:red">Cerrada</td>  <%}%>
                <td><a href="/asistente/conversacion?id=<%=conversacion.getIdconversacion()%>">Abrir</a></td>
            </tr>
            <%}
        }%>
    </table>
    <%}%>
</body>
</html>
