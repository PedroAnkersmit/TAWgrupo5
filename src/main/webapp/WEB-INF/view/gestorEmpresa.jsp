<%@ page import="com.taw.grupo5.entity.EmpresaEntity" %><%--
  Created by IntelliJ IDEA.
  User: ignam
  Date: 24/04/2023
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  EmpresaEntity empresaEntity = (EmpresaEntity) request.getAttribute("empresa");
%>

<html>
<head>
    <title>Empresa</title>
</head>
<body>
    <h1>Datos del cliente: <%=empresaEntity.getNombre()%></h1>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Fecha de cierre</th>
        </tr>

        <tr>
            <td><%=empresaEntity.getIdempresa()%></td>
            <td><%=empresaEntity.getNombre()%></td>
            <td><%=empresaEntity.getFechacierre()%></td>
        </tr>
    </table>
</body>
</html>
