<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.taw.grupo5.entity.EmpleadoEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="java.sql.Timestamp" %>
<%--
  Created by IntelliJ IDEA.
  User: Hilaria
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
    String error = (String) request.getAttribute("error");
%>
<html>
<head>
    <style><%@include file="/css/formularios.css"%></style>
    <title>Chat</title>
</head>
<body>
<p>
<a href="/asistente/misconversaciones">
        <button>Volver a conversaciones</button></a><br><br>
<p style="color: red; font-weight: bold;"><%=error%></><br>

<form:form modelAttribute="mensaje" action="/asistente/crear" method="post">
        Asunto:
        <form:input path="conversacionByIdconversacion.asunto"></form:input>
        Enviar mensaje a:
        <form:select path="conversacionByIdconversacion.empleadoByIdempleado"
                     items="${listaAsistentes}" itemLabel="nombre" itemValue="idempleado"></form:select><br/>
        Mensaje:
        <form:textarea path="contenido"></form:textarea>


        <form:hidden path="conversacionByIdconversacion.abierto" value="1"/>
        <form:hidden path="conversacionByIdconversacion.clienteByIdcliente" value="<%=cliente.getIdcliente()%>"/>
        <form:hidden path="idmensaje"/>
        <form:hidden path="enviadoporasistente" value="<%=esAsistente%>"/>
        <form:hidden path="conversacionByIdconversacion"/>
        <form:hidden path="fechaenvio" value="<%=new Timestamp(System.currentTimeMillis())%>"/>


    <form:button>Enviar</form:button>
 </form:form>
</p>
</body>
</html>
