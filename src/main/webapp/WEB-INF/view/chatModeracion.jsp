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
    //int esAsistente = (int) request.getAttribute("esAsistente");
  SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
%>

<html>
<head>
  <title>Chat</title>
</head>
<body>

<h1>Asunto: <%=conversacion.getAsunto()%><%if(!(conversacion.getAbierto()>0)){%>  [Cerrado]<%}%></h1>

  <a href="/asistente/moderar?id=<%=conversacion.getIdconversacion()%>">
    <button>Mensajes del cliente</button></a> <!-- Requisito US-27 -->
    <a href="/asistente/conversaciones?id=<%=conversacion.getEmpleadoByIdempleado().getIdempleado()%>">
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

    <%if(conversacion.getAbierto()>0){%>
  <form:form modelAttribute="mensaje" action="/asistente/enviar" method="post">
    <form:hidden path="idmensaje"/>
    <form:textarea path="contenido"></form:textarea>
    <form:hidden path="enviadoporasistente" value="1"/>
    <form:hidden path="conversacionByIdconversacion.idconversacion" value="<%=conversacion.getIdconversacion()%>"/>
    <form:hidden path="fechaenvio" value="<%=new Timestamp(System.currentTimeMillis())%>"/>

    <form:button>Enviar</form:button>
  </form:form>
    <%}%>
</body>
</html>
