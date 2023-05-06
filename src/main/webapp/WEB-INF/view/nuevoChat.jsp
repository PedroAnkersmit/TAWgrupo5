<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.taw.grupo5.entity.EmpleadoEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="java.sql.Timestamp" %>
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
    List<EmpleadoEntity> listaAsistentes = (List< EmpleadoEntity>) request.getAttribute("listaAsistentes");
    EmpleadoEntity empleado = (EmpleadoEntity) request.getAttribute("empleado");
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
%>
<html>
<head>
    <title>Chat</title>
</head>
<body>


<%if(esAsistente>0){     //Botones del asistente%>
<a href="/asistente/volver?id=< %=conversacion.getEmpleadoByIdempleado().getIdempleado()%>">
        <%}else{                //Botones del cliente%>
    <a href="/asistente/misconversaciones">
        <%}%>
        <button>Volver a conversaciones</button></a><br><br>

<form:form modelAttribute="mensaje" action="/asistente/crear" method="post">
        <form:hidden path="conversacionByIdconversacion.abierto" value="1"/>
        Asunto:
        <form:input path="conversacionByIdconversacion.asunto"></form:input>


    <%if(esAsistente>0){%>
    <form:hidden path="conversacionByIdconversacion.empleadoByIdempleado" value="<%=empleado.getIdempleado()%>"/> <!-- PROVISIONAL HASTA TENER HTTPSESSION-->
    <%}else{%>
    <form:hidden path="conversacionByIdconversacion.clienteByIdcliente" value="<%=cliente.getIdcliente()%>"/> <!-- PROVISIONAL HASTA TENER HTTPSESSION-->
    <%}%>
    <table border="1">
        <tr>
            <%if(esAsistente>0){     //Headings del asistente%>
            <th>Usted</th>
            <th>Cliente</th>

            <%}else{                //Headings del cliente%>
            <th>Asistente</th>
            <th>Usted</th>
            <%}%>

        </tr>
    </table>

        <form:hidden path="idmensaje"/>
        <form:textarea path="contenido"></form:textarea>
        <form:hidden path="enviadoporasistente" value="<%=esAsistente%>"/>
        <form:hidden path="conversacionByIdconversacion"/>
        <form:hidden path="fechaenvio" value="<%=new Timestamp(System.currentTimeMillis())%>"/>

    Enviar mensaje a:
    <%if(esAsistente>0){%>
    <form:select path="conversacionByIdconversacion.clienteByIdcliente"
                                   items="${listaClientes}" itemLabel="nombre" itemValue="idcliente"></form:select><br/>
    <%}else{%>
    <form:select path="conversacionByIdconversacion.empleadoByIdempleado"
                                   items="${listaAsistentes}" itemLabel="nombre" itemValue="idempleado"></form:select><br/>
    <%}%>
    <form:button>Enviar</form:button>
 </form:form>
</body>
</html>
