<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.entity.*" %>


<%--
  Created by IntelliJ IDEA.
  User: Pedro
  Date: 24/03/2023
  Time: 16:57
  To change this template use File | Settings | File Templates.

  Created by Pedro Ankersmit Carrión
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClienteEntity usuario = (ClienteEntity) request.getAttribute("user");
    List<CuentaEntity> cuentas = (List<CuentaEntity>) request.getAttribute("accounts");
    List<OperacionEntity> operaciones = (List<OperacionEntity>) request.getAttribute("operations");
%>
<html>
<head>
    <style><%@include file="/css/styles.css"%></style>
    <title>Title</title>
</head>
<body>

<h2>Ir a mis conversaciones:</h2> <a href="/asistente/misconversaciones"><button>Mis conversaciones</button></a>

<h1>Mis datos: </h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>ID_CONVERSACION</th>
        <th>NOMBRE</th>
        <th>EMAIL</th>
        <th>TELEFONO</th>
        <th>FECHA_INICIO</th>
        <th></th>
    </tr>

    <tr>
        <td><%= usuario.getIdcliente()%>
        </td>
        <td><%= usuario.getIdconversacion()%>
        </td>
        <td><%= usuario.getNombre()%>
        </td>
        <td><%= usuario.getEmail()%>
        </td>
        <td><%= usuario.getTelefono()%>
        </td>
        <td><%= usuario.getFechainicio()%>
        </td>
        <td><a href="/clienteHome/editar?id=<%= usuario.getIdcliente()%>"> Editar datos</a></td>
    </tr>
</table>
<h1>Mis cuentas:</h1>
<table border="1">
    <tr>
        <th>NÚMERO DE CUENTA</th>
        <th>ESTADO</th>
        <th>SALDO</th>
        <th>FECHA DE APERTURA</th>
        <th>FECHA DE CIERRE</th>
        <th></th>
        <th></th>
    </tr>
    <%
        for (CuentaEntity c : cuentas) {
    %>
    <tr>
        <td><%=c.getNumerocuenta()%>
        </td>
        <td><%=c.getTipoestadoByIdestado().getNombre()%>
        </td>
        <td><%=c.getSaldo()%>
        </td>
        <td><%=c.getFechaapertura()%>
        </td>
        <td><%=c.getFechacierre()%>
        </td>
        <td><a href="/clienteHome/transfer?id=<%=c.getIdcuenta()%>">Hacer transferecia</a></td>
        <td><a href="/clienteHome/cambio?id=<%=c.getIdcuenta()%>">Cambiar divisa</a> </td>
        <% if (c.getTipoestadoByIdestado().getIdtipoestado() == 1) {
        %>
        <td><a href="/solicitarActivacion?id=<%=c.getIdcuenta()%>">Solicitar Activacion</a></td>
        <%
        } else if (c.getTipoestadoByIdestado().getIdtipoestado() == 4) {
        %>
        <td><a href="/solicitarDesbloqueo?id=<%=c.getIdcuenta()%>">Solicitar Desbloqueo</a></td>
        <%
        }%>
    </tr>
    <%
        }
    %>
</table>
<h1>Mis operaciones:</h1>
<form:form action="/clienteHome/filtrar" method="post" modelAttribute="filtro">
    <p>
    <input name="idCliente" value="<%=usuario.getIdcliente()%>" hidden>
    Tipo de Operacion:</br>
    <form:checkbox path="transferencia"/> Transferencia
        <form:checkbox path="cambioDivisa"/> Cambio de Divisa
    <form:checkbox path="sacarDinero"/> Extraccion</br>

    <form:button>Filtrar</form:button>
    </p>
</form:form>

<table border="1">
    <tr>
        <th>IDENTIFICADOR</th>
        <th>FECHA DE INSTRUCCION</th>
        <th>TIPO</th>
    </tr>
    <%
        for (OperacionEntity o : operaciones) {
    %>
    <tr>
        <td><%=o.getIdoperacion()%>
        </td>
        <td><%=o.getFecha()%>
        </td>
        <td><%
            if (!o.getTransferenciasByIdoperacion().isEmpty()) {
                for(TransferenciaEntity t : o.getTransferenciasByIdoperacion()){
        %>
            <p>Transferencia:</p>
            Fecha de Ejecucion: <%=t.getFechainstruccion()%></br>
            Movimiento: <%=t.getCantidad()%></br>
            Cuenta de Destino: <%
                for(CuentaEntity c : cuentas){
                    if(c.getIdcuenta() == t.getIdTransferencia()){
                        %><%=c.getNumerocuenta()%> <%

                    }
                }
            %>
            <%
                }
            }
            if (!o.getSacardinerosByIdoperacion().isEmpty()) {
                for(SacardineroEntity s : o.getSacardinerosByIdoperacion()){
            %>
            <p>Extracción:</p>
            Cantidad: <%=s.getCantidad()%>
            <%
                }
            }
            if(!o.getCambiodivisasByIdoperacion().isEmpty()){
                for(CambiodivisaEntity c: o.getCambiodivisasByIdoperacion()){
            %>
            <p>Cambio de Divisa</p>
            Moneda
            Comprada: <%=c.getCantidadcompra()%> <%=c.getMonedacompra()%> </br>
            Moneda
            Vendida: <%=c.getCantidadventa()%> <%=c.getMonedaventa()%> </br>
            Comision: <%=c.getComision()%>
            <%
                }
            }
            %>
        </td>
    </tr>

    <%
        }
    %>
</table>


<h6> Accediendo con el siguiente sessionid: <%= session.getId() %>
</h6>
<a href="/clienteHome/cerrarSesion"><button>Cerrar sesion</button></a>
</body>
</html>