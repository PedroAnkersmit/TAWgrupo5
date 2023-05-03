<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.taw.grupo5.entity.ConversacionEntity" %>
<%@ page import="com.taw.grupo5.entity.MensajeEntity" %>
<%@ page import="com.taw.grupo5.entity.EmpleadoEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: diamo
  Date: 03/05/2023
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  int esAsistente = (int) request.getAttribute("esAsistente");
    List<ClienteEntity> listaClientes = (List< ClienteEntity>) request.getAttribute("listaClientes");
    List< EmpleadoEntity> listaAsistentes = (List< EmpleadoEntity>) request.getAttribute("listaAsistentes");
%>
<html>
<head>
    <title>Chat</title>
</head>
<body>


<h1>Asunto:<%%></h1>

<%if(esAsistente>0){     //Botones del asistente%>
<a href="/asistente/volver?id=<%=conversacion.getEmpleadoByIdempleado().getIdempleado()%>">
        <%}else{                //Botones del cliente%>
    <a href="/asistente/misconversaciones?id=<%=conversacion.getClienteByIdcliente().getIdcliente()%>">
        <%}%>
        <button>Volver a conversaciones</button></a><br><br>

    <form:form modelAttribute="conversacion" action="/asistente/enviar" method="post">
        <form:hidden pathvalue="1"/>
    </form:form>

    <table border="1">
        <tr>
            <%if(esAsistente>0){     //Headings del asistente%>
            <th>Usted</th>
            <th><%=conversacion.getClienteByIdcliente().getNombre()%></th>

            <%}else{                //Headings del cliente%>
            <th>Su asistente</th>
            <th>Usted</th>
            <%}%>

        </tr>

        <!--                               IMPORTANTE, LEER
        A continuación se muestran todos los mensajes del chat. En nuestra base de datos no tenemos
        un atributo Fecha en la tabla Mensaje, por lo cual MensajeEntity no tiene atributo fecha.
        Esto es un problema para el requisito US-27.

        Tras comentarlo con el grupo y por recomendación del profesor de evitar hacer cambios en la
        base de datos, hemos decidido hacer append al mensaje de la fecha.

        En el form del final se obtiene el texto del mensaje y se le envía el objeto al controlador,
        el cual añadirá la fecha (se comenta en la propia clase).

        En la tabla de la conversación, al mostrar cada mensaje se "recorta" el String contenido para
        obtener el substring del mensaje enviado por el cliente y después el substring de la fecha.

        Como la fecha debería ser un atributo aparte, he hecho también las líneas de cómo sería el
        código si tuviéramos ese atributo tanto en la tabla de la conversación como en el formulario,
        aunque están comentadas. Los < %=%> están separados por un espacio adrede para que no los reconozca.
        -->


        <tr>
            <td> </td>
            <td> </td>
            <td> </td>

            <td> </td>
            <td> </td>
            <td> </td>
        </tr>
    </table>

<form:form modelAttribute="mensaje" action="/asistente/enviar" method="post">
        <form:hidden path="idmensaje"/>
        <form:textarea path="contenido"></form:textarea>
        <form:hidden path="enviadoporasistente" value="<%=esAsistente%>"/>
        <form:hidden path="conversacionByIdconversacion.idconversacion" value="<%=conversacion.getIdconversacion()%>"/>
    <!--
    Guardar la fecha en la base de datos: (separado por espacios adrede para evitar que lo detecte.
        Se usa SimpleDateFormat para que el campo ya se guarde como un String formateado en la base de datos
        y no se tenga que formatear en la tabla de la conversación (lineas 77 y 87). También se podría
        hacer así pero resulta más complicado)
    < form :hidden path="fecha" value="< %= new SimpleDateFormat("dd/MM/yy, HH:mm").format(new Date())%>"/>
    -->
    Enviar mensaje a: <form:select path="conversacionByIdconversacion.clienteByIdcliente"
                                   items="${}"></form:select><br/>
    <form:button>Enviar</form:button>
 </form:form>
</body>
</html>
