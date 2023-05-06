<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="com.taw.grupo5.entity.ConversacionEntity" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Hilaria
  Date: 27/04/2023
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
    List<ConversacionEntity> lista = (List<ConversacionEntity>) request.getAttribute("lista"); %>

<html>
<head>
    <title>Conversaciones</title>
</head>
<body>
  <h1><%=cliente.getNombre()%></h1>
  <h2>Tus conversaciones</h2>
  <!--<a href="/asistente/cerrarSesion"><button>Cerrar sesion</button></a>-->
  <a href="/clienteHome/"><button>Volver a tu portal</button></a><br>
  <a href="/asistente/nuevaConversacion"><button>Crear nueva conversación</button></a>

  <table border="1">
    <tr>
      <th>Asunto</th>
      <th>Asistente</th>
      <th>Conversacion</th>
    </tr>


    <% for (ConversacionEntity conversacion : lista){

      if(conversacion.getClienteByIdcliente().equals(cliente)){%>
      <tr>
        <% if(conversacion.getAbierto()>0){%>
        <td><%=conversacion.getAsunto()%></td>
        <td><%=conversacion.getEmpleadoByIdempleado().getNombre()%></td>
        <td><a href="/asistente/conversar?id=<%=conversacion.getIdconversacion()%>">Abrir</a></td>
        <td><a href="/asistente/cerrarConversacion?id=<%=conversacion.getIdconversacion()%>">Cerrar conversacion</a></td>
        <%}%>

      </tr>
    <%}

    }%>

  </table>
</body>
</html>
