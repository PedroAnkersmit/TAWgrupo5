<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.taw.grupo5.entity.ConversacionEntity" %>
<%@ page import="com.taw.grupo5.entity.MensajeEntity" %><%--
  Created by IntelliJ IDEA.
  User: Hilaria
  Date: 26/04/2023
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  ConversacionEntity conversacion = (ConversacionEntity) request.getAttribute("conversacion");%>

<html>
<head>
    <title>Chat</title>
</head>
<body>
    <h1><%=conversacion.getAsunto()%></h1>

    <a href="/asistente/"><button>Mensajes del cliente</button></a>
    <a href="/asistente/volver?id=<%=conversacion.getEmpleadoByIdempleado().getIdempleado()%>">
        <button>Volver</button></a><br><br>
    <table border="1">
        <tr>
            <th>Usted</th>
            <th><%=conversacion.getClienteByIdcliente().getNombre()%></th>
        </tr>

        <%for(MensajeEntity mensaje : conversacion.getMensajesByIdconversacion()){%>
        <tr>
            <%if(mensaje.getEnviadoporasistente()>0){%>
                <td><%=mensaje.getContenido()%></td>
                <td></td>

            <%}else{ // enviado por cliente%>
                <td></td>
                <td><%=mensaje.getContenido()%></td>
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
        <form:button>Enviar</form:button>
    </form:form>
    <%}%>
</body>
</html>
