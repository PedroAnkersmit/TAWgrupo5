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
  CuentaEntity cuenta = (CuentaEntity) request.getAttribute("cuenta");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

  <table border="1">
    <tr>
      <td>
        <%=cuenta.getSaldo()%>
        <%=cuenta.getNumeroCuenta()%>
        <a href="/cajeroEditar">Modificar datos</a>
      </td>
    </tr>
  </table>

</body>
</html>
