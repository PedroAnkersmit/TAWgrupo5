<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.taw.grupo5.entity.ConversacionEntity" %>
<%@ page import="com.taw.grupo5.entity.MensajeEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: diamo
  Date: 06/05/2023
  Time: 8:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  ConversacionEntity conversacion = (ConversacionEntity) request.getAttribute("conversacion");
  List<MensajeEntity> conversacionFiltrada = (List<MensajeEntity>) request.getAttribute("conversacionFiltrada");
  SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
%>

<html>
<head>
  <style><%@include file="/css/styles.css"%></style>
  <title>Chat</title>
</head>
<body>

<h1>Asunto: <%=conversacion.getAsunto()%><%if(!(conversacion.getAbierto()>0)){%>  [Cerrado]<%}%></h1>

  <a href="/asistente/conversacion?id=<%=conversacion.getIdconversacion()%>">
    <button>Todos los mensajes</button></a> <!-- Requisito US-27 -->
    <a href="/asistente/conversaciones">
    <button>Volver a conversaciones</button></a><br><br>


  <table border="1">
    <tr>
      <th>Usted</th>
      <th><%=conversacion.getClienteByIdcliente().getNombre()%></th>
      <th>Fecha y hora</th>
    </tr>


    <%for(MensajeEntity mensaje : conversacionFiltrada){%>
    <tr>
      <%if(mensaje.getEnviadoporasistente()>0){%>
      <td><%=mensaje.getContenido()%></td>
      <td></td>
      <td><%=formato.format(mensaje.getFechaenvio())%></td>

      <%}else{ // enviado por cliente%>
      <td></td>
      <td><%=mensaje.getContenido()%></td>
      <td><%=formato.format(mensaje.getFechaenvio())%></td>
      <%}%>
    </tr>
    <%}%>
  </table>

</body>
</html>
