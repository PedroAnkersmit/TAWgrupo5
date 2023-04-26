<%@ page import="com.taw.grupo5.entity.ClienteEntity" %>
<%@ page import="com.taw.grupo5.entity.CuentaEntity" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 24/04/2023
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  ClienteEntity cliente = (ClienteEntity) request.getAttribute("cliente");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

  <table border="1">
    <tr>
        <% for(CuentaEntity c : cliente.getCuentasByIdCliente()){ %>
          <td>
            <%= c.getNumeroCuenta() %>
            -
            <%= c.getSaldo() %>
          </td>
        <%}%>
        <a href="/cajeroEditar">Modificar datos</a>
    </tr>
  </table>

</body>
</html>
