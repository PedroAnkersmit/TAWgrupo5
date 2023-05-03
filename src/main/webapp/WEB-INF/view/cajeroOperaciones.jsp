<%@ page import="com.taw.grupo5.entity.CuentaEntity" %>
<%@ page import="com.taw.grupo5.entity.OperacionEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 02/05/2023
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  CuentaEntity cuenta = (CuentaEntity) request.getAttribute("cuenta");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

  <table border="1">

    <tr>
      <th>ID</th>
      <th>FECHA</th>
    </tr>

    <% for(OperacionEntity o : cuenta.getOperacionsByIdcuenta()){ %>

      <tr>
        <td><%=o.getIdoperacion()%></td>
        <td><%=o.getFecha()%></td>
      </tr>

    <%}%>

  </table>

  <a href="/cajero/sacarDinero?idCuenta=<%=cuenta.getIdcuenta()%>">Sacar dinero</a><br/>
  <a href="/cajero/transferencia?idCuenta=<%=cuenta.getIdcuenta()%>">Transferencia</a><br/>
  <a href="/cajero/cambioDivisa?idCuenta=<%=cuenta.getIdcuenta()%>">Cambio divisa</a><br/>


</body>
</html>
