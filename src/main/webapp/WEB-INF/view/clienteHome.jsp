<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="com.taw.grupo5.dto.*" %>


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
    ClienteDTO usuario = (ClienteDTO) request.getAttribute("user");
    List<CuentaDTO> cuentas = (List<CuentaDTO>) request.getAttribute("accounts");
    List<TransferenciaDTO> transferencias = (List<TransferenciaDTO>) request.getAttribute("transfer");
    List<CambioDivisaDTO> cambios = (List<CambioDivisaDTO>) request.getAttribute("change");
    List<SacardineroDTO> extracciones = (List<SacardineroDTO>) request.getAttribute("extract");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Mis datos: </h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>ID_CONVERSACION</th>
        <th>NOMBRE</th>
        <th>EMAIL</th>
        <th>TELEFONO</th>
        <th>FECHA_INICIO</th>
    </tr>

    <tr>
        <td><%= usuario.getIdCliente()%>
        </td>
        <td><%= usuario.getIdConversacion()%>
        </td>
        <td><%= usuario.getNombre()%>
        </td>
        <td><%= usuario.getEmail()%>
        </td>
        <td><%= usuario.getTelefono()%>
        </td>
        <td><%= usuario.getFechainicio()%>
        </td>
        <td><a href="/clienteHome/editar?id=<%= usuario.getIdCliente()%>"> Editar datos</a></td>
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
    </tr>
    <%
        for (CuentaDTO c : cuentas) {
    %>
    <tr>
        <td><%=c.getNumerocuenta()%>
        </td>
        <td><%=c.getTipoEstado().getNombre()%>
        </td>
        <td><%=c.getSaldo()%>
        </td>
        <td><%=c.getFechaapertura()%>
        </td>
        <td><%=c.getFechacierre()%>
        </td>
        <td><a href="/clienteHome/transfer?id=<%=c.getIdcuenta()%>">Hacer transferecia</a></td>
        <td><a href="/clienteHome/cambio?id=<%=c.getIdcuenta()%>">Cambiar divisa</a></td>
        <% if (c.getTipoEstado().getIdTipoestado() == 1) {
        %>
        <td><a href="/solicitarActivacion?id=<%=c.getIdcuenta()%>">Solicitar Activacion</a></td>
        <%
        } else if (c.getTipoEstado().getIdTipoestado() == 4) {
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
    <input name="idCliente" value="<%=usuario.getIdCliente()%>" hidden>
    Tipo de Operacion:</br>
    Transferencia <form:checkbox path="transferencia"/>
    Cambio de Divisa <form:checkbox path="cambioDivisa"/>
    Extraccion <form:checkbox path="sacarDinero"/> </br>

    <form:button>Filtrar</form:button>
</form:form>

<table border="1">
    <tr>
        <th>IDENTIFICADOR</th>
        <th>FECHA DE INSTRUCCION</th>
        <th>DETALLES</th>
    </tr>
    <%
        if (!transferencias.isEmpty()) {
            for (TransferenciaDTO t : transferencias) {
    %>
    <tr>
        <td><%=t.getOperacion()%>
        </td>
        <td><%=t.getFechaInstruccion()%>
        </td>
        <td>
            <p>Transferencia:</p>
            Fecha de Ejecucion: <%=t.getFechaEjecucion()%></br>
            Movimiento: <%=t.getCantidad()%></br>
            Cuenta de Destino: <%
            for (CuentaDTO c : cuentas) {
                if (c.getIdcuenta() == t.getCuentaDestino()) {
        %> <%=c.getNumerocuenta()%> <%
                        }
                    }
            %></td>
    </tr><%
                }
            %>

    <%
        }
    %>
    <%
        if (!cambios.isEmpty()) {
            for (CambioDivisaDTO c : cambios) {
    %>
    <tr>
        <td><%=c.getOperacion().getIdOperacion()%>
        </td>
        <td>
        </td>
        <td>
        <p>Cambio de Divisa</p>
        Moneda
        Comprada: <%=c.getCantidadcompra()%> <%=c.getMonedacompra()%> </br>
        Moneda
        Vendida: <%=c.getCantidadventa()%> <%=c.getMonedaventa()%> </br>
        Comision: <%=c.getComision()%>
        </td>
    </tr>
                <%
                }
            }
        %>


    <%
        if (!extracciones.isEmpty()) {
            for (SacardineroDTO s : extracciones) {
    %>
    <tr>
        <td><%=s.getOperacion()%>
        </td>
        <td><%=s.getOperacion().getFecha()%>
        </td><td>
            <p>Extracción:</p>
            Cantidad: <%=s.getCantidad()%>
    </td>
    </tr>
            <%
                    }
                }
            %>

</table>


<h6> Accediendo con el siguiente sessionid: <%= session.getId() %>
</h6>

</body>
</html>