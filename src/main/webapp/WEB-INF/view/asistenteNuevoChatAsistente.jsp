<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.taw.grupo5.entity.EmpleadoEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="java.sql.Timestamp" %>
<%--
  Created by IntelliJ IDEA.
  User: Hilaria
  Date: 07/05/2023
  Time: 12:17
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
    <style><%@include file="/css/styles.css"%></style>
    <title>Chat</title>
</head>
<body>


<a href="/asistente/conversaciones">
        <button>Volver a conversaciones</button></a><br><br>

    <form:form modelAttribute="mensaje" action="/asistente/crear" method="post">
        <form:hidden path="conversacionByIdconversacion.abierto" value="1"/>
    Asunto:
        <form:input path="conversacionByIdconversacion.asunto"></form:input>


        <form:hidden path="conversacionByIdconversacion.empleadoByIdempleado" value="<%=empleado.getIdempleado()%>"/> <!-- PROVISIONAL HASTA TENER HTTPSESSION-->

    <table border="1">
        <tr>
            <th>Usted</th>
            <th>Cliente</th>

        </tr>
    </table>

        <form:hidden path="idmensaje"/>
        <form:textarea path="contenido"></form:textarea>
        <form:hidden path="enviadoporasistente" value="<%=esAsistente%>"/>
        <form:hidden path="conversacionByIdconversacion"/>
        <form:hidden path="fechaenvio" value="<%=new Timestamp(System.currentTimeMillis())%>"/>

    Enviar mensaje a:
        <form:select path="conversacionByIdconversacion.clienteByIdcliente"
                     items="${listaClientes}" itemLabel="nombre" itemValue="idcliente"></form:select><br/>

    <form:button>Enviar</form:button>
    </form:form>
</body>
</html>
