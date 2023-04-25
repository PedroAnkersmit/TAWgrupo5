<%@ page import="com.taw.grupo5.entity.ClienteEntity" %><%--
  Created by IntelliJ IDEA.
  User: jesus
  Date: 25/04/2023
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ClienteEntity cliente = (ClienteEntity) request.getAttribute("clientePortal");
%>

<html>
<head>
    <title>Portal empleado</title>
</head>
<body>
<h1>Bienvenido <%=cliente.getNombre()%></h1>
<h2>Modificar datos</h2>
<a href="./editarcliente?id=<%=cliente.getIdcliente()%>"><button>Modificar mis datos</button></a>
<a href="./editarempresa?id=<%=cliente.getEmpresaByIdempresa().getIdempresa()%>"><button>Modificar datos de mi empresa</button></a>
<h2>Listado de compañeros de empresa (si es socio, puede bloquear)</h2>
<button>Hacer transferencia</button><br/>
<button>Hacer cambio de divisas</button><br/>
<h2>Listado de operaciones de compñaeros de empresa</h2>
<button>Solicitar activación cuenta (o bloqueo)</button>
</body>
</html>
