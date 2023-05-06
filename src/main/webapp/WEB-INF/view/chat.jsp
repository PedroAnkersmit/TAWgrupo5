<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.taw.grupo5.entity.ConversacionEntity" %>
<%@ page import="com.taw.grupo5.entity.MensajeEntity" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Timestamp" %>
<%--
  Created by IntelliJ IDEA.
  User: Hilaria
  Date: 26/04/2023
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  ConversacionEntity conversacion = (ConversacionEntity) request.getAttribute("conversacion");
    int esAsistente = (int) request.getAttribute("esAsistente");
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
%>

<html>
<head>
    <title>Chat</title>
</head>
<body>

    <h1>Asunto: <%=conversacion.getAsunto()%><%if(!(conversacion.getAbierto()>0)){%>  [Cerrado]<%}%></h1>

    <%if(esAsistente>0){     //Botones del asistente%>
    <a href="/asistente/moderar?id=<%=conversacion.getIdconversacion()%>">
        <button>Mensajes del cliente</button></a> <!-- Requisito US-27 -->
    <a href="/asistente/conversaciones?id=<%=conversacion.getEmpleadoByIdempleado().getIdempleado()%>">

    <%}else{                //Botones del cliente%>
    <a href="/asistente/misconversaciones">
    <%}%>


    <button>Volver a conversaciones</button></a><br><br>


    <table border="1">
        <tr>
        <%if(esAsistente>0){     //Headings del asistente%>
            <th>Usted</th>
            <th><%=conversacion.getClienteByIdcliente().getNombre()%></th>
            <th>Fecha y hora</th>

        <%}else{                //Headings del cliente%>
            <th><%=conversacion.getEmpleadoByIdempleado().getNombre()%></th>
            <th>Usted</th>
            <th>Fecha hora</th>
        <%}%>

        </tr>

        <%for(MensajeEntity mensaje : conversacion.getMensajesByIdconversacion()){%>
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
        <form:hidden path="enviadoporasistente" value="<%=esAsistente%>"/>
        <form:hidden path="conversacionByIdconversacion.idconversacion" value="<%=conversacion.getIdconversacion()%>"/>
        <form:hidden path="fechaenvio" value="<%=new Timestamp(System.currentTimeMillis())%>"/>

        <form:button>Enviar</form:button>
    </form:form>
    <%}%>
</body>
</html>
